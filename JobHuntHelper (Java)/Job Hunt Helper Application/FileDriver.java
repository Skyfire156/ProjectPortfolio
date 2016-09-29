/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nate
 */
public class FileDriver {
    private FileReader file;
    private FileWriter results;
    private BufferedReader br;
    private BufferedWriter bw;
    private String resultstring;

    public FileDriver(){
        
    }
    
    public void openFile(String filename) throws FileNotFoundException{
        file = new FileReader(filename+".txt");
        br = new BufferedReader(file);
    }
    
    public void openOutput(String filename) throws IOException{
        results = new FileWriter(filename+"-output.txt");
        bw = new BufferedWriter(results);
    }
    
    public String getNextLine(){
        String test = "";
        try {
            test = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(FileDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
    }
    
    public void updateResults(String data){
        resultstring += data+"\n";
    }
    
    public void closeFiles(){
        try {
            bw.write(resultstring);
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(FileDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean fileReady(){
        try {
            return br.ready();
        } catch (IOException ex) {
            Logger.getLogger(FileDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
