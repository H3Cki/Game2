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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Klasa przycisku kontrolującego przejscie do następnego poziomu lub rozpoczęcie gry od nowa
 * @author Radek Urbanik
 */
public class NextButton extends Button{
    
    boolean freshStart;
    /**
    * Stworzenie przycisku, ustawienie domyślnej widoczności na false
    * 
    */
    public NextButton(){  
        setVisible(false);
    }
    
    
    /**
    * Ustawienie przycisku na tryb wyświetlania pozostałej ilości przycisków do kliknięcia w sekwencji, wyłączenie akcji przycisku, ewentualnie może wracać do menu.
    * 
    */
    public void setClicks(){
        setVisible(true);
        setOnAction(null);
        getStylesheets().clear();
        this.getStylesheets().add(getClass().getResource("clickButton.css").toExternalForm());
        this.setText((Projekt.gameInstance.sequence.size()-Projekt.gameInstance.playerSequence.size())+" LEFT");
        freshStart=true;
    }
    
    /**
    * Ustawienie przycisku na styl po przegranej, restartuje gre
    * 
    */
    public void setFail(){
        getStylesheets().clear();
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projekt.startGame(freshStart);
                setVisible(false);
            }
        });

        this.getStylesheets().add(getClass().getResource("failButton.css").toExternalForm());
        this.setText("TRY AGAIN");
        freshStart=true;
        
    }
    /**
    * Ustawienie przycisku na styl po wygranym poziomie, przejscie do nastepnego poziomu
    * 
    */
    public void setNext(){
        getStylesheets().clear();
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projekt.startGame(freshStart);
                setVisible(false);
            }
        });

        this.getStylesheets().add(getClass().getResource("nextButton.css").toExternalForm());
        this.setText("NEXT");
        freshStart=false;
        
    }
}
