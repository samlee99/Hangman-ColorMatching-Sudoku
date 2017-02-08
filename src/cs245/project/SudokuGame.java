/***************************************************************
* file: SudokuGame.java
* author: Sam Lee, Andrew Nipp, Joshua Ludwig, Steven Mai, Je'Don Carter
* class: CS 245 – Programming Graphical User Interfaces
*
* assignment: Project v1.2
* date last modified: 2/7/2017
*
* purpose: This is the model for the sudoku game.
* 
*
****************************************************************/ 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author andrew
 */
public class SudokuGame extends BaseGame {
    
    private ArrayList<ArrayList<Integer>> grid;
    private final int GRID_SIZE;
    private SudokuGUI sGui;
    int sudokuScore;
    int[] solution = {8, 3, 5, 4, 1, 6, 9, 2, 7, 
                      2, 9, 6, 8, 5, 7, 4, 3, 1, 
                      4, 1, 7, 2, 9, 3, 6, 5, 8,
                      5, 6, 9, 1, 3, 4, 7, 8, 2,
                      1, 2, 3, 6, 7, 8, 5, 4, 9, 
                      7, 4, 8, 5, 2, 9, 1, 6, 3,
                      6, 5, 2, 7, 8, 1, 3, 9, 4,
                      9, 8, 1, 3, 4, 5, 2, 7, 6,
                      3, 7, 4, 9, 6, 2, 8, 1, 5};

    
    public SudokuGame(){
        super("Sudoku");
        GRID_SIZE = 9;
        sudokuScore = 540;
    }
    public void setSudokuGui(SudokuGUI sGui){
        this.sGui = sGui;
    }
    @Override
    protected void initGame(){
        grid = new ArrayList<>();
        for(int i = 0; i < GRID_SIZE; ++i){
            grid.add(new ArrayList<>());
            for(int j = 0; j < GRID_SIZE; ++j){
                grid.get(i).add(-1);
            }
        }
        addNumbers();
        System.out.println(gridToString());
    }
    private void addNumbers(){
	grid.get(0).set(0, 8);
        grid.get(0).set(3, 4);
        grid.get(0).set(5, 6);
        grid.get(0).set(8, 7);
        grid.get(1).set(6, 4);
        grid.get(2).set(1, 1);
        grid.get(2).set(6, 6);
        grid.get(2).set(7, 5);
        grid.get(3).set(0, 5);       
        grid.get(3).set(2, 9);
        grid.get(3).set(4, 3);
        grid.get(3).set(6, 7);
        grid.get(3).set(7, 8);
        grid.get(4).set(4, 7);
        grid.get(5).set(1, 4);
        grid.get(5).set(2, 8);
        grid.get(5).set(4, 2);
        grid.get(5).set(6, 1);        
	grid.get(5).set(8, 3);
        grid.get(6).set(1, 5);
        grid.get(6).set(2, 2);
        grid.get(6).set(7, 9);
        grid.get(7).set(2, 1);
	grid.get(8).set(0, 3);
        grid.get(8).set(3, 9);
        grid.get(8).set(5, 2);
        grid.get(8).set(8, 5); 
    }
    
    public ArrayList<ArrayList<Integer>> getGrid(){
        return grid;
    }
    
    public void addToGrid(int i, int j, int val){
        grid.get(i).set(j, val);
        System.out.println(gridToString());
    }
    
    public void removeFromGrid(int i, int j){
        grid.get(i).set(j,-1);
    }
    
    public String gridToString(){
        String gridString = "";
        for(int i = 0; i < GRID_SIZE; ++i){
            for(int j = 0; j < GRID_SIZE; ++j){
                gridString += grid.get(i).get(j) + ",\t";
                if((j+1)%3 == 0) gridString += "\t";
            }
            if((i+1)%3 == 0) gridString += "\n";
            gridString += "\n";
        }
        return gridString;
    }
    
    public void validSubmit(){
        int k = 0;
        for(int i = 0; i < GRID_SIZE; ++i){
            
            for(int j = 0; j < GRID_SIZE; ++j){
                if(grid.get(i).get(j) != solution[k]){
                    sudokuScore -= 10;
                }
                k++;
            }
        }
        
        if(sudokuScore < 540) {
            int reply = JOptionPane.showConfirmDialog(null, "Retry?", "Incorrect answer.  Would you like to try again?", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.NO_OPTION) {
                endGame();
            } else {
                sudokuScore = 540;
            }
        } else {
            endGame();
        }
    }

    private void endGame() {
        HighScoreScreen hs = new HighScoreScreen();
        HighScoreTracker hsTrack = new HighScoreTracker();
        hs.setPlayerScore(getScore() + sudokuScore);
        hsTrack.addScore(getScore() + sudokuScore);
        hs = new HighScoreScreen();
        hs.setVisible(true);
        hs.Back.setVisible(false);
        hs.End.setVisible(true);
        hs.Player_Score.setVisible(true);
        hs.Player_Score.setText("Your Score: " + (getScore() + sudokuScore));
        sGui.dispose();
    }
}
