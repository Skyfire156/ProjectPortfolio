/*
 * This is Nathan Hillman's Licence Header.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sliphypotester;

import java.util.Random;

/**
 *
 * @author natha_000
 */
public class Slip {
    
    private final int value;
    
    public Slip(){
        Random rand = new Random();
        value = rand.nextInt();
    }
    
    public Slip(int minimum, int maximum){
        Random rand = new Random();
        int i;
        do{
        i = rand.nextInt(maximum);
        }while(i < minimum);
        value = i;
    }
    
    public int value(){
        return value;
    }
    
}
