/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 * Klasa mechanicznej trudności gry
 * @author Radek Urbanik
 */
public class MechanicalDifficulty {
    public static boolean disappear = false;
    public static double multiplier = 0.5;
    public static int difficulty = 5;
    public static double base_delay = 1.25*1000;
    /**
    * Ustawienie poziomu trudności
    * @param multi przelicznik trudności
    */
    static void set(double multi){
        difficulty = (int)multi;
        multiplier = 1-(multi/10);
        if(multiplier==0){
            disappear=true;
        }
    }
}
