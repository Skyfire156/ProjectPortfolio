package boxessim;

import java.util.Random;


public class Host {
        private Box[] boxes;
	private Player player;
	private Random rand;
	
	public Host(){
		rand = new Random();
                boxes = new Box[3];
                boxes[0] = new Box();
                boxes[1] = new Box();
                boxes[2] = new Box();
	}
	
	public Host(Player a){
		player = a;
		rand = new Random();
                boxes = new Box[3];
                boxes[0] = new Box();
                boxes[1] = new Box();
                boxes[2] = new Box();
	}
	
	private void openBox(){
		boolean working = true;
		Box current;
		while(working){
			int i = rand.nextInt(3);
                        current = boxes[i];
			if(!current.isSelected() && !current.hasPrize()){
				current.setOpen(true);
				working = false;
			}
			
		}
	}
	
	private void getUserChoice(){
		int i = player.initialSelection();
		boxes[i].setSelected(true);
	}
	
	private void updateStrategy(){
		boolean Switch = player.getStrategy();
		if(Switch){
			for(int i = 0; i<3; i++){
                            if(boxes[i].isSelected()){
                                boxes[i].setSelected(false);
                            }
                            else if(!boxes[i].isSelected()&& !boxes[i].isOpen()){
                                boxes[i].setSelected(true);
                            }
                        }
		}
	}
	
	private void placePrize(){
		int i = rand.nextInt(3);
		boxes[i].setPrize(true);
	}
	
	public boolean playGame(){
		placePrize();
		getUserChoice();
		openBox();
		updateStrategy();
		Box current = boxes[0];
                for(int i = 0; i<3; i++){
                    if(boxes[i].isSelected())
                        current = boxes[i];
                }
                return current.hasPrize();
	}

}
