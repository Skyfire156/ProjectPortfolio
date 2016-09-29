

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Nate
 */
public class Flush extends Payout {
    
    public Flush(){
        payrate = 5;
    }
    
    public Flush(int i){
        payrate = i;
    }
    
    @Override
    public double isPaid(Hand hand){
        double retval = payrate;
        Card[] cards = hand.getTemps();
        for(int i = 0; i<hand.getSize()-1; i++){
            if(cards[i].getSuit() != cards[i+1].getSuit()){
                retval = 0;
                break;
            }
        }
        return retval;
    }
    
}
