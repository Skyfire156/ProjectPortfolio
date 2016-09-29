import java.util.Random;


public class Host {
	private Box boxA;
	private Box boxB;
	private Box boxC;
	private Player player;
	private Random rand;
	private int numplays;
	private int numplayed;
	private int numwins;
	
	public Host(){
		rand = new Random();
	}
	
	public Host(Player a){
		player = a;
		rand = new Random();
	}
	
	private void openBox(){
		boolean working = true;
		Box current;
		while(working){
			int i = rand.nextInt(3);
			if(i == 0){
				current = boxA;
			}else if(i == 1){
				current = boxB;
			}else{
				current = boxC;
			}
			if(!current.isSelected() && current.isEmpty()){
				current.setOpen(true);
				working = false;
			}
			
		}
	}
	
	private void getUserChoice(){
		int i = player.initialSelection();
		if(i == 1){
			boxA.setSelected(true);
		}else if(i == 2){
			boxB.setSelected(true);
		}else{
			boxC.setSelected(true);
		}
	}
	
	private void updateStrategy(){
		boolean Switch = player.getStrategy();
		if(Switch){
			if(!boxA.isSelected()&& !boxA.isOpen() ){
				boxA.setSelected(true);
				boxB.setSelected(false);
				boxC.setSelected(false);
			}else if(!boxB.isSelected() && !boxB.isOpen()){
				boxA.setSelected(false);
				boxB.setSelected(true);
				boxC.setSelected(false);
			}else if(!boxC.isSelected() && !boxC.isOpen()){
				boxA.setSelected(false);
				boxB.setSelected(false);
				boxC.setSelected(true);
				}
		}
	}
	
	private void placePrize(){
		int i = rand.nextInt(3);
		if(i == 0){
			boxA.setPrize(true);
		}else if(i == 1){
			boxB.setPrize(true);
		}else{
			boxC.setPrize(true);
		}
	}
	
	private void updateWins(){
		
	}
	
	private void playAgain(){
		boxA = new Box();
		boxB = new Box();
		boxC = new Box();
		playGame(numplays-1);
	}
	
	public int getOutcome(){
		return numwins;
	}
	
	public void playGame(int num){
		numplays = num;
		placePrize();
		getUserChoice();
		openBox();
		updateStrategy();
		updateWins();
		if(numplays >=1)
			playAgain();
		
	}

}
