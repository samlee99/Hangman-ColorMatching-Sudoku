/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import java.awt.Color;
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
    private final String[] COLORS = {"red", "yello", "green", "blue", "purple"};
    Random r = new Random();
    ColorGameGUI cgGUI;
    HangMan hangman;
    
    public ColorGame(){
        super("ColorGame");
    }    
    @Override
    protected void initGame() {
        score = 0;
        int randIndex = new Random().nextInt(COLORS.length);
        selectedColor = COLORS[randIndex];
    }
    
    public void loadColorPage()
    {
        this.score = hangman.score;
        setColorGameGUI(score);
        cgGUI.setVisible(true);
        
    }
    
    public void setColorGameGUI(int score)
    {
        int color = r.nextInt(5) + 1;
        changeColor(color);
        int colorNum = r.nextInt(5);
        changeName(colorNum);
        this.score = score;
        cgGUI.Color_Label.setText("Your Score: "  + score);
    }
    
    public void changeColor(int num)
    {
        if(num == 1)
        cgGUI.Color_Label.setForeground(Color.blue);
        else if(num == 2)
        cgGUI.Color_Label.setForeground(Color.red);
        else if(num == 3)
        cgGUI.Color_Label.setForeground(Color.green);
        else if(num == 4)
        cgGUI.Color_Label.setForeground(Color.pink);
        else if(num == 5)
        cgGUI.Color_Label.setForeground(Color.yellow);
    }
    
    public void changeName(int num)
    {
        cgGUI.Color_Label.setText(COLORS[num]);
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
