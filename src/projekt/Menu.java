/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Klasa menu
 * @author Radek Urbanik
 */
public class Menu {
    public static ImageView logo = new ImageView("/img/logo.png");
    public static Image blind = new Image("/img/blind.png");
    VBox vbox = new VBox(Projekt.WIDTH/10);
    Button menuPlayButton = new Button("PLAY");
    Parent od,d,hs;
    
    /**
    * 
    * Tworzenie nowej instancji menu
    * @throws IOException błąd łądowania plików potrzebnych do wyświetlenia menu
    */
    public Menu() throws IOException{
        Stage stage = Projekt.stage;
        menuPlayButton.setStyle("-fx-border-color: lightgray;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: lightgreen;-fx-text-fill: white;-fx-font-size: 40px;-fx-font-family: Arial;-fx-font-weight: bold;");
        Label menuHighScore = new Label("HIGH SCORE: " + Game.highScore); 
        menuHighScore.setFont(Font.font ("Arial", 25));
        menuHighScore.setTextFill(Color.BLACK);
        menuPlayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projekt.startGame(true);
            }
        });
        StackPane root = new StackPane();
        
        Parent od;
        
        
        hs = FXMLLoader.load(getClass().getResource("HSLABEL.fxml"));
        od = FXMLLoader.load(getClass().getResource("ODPicker.fxml"));
        d = FXMLLoader.load(getClass().getResource("DSlider.fxml"));
        HBox hBox = new HBox(od,d);
        hBox.setAlignment(Pos.CENTER);
        
        vbox.getChildren().addAll(logo,hs, menuPlayButton,hBox);
        root.getChildren().add(vbox);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, Projekt.WIDTH, Projekt.HEIGHT);
        stage.setTitle(Projekt.name+" - MENU");
        stage.setScene(scene);
        stage.show();
    }
    
}
