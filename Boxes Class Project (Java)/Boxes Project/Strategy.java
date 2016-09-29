import java.util.Random;


public class Strategy {
	String strat;
	Random rand;
	
	public Strategy(){
		
	}
	
	public Strategy(String s){
		strat = s;
	}
	
	public void setStrategy(String s){
		strat = s;
	}
	
	public String getStrategy(){
		return strat;
	}
	
	public boolean getChoice(){
		if(strat =="Switch"){
			return true;
		}else if(strat =="Keep"){
			return false;
		}else{
			rand = new Random();
			return rand.nextBoolean();
		}
	}

}
