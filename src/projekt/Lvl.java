/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;


/**
 * Klasa poziomu gry
 * @author Radek Urbanik
 *
 */
public class Lvl {
    public int level = 1;
    public int brows = 3;
    public int slen = 2;
    private int levelStages = 5;
    private boolean init = true;
    public Lvl(){}
    
    /**
    * Poziom w górę, ewentualne zwiększenie rozmiaru siatki i długości sekwencji
    * 
    */
    public void levelUp(){
        if(init){
            init = false;
            return;
        }
        if(level%levelStages==0){
            brows++;
            slen=(int)(level/levelStages)+1;
            Projekt.gameInstance.opts.setDisable(false);
        }
        level++;
        if(slen<brows*brows)
        {
            slen++;
        }
        
    }
}
