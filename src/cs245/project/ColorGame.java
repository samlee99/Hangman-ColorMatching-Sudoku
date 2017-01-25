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
    private int round = 0;
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
        //int color = r.nextInt(5);
        changeColor(selectedColor);
        
        int colorNum = r.nextInt(5);
        changeName(colorNum);
        
        setScore(score);
        cgGUI.getScoreLabel().setText("Your Score: "  + getScore());
        cgGUI.initRandomColors();
    }
    
    public void changeColor(String color)
    {
        switch (color) {
            case "red":
                cgGUI.getColorLabel().setForeground(Color.red);
                break;
            case "yellow":
                cgGUI.getColorLabel().setForeground(Color.yellow);
                break;
            case "green":
                cgGUI.getColorLabel().setForeground(Color.green);
                break;
            case "blue":
                cgGUI.getColorLabel().setForeground(Color.blue);
                break;
            case "purple":
                cgGUI.getColorLabel().setForeground(new Color(255,0,255));
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
        cgGUI.getColorLabel().setText(COLORS[num]);
    }
    
    public void setGuessedColor(String color){
        guessedColor = color;
    }
    
    @Override
    protected void gameLoop() {
        while(running && round < 5) {
            while(guessedColor.equals("") && running == true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ColorGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(selectedColor + " - " + guessedColor);
            if(guessedColor.equals(selectedColor)){
                setScore(100);              
            }
            int randIndex = new Random().nextInt(COLORS.length);
            selectedColor = COLORS[randIndex];
            changeColor(selectedColor);
            int colorNum = r.nextInt(5);
            changeName(colorNum); 
            guessedColor = "";
            round++;
        }
        showEndScreen();
    }
    
    private void showEndScreen(){
        System.out.println("GAME OVER");
    }
    
    public String getColor(){
        return selectedColor;
    }
}
