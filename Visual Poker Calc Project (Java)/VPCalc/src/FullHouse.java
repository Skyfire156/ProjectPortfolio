

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
public class FullHouse extends Payout{
    
    public FullHouse(){
        payrate = 8;
    }
    
    public FullHouse(int i){
        payrate = i;
    }
    
    public double isPaid(Hand hand){
        double retval = 0;
        int s = hand.getSize();
        Card[] cards = hand.getTemps();
        for(int i=0; i< s; i++){
            for (int j=i+1; j< s; j++){
                if(cards[i].getValue() == cards[j].getValue()){
                    Card[] temp = new Card[hand.getSize()-2];
                    int k =0;
                    for(Card c: cards){
                        if(c != cards[i] && c != cards[j]){
                            temp[k] = c;
                            k++;
                        }
                    }
                    if(hasThree(temp))
                        retval = payrate;
                }
            }
            
        }
        return retval;
    }
    
    private boolean hasThree(Card[] cards){
        boolean retval = false;
        for(int i=0; i< cards.length; i++){
            for (int j=i+1; j< cards.length; j++){
                for (int k=j+1; k< cards.length; k++){
                    if(cards[i].getValue() == cards[j].getValue() && cards[j].getValue() == cards[k].getValue()){
                        retval = true;
                    }
                }
            }
        }
        return retval;
    }
    
    
    
}
