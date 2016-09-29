

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author Nate
 */
public class POTable {
    
    private Payout[] payouts;
    private int permutations;
    
    public POTable(){
        payouts = new Payout[9];
        payouts[0] = new RoyalFlush();
        payouts[1] = new StraightFlush();
        payouts[2] = new FourKind();
        payouts[3] = new FullHouse();
        payouts[4] = new Flush();
        payouts[5] = new Straight();
        payouts[6] = new ThreeKind();
        payouts[7] = new TwoPair();
        payouts[8] = new Pair();
    }
    
    public POTable(int[] pouts){
        payouts = new Payout[9];
        payouts[0] = new RoyalFlush(pouts[0]);
        payouts[1] = new StraightFlush(pouts[1]);
        payouts[2] = new FourKind(pouts[2]);
        payouts[3] = new FullHouse(pouts[3]);
        payouts[4] = new Flush(pouts[4]);
        payouts[5] = new Straight(pouts[5]);
        payouts[6] = new ThreeKind(pouts[6]);
        payouts[7] = new TwoPair(pouts[7]);
        payouts[8] = new Pair(pouts[8]);
    }
    
    public double handcheck(Hand hand, Deck deck){
        return iterStrat(hand, deck);
    }
    
    private double iterStrat(Hand hand, Deck deck){
        boolean[] k= new boolean[hand.getSize()];
        Deck d = new Deck();
        Card c = deck.iterateCard(0);
        while(c != null){
            if(!hand.contains(c)){
                d.addCard(c);
            }
            c = deck.iterateCard(0);
        }
        double retval = iterStrat(hand, 0, k, d);
        return retval;
    }
    
    private double iterStrat(Hand h, int pos, boolean[] keeps, Deck d){
        double retval = 0;
        double tempval;
        
        if(pos < h.getSize()-1){
            keeps[pos] = true;
            tempval = iterStrat(h,pos+1,keeps, d);
            if(tempval > retval)
                retval = tempval;
            keeps[pos] = false;
            tempval= iterStrat(h,pos+1,keeps, d);
            if(tempval > retval)
                retval = tempval;
        }else{
            keeps[pos] = true;
            tempval = iterFinal(h, keeps, d);
            if(tempval > retval)
                retval = tempval;
            h.resetTemps();
            keeps[pos] = false;
            tempval = iterFinal(h, keeps, d);
            if(tempval > retval)
                retval = tempval;
            h.resetTemps();
            
        }
        return retval;
    }
    
    private double iterFinal(Hand hand, boolean[] keeps, Deck deck){
        permutations = 1;
        double retval = iterFinalRec(hand, 0, keeps, deck, 0);
        
        return retval;
    }
    
    private double iterFinalRec(Hand h, int pos, boolean[] keeps, Deck d, int i){
        Card c;
        double retval = 0;
        if(pos < h.getSize()-1){
            if(keeps[pos]){
                retval+= iterFinalRec(h, pos+1, keeps, d, i);
            }else{
                c = d.iterateCard(0);
                while(c != null){
                    h.addTemp(c, pos);
                    retval+= iterFinalRec(h, pos+1, keeps, d, i+1);
                    permutations++;
                    i++;
                    c = d.iterateCard(i);
                }
            }
        }else{
            if(keeps[pos]){
                retval+= amountPaid(h);
            }else{
                c = d.iterateCard(0);
                while(c != null){
                    h.addTemp(c, pos);
                    retval+= amountPaid(h);
                    c = d.iterateCard(0);
                    permutations++;
                }
            }
        }
        if(pos == 0){
            retval = retval/permutations;
        }
        return retval;
    }
    
    public double amountPaid(Hand hand){
        double retval = 0;
        for(Payout p: payouts){
            double tempval = p.isPaid(hand);
            if(tempval > retval)
                retval = tempval;
        }
        return retval;
    }
    
}
