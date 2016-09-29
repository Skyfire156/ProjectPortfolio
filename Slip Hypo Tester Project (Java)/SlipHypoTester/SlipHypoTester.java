/*
 * This is Nathan Hillman's Licence Header.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sliphypotester;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author natha_000
 */
public class SlipHypoTester {
    
    public static final int STANDARD_NUMBER_OF_SLIPS = 3;
    private static final long DEFAULT_NUM_ROUNDS = 10000000;
    private ArrayList<Slip> slips;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SlipHypoTester test = new SlipHypoTester();
        Map<ThreeSlipStrategy, Long> result = test.simulatePlay(DEFAULT_NUM_ROUNDS);
        System.out.println("Simulation Results for 10000000 Rounds");
        System.out.println("----");
        System.out.println("Uninformed Strategies");
        for(ThreeSlipStrategy ts: ThreeSlipStrategy.values()){
            if(!ts.informed()){
                Long wins = result.get(ts);
                int percent = (int) (((float)wins/DEFAULT_NUM_ROUNDS)*100);
                System.out.println("   "+ts.description()+": "+wins+"("+percent+"%)");
            }
        }
        System.out.println("----");
        System.out.println("Informed Strategy");
        Long wins = result.get(ThreeSlipStrategy.INFORMED_STRATEGY);
        int percent = (int) (((float)wins/DEFAULT_NUM_ROUNDS)*100);
        System.out.println("   "+ThreeSlipStrategy.INFORMED_STRATEGY.description()+": "+wins+"("+percent+"%)");
        System.out.println("----");
        System.out.println("Contributions to Informed Strategy");
        Long win2 = result.get(ThreeSlipStrategy.STOP_IF_BETTER);
        int percent2 = (int) (((float)win2/wins)*100);
        System.out.println("   "+ThreeSlipStrategy.STOP_IF_BETTER.description()+": "+win2+"("+percent2+"%)");
        win2 = result.get(ThreeSlipStrategy.CONTINUE_IF_WORSE);
        percent2 = (int) (((float)win2/wins)*100);
        System.out.println("   "+ThreeSlipStrategy.CONTINUE_IF_WORSE.description()+": "+win2+"("+percent2+"%)");
        System.out.println("----");
        System.out.println("Comparison");
        wins = 0L;
        ThreeSlipStrategy bestU = null, bestI = ThreeSlipStrategy.INFORMED_STRATEGY;
        for(ThreeSlipStrategy ts: ThreeSlipStrategy.values()){
            if(!ts.informed()){
                if(wins < result.get(ts)){
                    bestU = ts;
                    wins = result.get(ts);
                    percent = (int) (((float)wins/DEFAULT_NUM_ROUNDS)*100);
                }
                
            }
            
        }
        win2 = result.get(bestI);
        percent2 = (int) (((float)win2/DEFAULT_NUM_ROUNDS)*100);
        System.out.println("   "+bestU.description()+": "+wins+"("+percent+"%)");
        System.out.println("   "+bestI.description()+": "+win2+"("+percent2+"%)");
    }
    
    public SlipHypoTester(){
        slips = new ArrayList();
        Slip sl;
        for(int i =0; i< STANDARD_NUMBER_OF_SLIPS; i++){
            sl = new Slip();
            slips.add(sl);
        }
    }
    
    public SlipHypoTester(int numslips){
        slips = new ArrayList();
        Slip sl;
        for(int i =0; i< numslips; i++){
            sl = new Slip();
            slips.add(sl);
        }
    }
    
    public Map<ThreeSlipStrategy, Long> simulatePlay(long numberOfRounds){
        Map<ThreeSlipStrategy, Long> retval = new TreeMap();
        for(ThreeSlipStrategy ts: ThreeSlipStrategy.values()){
            retval.put(ts, 0L);
        }
        for(long i = 0; i<numberOfRounds; i++){
            slips = getNewSlips();
            retval = runPlay(retval);
        }
        return retval;
    }
    
    public Map<ThreeSlipStrategy, Long> simulatePlay(long numberOfRounds, int minimum, int maximum){
        return null;
    }
    
    private Map<ThreeSlipStrategy, Long> runPlay(Map<ThreeSlipStrategy, Long> starter){
        int winner = Integer.MIN_VALUE;
        for(Slip s: slips){
            if(s.value() > winner){
                winner = s.value();
            }
        }
        if(slips.get(0).value() == winner ){
            starter.replace(ThreeSlipStrategy.KEEP_ORIGINAL, starter.get(ThreeSlipStrategy.KEEP_ORIGINAL)+1);
        }
        if(slips.get(1).value() == winner ){
            starter.replace(ThreeSlipStrategy.SWITCH_TO_REVEALED, starter.get(ThreeSlipStrategy.SWITCH_TO_REVEALED)+1);
            starter.replace(ThreeSlipStrategy.INFORMED_STRATEGY, starter.get(ThreeSlipStrategy.INFORMED_STRATEGY)+1);
            starter.replace(ThreeSlipStrategy.STOP_IF_BETTER, starter.get(ThreeSlipStrategy.STOP_IF_BETTER)+1);
        }
        if(slips.get(2).value() == winner ){
            starter.replace(ThreeSlipStrategy.SWITCH_TO_UNKNOWN, starter.get(ThreeSlipStrategy.SWITCH_TO_UNKNOWN)+1);
            if(slips.get(1).value() < slips.get(0).value()){
                starter.replace(ThreeSlipStrategy.INFORMED_STRATEGY, starter.get(ThreeSlipStrategy.INFORMED_STRATEGY)+1);
                starter.replace(ThreeSlipStrategy.CONTINUE_IF_WORSE, starter.get(ThreeSlipStrategy.CONTINUE_IF_WORSE)+1);
            }
        }
        return starter;
    }
    
    private ArrayList<Slip> getNewSlips(){
        ArrayList<Slip> retval = new ArrayList();
        ArrayList<Integer> vals = new ArrayList();
        for(int i = 0; i<slips.size(); i++){
            Slip s = new Slip();
            while(vals.contains(s.value())){
                s = new Slip();
            }
            retval.add(s);
            vals.add(s.value());
        }
        return retval;
    }
    
}
