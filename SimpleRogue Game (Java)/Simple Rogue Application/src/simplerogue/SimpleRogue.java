/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplerogue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natha_000
 */
public class SimpleRogue {
    public static Encounter Room;
    public static NameGenerator namelist;


    public static void main() {
        // TODO code application logic here
        // On Start of game...
        namelist = new NameGenerator();
    }
    
    public void New_Encounter(){
        Player_stats Hero = new Player_stats();
        Room = new Encounter(Hero);
    }
    
    public void save(Encounter data) throws FileNotFoundException, IOException{
        String savepath = "simplesave.sav";
        File newsave = new File(savepath);
        FileOutputStream savefile = new FileOutputStream(newsave);
        ObjectOutputStream savedata = new ObjectOutputStream(savefile);
        savedata.writeObject(data);
        savedata.close();
    }
    
    public Encounter load() throws FileNotFoundException, IOException, ClassNotFoundException{
        String loadpath = "simplesave.sav";
        File savedgame = new File(loadpath);
        FileInputStream loadfile = new FileInputStream(savedgame);
        Encounter room;
        try (ObjectInputStream loaddata = new ObjectInputStream(loadfile)) {
            room = (Encounter) loaddata.readObject();
        }
        return room;
    }

}

    class Entity_Stats implements java.io.Serializable {
        String Name;
        int HP = 1;
        int Atk = 1;
        int Def = 1;
        int Dodge = 1;
        int Crit = 1;
        int ToHit = 1;
        ArrayList<Effects> Abilities = new ArrayList<>();

        public void Random_Ability(Entity_Stats entity){
            Random abilitypick = new Random();
            int i = 0;
            switch(abilitypick.nextInt(9)){
                case 0: Fire fire = new Fire();
                    for (Effects Ability : entity.Abilities) {
                        if ("Fire".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(fire);}
                break;
                case 1: Lightning lightning = new Lightning();
                for (Effects Ability : entity.Abilities) {
                        if ("Lightning".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(lightning);}
                break;
                case 2: Cold cold = new Cold();
                for (Effects Ability : entity.Abilities) {
                        if ("Cold".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(cold);}
                break;
                case 3: 
                    Acid acid = new Acid();
                    for (Effects Ability : entity.Abilities) {
                        if ("Acid".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(acid);}
                break;
                case 4: 
                    Shield shield = new Shield();
                    for (Effects Ability : entity.Abilities) {
                        if ("Shield".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(shield);}
                break;
                case 5: 
                    Counter counter = new Counter();
                    for (Effects Ability : entity.Abilities) {
                        if ("Counter".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(counter);}
                break;
                case 6: 
                    Echo echo = new Echo();
                    for (Effects Ability : entity.Abilities) {
                        if ("Echo".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(echo);}
                break;
                case 7: 
                Blinding blinding = new Blinding();
                    for (Effects Ability : entity.Abilities) {
                        if ("Blinding".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(blinding);}
                break;
                case 8:
                    Poison poison = new Poison();
                    for (Effects Ability : entity.Abilities) {
                        if ("Poison".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(poison);}
                break;
            }
        }
        
        public void New_Ability(String ability, Entity_Stats entity){
            int i = 0;
            switch(ability){
                case "Fire": Fire fire = new Fire();
                    for (Effects Ability : entity.Abilities) {
                        if ("Fire".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(fire);}
                break;
                case "Lightning": Lightning lightning = new Lightning();
                for (Effects Ability : entity.Abilities) {
                        if ("Lightning".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(lightning);}
                break;
                case "Cold": Cold cold = new Cold();
                for (Effects Ability : entity.Abilities) {
                        if ("Cold".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(cold);}
                break;
                case "Acid": 
                    Acid acid = new Acid();
                    for (Effects Ability : entity.Abilities) {
                        if ("Acid".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(acid);}
                break;
                case "Shield": 
                    Shield shield = new Shield();
                    for (Effects Ability : entity.Abilities) {
                        if ("Shield".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(shield);}
                break;
                case "Counter": 
                    Counter counter = new Counter();
                    for (Effects Ability : entity.Abilities) {
                        if ("Counter".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(counter);}
                break;
                case "Wounding": 
                    Wounding wounding = new Wounding();
                    for (Effects Ability : entity.Abilities) {
                        if ("Wounding".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(wounding);}
                break;
                case "Echo": 
                    Echo echo = new Echo();
                    for (Effects Ability : entity.Abilities) {
                        if ("Echo".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(echo);}
                break;
                case "Blinding":
                    Blinding blinding = new Blinding();
                    for (Effects Ability : entity.Abilities) {
                        if ("Blinding".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(blinding);}
                break;
                case "Poison":
                    Poison poison = new Poison();
                    for (Effects Ability : entity.Abilities) {
                        if ("Poison".equals(Ability.Name)){Ability.Increase_Level();
                        i++;
                        }
                    }
                    if (i == 0){entity.Abilities.add(poison);}
                break;
            }
        }

    }

    class Player_stats extends Entity_Stats {
        public Player_stats(){
            HP = 20;
            ToHit = 60;
        }
        
        public void Ability_Parse(){
            String List_o_abilities = "";
            for (Effects Ability : Abilities) {
                List_o_abilities += Ability.Name+"("+Ability.Level+") \n";
            }
            
                SimpleRogueGui.AbilityList.setText(List_o_abilities);
        }
        
        public void Stats_Parse(){
            String List_o_stats = "";
            List_o_stats += "HP:"+HP+"\n";
            List_o_stats += "Atk:"+Atk+"\n";
            List_o_stats += "Def:"+Def+'\n';
            List_o_stats += "Acc:"+ToHit+"\n";
            List_o_stats += "Crit:"+Crit+"\n";
            List_o_stats += "Dodge:"+Dodge;
            
                SimpleRogueGui.StatsList.setText(List_o_stats);
        }

    }

    class Monster_stats extends Entity_Stats {
        String Monster_Type;
        String Death_Reward;
        
        public Monster_stats() {
            /*Monster stat range is lvl to lvl x 2, specific classes will have 
            multipliers to make stats stronger or weaker as is typical for that 
            type of creature*/
            HP = Random_Stat();
            Atk = Random_Stat();
            Def = Random_Stat();
            Dodge = Random_Stat()/4;
            Crit = Random_Stat()/4;
            ToHit = Random_Stat()+30;
            Ability_Generator();
            
            Random Monsterandom = new Random();
            int MonsterPick = Monsterandom.nextInt(14);
            switch(MonsterPick){
            case 0: Monster_stats.Aberrant NewAberrant = this.new Aberrant();
                Monster_Type = "Aberrant";
                Name = Name_Generator();
                break;
            case 1: Monster_stats.Animal NewAnimal = this.new Animal();
                Monster_Type = "Animal";
                Name = Name_Generator();
                break;
            case 2: Monster_stats.Construct NewConstruct = this.new Construct();
                Monster_Type = "Construct";
                Name = Name_Generator();
                break;
            case 3: Monster_stats.Dragon NewDragon = this.new Dragon();
                Monster_Type = "Dragon";
                Name = Name_Generator();
                break;
            case 4: Monster_stats.Fey NewFey = this.new Fey();
                Monster_Type = "Fey";
                Name = Name_Generator();
                break;
            case 5: Monster_stats.Humanoid NewHumanoid = this.new Humanoid();
                Monster_Type = "Humanoid";
                Name = Name_Generator();
                break;
            case 6: Monster_stats.Monstrous_Humanoid NewMonstrous = this.new Monstrous_Humanoid();
                Monster_Type = "Monstrous Humanoid";
                Name = Name_Generator();
                break;
            case 7: Monster_stats.Ooze NewOoze = this.new Ooze();
                Monster_Type = "Ooze";
                Name = Name_Generator();
                break;
            case 8: Monster_stats.Elemental NewElemental = this.new Elemental();
                Monster_Type = "Elemental";
                Name = Name_Generator();
                break;
            case 9: Monster_stats.Demon NewDemon = this.new Demon();
                Monster_Type = "Demon";
                Name = Name_Generator();
                break;
            case 10: Monster_stats.Celestial NewCelestial = this.new Celestial();
                Monster_Type = "Celestial";
                Name = Name_Generator();
                break;
            case 11: Monster_stats.Beast NewBeast = this.new Beast();
                Monster_Type = "Beast";
                Name = Name_Generator();
                break;
            case 12: Monster_stats.Plant NewPlant = this.new Plant();
                Monster_Type = "Plant";
                Name = Name_Generator();
                break;
            case 13: Monster_stats.Undead NewUndead = this.new Undead();
                Monster_Type = "Undead";
                Name = Name_Generator();
                break;
                
            }
        if (HP < 1){ HP = 1;}
            
        }
        
        private int Random_Stat() {
            Random rand = new Random();
            int stat;
            stat = rand.nextInt(Encounter.Current_Floor + 1) + Encounter.Current_Floor;
            return stat;
        }
        
        private String Name_Generator(){
        String NewName;
        NewName = SimpleRogue.namelist.New_Name(this.Monster_Type);
        
        return NewName;
    }
        
        private void Ability_Generator(){
            int numabilities = Encounter.Current_Floor*4;
            int i = 0;
            while (numabilities > 100){
                i++;
                numabilities -= 100;
            }
            Random abilichance = new Random();
            if (abilichance.nextInt(99)+1 < numabilities){
                i++;
            }
            while(i>0){
                Random_Ability(Monster_stats.this);
                i--;
            }
        }
    
 
        class Aberrant{
            public Aberrant() {
                HP = (int) (HP * 1.4);
                Dodge = (int) (Dodge * 0.7);
                Crit = (int) (Crit * 1.2);
                ToHit = (int) (ToHit * 0.7);
                Death_Reward = "Crit";
            }
        }
        
        class Animal{
            public Animal() {
                Atk = (int) (Atk * 0.7);
                Dodge = (int) (Dodge * 1.2);
                ToHit = (int) (ToHit * 1.1);
                Death_Reward = "Dodge";
            }
        
        }
        
        class Construct{
            public Construct(){
                Def = Def*2;
                ToHit = (int) (ToHit*0.8);
                HP = (int) (HP*1.3);
                Atk = (int) (Atk*0.8);
                Dodge = (int) (Dodge*0.1);
                Death_Reward = "Def";
            }
        }
        
        class Dragon{
            public Dragon(){
                Atk = (int) (Atk*1.1);
                Def = (int) (Def*1.3);
                HP = (int) (HP*1.4);
                Dodge = (int) (Dodge*0.5);
                ToHit = (int) (ToHit*0.7);
                New_Ability("Fire", Monster_stats.this);
                Death_Reward = "Atk";
            }    
        }
        
        class Fey{
            public Fey(){
                HP = (int) (HP*0.5);
                Dodge = (int) (Dodge*1.5);
                ToHit = (int) (ToHit*1.4);
                Atk = (int) (Atk*0.6);
                Death_Reward = "Dodge";
            }
            
        }
        
        class Humanoid{
            public Humanoid(){
            ToHit = (int) (ToHit*1.2);
            Atk = (int) (Atk*0.8);
            Death_Reward = "ToHit";
            }
        }
        
        class Beast{
            public Beast(){
            HP = (int) (HP*1.2);
            Def = (int) (Def*1.3);
            Atk = (int) (Atk*0.7);
            Crit = (int) (Crit*0.8);
            Death_Reward = "Def";
            }
        }
        
        class Monstrous_Humanoid{
            public Monstrous_Humanoid(){
                HP = (int) (HP*1.5);
                Def = (int) (Def*1.1);
                ToHit = (int) (ToHit*0.7);
                Death_Reward = "ToHit";
            }
        }
        
        class Ooze{
            public Ooze(){
                Def = (int) (Def*1.8);
                HP = (int) (HP*1.5);
                Atk = (int) (Atk*0.4);
                Crit = (int) (Crit*1.2);
                Dodge = (int) (Dodge*0.1);
                New_Ability("Acid", Monster_stats.this);
                Death_Reward = "Crit";
            }
            
        }
        
        class Elemental{
            public Elemental(){
                Dodge = (int) (Dodge*1.4);
                Atk = (int) (Atk*1.2);
                Def = (int) (Def*0.7);
                HP = (int) (HP*0.7);
                String[] element = {"Fire", "Acid", "Cold", "Lightning"};
                Random elepick = new Random();
                int pick = elepick.nextInt(4);
                New_Ability(element[pick], Monster_stats.this);
                Death_Reward = "Crit";
            }
            
        }
        
        class Demon{
            public Demon(){
                Atk = (int) (Atk*1.2);
                Def = (int) (Def*1.1);
                ToHit = (int) (ToHit*1.1);
                Dodge = (int) (Dodge*0.6);
                New_Ability("Fire", Monster_stats.this);
                Death_Reward = "ToHit";
            }
        }
        
        class Celestial{
            public Celestial(){
            Atk = (int) (Atk*1.4);
            Dodge = (int) (Dodge*0.7);
            New_Ability("Cold", Monster_stats.this);
            Death_Reward = "Atk";
            }
        }
        
        class Plant{
            public Plant(){
                HP = HP*2;
                Def = (int) (Def*1.3);
                Death_Reward = "Atk";
            }
            
        }
        
        class Undead{
            public Undead(){
                HP = (int) (HP*1.5);
                Def = (int) (Def*1.4);
                ToHit = (int) (ToHit*0.5);
                Dodge = (int) (Dodge*0.4);
                Death_Reward = "Def";
            }
            
        }
        
    
    }

class Encounter implements java.io.Serializable {
    public static int Current_Floor = 1;
    int Private_Current = Current_Floor;
    int Maximum_Reached = 1;
    Player_stats Hero;
    Monster_stats Opponent;
    boolean examined;
    int StoryLevel = 1;
    int Bookmark = 0;
    StoryTeller storybook;
    static ArrayList<String> buffer;
    
    public Encounter(Player_stats Player){
        this.buffer = new ArrayList<>();
        Hero = Player;
        storybook = new StoryTeller();
        Story_Time();
    }
    
    private int Combat_Roll(){
        Random Roll = new Random();
        int Result = Roll.nextInt(100) + 1;
        return Result;
    }
    
    public void Attack(){
        if (buffer != null)
            buffer.clear();
        Text_Interface("", true);
        Player_combat();
        Monster_combat();
        Private_Current = Current_Floor;
        if (Hero.HP <= 0){
            Player_Death();
        }
        
        if (Opponent.HP <= 0){
            Story_Time();
        }
    }
    
    public void Examine(){
        if (buffer != null)
            buffer.clear();
        Text_Interface("You Examine "+ Opponent.Name+": \n", false);
        Text_Interface("Creature Type: "+Opponent.Monster_Type+"\n", false);
        Text_Interface("HP: "+Opponent.HP+"\n", false);
        Text_Interface("Damage: "+Opponent.Atk+"\n", false);
        Text_Interface("Special Abilities: \n", false);
        if(Opponent.Abilities.size() > 0){
            for (Effects ability : Opponent.Abilities) {
                String abilities = "    "+ability.Name+"\n";
                Text_Interface(abilities, false);
            }
        }
        
        Opponent.Def-= (Current_Floor/4);
        Text_Interface("Opponent's Def reduced by "+(Current_Floor/4)+"\n", false);
        Opponent.Dodge--;
        Text_Interface("Opponent's Dodge reduced by 1 \n", false);
        if (!examined){
            examined = true;
            if(Opponent.Abilities.size() > 0)
                Text_Interface("Studied "+Opponent.Name+"'s abilities \n");
        }
        
        Monster_combat();
        Private_Current = Current_Floor;
        
        if (Hero.HP <= 0){
            Player_Death();
        }
        
        if (Opponent.HP <= 0){
            Monster_Death();
        }
    }
    
    public void Retreat(){
        if (Current_Floor > 1){
            Current_Floor --;
            buffer.clear();
        Text_Interface("(--- You retreat to level "+Current_Floor+" ---)\n", true);
        Opponent = new Monster_stats();
        Text_Interface("You Encounter "+Opponent.Name+"\n", false);
        Private_Current = Current_Floor;
        }
        else {
            Text_Interface("Can't Retreat! \n", false);
        }
    }
    
    public void Player_combat(){
        boolean PlayerHit = false;
        boolean MonsterDodge = false;
        boolean PlayerCrit = false;
        int Damage = Hero.Atk - Opponent.Def;
        
        if (Hero.ToHit > Combat_Roll()) {
            PlayerHit = true;
        }
        
        if (Opponent.Dodge > Combat_Roll()) {
            MonsterDodge = true;
        }
        
        if (Hero.Crit > Combat_Roll()){
            PlayerCrit = true;
        }
        
        if (PlayerHit && !MonsterDodge && !PlayerCrit){
            if (1 > Damage){Damage = 1;}
            Text_Interface("You hit "+Opponent.Name+" for "+Damage+" Damage! \n", false);
            if (Hero.Abilities != null){ 
                for (Effects Ability : Hero.Abilities) {
                    if(Ability.Atkability){Damage = Ability.Combat(Hero, Opponent, Damage, false);}
                }
            }
            if (Opponent.Abilities != null){ 
                for (Effects Ability : Opponent.Abilities) {
                    if(!Ability.Atkability){Damage = Ability.Combat(Hero, Opponent, Damage, true);}
                }
            }
            Opponent.HP -= Damage;
        } 
        else if ((PlayerHit || PlayerCrit) && MonsterDodge){
            Text_Interface(Opponent.Name+ " Dodged your attack! \n", false);
        } 
        else if (PlayerCrit && !MonsterDodge){
            if (1 > Damage){Damage = 1;}
            Damage = Damage*2;
            Text_Interface("You CRITTED "+Opponent.Name+" for "+Damage+" Damage! \n", false);
            if (Hero.Abilities != null){ 
                for (Effects Ability : Hero.Abilities) {
                    if(Ability.Atkability){ Damage = Ability.Combat(Hero, Opponent, Damage, false);}
                }
            }
            if (Opponent.Abilities != null){ 
                for (Effects Ability : Opponent.Abilities) {
                    if(!Ability.Atkability){Damage = Ability.Combat(Hero, Opponent, Damage, true);}
                }
            }
            Opponent.HP -= Damage;
        }
        else {
           Text_Interface("Your attack misses " + Opponent.Name + "!\n", false);
           Damage = 0;
           if (Hero.Abilities != null){ 
                for (Effects Ability : Hero.Abilities) {
                    if(Ability.Atkability){ Damage = Ability.Combat(Hero, Opponent, Damage, false);}
                }
            }
            if (Opponent.Abilities != null){ 
                for (Effects Ability : Opponent.Abilities) {
                    if(!Ability.Atkability){Damage = Ability.Combat(Hero, Opponent, Damage, true);}
                }
            }
            Opponent.HP -= Damage;
        }
    }
    
    public void Monster_combat(){
        boolean MonsterHit = false;
        boolean PlayerDodge = false;
        boolean MonsterCrit = false;
        int Damage = Opponent.Atk - Hero.Def;
        
        if (Hero.Dodge > Combat_Roll()){
            PlayerDodge = true;
        }
        
        if (Opponent.ToHit > Combat_Roll()){
            MonsterHit = true;
        }
        
        if (Opponent.Crit > Combat_Roll()){
            MonsterCrit = true;
        }
        
        if (MonsterHit && !PlayerDodge && !MonsterCrit){
            if (1 > Damage){Damage = 1;}
            Text_Interface(Opponent.Name + " hits you for "+ Damage+ " Damage! \n", false);
            if (Opponent.Abilities != null){
                for (Effects Ability : Opponent.Abilities) {
                    if(Ability.Atkability){Damage = Ability.Combat(Opponent, Hero, Damage, true);}
                }
            }
            if (Hero.Abilities != null){ 
                for (Effects Ability : Hero.Abilities) {
                    if(!Ability.Atkability){Damage = Ability.Combat(Opponent, Hero, Damage, false);}
                }
            }
            Hero.HP -= Damage;
        }
        else if ((MonsterHit || MonsterCrit) && PlayerDodge){
            Text_Interface("You dodged " +Opponent.Name+ "'s attack! \n", false);
        }
        else if (MonsterCrit && !PlayerDodge){
            if (1 > Damage){Damage = 1;}
            Damage = Damage*2;
            Text_Interface(Opponent.Name + " CRITS you for "+ Damage+ " Damage! \n", false);
            if (Opponent.Abilities != null){
                for (Effects Ability : Opponent.Abilities) {
                    if(Ability.Atkability){Damage = Ability.Combat(Opponent, Hero, Damage, true);}
                }
            }
            if (Hero.Abilities != null){
                for (Effects Ability : Hero.Abilities) {
                    if(!Ability.Atkability){Damage = Ability.Combat(Opponent, Hero, Damage, false);}
                }
            }
            Hero.HP -= Damage;
        }
        else{
            Damage = 0;
            Text_Interface(Opponent.Name + " misses you! \n", false);
            if (Opponent.Abilities != null){
                for (Effects Ability : Opponent.Abilities) {
                    if(Ability.Atkability){Damage = Ability.Combat(Opponent, Hero, Damage, true);}
                }
            }
            if (Hero.Abilities != null){
                for (Effects Ability : Hero.Abilities) {
                    if(!Ability.Atkability){Damage = Ability.Combat(Opponent, Hero, Damage, false);}
                }
            }
            Hero.HP -= Damage;
        }
        Text_Interface("\n", false);
    }
    
    public void Player_Death(){
        Text_Interface("", true);
        SimpleRogueGui.AttackButton.setEnabled(false);
        SimpleRogueGui.ExamineButton.setEnabled(false);
        SimpleRogueGui.RetreatButton.setEnabled(false);
        SimpleRogueGui.OnLoss.setVisible(true);
    }
    
    public void Monster_Death(){
        Text_Interface("", true);
        Buffer_Recall();
        Text_Interface("\n You killed "+Opponent.Name +"!\n", false);
            Current_Floor++;
            if (Current_Floor <= 99){
                Kill_Reward();
                Text_Interface("You have reached \n (--- Floor "+Current_Floor+" ---)\n", false);
                if(Current_Floor > Maximum_Reached){
                    Maximum_Reached = Current_Floor;
                    Hero.HP += 20;
                    Text_Interface("and have gained 20 HP \n \n", false);
                }
                Monster_Spawn();
            }
            else if (Current_Floor == 100){
                Kill_Reward();
                Text_Interface("\n (---You have reached floor "+Current_Floor+" ---)\n", false);
                if(Current_Floor > Maximum_Reached){
                    Maximum_Reached = Current_Floor;
                    Hero.HP += 20;
                    Text_Interface("and have gained 20 HP \n \n", false);
                }
                Opponent = new UniqueMonsters("Final");
                Text_Interface("You Encounter "+Opponent.Name+"\n", false);
            }
            else {SimpleRogueGui.OnWin.setVisible(true);}
    }
    
    public void Monster_Spawn(){
        Text_Interface("You encounter new opponents on floor "+Current_Floor+", choose who you will face. \n", false);
        Monster_stats Op1;
        Monster_stats Op2;
        Monster_stats Op3;
        if (Combat_Roll() < 98){Op1 = new Monster_stats();}
        else { Op1 = new UniqueMonsters("Random");}
        if (Combat_Roll() < 98){Op2 = new Monster_stats();}
        else { Op2 = new UniqueMonsters("Random");}
        if (Combat_Roll() < 98){Op3 = new Monster_stats();}
        else { Op3 = new UniqueMonsters("Random");}
        Text_Interface(Op1.Name+" \n Type: "+Op1.Monster_Type+". \n", false);
        Text_Interface(Op2.Name+" \n Type: "+Op2.Monster_Type+". \n", false);
        Text_Interface(Op3.Name+" \n Type: "+Op3.Monster_Type+". \n", false);
        SimpleRogueGui.Monster_Choice(Op1, Op2, Op3);
        Private_Current = Current_Floor;
    }
    
    public void Kill_Reward(){
        if (null != Opponent.Death_Reward)switch (Opponent.Death_Reward) {
            case "Dodge":
                if (Hero.Dodge < 50){
                    Hero.Dodge++;
                    Text_Interface("You have gained 1 Dodge\n", false);
                    break;
                }
            case "Def":
                Hero.Def += 3;
                Text_Interface("You have gained 3 Def\n", false);
                break;
            case "Crit":
                if (Hero.Crit < 75){
                    Hero.Crit++;
                    Text_Interface("You have gained 1 Crit\n");
                    break;
                }
            case "ToHit":
                if (Hero.ToHit < 100){
                    Hero.ToHit += 2;
                    if (Hero.ToHit > 100){Hero.ToHit = 100;}
                    Text_Interface("You have gained 2 ToHit\n");
                    break;
                }
            case "Atk":
                Hero.Atk += 3;
                Text_Interface("You have gained 3 Atk\n");
                break;
        }
        Random randstat = new Random();
        switch (randstat.nextInt(5)) {
            case 0:
                if (Hero.Dodge < 50){
                    Hero.Dodge++;
                    Text_Interface("You have gained 1 Dodge\n");
                    break;
                }
            case 1:
                Hero.Def += 2;
                Text_Interface("You have gained 2 Def\n");
                break;
            case 2:
                if (Hero.Crit < 75){
                    Hero.Crit++;
                    Text_Interface("You have gained 1 Crit\n");
                    break;
                }
            case 3:
                if (Hero.ToHit < 100){
                    Hero.ToHit ++;
                    Text_Interface("You have gained 1 ToHit\n");
                    break;
                }
            case 4:
                Hero.Atk += 2;
                Text_Interface("You have gained 2 Atk\n");
                break;
        }
        if  (Opponent.Abilities.size() > 0){
            Random ftyfty = new Random();
            Effects hero_ability;
            for (Effects ability : Opponent.Abilities) {
                if(examined){
                    if (!Hero.Abilities.contains(ability)){
                        examined = false;
                        Hero.New_Ability(ability.Name, Hero);
                    }
                    else if (Hero.Abilities.contains(ability)){
                        hero_ability = Hero.Abilities.get(Hero.Abilities.indexOf(ability));
                        if(hero_ability.Level < 10){
                            Hero.New_Ability(ability.Name, Hero);
                            examined = false;
                        }
                    }
                }
                else if (ftyfty.nextBoolean()){
                    Hero.New_Ability(ability.Name, Hero);
                }
            }
            
        }
    }
    
    public void New_Opponent(){
        Opponent = new Monster_stats();
    }
    
    private void Story_Time(){
        if (Maximum_Reached == StoryLevel && Current_Floor == StoryLevel ){
            if (Current_Floor == 90 || Current_Floor == 1 ){
            StoryLevel += 9;
            }
            else {StoryLevel += 10;}
            String story = storybook.Story_Section(Bookmark);
            Bookmark ++;
            Text_Interface(story, true);
            SimpleRogueGui.ExamineButton.setVisible(false);
            SimpleRogueGui.AttackButton.setVisible(false);
            SimpleRogueGui.RetreatButton.setVisible(false);
            SimpleRogueGui.ContinueButton.setVisible(true);
        }
        else if (Current_Floor >= 1){
            Monster_Death();
        }
        else {
            Text_Interface("Something done got fucked up.", false);
        }
    }
    
    public static void Text_Interface(String message, Boolean clear){
        if (clear){
            SimpleRogueGui.MessageBox.selectAll();
        }
        if (buffer == null){
            buffer = new ArrayList<>();
            buffer.add(message);
        }
        else{
            buffer.add(message);
        }
            
        SimpleRogueGui.MessageBox.replaceSelection(message+"\n");
    }
    
    public static void Text_Interface(String message){
        Text_Interface(message, false);
    }
    
    public static void Buffer_Recall(){
        String messages = "";
        for(String msg : buffer){
            messages= messages + msg;
        }
        Text_Interface(messages);
    }

}

class Effects implements java.io.Serializable {
    String Name;
    String message;
    String altmessage;
    String DmgType;
    int Level;
    int DmgLvl;
    Random dmgrand = new Random();
    int Dmgmod;
    Boolean Atkability = true;
    
    public Effects(){
    }
    
    public int Active_chance(){
        Random activrand = new Random();
        int retval = activrand.nextInt(99)+1;
        return retval;
    }
    
    public void Effect_damage(){
        Random randdmg = new Random();
        switch (DmgLvl){
            case 1: Dmgmod = 1;
                break;
            case 2: Dmgmod = randdmg.nextInt(4)+1;
                break;
            case 3: Dmgmod = randdmg.nextInt(6)+1;
                break;
            case 4: Dmgmod = randdmg.nextInt(8)+1;
                break;
            case 5: Dmgmod = randdmg.nextInt(10)+1;
        }
    }
    
    public Boolean Activation(){
        Boolean Activate = false;
        if (Active_chance() <= Level*10){
            Activate = true;
        }
        return Activate;
    }
    
    public void Increase_Level(){
        if (Level <= 9){ Level++;}
        if (Level == 3|| Level == 5 ||Level == 7 || Level == 9 ){ DmgLvl++;}
    }
    
    public int Combat(Entity_Stats attacker, Entity_Stats defender, int Damage, Boolean alt){
        Effect_damage();
        if (Activation()){
            Damage += Dmgmod;
            message = "Your "+Name+" ability deals "+Dmgmod+" "+DmgType+" damage! \n";
            altmessage = "You have taken "+Dmgmod+" "+DmgType+" damage! \n";
            if (alt){
            Encounter.Text_Interface(altmessage);}
            else{
            Encounter.Text_Interface(message);   
            }
        }
        return Damage;
    }
}

class Fire extends Effects{
    public Fire(){
        Name = "Fire";
        DmgType = "Fire";
        Level = 1;
        DmgLvl = 1;

    }
}

class Lightning extends Effects{
    public Lightning(){
        Name = "Lightning";
        DmgType = "Lightning";
        Level = 1;
        DmgLvl = 1;
    }
}

class Cold extends Effects{
    public Cold(){
        Name = "Cold";
        DmgType = "Cold";
        Level = 1;
        DmgLvl = 1;
    }
}

class Acid extends Effects{
    public Acid(){
        Name = "Acid";
        DmgType = "Acid";
        Level = 1;
        DmgLvl = 1;
    }
}

class Shield extends Effects{
    public Shield(){
        Name = "Shield";
        Level = 1;
        DmgLvl = 1;
        Atkability = false;
    }
    
    @Override
    public int Combat(Entity_Stats attacker, Entity_Stats defender, int Damage, Boolean alt){
        if (1 > Damage){Damage = 1;}
        int dmgblocked;
        if(Activation()){
            Effect_damage();
            if (Dmgmod > Damage){
                dmgblocked = Damage;
            }
            else {
                dmgblocked = Dmgmod;
            }
            
            Damage -= dmgblocked;
            message = "Your Shield blocked "+dmgblocked+" Damage! \n";
            altmessage = "Opponent's Shield blocked "+dmgblocked+" Damage! \n";
            
            if (alt){
            Encounter.Text_Interface(altmessage);}
            else{
            Encounter.Text_Interface(message);   
            }
        }
        return Damage;
    }
}

class Counter extends Effects{
    public Counter(){
        Name = "Counter";
        Level = 1;
        Atkability = false;
    }
    
    @Override
    public Boolean Activation(){
        Boolean Activate = false;
        if (Active_chance() <= Level*5){
            Activate = true;
        }
        return Activate;
    }
    
    @Override
    public int Combat(Entity_Stats attacker, Entity_Stats defender, int Damage, Boolean alt){
        if (1 > Damage){Damage = 1;}
        if (Activation()){
            attacker.HP -= Damage;
            message = "You Countered for "+Damage+" Damage! \n";
            altmessage = "Opponent Countered for "+Damage+" Damage! \n";
            
            if (alt){
            Encounter.Text_Interface(altmessage);}
            else{
            Encounter.Text_Interface(message);   
            }
        }
        return Damage;
    }
}

class Echo extends Effects{
    public Echo(){
        Name = "Echo";
        Level = 1;
        
    }
    
    @Override
    public int Combat(Entity_Stats attacker, Entity_Stats defender, int Damage, Boolean alt){
        if (1 > Damage){Damage = 1;}
        if (Activation()){
            attacker.HP -= Damage;
            message = "Your Echo deals "+Damage+" Damage to "+defender.Name+" and yourself! \n";
            altmessage = attacker.Name+"'s Echo deals "+Damage+" Damage to you and itself! \n";
            Damage = Damage*2;
            
            if (alt){
            Encounter.Text_Interface(altmessage);}
            else{
            Encounter.Text_Interface(message);
            }
        }
        return Damage;
    }
}

class Wounding extends Effects{
    public Wounding(){
        Name = "Wounding";
        Level = 1;
    }
    
    @Override
    public Boolean Activation(){
        Boolean Activate = false;
        if (Active_chance() <= Level*5){
            Activate = true;
        }
        return Activate;
    }
    
    
    @Override
    public int Combat(Entity_Stats attacker, Entity_Stats defender, int Damage, Boolean alt){
        Effect_damage();
        if (Activation()){
            defender.Def -= Dmgmod;
            message = "You wounded "+defender.Name+", lowering their Defense by "+Dmgmod+"! \n";
            altmessage = attacker.Name+" wounded you, draining "+Dmgmod+" Defense! \n";
            
            if (alt){
            Encounter.Text_Interface(altmessage);}
            else{
            Encounter.Text_Interface(message);   
            }
        }
        return Damage;
    }
}

class Blinding extends Effects{
    
    public Blinding(){
        Name = "Blinding";
        Level = 1;
    }
    
    @Override
    public Boolean Activation(){
        Boolean Activate = false;
        if (Active_chance() <= Level*5){
            Activate = true;
        }
        return Activate;
    }
    
    @Override
    public int Combat(Entity_Stats attacker, Entity_Stats defender, int Damage, Boolean alt){
        Effect_damage();
        if (Activation()){
            defender.ToHit -= Dmgmod;
            message = "You blinded "+defender.Name+", lowering their Accuracy by "+Dmgmod+"! \n";
            altmessage = attacker.Name+" dimmed your vision, lowering your Accuracy by "+Dmgmod+"! \n";
            
            if (alt){
            Encounter.Text_Interface(altmessage);}
            else{
            Encounter.Text_Interface(message);   
            }
        }
        return Damage;
    }
}

class Poison extends Effects {
    boolean Poisoned;
    int Poisontick;
    
    public Poison(){
        Name = "Poison";
        Level = 1;
        Poisoned = false;
    }
    
    @Override
    public int Combat(Entity_Stats attacker, Entity_Stats defender, int Damage, Boolean alt){
        Effect_damage();
        if (Activation() && !Poisoned){
            Poisoned = true;
            Poisontick = Dmgmod;
            message = "You Poisoned "+defender.Name+"! \n";
            altmessage = attacker.Name+" Poisoned you! \n";
            
            if (alt){
            Encounter.Text_Interface(altmessage);}
            else{
            Encounter.Text_Interface(message);   
            }
        }
        else if(Poisoned && Poisontick > 0){
            Damage += Dmgmod;
            Poisontick --;
            if (Poisontick ==0){
                Poisoned = false;
            }
            message = "Your Poison deals "+Dmgmod+" damage to "+defender.Name+"! \n";
            altmessage = attacker.Name+"'s Poison deals "+Dmgmod+" damage to you! \n";
            
            if (alt){
            Encounter.Text_Interface(altmessage);}
            else{
            Encounter.Text_Interface(message);   
            }
        }
        
        if (Damage >= defender.HP)
            Poisoned = false;
        return Damage;
    }
}