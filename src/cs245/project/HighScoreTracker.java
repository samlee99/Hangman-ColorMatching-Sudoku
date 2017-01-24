/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import java.util.*;
import java.io.*;
/**
 *
 * @author PC
 */
public class HighScoreTracker {
    private ArrayList<Score> scores;
    
    private static final String FILE_NAME = "highscorerecord.txt";
    
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    
    public HighScoreTracker() {
        scores = new ArrayList<>();
    }
    
    private void sort() {
        ScoreCompare comparator = new ScoreCompare();
        Collections.sort(scores, comparator);
        Collections.reverse(scores);
    }
    
    public ArrayList<Score> getScores() {
        openScoresFile();
        sort();
        return scores;
    }
    
    public void addScore(String playerName, int score) {
        openScoresFile();
        scores.add(new Score(playerName, score));
        updateScoresFile();
    }
    
    public void openScoresFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch(IOException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            try {
                if(inputStream != null) inputStream.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    public void updateScoresFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            outputStream.writeObject(scores);
        } catch(IOException e) {
            System.out.println(e);
        } finally {
            try {
                if(outputStream != null) outputStream.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
