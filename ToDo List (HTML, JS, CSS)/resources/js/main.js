
var data = (localStorage.getItem('todoList')) ? JSON.parse(localStorage.getItem('todoList')): 
{
	todo: [],
	completing: [],
	completed: []
};

var removeSVG = '<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 22 22" style="enable-background:new 0 0 22 22;" xml:space="preserve"><rect class="noFill" width="22" height="22"/><g><g><path class="fill" d="M16.1,3.6h-1.9V3.3c0-1.3-1-2.3-2.3-2.3h-1.7C8.9,1,7.8,2,7.8,3.3v0.2H5.9c-1.3,0-2.3,1-2.3,2.3v1.3c0,0.5,0.4,0.9,0.9,1v10.5c0,1.3,1,2.3,2.3,2.3h8.5c1.3,0,2.3-1,2.3-2.3V8.2c0.5-0.1,0.9-0.5,0.9-1V5.9C18.4,4.6,17.4,3.6,16.1,3.6z M9.1,3.3c0-0.6,0.5-1.1,1.1-1.1h1.7c0.6,0,1.1,0.5,1.1,1.1v0.2H9.1V3.3z M16.3,18.7c0,0.6-0.5,1.1-1.1,1.1H6.7c-0.6,0-1.1-0.5-1.1-1.1V8.2h10.6V18.7z M17.2,7H4.8V5.9c0-0.6,0.5-1.1,1.1-1.1h10.2c0.6,0,1.1,0.5,1.1,1.1V7z"/></g><g><g><path class="fill" d="M11,18c-0.4,0-0.6-0.3-0.6-0.6v-6.8c0-0.4,0.3-0.6,0.6-0.6s0.6,0.3,0.6,0.6v6.8C11.6,17.7,11.4,18,11,18z"/></g><g><path class="fill" d="M8,18c-0.4,0-0.6-0.3-0.6-0.6v-6.8c0-0.4,0.3-0.6,0.6-0.6c0.4,0,0.6,0.3,0.6,0.6v6.8C8.7,17.7,8.4,18,8,18z"/></g><g><path class="fill" d="M14,18c-0.4,0-0.6-0.3-0.6-0.6v-6.8c0-0.4,0.3-0.6,0.6-0.6c0.4,0,0.6,0.3,0.6,0.6v6.8C14.6,17.7,14.3,18,14,18z"/></g></g></g></svg>';
var completeSVG = '<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 22 22" style="enable-background:new 0 0 22 22;" xml:space="preserve"><rect y="0" class="noFill" width="22" height="22"/><g><path class="fill" d="M9.7,14.4L9.7,14.4c-0.2,0-0.4-0.1-0.5-0.2l-2.7-2.7c-0.3-0.3-0.3-0.8,0-1.1s0.8-0.3,1.1,0l2.1,2.1l4.8-4.8c0.3-0.3,0.8-0.3,1.1,0s0.3,0.8,0,1.1l-5.3,5.3C10.1,14.3,9.9,14.4,9.7,14.4z"/></g></svg>';
var thirty_seconds = 30 * 1000;
var five_seconds = 5 * 1000;
var itemIntervalID;

renderToDoList();
dayPercent();
setInterval(dayPercent, thirty_seconds);

document.getElementById('add').addEventListener('click', submitFunction);

document.getElementById('item').addEventListener('keypress', function(e) {
	if(e.keyCode == 13){
		submitFunction();
	}
});

function submitFunction(){
	var value = document.getElementById('item').value;
	var isdaily = document.getElementById('dailyCheck').checked;
	var timegoalpick = document.getElementById('timegoal').value;
	if(value) {
		var item = {
			text: value,
			daily: isdaily,
			timegoal: (timegoalpick > 0)? timegoalpick : false
		};
		data.todo.push(item);
		addItemTodo(item);
		document.getElementById('item').value = '';
		document.getElementById('dailyCheck').checked = false;
	};
	dataObjUpdated();
}

function renderToDoList() {
	if( !data.todo.length && !data.completing.length) return;

	for (var i in data.todo) {
		addItemTodo(data.todo[i], false);
	}

	for(var i in data.completing){
		addItemTodo(data.completing[i], true);
	}

	var today = new Date().setHours(0,0,0,0);
	var tmpcomp = [];

	for(var i in data.completed){
		if(data.completed[i].daydone != today){
			var item = data.completed[i];
			data.todo.push(item);
			addItemTodo(item, false);
		} else {
			tmpcomp.push(data.completed[i]);
		}
	}
	data.completed = tmpcomp;

	
}

function dataObjUpdated() {
	localStorage.setItem('todoList', JSON.stringify(data));
}

