/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Główna klasa gry
 * @author Radosław Urbanik
 */
public class Projekt extends Application {
    public static String name = "GRA²";
    private static Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private static int taskBarHeight = scrnSize.height - winSize.height;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WIDTH = (int)(screenSize.getHeight()/3)*2;
    public static int HEIGHT = (int) (WIDTH + 155);
    public static Stage stage;
    public static Game gameInstance;
    
    /**
    * Rozpoczęcie programu, początkowe wyświetlenie menu 
    * @param primaryStage główna scena gry
    */
    @Override
    public void start(Stage primaryStage) throws IOException{
        
        stage = primaryStage;
        stage.getIcons().add(new Image("/img/logo.png"));
        stage.setResizable(false);
        Game.loadHs();
        try {
            new Menu();
        } catch (IOException ex) {
            Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
    * Uruchomienie gry właściwej
    * @param freshStart zmienna opisująca czy rozpoczęcie gry powinno wiązać sie z utworzeniem nowej instancji gry
    */
    public static void startGame(boolean freshStart) {
        if(freshStart){
            gameInstance = new Game();

        }
        gameInstance.run();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
