import java.util.Random;


public class Player {
	private Strategy strategy;
	private Random rand;
	
	public Player(){
		
	}
	
	public Player(Strategy strat){
		strategy = strat;
	}
	
	public int initialSelection(){
		rand = new Random();
		return rand.nextInt(3)+1;
	}
	
	public boolean getStrategy(){
		return strategy.getChoice();
	}

}
