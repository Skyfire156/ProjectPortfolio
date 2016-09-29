

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
public class Deck {
    int size;
    Node top;
    Node iter;
    Node bottom;
    
    public Deck(){
        size =0;
        top = new Node();
        bottom = new Node();
    }
    
    public boolean fillDeck(boolean standard){
        if(standard){
            for(Suit s: Suit.values()){
                for(Value v: Value.values()){
                    Card a = new Card(s, v);
                    boolean added = addCard(a);
                    if(!added){
                        System.out.println("Duplicate Card Detected!");
                        return false;
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }
    
    public Card drawCard(){
        Card retval = top.card;
        if(size >= 2){
        top = top.next;
        bottom.next = top;
        }else if(size ==1){
            top = new Node();
            bottom = new Node();
        }else{
            return null;
        }
        size --;
        return retval;
    }
    
    public Card drawAtPos(int i){
        Card retval;
        Node current = top;
        for(int j = 0; j<i; j++){
            current = current.next;
        }
        retval = current.card;
        return retval;
    }
    
    public boolean addCard(Card c){
        Node current = top;
        do{
            if(current.card != null){
                if(current.card.compareto(c)){
                    System.out.println("dupe card!");
                    return false;}
                else
                    current = current.next;
            }
        }while(current != top);
        if(size ==0){
            top.card = c;
            top.next = top;
            size++;
        }else if(size==1){
            bottom = top;
            top = new Node();
            top.card = c;
            top.next = bottom;
            bottom.next = top;
            size++;
        }else{
            Node newtop = new Node();
            newtop.card = c;
            newtop.next = top;
            bottom.next = newtop;
            top = newtop;
            size++;
        }
        return true;
    }
    
    public Card iterateCard(int pos){
        boolean overflow = (iter == top);
        if(iter == null){
            iter = top;
            int i = 0;
            while(i<pos){
                iter = iter.next;
                overflow = (iter == top);
                if(overflow)
                    break;
                i++;
            }
        }
        Card retval;
        if(overflow){
            retval = null;
            iter = null;
        } else{
            retval = iter.card;
            iter = iter.next;
            
        }
        return retval;
    }
    
    public void resetIter(){
        iter = null;
    }
    
    public int discard(Card c){
        Node current = top;
        Node previous = bottom;
        int retval = 0;
        while(current.next != top){
            if(current.card.compareto(c)){
                previous.next = current.next;
                if(current == top)
                    current.next = top;
                size--;
            }else{
                previous = current;
                current = current.next;
                retval++;
            }
        }
        return retval;
    }
    
    public void insert(Card c, int pos){
        Node current = top;
        Node previous = bottom;
        for(int i=0; i<pos; i++){
            previous = current;
            current = current.next;
        }
        Node insert = new Node();
        insert.card = c;
        previous.next = insert;
        insert.next = current;
        if(current == top)
            top = insert; 
        size++;
    }
    
    class Node{
        Card card;
        Node next;
    }
}
