/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs245.project;

import java.util.Random;

/**
 *
 * @author amnipp
 */
public class HangMan {
    private final String[] WORD_LIST = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};
    private boolean running;
    private String selectedWord;
    private int attempt;
    private int score;
    
    public HangMan(){
        initGame();
        gameLoop();
        endGame();
    }
    
    public void initGame(){
        running = true;
        score = 100;
        //Select a word
        int randIndex = new Random().nextInt(WORD_LIST.length+1);
        selectedWord = WORD_LIST[randIndex];
    }
    
    private void gameLoop(){
        while(running){
            
        }
    }
    
    public void endGame(){
        
    }
    
    public char selectCharacter(){
        return ' ';
    }
     
    public boolean checkCharacter(char letter){
        return selectedWord.contains(selectedWord);
    }
    
    public String getSelectedWord(){
        return selectedWord;
    }
    
    public int getScore(){
        return score;
    }
}
