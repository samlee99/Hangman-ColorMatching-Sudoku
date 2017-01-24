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
    private String guessedColor = "";
    private final String[] COLORS = {"red", "yellow", "green", "blue", "purple"};
    Random r = new Random();
    ColorGameGUI cgGUI;
    
    public ColorGame(){
        super("ColorGame");
    }    
    @Override
    protected void initGame() {
        int randIndex = new Random().nextInt(COLORS.length);
        selectedColor = COLORS[randIndex];
    }
    
    public void loadColorPage(int score, ColorGameGUI cgGUI)
    {
        this.cgGUI = cgGUI;
        setScore(score);
       // this.score = hangman.score;
        setColorGameGUI(score);
        cgGUI.setVisible(true);
        
    }
    
    public void setColorGameGUI(int score)
    {
        int color = r.nextInt(5) + 1;
        changeColor(color);
        int colorNum = r.nextInt(5);
        changeName(colorNum);
        setScore(score);
        cgGUI.Color_Label.setText("Your Score: "  + getScore());
        cgGUI.initRandomColors();
    }
    
    public void changeColor(int num)
    {
        switch (num) {
            case 1:
                cgGUI.Color_Label.setForeground(Color.blue);
                break;
            case 2:
                cgGUI.Color_Label.setForeground(Color.red);
                break;
            case 3:
                cgGUI.Color_Label.setForeground(Color.green);
                break;
            case 4:
                cgGUI.Color_Label.setForeground(Color.pink);
                break;
            case 5:
                cgGUI.Color_Label.setForeground(Color.yellow);
                break;
            default:
                break;
        }
    }
    public String[] getColorList(){
        return COLORS;
    }
    public void changeName(int num)
    {
        cgGUI.Color_Label.setText(COLORS[num]);
    }

    @Override
    protected void gameLoop() {
        while(running) {
            while(guessedColor.equals("") && running == true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ColorGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    public boolean checkColor(String guess){
        if (guess.equals(selectedColor)){
            return true;
        }
        return false;
    }
    
    public String getColor(){
        return selectedColor;
    }
}
