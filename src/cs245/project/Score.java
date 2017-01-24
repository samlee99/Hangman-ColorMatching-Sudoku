/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import java.io.Serializable;
/**
 *
 * @author PC
 */
public class Score implements Serializable{
    private int score;
    private String playerName;
    
    public Score(String name, int score){
        this.score = score;
        this.playerName = name;
    }
    
    public int getScore(){
        return score;
    }
    
    public String getPlayerName() {
        return playerName;
    }
}
