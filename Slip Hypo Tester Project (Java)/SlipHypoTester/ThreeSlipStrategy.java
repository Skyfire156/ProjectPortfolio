/*
 * This is Nathan Hillman's Licence Header.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sliphypotester;

import java.util.ArrayList;

/**
 *
 * @author natha_000
 */
public enum ThreeSlipStrategy {
    
    CONTINUE_IF_WORSE("Discard second if smaller than first", true),
    INFORMED_STRATEGY("Keep second if larger than first, otherwise discard it", true),
    KEEP_ORIGINAL("Keep first slip chosen", false),
    STOP_IF_BETTER("Keep second if larger than first", true),
    SWITCH_TO_REVEALED("Keep second slip chosen", false),
    SWITCH_TO_UNKNOWN("Choose last slip", false);
    
    
    private final String description;
    private final boolean informed;
    ThreeSlipStrategy(String desc, boolean inf){
        this.description = desc;
        this.informed = inf;
    }

    public String description() {
        return description;
    }
    
    boolean informed(){
        return informed;
    }
    
    
    
    
    
}
