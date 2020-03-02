/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Klasa gry właściwej
 * @author Radek Urbanik
 */
public class Game{
    public static int highScore = 0;
    public int currentScore;
    public List<GameButton> buttons = new ArrayList<GameButton>();
    public List<GameButton> sequence = new ArrayList<GameButton>();
    public List<GameButton> playerSequence = new ArrayList<GameButton>();
    NextButton nextButton = new NextButton();
    MenuButton menuButton = new MenuButton();
    public int clicks;
    public Label gameHighScore = new Label(); 
    public HBox opts = new HBox();
    public Lvl level = new Lvl();
    
    
    /**
    * Obliczenie wyniku
    * 
    */
    void calculateHS() {
        int base_score = 10;
        double odrating=1,mdrating=1, disappearrating = 1,score;
        if(OpticalDifficulty.difficulty=="Weak"){
            odrating = 0;
        }else if(OpticalDifficulty.difficulty=="Normal"){
            odrating = 1;
        }else if(OpticalDifficulty.difficulty=="Ninja"){
            odrating = 5;
        }
        if(MechanicalDifficulty.disappear){
            disappearrating = 5;
        }else{
            disappearrating = 0;
        }
        mdrating = MechanicalDifficulty.difficulty*2;

        score = (Projekt.gameInstance.sequence.size()*Projekt.gameInstance.sequence.size()+base_score)*(odrating+mdrating+disappearrating);
        
        currentScore = currentScore + (int)score;
        if(Game.highScore<currentScore){
            Game.highScore=currentScore;
            
            File f = new File("src/data/highscore.txt");
            
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PrintWriter writer;
            try {
                
                writer = new PrintWriter(f.getAbsolutePath(), "UTF-8");
                writer.println(""+Game.highScore);
                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    
    }
    
    /**
    * Reset wyniku
    * 
    */
    static void resetHs(){
        File f = new File("src/data/highscore.txt");
            
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PrintWriter writer;
            try {
                
                writer = new PrintWriter(f.getAbsolutePath(), "UTF-8");
                highScore=0;
                writer.println(""+0);
                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
            }
        Projekt.gameInstance.tick();
    }
    /**
    * Załadowanie wyniku z pliku
    * 
    */
    static void loadHs(){
        File f = new File("src/data/highscore.txt");
        String fp = f.getAbsolutePath();
        if(f.exists()) { 
            Scanner sc;
            try {
                sc = new Scanner(f);
                String score = sc.next();
                highScore = Integer.parseInt(score); 
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else
        {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            }
    }
    /**
    * Stworzenie sekwencji, ktora gracz bedzie musial odtworzyc, długość zależna od obecnego poziomu 
    * 
    */
    void create_sequence(){
        sequence = new ArrayList<GameButton>();
        playerSequence = new ArrayList<GameButton>();
        int randomid;
        for(int i = 0; i < level.slen; i++)
        {
            randomid = ThreadLocalRandom.current().nextInt(0, buttons.size());
            while(sequence.contains(buttons.get(randomid))){
                randomid = ThreadLocalRandom.current().nextInt(0, buttons.size());
            }
            sequence.add(buttons.get(randomid));
        }
        
    }
    

    /**
    * Wizualne odtworzenie wygenerowanej sekwencji, parametry zależne od optycznego poziomu trudności
    * 
    */
    void play_sequence(){
        
        KeyFrame k;
        Timeline timeline = new Timeline();
        int i = 1;

        for(GameButton btn: sequence){
            
            
            
            String str = String.format ("%d", i);
            
            if(MechanicalDifficulty.disappear && i > 0){
                final int idx = i-1;
                
                k = new KeyFrame(Duration.millis(MechanicalDifficulty.base_delay*MechanicalDifficulty.multiplier*(i+1)), e -> sequence.get(idx).unpress());
                timeline.getKeyFrames().add(k);
                
            }
            
      
            
            k = new KeyFrame(Duration.millis(MechanicalDifficulty.base_delay*MechanicalDifficulty.multiplier*i), e -> btn.zeroPress(str));
            timeline.getKeyFrames().add(k);
            i++;
        }
        if(!MechanicalDifficulty.disappear){
            for(GameButton btn: sequence){

                k = new KeyFrame(Duration.millis(MechanicalDifficulty.base_delay*MechanicalDifficulty.multiplier*i), e -> btn.unpress());
                timeline.getKeyFrames().add(k);
            }
        }
        for(GameButton btn: buttons){
            k = new KeyFrame(Duration.millis(MechanicalDifficulty.base_delay*MechanicalDifficulty.multiplier*i), e -> btn.addListener());
            timeline.getKeyFrames().add(k);
        }
        k = new KeyFrame(Duration.millis(MechanicalDifficulty.base_delay*MechanicalDifficulty.multiplier*i), e -> nextButton.setClicks());
        timeline.getKeyFrames().add(k);
        
        Platform.runLater(timeline::play);

    }
    /**
    * Zwiększenie zmiennej clicks o jeden
    * 
    */
    void click(){
        clicks++;
    }
    /**
    * Wygenerowanie siatki przycisków gry
    * 
    */
    GridPane create_grid(){
        GridPane grid = new GridPane();
        buttons = new ArrayList<GameButton>();
        int k = 0;
        for(int i = 0; i < level.brows; i++)
        {
            for(int j = 0; j < level.brows; j++)
            {
                GameButton btn = new GameButton(level.brows);
                btn.id = k;
                buttons.add(btn);
                grid.add(btn, j, i);
                k++;
            }
        }
        return grid;
    }
    
    
    
    public void run() {
        Projekt.gameInstance.level.levelUp();
        setup();
        create_sequence();
        play_sequence();
        
        tick();
        
    }

    public void tick(){
        
        gameHighScore.setText("HIGH SCORE: " + Game.highScore+"\nSCORE: "+currentScore+" (Level "+Projekt.gameInstance.level.level+")");
        Projekt.stage.show();
    }
    
    void setup(){
        clicks=0;
        
        VBox vbox = new VBox(10);
        gameHighScore.setFont(Font.font ("Segoe UI", 25));
        StackPane root = new StackPane(); 
        Scene scene = new Scene(root, Projekt.WIDTH, Projekt.HEIGHT);
        Projekt.stage.setTitle(Projekt.name+" - GRA");
        Projekt.stage.setScene(scene);
        
        //nextButton.setVisible(false);
        
        
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        Parent od,d;
        
        try {
            od = FXMLLoader.load(getClass().getResource("ODPicker.fxml"));
            d = FXMLLoader.load(getClass().getResource("DSlider.fxml"));
            opts = new HBox(od,d);
            opts.setAlignment(Pos.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        HBox hBox = new HBox(menuButton,nextButton, region1,gameHighScore);
        
        opts.setDisable(true);
        vbox.getChildren().addAll(hBox,create_grid(),opts);
        vbox.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(vbox);
    }

    void Game(){
    }

    
}
