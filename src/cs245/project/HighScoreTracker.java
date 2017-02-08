/***************************************************************
* file: HighScoreTracker.java
* author: Sam Lee, Andrew Nipp, Joshua Ludwig, Steven Mai, Je'Don Carter
* class: CS 245 – Programming Graphical User Interfaces
*
* assignment: Project v1.2
* date last modified: 1/25/2017
*
* purpose: This file is to track the high scores.  It reads from a text file,
* puts the information into two arrays and if the user is top 5, it replaces 
* the lowest score and adds in the new score.
*
****************************************************************/ 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import java.awt.Frame;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
/**
 *
 * @author PC
 */
public class HighScoreTracker {
    public Frame frame;
    private final int HIGH_SCORE_AMOUNT= 5;
    private String playerNames[] = new String[HIGH_SCORE_AMOUNT];
    private int scores[] = new int[HIGH_SCORE_AMOUNT];
    private static final String FILE_NAME = "highscorerecord.txt";
    
    // method: HighScoreTracker
        // purpose: this method opens the file, reads the contents, and stores into two arrays.
        //One for player names and another for scores
    public HighScoreTracker() {
        BufferedReader inputStream = null;
        String inputString = "";
        int numStart, count = 0;
        try {
        inputStream = new BufferedReader(new FileReader(FILE_NAME));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        try {
            inputString = inputStream.readLine();
        }
        catch(Exception e) {
            
        }
        while((inputString != null) && (count < HIGH_SCORE_AMOUNT)){
            numStart = inputString.indexOf(' ');
            playerNames[count] = inputString.substring(0, numStart);
            scores[count] = Integer.valueOf(inputString.substring(numStart+1, inputString.length()));
            try {
                inputString = inputStream.readLine();
            }
            catch(Exception e) {
                
            }
            count++;
        }
            try {
                inputStream.close();
            }
            catch(Exception e) {
                
            }        
    }

       // method: addScore
        //parameter: the total score
	// purpose: if the score is top 5, user is prompted to give intials and the high score is updated
    public void addScore(int score) {
        String name = "";
        if(score > scores[scores.length-1]) {
            name = JOptionPane.showInputDialog(frame, "New High Score! Enter your initials: ");
            scores[scores.length-1] = score;
            playerNames[scores.length-1] = name;
            sort();
            writeScores();
        }
    }
    // method: sort
        // purpose: this method sorts the two arrays into the correct order  
    public void sort() {
        String tmpName;
        int tmpScore;
        for(int i = scores.length-1; i> 0;i--){
            if(scores[i] > scores[i-1]) {
                tmpName = playerNames[i];
                playerNames[i] = playerNames[i-1];
                playerNames[i-1] = tmpName;
                tmpScore = scores[i];
                scores[i] = scores[i-1];
                scores[i-1] = tmpScore;               
            }
        }
    }
    // method: writeScores()
        // purpose: this writes the top 5 scores to the test file
    public void writeScores() {
        BufferedWriter outputStream = null;
        try{
            outputStream = new BufferedWriter(new FileWriter(FILE_NAME));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        try{
           for(int i = 0; i < HIGH_SCORE_AMOUNT; i++) {
               outputStream.write(playerNames[i] + " " + scores[i] + "\n");
           } 
        }
        catch(Exception e) {
            System.out.println(e);
        }
        try{
            outputStream.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
                        
    }

}