function addItemTodo(todo, completing) {
	var list = (completing) ? document.getElementById('completing'): document.getElementById('incomplete');

	var item = document.createElement('li');
	item.innerText = todo.text;

	var dayLabel = document.createElement('label');
	dayLabel.id = "daylabel";
	dayLabel.innerText = (todo.daily)? "Daily" : '';

	var completionDiv = document.createElement('div');
	completionDiv.id = "loadbar";
	var timetotal = todo.timegoal*60*60; //convert hours to seconds
	var tratio = (todo.timespent/timetotal)*100;
	completionDiv.style["width"] = tratio.toFixed(2)+"%";

	var buttons = document.createElement('div');
	buttons.classList.add('buttons');

	var remove = document.createElement('button');
	remove.classList.add('remove');
	remove.innerHTML = removeSVG;

	remove.addEventListener('click', removeItem);

	var complete = document.createElement('button');
	complete.classList.add('complete');
	complete.innerHTML = completeSVG;

	complete.addEventListener('click', completeItem);

	buttons.appendChild(remove);
	buttons.appendChild(complete);
	item.appendChild(completionDiv);
	item.appendChild(dayLabel);
	item.appendChild(buttons);


	list.appendChild(item);
}

function removeItem(e) {
	var item = this.parentNode.parentNode;
	var parent = item.parentNode;  
	var id = parent.id;
	var value = item.innerText;
	
	
	if(id == 'incomplete'){
		var ind = data.todo.findIndex(x => x.text === value);
		data.todo.splice(ind, 1);
	} else {
		data.completing.pop();
	}
		


	parent.removeChild(item);
	dataObjUpdated();
}

function completeItem(e) {
	var item = this.parentNode.parentNode;
	var parent = item.parentNode;
	var id = parent.id;
	var value = item.childNodes[0].nodeValue;
	var dataset = (id == 'completing')? data.completing : data.todo;
	var ind = dataset.findIndex(x => x.text === value);
	var todoObj = dataset.splice(ind, 1)[0];

	if(id == 'completing' || todoObj.timegoal){
		swapItem(item, todoObj);
	} else if( todoObj.daily ){
		delayItem(item, todoObj);
	} else {
		parent.removeChild(item);
	}

	dataObjUpdated();
}

function swapItem(item, todoObj) {
	var parent = item.parentNode;
	var id = parent.id;
	var newList;
	var dataset;

	if(id== 'incomplete') {
		//if item is being moved from todo list to completing list
		dataset = data.completing;
		newList = document.getElementById('completing');
		itemIntervalID = setInterval(itemPercent, five_seconds);
		
		if(!todoObj.timespent) {
			//set target item time spent to 0 if it hasn't been worked on.
			todoObj.timespent = 0;
		}	
		
		if(newList.hasChildNodes()){
			//if an item is being completed already, move it back
			var switchedout = document.getElementById('completing').childNodes[0];
			var swobj = data.completing.pop();
			data.todo.push(swobj);
			var otherList = document.getElementById('incomplete');
			otherList.appendChild(switchedout);
		}
	} else {
		//if item is being moved from completing list to todo list
		newList = document.getElementById('incomplete');
		dataset = data.todo;
		clearInterval(itemIntervalID);
	}

	//move the chosen item to the correct list
	dataset.push(todoObj);
	newList.appendChild(item);

}

function delayItem(item, todoObj) {
	todoObj.daydone = new Date().setHours(0,0,0,0);
	data.completed.push(todoObj);
	var parent = item.parentNode;
	parent.removeChild(item);
}

function dayPercent() {
	var time = new Date();
	var h = time.getHours();
	var m = time.getMinutes();

	var hrdiff = 15-((h >= 9)? (h - 9) : ((h == 0)? 15: 0));
	var mindiff = 60 - m;

	var totalTime = 16*60; //minutes in 'work'day

	var ratio = (((hrdiff * 60)+ mindiff)/totalTime)*100;

	document.getElementById('daypercent').innerText = ratio.toFixed(2)+"%";
}

function itemPercent() {
	if(!document.getElementById('completing').hasChildNodes()) return;
	var completing = document.getElementById('completing').childNodes[0];
	var item = data.completing[0];

	item.timespent +=5; //increment time spent
	var timetotal = item.timegoal*60*60; //convert hours to seconds
	var tratio = (item.timespent/timetotal)*100;

	if(tratio >= 100) {
		item.timespent = 0;
		data.completing.pop();
		delayItem(completing, item);
	}


	document.getElementById("loadbar").style["width"] = tratio.toFixed(2)+"%";
	dataObjUpdated();
}

