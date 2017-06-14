document.addEventListener("DOMContentLoaded", checkBoxListener);
document.addEventListener("DOMContentLoaded", centerButtonListener);

var arechecked = false;

function centerButtonListener() {
	 var cb = document.getElementById("center_button");
	 cb.addEventListener("click", toggleAllBoxes);
}

function toggleAllBoxes() {
	var checklist = document.getElementsByClassName("check");

	var numchecked = 0;
	var numnot = 0;

	var i = checklist.length;
	while(i--){
		(checklist[i].checked)? numchecked++ : numnot++;
	}

	arechecked = !(numchecked >= numnot);

	for(var i = 0; i < checklist.length; i++) {
		checklist[i].checked = arechecked;
		manualrotateChildren(checklist[i]);
	}
}


function checkBoxListener(){
	var checks = document.getElementsByClassName("check");
	for(var i = 0; i < checks.length; i++){
		checks[i].addEventListener("change", rotateChildren);
	}
}

function rotateChildren(e){

	var parent = this.parentNode;
	var location = (this.classList.contains("inner"))? "inner": "outer";
	var list = null;

	for(var i = 0; i < parent.children.length; i++){
		var child = parent.children[i];
		if(child.tagName === "UL" && child.classList.contains(location)){
			list = child;
		}
	}

	if(!list) return;

	var degree = this.checked? 90/(list.children.length+1): 0;

	if(list.classList.contains("right")) degree = degree*-1;
	
	var step = degree;

	if(list.classList.contains("bottom")){
		degree = degree*list.children.length*-1;
	}

	for(var i = list.children.length-1; i >= 0; i--){
		var child = list.children[i];
		child.style.transform = "rotate("+degree+"deg)";
		degree += step;
	}

}

function manualrotateChildren(checkedItem){

	var parent = checkedItem.parentNode;
	var location = (checkedItem.classList.contains("inner"))? "inner": "outer";
	var list = null;

	for(var i = 0; i < parent.children.length; i++){
		var child = parent.children[i];
		if(child.tagName === "UL" && child.classList.contains(location)){
			list = child;
		}
	}

	if(!list) return;

	var degree = checkedItem.checked? 90/(list.children.length+1): 0;

	if(list.classList.contains("right")) degree = degree*-1;
	
	var step = degree;

	if(list.classList.contains("bottom")){
		degree = degree*list.children.length*-1;
	}

	for(var i = list.children.length-1; i >= 0; i--){
		var child = list.children[i];
		child.style.transform = "rotate("+degree+"deg)";
		degree += step;
	}

}