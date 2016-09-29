package boxessim;


public class Box {
	private boolean prize;
	private boolean selected;
	private boolean open;
	
	public Box(){
		
	}
	
	public boolean hasPrize(){
		return prize;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public boolean isOpen(){
		return open;
	}
	
	public void setPrize(boolean p){
		prize = p;
	}
	
	public void setSelected (boolean s){
		selected = s;
	}
	
	public void setOpen (boolean o){
		open = o;
	}

}
