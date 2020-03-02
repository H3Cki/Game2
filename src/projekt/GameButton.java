/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Klasa przycisku siatki gry właściwej
 * @author Radek Urbanik
 */
public class GameButton extends Button{
    public int id;
    
    /**
    * Tworzenie przycisku siatki
    * @param divides ilosc podziałów siatki w celu obliczenia rozmiaru przycisku
    */
    public GameButton(int divides){
        setMinWidth((int)Projekt.WIDTH/divides);
        setMinHeight((int)Projekt.WIDTH/divides);
        setDefault();
    }
    
    
    /**
    *
    * Ustawienie stylu na domyślny
    */
    public void setDefault(){
        getStylesheets().clear();
        getStylesheets().add(getClass().getResource(OpticalDifficulty.button).toExternalForm());
    }

     /**
    * Dodanie akcji do przycisku
    * 
    */
    public void addListener(){
        setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    addToSequence();
                    short correct = checkSequenceCorrect();
                    switch (correct) {
                        case 0:
                            wrong();
                            Projekt.gameInstance.sequence.get(Projekt.gameInstance.playerSequence.size()-1).correct();
                            removeListeners();
                            Projekt.gameInstance.nextButton.setFail();
                            Projekt.gameInstance.nextButton.setVisible(true);
                            //Projekt.gameInstance.opts.setDisable(false);
                            break;
                        case 1:
                            press();
                            Projekt.gameInstance.nextButton.setClicks();
                            break;
                        case 2:
                            Projekt.gameInstance.nextButton.setNext();
                            Projekt.gameInstance.nextButton.setVisible(true);
                            
                            correct();
                            if(OpticalDifficulty.numeration){
                                setText(""+Projekt.gameInstance.clicks);
                            }
                            correctAll();
                            removeListeners();
                            //Projekt.gameInstance.opts.setDisable(false);
                            
                    }
                    Projekt.gameInstance.tick();
                    removeListener();
                
                }
            });
    }
    /**
    * Usunięcie akcji przycisku
    * 
    */
    public void removeListener(){
        setOnAction(null);
    }
    /**
    * Dodanie akcji wszystkich przycisków
    * 
    */
    public void removeListeners(){
        for(GameButton btn:Projekt.gameInstance.buttons){
            btn.setOnAction(null);
        }
    }
    /**
    * Dodanie przycisku do sekwencji tworzonej przez gracza
    * 
    */
    public void addToSequence(){
        Projekt.gameInstance.playerSequence.add(this);
    }
    
    
    /**
    * Sprawdzenie czy sekwencja gracza zgadza się z poprawną sekwencją
    * @return zwraca stan poprawnosci sekwencji
    * //0-niepoprawn
        //1-poprawny
        //2-poprawny,zakonczony
    */
    public short checkSequenceCorrect(){
        //0-incorrect
        //1-correct
        //2-correct,completed
        int currentSize = Projekt.gameInstance.playerSequence.size();
        int totalSize = Projekt.gameInstance.sequence.size();
        if(currentSize == totalSize){
            if(Projekt.gameInstance.playerSequence.equals(Projekt.gameInstance.sequence)){
                return 2;
            }return 0;
        }
        
        for(int i = 0; i < currentSize; i++){
            if(Projekt.gameInstance.playerSequence.get(i)!=Projekt.gameInstance.sequence.get(i)){
                return 0;
            }
        }
        return 1;
    }
    
    /**
    * Nacisniecie przycisku siatki bez wywoływania efektu, tylko wizualne
    * @param t tekst do umieszczenia na przycisku
    */
    public void zeroPress(String t)
    {
        if(OpticalDifficulty.numeration){
            setStyle(String.format("-fx-font-size: %dpx;", (int)(0.45 * this.getHeight())));
            setText(t);
        }
        getStylesheets().add(getClass().getResource("buttonpressed.css").toExternalForm());
        
    }

    /**
    * Nacisniecie przycisku siatki przez gracza
    * 
    */
    public void press()
    {
        Projekt.gameInstance.click();
        if(OpticalDifficulty.numeration){
            setText(""+Projekt.gameInstance.clicks);
            setStyle(String.format("-fx-font-size: %dpx;", (int)(0.45 * this.getHeight())));
        }
        setDefault();
        getStylesheets().add(getClass().getResource("buttonpressed.css").toExternalForm());
    }

    /**
    * Odkliknięcie przycisku
    * 
    */
    public void unpress()
    {
        setText("");
        setDefault();
        getStylesheets().add(getClass().getResource(OpticalDifficulty.button).toExternalForm());
        Projekt.gameInstance.tick();
    }

    /**
    * Ustawienie stylu przycisku na niepoprawny
    * 
    */
    public void wrong()
    {
        getStylesheets().add(getClass().getResource("buttonwrongcss.css").toExternalForm());
    }
    /**
    * Ustawienie stylu przycisku na poprawny
    * 
    */
    public void correct()
    {
        Projekt.gameInstance.calculateHS();
        Projekt.gameInstance.click();
        if(OpticalDifficulty.numeration){
            setText(""+Projekt.gameInstance.clicks);
        }

        getStylesheets().add(getClass().getResource("buttoncorrectcss.css").toExternalForm());
    }
    /**
    * Ustawienie stylu wszystkich przycisków na poprawny
    * 
    */
    public void correctAll()
    {
        for(GameButton btn:Projekt.gameInstance.sequence){
            btn.getStylesheets().add(getClass().getResource("buttoncorrectcss.css").toExternalForm());
        }

    }
}
