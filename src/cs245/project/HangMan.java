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
import javax.swing.ImageIcon;

/**
 *
 * @author amnipp
 */
public class HangMan implements Runnable{
    private final String[] WORD_LIST = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};
    private boolean running;
    private String selectedWord;
    private String hiddenWord;
    private char selectedChar;
    private int attempt;
    private int score = 100;
    private Thread t;
    private String threadName = "hangman";
    HighScoreScreen hs = new HighScoreScreen();
    private PlayScreen ps;
    private int badGuess = 1;
    private int guessesToWin = 10;
    private final int guessesToLose = 7;
    
    private void initGame(){
        running = true;
        score = 100;
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
    
    public void setPlayScreen(PlayScreen ps){
        this.ps = ps;
    }
    
    private void gameLoop() throws InterruptedException {
        while(running){
            while(selectedChar == ' ' && running == true){
                Thread.sleep(100);
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
                changeScore();
            }
            if(guessesToWin == 0){
                loadEndPage(score);
                ps.dispose();
            }
            else if (badGuess == guessesToLose){
                Thread.sleep(1000);
                loadEndPage(0);
                ps.dispose();
            }
        }
    }
    
    public void changeScore()
    {
        score -= 10;
        ps.gameScore.setText("Score: " + score);
    }
    
    public void  changeImage(int badGuess)
    {
        String imageName = "/cs245/project/trooper_images/trooper" + badGuess + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(imageName));
        ps.Image.setIcon(icon);
    }
    
    //realScore used to differentiate between skipping and winning games
    public void loadEndPage(int realScore)
    {
        hs.setVisible(true);
        hs.setPlayerScore(realScore);
        hs.Back.setVisible(false);
        hs.End.setVisible(true);
        hs.Player_Score.setVisible(true);
        hs.Player_Score.setText("Your Score: " + realScore);
    }
    public void selectCharacter(char letter){
        selectedChar = letter;
    }
     
    public void exitGame(){
        running = false;
    }
    
    private boolean checkCharacter(char letter){
        return selectedWord.toLowerCase().indexOf(Character.toLowerCase(letter)) > -1;
    }
    
    public String getSelectedWord(){
        return selectedWord;
    }
    
    public String getHiddenWord(){
        return hiddenWord;
    }
    
    public int getScore(){
        return score;
    }

    @Override
    public void run() {
        try{
            initGame();
            gameLoop();
        }catch(InterruptedException e){
            System.out.println("Interrupted Thread!");
        }
    }
    
    public void start() {
        if(t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
