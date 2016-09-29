

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * This is Nathan Hillman's Licence Header.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natha_000
 */
public class VPDriver {
    private VPCalc prog;
    
    public static void main( String[] args){
        VPDriver drive = new VPDriver("Payout.txt");
        System.out.println("Test 1: "+drive.testHand(0, 13, 25, 38, 3));
        System.out.println("Test 2: "+drive.testHand(10, 23, 14, 29, 43));
        System.out.println("Test 3: "+drive.testHand(0, 2, 4, 6, 8));
        System.out.println("Test 4: "+drive.testHand(16, 8, 1, 17, 6));
        System.out.println("Test 5: "+drive.testHand(12, 25, 38, 0, 22));
    }
    
    public VPDriver(){
        prog = new VPCalc();
    }
    
    public VPDriver(String path){
        FileDriver input = null;
        try {
            input = new FileDriver(path);
        } catch (IOException ex) {
            Logger.getLogger(VPDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i =0;
        int[] payouts = new int[10];
        while(input.fileReady()){
            String valuesIn = input.getNextLine();
            String[] valuesSplit = valuesIn.split(" ");
            for(String s: valuesSplit){
                payouts[i] = Integer.parseInt(s);
                i++;
            }
        }
        prog = new VPCalc(payouts);
    }
    
    public double testHand(int c, int d, int e, int f, int g){
        return prog.handOptimal(c, d, e, f, g);
    }
    
}
