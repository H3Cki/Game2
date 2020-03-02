/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 * Klasa optycznej trudności gry
 * @author Radek Urbanik
 */
public class OpticalDifficulty {
    public static String button = "buttoncss_0.css";
    public static boolean numeration = false;
    public static String difficulty = "Normal";
    /**
    * Ustawienie optycznej trudności gry na poziom łatwy
    * 
    */
    public static void setEasy(){
        numeration = true;
        button= "buttoncss_0.css";
        difficulty = "Weak";
    }
    /**
    * Ustawienie optycznej trudności gry na poziom normalny
    * 
    */
    public static void setNormal(){
        numeration = false;
        button = "buttoncss_1.css";
        difficulty = "Normal";
    }
    /**
    * Ustawienie optycznej trudności gry na poziom trudny
    * 
    */
    public static void setHard(){
        numeration = false;
        button = "buttoncss_2.css";
        difficulty = "Ninja";
    }
}
