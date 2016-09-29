

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Nate
 */
public class FourKind extends Payout{
    
    public FourKind(){
        payrate = 25;
    }
    
    public FourKind(int i){
        payrate = i;
    }
    
    
    @Override
    public double isPaid(Hand hand){
        int s = hand.getSize();
        Card[] cards = hand.getTemps();
        for(int i=0; i< s; i++){
            for (int j=i+1; j< s; j++){
                for (int k=j+1; k< s; k++){
                    for (int m=k+1; m< s; m++){
                        if(cards[i].getValue() == cards[j].getValue() 
                                && cards[j].getValue() == cards[k].getValue() 
                                && cards[k].getValue() == cards[m].getValue()){
                            return payrate;
                        }
                    }
                }
            }
        }
        return 0;
    }
    
}
