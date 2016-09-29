

/*
 * This is Nathan Hillman's Licence Header.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author natha_000
 */
public class RoyalFlush extends Payout {
    
    public RoyalFlush(){
        payrate = 250;
    }
    
    public RoyalFlush(int i){
        payrate = i;
    }
    
    public double isPaid(Hand hand){
        Straight str = new Straight();
        Flush flu = new Flush();
        double retval = 0;
        if(jackLow(hand)){
            if(str.isPaid(hand) != 0 && flu.isPaid(hand) != 0)
                retval = payrate;
        }
        return retval;
    }
    
    private boolean jackLow(Hand hand){
        boolean retval = true;
        Card[] cards = hand.getTemps();
        for(Card c: cards){
            if(c.getValue().Rank() < 10)
                retval = false;
        }
        return retval;
    }
}
