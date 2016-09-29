/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package downtimeapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Downtimeapp {
    Player character;
    Database data;
    DowntimeGUI gui;
    public static void main(String[] args) {
        Downtimeapp program = new Downtimeapp();
    }
    
    public Downtimeapp(){
        data = new Database();
        
        
        try {
            character = loadData();
        } catch (IOException ex) {
            Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(character == null)
            character = new Player(data);
        //to be deleted once testing is done
        /**
        character.day = 168;
        Building test = new Building(data);
        test.name = "Tavern";
        character.buildings.add(test);
        character.adminbuildRoom(test, "Bar", true, 0);
        character.adminbuildRoom(test, "Common Room", true, 0);
        character.adminbuildRoom(test, "Kitchen", true, 0);
        character.adminbuildRoom(test, "Lavatory", true, 0);
        character.adminbuildRoom(test, "Office", true, 0);
        character.adminbuildRoom(test, "Storage", true, 0);
        character.adminbuildRoom(test, "Secret Room", true, 0);
        character.adminbuildRoom(test, "Sewer Access", true, 0);
        character.adminbuildRoom(test, "Stall", true, 0);
        character.adminbuildRoom(test, "Lodging", true, 0);
        character.adminbuildRoom(test, "Storefront", true, 0);
        Building firstguild = new Building(data);
        firstguild.name = "Thieves' Guild";
        character.orgs.add(firstguild);
        character.adminbuildRoom(firstguild, "Soldiers", false, 0);
        character.adminbuildRoom(firstguild, "Scofflaws", false, 0);
        character.adminbuildRoom(firstguild, "Robbers", false, 0);
        character.adminbuildRoom(firstguild, "Cutpurses", false, 0);
        character.adminbuildRoom(firstguild, "Cutpurses", false, 0);
        character.adminbuildRoom(firstguild, "Mage", false, 0);
        Building manor = new Building(data);
        manor.name = "City Manor";
        character.buildings.add(manor);
        character.adminbuildRoom(manor, "Armory", true, 0);
        character.adminbuildRoom(manor, "Auditorium", true, 0);
        character.adminupgradeRoom(manor, manor.rooms.get(1), "Furnish", true);
        character.adminbuildRoom(manor, "Auditorium", true, 0);
        character.adminupgradeRoom(manor, manor.rooms.get(2), "Furnish", true);
        character.adminbuildRoom(manor, "Bar", true, 0);
        character.adminbuildRoom(manor, "Bath", true, 0);
        character.adminbuildRoom(manor, "Bath", true, 0);
        character.adminbuildRoom(manor, "Bedroom", true, 0);
        character.adminupgradeRoom(manor, manor.rooms.get(6), "Furnish", true);
        character.adminbuildRoom(manor, "Bedroom", true, 0);
        character.adminbuildRoom(manor, "Bedroom", true, 0);
        character.adminbuildRoom(manor, "Bedroom", true, 0);
        character.adminbuildRoom(manor, "Courtyard", true, 0);
        character.adminupgradeRoom(manor, manor.rooms.get(10), "Furnish", true);
        character.adminbuildRoom(manor, "Courtyard", true, 0);
        character.adminupgradeRoom(manor, manor.rooms.get(11), "Furnish", true);
        character.adminbuildRoom(manor, "Defensive Wall", true, 0);
        character.adminbuildRoom(manor, "Gatehouse", true, 0);
        character.adminbuildRoom(manor, "Escape Route", true, 0);
        character.adminbuildRoom(manor, "Kitchen", true, 0);
        character.adminbuildRoom(manor, "Lavatory", true, 0);
        character.adminbuildRoom(manor, "Lavatory", true, 0);
        character.adminbuildRoom(manor, "Office", true, 0);
        character.adminbuildRoom(manor, "Sewer Access", true, 0);
        character.adminbuildRoom(manor, "Sitting Room", true, 0);
        character.adminupgradeRoom(manor, manor.rooms.get(20), "Furnish", true);
        character.adminbuildRoom(manor, "Stall", true, 0);
        character.adminbuildRoom(manor, "Storage", true, 0);
        
        character.storedgp = 1463;
        character.Goods = 14;
        character.Influence = 10;
        character.Labor = 30;
        
        for(int i = 0; i<10; i++){
            Building tden = new Building(data);
            tden.name = "Thief Den";
            character.buildings.add(tden);
            character.adminbuildRoom(tden, "Common Room", true, 0);
            character.adminbuildRoom(tden, "Bar", true, 0);
            character.adminbuildRoom(tden, "False Front", true,0);
            character.adminbuildRoom(tden, "Storage", true,0);
        }
        for(int i = 0; i<12; i++){
        Building tguild = new Building(data);
        tguild.name = "Thief Guild";
        character.orgs.add(tguild);
        character.adminbuildRoom(tguild, "Scofflaws", false, 0);
        character.adminbuildRoom(tguild, "Robbers", false, 0);
        character.adminbuildRoom(tguild, "Cutpurses", false, 0);
        }
        
        for(int i = 0; i<3; i++){
            Building tden = new Building(data);
            tden.name = "Thief Den";
            character.buildings.add(tden);
            character.adminbuildRoom(tden, "Common Room", true, (156+5*i));
            character.adminbuildRoom(tden, "Bar", true,(156+5*i));
            character.adminbuildRoom(tden, "False Front", true,(156+5*i));
            character.adminbuildRoom(tden, "Storage", true,(156+5*i));
            if(i==2){
                Building tguild = new Building(data);
                tguild.name = "Thief Guild";
                character.orgs.add(tguild);
                character.adminbuildRoom(tguild, "Scofflaws", false, 166);
                character.adminbuildRoom(tguild, "Robbers", false, 166);
                character.adminbuildRoom(tguild, "Cutpurses", false, 166);  
            }
        }
        **/
        //end to be deleted
        
        gui = new DowntimeGUI(character, this);
        gui.setVisible(true);
        fillGUI();
        gui.updateGUI();
        
    }
    
    public Player loadData() throws FileNotFoundException, IOException, ClassNotFoundException{
        String loadpath = "DowntimeSave.sav";
        File savedgame = new File(loadpath);
        FileInputStream loadfile = new FileInputStream(savedgame);
        Player room;
        try (ObjectInputStream loaddata = new ObjectInputStream(loadfile)) {
            room = (Player) loaddata.readObject();
        }
        return room;
    }
    
    public void saveData(Player savdata){
        FileOutputStream savefile = null;
        try {
            File newsave = new File("DowntimeSave.sav");
            savefile = new FileOutputStream(newsave);
            ObjectOutputStream savedata = new ObjectOutputStream(savefile);
            savedata.writeObject(savdata);
            savedata.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                savefile.close();
            } catch (IOException ex) {
                Logger.getLogger(Downtimeapp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fillGUI(){
        for(Building build: character.buildings){
            gui.addBuilding(true);
        }
        for(Building build: character.orgs){
            gui.addBuilding(false);
        }
    }
    
}