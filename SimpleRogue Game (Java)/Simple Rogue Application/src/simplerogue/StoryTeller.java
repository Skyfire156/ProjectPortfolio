/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplerogue;

/**
 *
 * @author natha_000
 */
public class StoryTeller implements java.io.Serializable{
    
    public  StoryTeller(){
    }
    
    public static String Story_Section(int bookmark){
        String section;
        
        section = Story1[bookmark];
        
        return section;
    }
    
    static String[] Story1 = {"I duck into the cave, sighing relief as lightning "
            + "crashes behind me.  I light a torch and look around the hole that"
            + " will be my shelter until this storm blows over.  I frown, seeing "
            + "that I wasn't the first to get this idea.  Problem is, my new "
            + "roommates don't look very friendly.  Looks like it's going to be "
            + "a long night", 
        "I keep hoping each of these monsters will be the last, but there always"
            + " seems to be another stairway leading deeper.  I can't take the "
            + "chance that something will come up while I'm sleeping, need to "
            + "keep going and clear this place out before I get any rest...",
        "I'm beginning to suspect that this isn't your garden-variety cave. "
            + "It seems that even as I move deeper, things crowd in behind me.  "
            + "Every time I think about turning back, I find more monsters on "
            + "the floors I thought I'd already cleared...",
        "I seem to be gaining power with every monster I kill.  I don't know how"
            + ", but it seems that I've even taken some of their abilities for "
            + "myself.  I don't even feel tired anymore...", 
        "There is definitely some power in the bowels of this place, there has "
            + "to be something bringing all these creatures here.  I should find"
            + "it and put an end to it.  I don't want to think of the destructio"
            + "n that could be caused if these things were to get out of this"
            + "cave...",
        "The walls of the cave are looking less and less natural as I keep going"
            + ".  I think whatever's set up shop here is turning the place into"
            + "its own personal fortress.  How has this gone unnoticed for so"
            + "long?",
        "I've come across several corpses now, I think I know why no one knows "
            + "this place exists.  I can't bring myself to leave, knowing what "
            + "evil lurks here.  It must be stopped...",
        "My torch has long since guttered out, but I keep moving by the light "
            + "I seem to be emitting as I grow in power.  I've seen the edges of"
            + "things lurking just beyond my light, horrid creatures that "
            + "terrify me, even as powerful as I've become...",
        "My latest opponent screamed out a name as we fought, a name that set my"
            + "hair on end and made my teeth ache.  I think I know now what I'm "
            + "up against...",
        "I'm close now, moving through the halls of a palace.  The caves above "
            + "seem a distant dream as I get closer and closer to the being that"
            + "rules this place...",
        "I see its throne room now, the desecrated altar from which it summons "
            + "its minions.  I go now to end this "
            + "threat.  My the Gods help me, I hear it, laughing in my mind..."
    
    };
}
