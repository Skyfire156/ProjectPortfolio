

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Nate
 */
public class Card {
    private Value value;
    private Suit suit;
    
    public Card(){
        value = Value.Ace;
        suit = Suit.Hearts;
    }
    
    public Card(Suit s, Value v){
        value = v;
        suit = s;
    }
    
    public String getStats(){
        return value+" of "+suit;
    }
    
    public Suit getSuit(){
        return suit;
    }
    
    public Value getValue(){
        return value;
    }
    
    public boolean compareto(Card c){
        if(c != null){
            boolean val1 = value == c.getValue();
            boolean val2 =  suit == c.getSuit();
            return val1 && val2;
        }else{
            return false;
        }
        
    }
}
