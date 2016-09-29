/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxessim;

/**
 *
 * @author nhillman
 */
public class BoxHypoTester {
    
    private BoxHypoTester(){
        
    }

    
    public static long[] simulatePlay(long numGames){
        long[] retval = new long[3];
        Strategy s = new Strategy("Switch");
        Strategy k = new Strategy("Keep");
        Strategy r = new Strategy("Random");
        for(long L = 0; L < numGames; L++){
            Player p = new Player(k);
            Host h = new Host(p);
            if(h.playGame()){
                retval[0]++;
            }
            
        }
        for(long L = 0; L < numGames; L++){
            Player p = new Player(s);
            Host h = new Host(p);
            if(h.playGame()){
                retval[1]++;
            }
            
        }
        for(long L = 0; L < numGames; L++){
            Player p = new Player(r);
            Host h = new Host(p);
            if(h.playGame()){
                retval[2]++;
            }
            
        }
        return retval;
    }
    
}
