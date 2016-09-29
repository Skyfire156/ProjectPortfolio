
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;

  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Nate
 */
public class JobHuntHelper {

    private ArrayList<String> UselessWords;
    
    
    public JobHuntHelper(){
        FileDriver words = new FileDriver();
        UselessWords = new ArrayList();
        try {
            words.openFile("UselessWords");
        } catch (FileNotFoundException ex) {
            try {
                words.openOutput("Useless");
            } catch (IOException ex1) {
                Logger.getLogger(JobHuntHelper.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(JobHuntHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(words.fileReady()){
            UselessWords.add(words.getNextLine());
        }
        
        
    }
    
    public String wordStrip(String input){
        for (String word : UselessWords) {
            word = " "+word+" ";
            if(input.toLowerCase().contains(word)){
                ArrayList<String> in = new ArrayList();
                in.addAll(Arrays.asList(input.split(word)));
                input = in.stream().collect(Collectors.joining(" "));
            }
        }
        return input;
    }
    
    public String spaceStrip(String input){
        ArrayList<String> in = new ArrayList();
        ArrayList<String> out = new ArrayList();
        in.addAll(Arrays.asList(input.split("\n")));
        for(String s: in){
            if(!s.isEmpty() && !s.trim().isEmpty()){
                out.add(s);
            }
        }
        String output = out.stream().collect(Collectors.joining("\n"));
        return output;
    }
    
    public void clipboardCopy(String input){
        StringSelection select = new StringSelection(input);
        Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
        board.setContents(select, null);
    }
    
}
