/***************************************************************
* file: HighScoreScreen.java
* author: Sam Lee, Andrew Nipp, Joshua Ludwig, Steven Mai, Je'Don Carter
* class: CS 245 – Programming Graphical User Interfaces
*
* assignment: Project v1.1
* date last modified: 1/25/2017
*
* purpose: This is the color game file.  Colors are randomized and user clicks
* the button to match.  The score is displayed at the end.
* 
*
****************************************************************/ 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import java.awt.Color;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
    HighScoreTracker hsTrack = new HighScoreTracker();
    HighScoreScreen hs = new HighScoreScreen();
    
    public ColorGame(){
        super("ColorGame");
    }    
    @Override
    protected void initGame() {
        int randIndex = new Random().nextInt(COLORS.length);
        selectedColor = COLORS[randIndex];
    }
        // loadColorPage
	// purpose: this loads the color game page and continues the score counter
    public void loadColorPage(int score, ColorGameGUI cgGUI)
    {
        this.cgGUI = cgGUI;
        setScore(score);
       // this.score = hangman.score;
        setColorGameGUI(score);
        cgGUI.setVisible(true);
        
    }
            	// method: setColorGameGUI
	// purpose: this pushes the randomly selected color into the change color method
        // and randomly assigns the color text.  It also contains the score and 
        //starts the random colors in the gui.
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
        	// method: changeColor
	// purpose: this method assigns colors to the color text 
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
        	// method: getColorList
	// purpose: returns the string array of colors
    public String[] getColorList(){
        return COLORS;
    }
    	// method: changeName
	// purpose: changes the name of the color
    public void changeName(int num)
    {
        cgGUI.getColorLabel().setText(COLORS[num]);
    }
        	// method: setGuessedColor
	// purpose: this method sets the guessed color
    public void setGuessedColor(String color){
        guessedColor = color;
    }
    
    @Override
	// method: gameLoop
	// purpose: this contains game logic. If you guess correctly,
	// your score increases by 100 and if you don't it remains the same 
	// The rounds increase as you click.
    protected void gameLoop() {
        while(running && round < 5) {
            while(guessedColor.equals("") && running == true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ColorGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            AudioStream audio = null;
        //Play music
        InputStream inaudio = getClass().getResourceAsStream("/Sounds/Explosion.wav");
        try{
            audio = new AudioStream(inaudio);
            AudioPlayer.player.start(audio);
        }
        catch(Exception e)
        {
            System.out.print("File not found");
        }
            System.out.println(selectedColor + " - " + guessedColor);
            if(guessedColor.equals(selectedColor)){
                setScore(getScore()+100);
                cgGUI.scoreLabel.setText("Your Score: " + getScore());
            }
            int randIndex = new Random().nextInt(COLORS.length);
            selectedColor = COLORS[randIndex];
            changeColor(selectedColor);
            int colorNum = r.nextInt(5);
            changeName(colorNum); 
            guessedColor = "";
            round++;
        }
        showEndScreen(getScore());
    }
    // method: showEndScreen
        // purpose: revealst the end screen when finished with run through
    private void showEndScreen(int finalScore){
//        System.out.println("GAME OVER");
//        hs.setPlayerScore(finalScore);
//        hsTrack.addScore(finalScore);
//        hs = new HighScoreScreen();
//        hs.setVisible(true);
//        hs.Back.setVisible(false);
//        hs.End.setVisible(true);
//        hs.Player_Score.setVisible(true);
//        hs.Player_Score.setText("Your Score: " + finalScore);
        cgGUI.dispose();
        SudokuGame sgame = new SudokuGame();
        sgame.start();
        SudokuGUI sgui = new SudokuGUI();
        sgame.setSudokuGui(sgui);
        sgame.setScore(finalScore);
        sgui.sudokuStart(sgame);
        sgui.setVisible(true);
    }
        // method: getColor
	// purpose: retrieves the color from the colors string array
    public String getColor(){
        return selectedColor;
    }
}
