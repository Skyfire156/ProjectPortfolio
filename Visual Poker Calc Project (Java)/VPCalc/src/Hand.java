

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
public class Hand {
    private int size;
    private Card[] initialcards;
    private Card[] finalcards;
    
    public Hand(){
        size = 5;
        initialcards = new Card[size];
    }
    
    public Hand(int s){
        size = s;
        initialcards = new Card[size];
        finalcards = new Card[size];
    }
    
    public Hand(Card[] cards){
        initialcards = cards;
        finalcards = cards;
    }
    
    public void keepCards(boolean[] keeps){
        
    }
    
    public void addCard(Card c, int pos){
        if(this.contains(c))
            System.out.println("Adding Duplicate "+c.getStats());
        initialcards[pos] = c;
        finalcards[pos] = c;
    }
    
    public void addCard(Card c){
        if(this.contains(c))
            System.out.println("Adding Duplicate"+c.getStats());
        for(int i = 0; i<initialcards.length; i++){
            if(initialcards[i] == null){
                initialcards[i] = c;
                finalcards[i] = c;
                break;
            }
        }
    }
    
    public void addTemp(Card c, int pos){
        for(Card k: finalcards){
            if(c.compareto(k)){
            //System.out.println("Adding Duplicate "+c.getStats());
            }
        }
        finalcards[pos] = c;
    }
    
    public void resetTemps(){
        System.arraycopy(initialcards, 0, finalcards, 0, size);
    }
    
    public String handStats(){
        String retval = "";
        for(Card c: initialcards){
            retval = retval+" "+c.getStats();
        }
        return retval;
    }
    
    public String tempStats(){
        String retval = "";
        for(Card c: finalcards){
            retval = retval+" "+c.getStats();
        }
        return retval;
    }
    
    public boolean contains(Card c){
        boolean retval = false;
        for(Card k: initialcards){
            if(c.compareto(k))
                retval = true;
        }
        return retval;
    }
    
    public int getSize(){
        return size;
    }
    
    public Card[] getCards(){
        Card[] returns = new Card[size];
        System.arraycopy(initialcards, 0, returns, 0, size);
        return returns;
    }
    
    public Card[] getTemps(){
        Card[] returns = new Card[size];
        System.arraycopy(finalcards, 0, returns, 0, size);
        return returns;
    }
}
