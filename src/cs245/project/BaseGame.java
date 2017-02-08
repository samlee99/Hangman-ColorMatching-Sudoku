/***************************************************************
* file: BaseGame.java
* author: Sam Lee, Andrew Nipp, Joshua Ludwig, Steven Mai, Je'Don Carter
* class: CS 245 – Programming Graphical User Interfaces
*
* assignment: Project v1.2
* date last modified: 2/7/2017
*
* purpose: This is the BaseGame Class, which is the super class of the
* hangman and colors game.
* 
*
****************************************************************/ 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

/**
 *
 * @author amnipp
 */
public class BaseGame implements Runnable{
    
    private Thread t;
    private String gameName;
    private int score;
    
    
    public BaseGame(){
    }
    
    public BaseGame(String gameName){
        this.gameName = gameName;
    }
    
    protected void initGame(){};
    protected void gameLoop(){};
    
    public void startGame() {
        initGame();
        gameLoop();
    };

    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public void modifyScore(int score){
        this.score += score;
    }
    @Override
    public void run() {
        startGame();
    }
        // method: start
    // purpose: creates a new thread and starts it
    public void start() {
        if(t == null) {
            t = new Thread(this, gameName);
            t.start();
        }
    }
}
