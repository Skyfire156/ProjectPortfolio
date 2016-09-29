

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
public class StraightFlush extends Payout {
    
    public StraightFlush(){
        payrate = 50;
    }
    
    public StraightFlush(int i){
        payrate = i;
    }
    
    public double isPaid(Hand hand){
        Straight str = new Straight();
        Flush flu = new Flush();
        double retval = 0;
        if(str.isPaid(hand) != 0 && flu.isPaid(hand) != 0)
            retval = payrate;
        return retval;
    }
}
