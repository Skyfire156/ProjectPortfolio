
public class Box {
	private boolean empty;
	private boolean selected;
	private boolean open;
	
	public Box(){
		
	}
	
	public boolean isEmpty(){
		return empty;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public boolean isOpen(){
		return open;
	}
	
	public void setPrize(boolean prize){
		empty = !prize;
	}
	
	public void setSelected (boolean s){
		selected = s;
	}
	
	public void setOpen (boolean o){
		open = o;
	}

}
