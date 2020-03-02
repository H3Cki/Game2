/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;



/**
* Kontroler suwaka trudnosci mechanicznej.
 *
 * @author Radek Urbanik
 */
public class DSliderController implements Initializable {
    public List<Integer> values = new ArrayList<Integer>();
    
    @FXML
    Slider dslider;
    @FXML
    TextField dtext;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dslider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    
                    int v = new_val.intValue()/10;
                    if(v>=9){
                        v=9;
                       
                        dslider.setValue(100);
                    }else{
                        dslider.setValue(v*10);
                    }
                    
                    dtext.setText(String.format("Mechanical Difficulty "+ (int)v));
                    MechanicalDifficulty.set(v);
            }

      
        });
        for(int i = 1; i < 11; i++){
            values.add(i);
        }
        if(MechanicalDifficulty.difficulty==9)
        {
            dslider.setValue(100);
        }else{
            dslider.setValue(MechanicalDifficulty.difficulty*10);
        }
        
        dtext.setDisable(true);
        dtext.setAlignment(Pos.CENTER);
        dtext.setText("Mechanical Difficulty "+MechanicalDifficulty.difficulty);
       
    }
    
    
}
