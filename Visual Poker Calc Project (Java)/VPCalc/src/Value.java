

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Nate
 */
public enum Value {
    
    Ace,
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King;
    
    public int Rank(){
        if(this.ordinal() == 0){
            return 14;
        }else{
            return this.ordinal()+1;
        }
    }
}
