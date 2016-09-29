/*
 * This is Nathan Hillman's Licence Header.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package onelane;

import java.util.ArrayList;

/**
 *
 * @author natha_000
 */
public class OneLane {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numcars = 25;
        int time = 256;
        if(args.length > 0){
            numcars = Integer.parseInt(args[0]);
            time = Integer.parseInt(args[1]);
        }
        System.out.println("Starting One Lane Clump Count with "+numcars+" cars...");
        OneLane sim = new OneLane(numcars);
        System.out.println("Simulating action over time "+time+"...");
        int clumps = sim.simulate(time);
        System.out.println("Clumps counted: "+clumps);    
    }
    
    private ArrayList<Car> cars;
    
    public OneLane(){
        cars = new ArrayList();
        for(int i = 0; i<25; i++){
            Car c = new Car();
            cars.add(c);
            if(i > 0){
                cars.get(i-1).setCarinFront(c);
            }
        }
    }
    
    public OneLane(int numcars){
        cars = new ArrayList();
        for(int i = 0; i<numcars; i++){
            Car c = new Car();
            cars.add(c);
            if(i > 0){
                cars.get(i-1).setCarinFront(c);
            }
        }
    }
    
    public OneLane(int numcars, int min, int max){
        cars = new ArrayList();
        for(int i = 0; i<numcars; i++){
            Car c = new Car(min, max);
            cars.add(c);
            System.out.println(i);
            if(i > 0){
                cars.get((i-1)).setCarinFront(c);
            }
        }
    }
    
    /** 
	*Takes an int of amount of time, in ticks, to run the simulation for, produces the number of clumps
	*@param T the amount of time to run the simulation for
	*@return returns the number of clumps counted at the end of the simulation
	**/
	public int simulate(int T){
        for(int i = 0; i<T; i++){
            for(Car c: cars){
                c.tickTime();
            }
        }
        int clumps = 0;
        for(Car c: cars){
            if(c.clumpLeader()){
                clumps++;
            }
        }
        return clumps;
    }
    
}
