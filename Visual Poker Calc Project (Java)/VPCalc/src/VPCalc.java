

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
public class VPCalc {

    private Deck deck;
    private Hand hand;
    private POTable po;
    private int expected;
    
    public VPCalc(){
        deck = new Deck();
        deck.fillDeck(true);
        hand = new Hand();
        po = new POTable();
        expected = 0;
    }
    
    public VPCalc(int[] payouts){
        deck = new Deck();
        deck.fillDeck(true);
        hand = new Hand();
        po = new POTable(payouts);
        expected = 0;
    }

    
    public double calcExpected(){
        double retval = iterdeck(5);
        return retval;
    }
    
    private double iterdeck(int handsize){
        Hand h = new Hand(handsize);
        double retval = iterdeck(0, deck, h, 0);
        return retval;
    }
    
    private double iterdeck(int pos, Deck d, Hand h, int i){
        Card c;
        double retval = 0;
        if(pos < h.getSize()-1){ 
            c = d.iterateCard(0);
            while(c != null){
                h.addCard(c, pos);
                retval+= iterdeck(pos+1, d, h, i+1);
                i++;
                c = d.iterateCard(i);
            }
        }else{
            c = d.iterateCard(0);
            while(c != null){
                h.addCard(c, pos);
                retval += po.handcheck(h, d);
                if((retval % 1000) == 0){
                    double total;
                    total = 6754593081600.0;
                    System.out.println((retval/total)*100+"%");
                }
                c = d.iterateCard(0);
            }
        }
        return retval;
    }

    public double handOptimal(int c1, int c2, int c3, int c4, int c5){
        double retval;
        Hand h = new Hand(5);
        int[] cards = new int[5];
        cards[0] = c1;
        cards[1] = c2;
        cards[2] = c3;
        cards[3] = c4;
        cards[4] = c5;
        for(int i = 0; i<5; i++){
            int s = cards[i]/13;
            int v = cards[i]%13;
            for(Value val: Value.values()){
                for(Suit suit: Suit.values()){
                    if(s == suit.ordinal() && v == val.ordinal()){
                        Card c = new Card(suit, val);
                        h.addCard(c, i);
                    }
                }
            }
        }
        retval = po.handcheck(h, deck);
        return retval;
    }
}
