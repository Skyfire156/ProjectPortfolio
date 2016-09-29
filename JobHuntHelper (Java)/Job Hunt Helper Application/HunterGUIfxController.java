/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.text.AttributedString;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nate
 */
public class HunterGUIfxController implements Initializable {

    
    @FXML private TextArea inputText;
    @FXML private TextArea outputText;
    @FXML private Button toClipboard;
    private JobHuntHelper hunter;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hunter = new JobHuntHelper();
        inputText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                String newOut = hunter.wordStrip(newValue);
                newOut = hunter.spaceStrip(newOut);
                outputText.setText(newOut);
            }
        });
        
        toClipboard.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //String out = outputText.getText();
                outputText.selectAll();
                outputText.copy();
                //hunter.clipboardCopy(out);
            }
        
        });
    }
    
    private void updateOutText(String text){
        outputText.setText(text);
    }
    
}
