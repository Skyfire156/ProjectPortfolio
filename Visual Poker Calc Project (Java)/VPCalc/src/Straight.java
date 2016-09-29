

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Nate
 */
public class Straight extends Payout{
    
    
    public Straight(){
       payrate = 4;
    }
    
    public Straight(int i){
        payrate = i;
    }
    
    public double isPaid(Hand hand){
        double retval = payrate;
        
        Card[] cards = hand.getTemps();
        cards = sort(cards);
        for(int i = 0; i< cards.length-1; i++){
            if(cards[i].getValue().Rank()+1 != cards[i+1].getValue().Rank()){
                retval = 0;
                break;
            }
        }
        return retval;
    }
    
    private Card[] sort(Card[] cards){
        for(int i = 1; i<cards.length; i++){
            for(int k = i; k > 0; k--){
                if(cards[k].getValue().Rank() < cards[k-1].getValue().Rank()){
                    Card temp = cards[k];
                    cards[k] = cards[k-1];
                    cards[k-1] = temp;
                }
            }
        }
        
        return cards;
    }
}
