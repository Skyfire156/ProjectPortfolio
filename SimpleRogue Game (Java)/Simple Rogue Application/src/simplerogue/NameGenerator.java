/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplerogue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author natha_000
 */
public class NameGenerator {
    Map<String, List<String>> Markov = new HashMap<>();
    
    public NameGenerator(){
        Name_Seeder();
        Markov_Generation(Humanoid_Seeds);
    }
    
    private void Markov_Generation(String[] Names){
        for (String Name : Names){
            for (int i = 0; i < (Name.length()-2); i++){
                String start = Name.substring(i, i+2);
                String next = Name.substring(i+2, i+3);
                if (Markov.containsKey(start)){
                    List<String> current = Markov.get(start);
                    current.add(next);
                    Markov.put(start, current);
                }
                else{
                    List<String> current = new ArrayList<>();
                    current.add(next);
                    Markov.put(start, current);
                }
            }
        }
    }
    
    public String Name_Generator(){
        Random picker = new Random();
        int length = picker.nextInt(7)+4;
        String name = "";
        for (int i = 0; i<(length); i++){
            if (i > 0){
                String newkey = name.substring((name.length()-2));
                List<String> possibles = Markov.get(newkey);
                if (Markov.get(newkey) != null){
                    name += possibles.get(picker.nextInt(possibles.size()));
                }
            }
            else{
                List<String> starters = new ArrayList<>(Markov.keySet());
                name = starters.get(picker.nextInt(starters.size()));
            }
        }
        
        
        return name;
    }
    
    public String New_Name(String type){
        String name;
        switch(type){
            case "Aberrant":
                break;
            case "Animal":
                break;
            case "Construct":
                break;
            case "Dragon":
                break;
            case "Fey":
                break;
            case "Humanoid":
                break;
            case "Monstrous Humanoid":
                break;
            case "Ooze":
                break;
            case "Elemental":
                break;
            case "Demon":
                break;
            case "Celestial":
                break;
            case "Beast":
                break;
            case "Plant":
                break;
            case "Undead":
                break;
        }
        name = Name_Generator();
        return name;
    }
    
    private void Name_Seeder(){
        String[] seeds = {"/simplerogue/NameLists/Humanoid_Names.csv"};
        BufferedReader br = null;
        String line = "";
        String splitby = ",";
        List<String> names = new ArrayList<>();
        InputStream newpath;
        
        for (String seed : seeds){
            try{
                newpath = getClass().getResourceAsStream(seed);
                br = new BufferedReader(new InputStreamReader(newpath));
                while ((line = br.readLine()) != null){
                    names.add(line);
                    Humanoid_Seeds = names.toArray(seeds);    
                }
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            finally{
                if (br != null){
                    try {
                        br.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    String[] Humanoid_Seeds;
}
