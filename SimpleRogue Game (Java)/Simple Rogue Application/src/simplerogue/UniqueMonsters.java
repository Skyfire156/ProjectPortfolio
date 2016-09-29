/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplerogue;

import java.util.Random;

/**
 *
 * @author natha_000
 */
public class UniqueMonsters extends Monster_stats{
    public UniqueMonsters(String summon){
        HP = Random_Stat();
        Atk = Random_Stat();
        Def = Random_Stat();
        ToHit = Random_Stat() + 30;
        Crit = Random_Stat() /4;
        Dodge = Random_Stat() /4;
        switch(summon){
            case "Random": Random_Unique();
                break;
            case "Default": Default();
                break;
            case "Terror Lord": Terror_Demon();
                break;
            case "GG": GG();
                break;
            case "Final": Final();
                break;
            case "Tucker": Kobold();
                break;
        }
    }
    
    private int Random_Stat() {
            Random rand = new Random();
            int stat;
            stat = rand.nextInt(Encounter.Current_Floor + 1) + Encounter.Current_Floor;
            return stat;
    }
    
    private void Random_Unique(){
        Random UniquePicker = new Random();
        int Uniquepicked = UniquePicker.nextInt(4);
        switch(Uniquepicked){
            case 0: Default();
                break;
            case 1: GG();
                break;
            case 2: Terror_Demon();
                break;
            case 3: Kobold();
                break;
        }
    }
    
    private void Default(){
        Name = "MissingNo";
        Monster_Type = "Abberant";
        HP = 88;
        Atk = 77;
        Def = 66;
        ToHit = 55;
        Crit = 44;
        Dodge = 33;
    }
    
    private void GG(){
        Name = "GG";
        Monster_Type = "DOOM";
        HP *= 100;
        Atk *= 100;
        Def *= 100;
        ToHit *= 100;
        Crit += 40;
        Dodge += 40;
        Counter counter = new Counter();
        for (int i = 0; i < 9; i++){
            counter.Increase_Level();}
        Abilities.add(counter);
    }
    
    private void Terror_Demon(){
        Name = "Terror Lord";
        Monster_Type = "Demon";
        HP = 300;
        Atk = 150;
        Def = 200;
        Dodge = 20;
        Crit = 30;
        ToHit = 99;
        this.New_Ability("fire", this);
        this.New_Ability("Wounding", this);
    }
    
    private void Final(){
        Name = "Lord of Damnation";
        HP = 300;
        Atk = 150;
        Def = 200;
        Dodge = 20;
        Crit = 30;
        ToHit = 99;
        Counter counter = new Counter();
        for (int i = 0; i < 9; i++){
            counter.Increase_Level();}
        Abilities.add(counter);
        Lightning lightning = new Lightning();
        for (int i = 0; i < 9; i++){
            lightning.Increase_Level();}
        Abilities.add(lightning);
        Wounding wounding = new Wounding();
        for (int i = 0; i < 5; i++){
            wounding.Increase_Level();}
        Abilities.add(wounding);
    }
    
    private void Kobold(){
        Name = "Tucker";
        Monster_Type = "Kobold";
        ToHit = 100;
        Def = Def*3;
        HP = HP*4;
        Wounding wounding = new Wounding();
        for (int i = 0; i < 9; i++){
            wounding.Increase_Level();}
        Abilities.add(wounding);
        Blinding blinding = new Blinding();
        for (int i = 0; i < 9; i++){
            blinding.Increase_Level();}
        Abilities.add(blinding);
        Poison poison = new Poison();
        for (int i = 0; i < 9; i++){
            poison.Increase_Level();}
        Abilities.add(poison);
    }
}

    