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
