/***************************************************************
* file: HangMan.java
* author: Sam Lee, Andrew Nipp, Joshua Ludwig, Steven Mai, Je'Don Carter
* class: CS 245 – Programming Graphical User Interfaces
*
* assignment: Project v1.0
* date last modified: 1/18/2017
*
* purpose: This file is for the actual HangMan game where the user
* will play the game.  The user's score will be displayed on the high
* scores screen.
*
****************************************************************/ 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs245.project;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *	
 * @author amnipp
 */
public class HangMan extends BaseGame{
    private final String[] WORD_LIST = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};
    private boolean running;
    private String selectedWord;
    private String hiddenWord;
    private char selectedChar;
    private int attempt;
    //public int score = 100;
    private Thread t;
    private String threadName = "hangman";
    HighScoreScreen hs = new HighScoreScreen();
    ColorGameGUI cgGUI;
    private PlayScreen ps;
    private int badGuess = 1;
    private int guessesToWin = 10;
    private final int guessesToLose = 7;
    
    public HangMan(){
        super("HangMan");
    }
    
	// method: initGame
	// purpose: initializes all private fields, selects a word at random
	// creates the hiddenWord and fills it with underscores
    @Override
    protected void initGame(){
        running = true;
        setScore(100);
        selectedChar = ' ';
        //Select a word
        int randIndex = new Random().nextInt(WORD_LIST.length);
        selectedWord = WORD_LIST[randIndex];
        //We need to create a string of the same size as selectedWord by fill it with underscore
        char[] createHiddenWord = new char[selectedWord.length()];
        Arrays.fill(createHiddenWord,'_');
        hiddenWord = new String(createHiddenWord);
        setGuessesToWin(selectedWord);
    }
    // method: correctGuess
	// parameters: letter that was correctly guessed
	// purpose: updates the hiddenWord with the correctly guessed letter
	// it also calls playscreen so that the gui is updated as well
    public void correctGuess(char letter) {
        int pos = selectedWord.toLowerCase().indexOf(Character.toLowerCase(letter));
        while(pos >= 0){
            StringBuilder builder = new StringBuilder(hiddenWord);
            builder.deleteCharAt(pos);
            hiddenWord = builder.insert(pos, letter).toString();       
            pos = selectedWord.toLowerCase().indexOf(Character.toLowerCase(letter), pos+1);    
        }
        
            System.out.println(hiddenWord);
        ps.updateHiddenWord(hiddenWord);
    }
    // method: setGuessesToWin
	// parameters: the selected word
	// purpose: calculates the amount of guess required for the player
	// to win the game
    public void setGuessesToWin(String selectedWord){
        char c;
        String chars = "";
        for(int i = 0; i < selectedWord.length(); i++){
            c = selectedWord.charAt(i);
            if (chars.indexOf(c) == -1){
                chars += c;
            }
        }
        guessesToWin = chars.length();
    }
    
	// method: setPlayScreen
	// parameter: the play screen class that was created
	// purpose: allows this class to access methods from the playscreen
	// class in order to update the gui
    public void setPlayScreen(PlayScreen ps){
        this.ps = ps;
    }
    
	// method: gameLoop
	// purpose: the internal game loop which holds all the logic for the game
	// It checks if the player guessed correctly and updates the screen, it also
	// updates the score. 
    @Override
    protected void gameLoop(){
        while(running){
            while(selectedChar == ' ' && running == true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HangMan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
            if(checkCharacter(selectedChar)) {
                System.out.println("Found " + selectedChar);
                correctGuess(selectedChar);
                ps.hideKey(selectedChar);
                guessesToWin--;
                selectedChar = ' ';
            }else{
                System.err.println("Did not find " + selectedChar);
                ps.hideKey(selectedChar);
                selectedChar = ' ';
                badGuess += 1;
                changeImage(badGuess);
                modifyScore(-10);
                ps.gameScore.setText("Score: " + getScore());
            }
            if(guessesToWin == 0){
                loadEndPage(getScore());
                ps.dispose();
            }
            else if (badGuess == guessesToLose){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HangMan.class.getName()).log(Level.SEVERE, null, ex);
                }
                loadEndPage(0);
                ps.dispose();
            }
        }
    }
    // method: changeScore
	// purpose: updates the score when the player has an incorrect guess
   /* public void changeScore(int change)
    {
        score += change;
        ps.gameScore.setText("Score: " + score);
    }*/
    // method: changeImage
	// parameter: the number of incorrect guesses
	// purpose: updates the "hanged man" with the latest image
    public void changeImage(int badGuess)
    {
        String imageName = "/cs245/project/trooper_images/trooper" + badGuess + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(imageName));
        ps.Image.setIcon(icon);
    }
    
	// method: loadEndPage
    // parameter: realScore used to differentiate between skipping and winning games
	// purpose: loads the end game page
    public void loadEndPage(int realScore)
    {
        hs.setVisible(true);
        hs.setPlayerScore(realScore);
        hs.Back.setVisible(false);
        hs.End.setVisible(true);
        hs.Player_Score.setVisible(true);
        hs.Player_Score.setText("Your Score: " + realScore);
    }
    
	// method: selectCharacter
	// parameter: selected char
	// purpose: Updates the selected character with the latest choice
	// it is called by PlayScreen in order to interupt the gameloop
	// 
    public void selectCharacter(char letter){
        selectedChar = letter;
    }
    // method: exitGame
	// purpose: exits the game
    public void exitGame(){
        running = false;
    }
    // method: checkCharacter
	// purpose: checks to see if the selected character is in the chosen word
    private boolean checkCharacter(char letter){
        return selectedWord.toLowerCase().indexOf(Character.toLowerCase(letter)) > -1;
    }
    // method: getSelectedWord
	// purpose: gets the selected word
    public String getSelectedWord(){
        return selectedWord;
    }
    // method: getHiddenWord
	// purpose: gets the hidden word   
    public String getHiddenWord(){
        return hiddenWord;
    }
    // method: getScore
	// purpose: gets the score
    /*public int getScore(){
        return score;
    }*/
}
