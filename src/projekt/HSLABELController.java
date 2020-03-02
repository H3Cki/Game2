/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * Kontroler paska rekordu.
 *  @author Radek Urbanik
 */

public class HSLABELController implements Initializable {
    @FXML
    AnchorPane pane;
    @FXML
    Label textm,text2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setPrefWidth(Projekt.WIDTH+100);
        text2.setText("HIGH SCORE\n"+Game.highScore);
        text2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Game.resetHs();
                try {
                    new Menu();
                } catch (IOException ex) {
                    Logger.getLogger(HSLABELController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }    
    
}
