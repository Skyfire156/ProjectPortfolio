/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package removekth;

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
 * @author natha_000
 */
public class RemoveKth<Item>{

    private Node head;
    private int count;
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        RemoveKth<Integer> test = new RemoveKth<>();
        FileDriver scan = null;
        try {
            scan = new FileDriver();
        } catch (IOException ex) {
            Logger.getLogger(RemoveKth.class.getName()).log(Level.SEVERE, null, ex);
        }
        do{
        String textline = null;
        for(int i = 0; i < 3; i++){
                 textline = scan.getNextLine();
                if( i == 0)
                    scan.updateResults(textline);
                if( i == 1){
                    scan.updateResults("Original List: \n"+textline);
                    String[] inputs = textline.split(",");
                     for (String input : inputs) {
                         test.insert(Integer.parseInt(input));
                     }
                }
                if(i == 2){
                    test.delete(Integer.parseInt(textline));
                }
                    
            }
        //Integer t = test.delete(-1);
        //System.out.println(t);
        scan.updateResults("New List after deleting index: "+textline);
        String returntext = "[";
        while(!test.isEmpty()){
            int t = test.delete(1);
            returntext += t+",";
        }
        scan.updateResults(returntext+"]");
        }while(scan.fileReady());
        scan.closeFiles();
    }
    public RemoveKth(){
        count = 0;
    }
    
    public boolean isEmpty(){
        return count==0;
    }
    
    public void insert(Item x){
        Node temp = new Node();
        temp.data = x;
        if(count == 0){
            head = temp;
        }else{
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = temp;
            temp.prev = current;
        }
        count++;
    }
    
    public Item delete(int k){
        Node current = head;
        if(count >= k && k>0){
            for(int i = 1; i<k; i++){
                current = current.next;
            }
            Item retval = (Item) current.data;
            if(current.next != null)
                current.next.prev = current.prev;
            if(current.prev != null)
                current.prev.next = current.next;
            if(current == head)
                head = current.next;
            count --;
            return retval;
        }else{
            return null;
        }
    }
    
    class Node<Item>{
        Item data;
        Node next;
        Node prev;
    }
}

class FileDriver{
    private FileReader file;
    private FileWriter results;
    private BufferedReader br;
    private BufferedWriter bw;
    private String resultstring;

    public FileDriver() throws FileNotFoundException, IOException {
        file = new FileReader("DeleteKthData.txt");
        br = new BufferedReader(file);
        results = new FileWriter("DeleteKthTestResults.txt");
        bw = new BufferedWriter(results);
        resultstring = br.readLine(); //gets the programmer name from the file
        resultstring += br.readLine(); // gets name of test data author(s)
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
    
    public boolean fileReady() throws IOException{
        return br.ready();
    }
}