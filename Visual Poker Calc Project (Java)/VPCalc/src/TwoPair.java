

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Arrays;

/**
 *
 * @author Nate
 */
public class TwoPair extends Payout{
    
    public TwoPair(){
        payrate = 2;
    }
    
    public TwoPair(int p){
        payrate = p;
    }
    
    @Override
    public double isPaid(Hand hand){
        int s = hand.getSize();
        Card[] cards = hand.getTemps();
        double retval = 0;
        for(int i=0; i< s; i++){
            for (int j=i+1; j< s; j++){
                if(cards[i].getValue() == cards[j].getValue()){
                    Card[] temp = new Card[hand.getSize()-2];
                    int k = 0;
                    for(Card c: cards){
                        if(!c.compareto(cards[i]) && !c.compareto(cards[j])){
                            temp[k] = c;
                            k++;
                        }
                    }
                    return secPair(temp);
                }
            }
            
        }
        return 0;
    }
    
    private double secPair(Card[] cards){
        int s = cards.length;
        double retval = 0;
        for(int i=0; i< s; i++){
                for (int j=i+1; j< s; j++){
                    if(cards[i].getValue() == cards[j].getValue()){
                        retval = payrate;
                    }
                }
        }
        return retval;
    }
}
