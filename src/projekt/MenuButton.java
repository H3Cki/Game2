/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Klasa przycisku powrotu do menu
 * @author Radek Urbanik
 */
public class MenuButton extends Button{
    /**
    * Tworzenie przycisku powrotu do menu
    * 
    */
    public MenuButton(){  
        getStylesheets().add(getClass().getResource("yellow.css").toExternalForm());
        setText("<");
        setPrefHeight(30);
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new Menu();
                } catch (IOException ex) {
                    Logger.getLogger(MenuButton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
