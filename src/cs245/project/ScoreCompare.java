/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import java.util.Comparator;
/**
 *
 * @author PC
 */
public class ScoreCompare implements Comparator<Score>{
    public int compare(Score score1, Score score2) {
        int firstScore = score1.getScore();
        int secondScore = score2.getScore();
        
        if(firstScore < secondScore) {
            return -1;
        } else if (firstScore > secondScore) {
            return 1;
        } else {
            return 0;
        }
    }
}
