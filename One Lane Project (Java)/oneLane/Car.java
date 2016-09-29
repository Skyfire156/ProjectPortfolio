/*
 * This is Nathan Hillman's Licence Header.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package onelane;

import java.util.Random;

/**
 *
 * @author natha_000
 */
public class Car {
    private int prefSpeed;
    private int actSpeed;
    private int followDist;
    private boolean clumpLead;
    private Car frontCar;
    
    public Car(){
        Random rand = new Random();
        int ps = 0;
        while(ps < 25){
            ps = rand.nextInt(80);
        }
        prefSpeed = ps;
        actSpeed = prefSpeed;
        while(followDist <= 0){
            followDist = rand.nextInt();
        }
        clumpLead = true;
    }
    
    public Car(int ps){
        Random rand = new Random();
        prefSpeed = ps;
        actSpeed = prefSpeed;
        while(followDist <= 0){
            followDist = rand.nextInt();
        }
        clumpLead = true;
    }
    
    public Car(int min, int max){
        Random rand = new Random();
        int ps = 0;
        while(ps < min){
            ps = rand.nextInt(max);
        }
        prefSpeed = ps;
        actSpeed = prefSpeed;
        while(followDist <= 0){
            followDist = rand.nextInt();
        }
        clumpLead = true;
    }
    
    public void tickTime(){
        if(frontCar == null){
        } else {
            int speedDif = frontCar.getSpeed() - actSpeed;
            followDist = followDist + speedDif;
            if(followDist <= 0){
                actSpeed = frontCar.getSpeed();
                followDist = 0;
                clumpLead = false;
            }
        }
    }
    
    public void setCarinFront(Car c){
        frontCar = c;
    }
    
    public int getSpeed(){
        return actSpeed;
    }
    
    public boolean clumpLeader(){
        return clumpLead;
    }
    
}
