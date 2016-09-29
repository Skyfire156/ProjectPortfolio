




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * This is Nathan Hillman's Licence Header.
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natha_000
 */
public class FileDriver {
    private FileReader file;
    private BufferedReader br;

    public FileDriver(String pathname) throws FileNotFoundException, IOException {
        file = new FileReader(pathname);
        br = new BufferedReader(file);
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
    
    public boolean fileReady(){
        boolean retval = false;
        try {
            retval = br.ready();
        } catch (IOException ex) {
            Logger.getLogger(FileDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retval;
    }
}
