/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amnipp
 */
public class ColorGame extends BaseGame {
    private boolean running = true;
    private String selectedColor = "";
    private int score;
    private final String[] COLORS = {"Red", "Yello", "Green", "Blue", "Purple"};
    public ColorGame(){
        super("ColorGame");
    }    
    @Override
    protected void initGame() {
        score = 0;
        int randIndex = new Random().nextInt(COLORS.length);
        selectedColor = COLORS[randIndex];
    }
    @Override
    protected void gameLoop() {
        while(running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ColorGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public int getScore(){
        return score;
    }
    public String getColor(){
        return selectedColor;
    }
}
