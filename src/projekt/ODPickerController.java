/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
* Kontroler menu wyboru optycznej trudności gry
 *
 * @author Radek Urbanik
 */
public class ODPickerController implements Initializable {
    @FXML
    ComboBox odcombo;
    @FXML
    TextField odtext;
    @FXML
    Circle circle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(MechanicalDifficulty.disappear == true)
                {
                    circle.setFill(new ImagePattern(Menu.blind));
                }
                else
                {
                    circle.setFill(Color.TRANSPARENT);
                }
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                if(MechanicalDifficulty.disappear == true)
                {
                    MechanicalDifficulty.disappear = false;
                    circle.setFill(Color.TRANSPARENT);
                }
                else
                {
                    circle.setFill(new ImagePattern(Menu.blind));
                    MechanicalDifficulty.disappear = true;
                }
                
            }
        });
        
        
        
        
        odtext.setText("Optical Difficulty");
        odtext.setAlignment(Pos.CENTER);
        odcombo.setPromptText(OpticalDifficulty.difficulty);
        odtext.setDisable(true);
        odcombo.getItems().addAll(FXCollections.observableArrayList("Weak","Normal","Ninja"));
        odcombo.setOnAction(e -> {
            
            String value = (String) odcombo.getValue();
            if(value=="Normal"){
                OpticalDifficulty.setNormal();
            }else if(value=="Weak"){
                OpticalDifficulty.setEasy();
            }else{
                OpticalDifficulty.setHard();
            odcombo.setPromptText(value);
            }
        });
    }


}
    

