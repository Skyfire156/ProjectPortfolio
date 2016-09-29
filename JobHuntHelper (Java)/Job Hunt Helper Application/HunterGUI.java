/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Nate
 */
public class HunterGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        AnchorPane page = new AnchorPane();
        try {
            page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("HunterGUIfx.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(HunterGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Job Hunter Helper");
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
