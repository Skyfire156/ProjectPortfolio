

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Nate
 */
public class Pair extends Payout {
    
    public Pair(){
        payrate = 1;
    }
    
    public Pair(int pr){
        payrate = pr;
    }
    
    @Override
    public double isPaid(Hand hand){
        int s = hand.getSize();
        Card[] cards = hand.getTemps();
        for(int i=0; i< s; i++){
            if(cards[i].getValue().Rank()>= Value.Jack.Rank()){
                for (int j=i+1; j< s; j++){
                    if(cards[i].getValue() == cards[j].getValue()){
                        return payrate;
                    }
                }
            }
        }
        return 0;
    }
    
}
