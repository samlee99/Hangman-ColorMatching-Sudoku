/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

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
    
    public SudokuGame(){
        super("Sudoku");
        GRID_SIZE = 9;
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
    
    private boolean validSubmit(){
        for(int i = 0; i < GRID_SIZE; ++i){
            ArrayList<Integer> row = new ArrayList<>(GRID_SIZE);
            ArrayList<Integer> box = new ArrayList<>(GRID_SIZE);
            ArrayList<Integer> column = (ArrayList<Integer>)grid.get(i).clone();
            for(int j = 0; j < GRID_SIZE; ++j){
                row.set(j, grid.get(j).get(i));
                int iBox = (i/3)*3 + j/3;
                int jBox = i*3%9+j%3;
                box.set(j, grid.get(iBox).get(jBox));
            }
            if (!(validate(column) && validate(row) && validate(box)))
                return false;            
        }
        return true;
    }
    private boolean validate(ArrayList<Integer> check){
        int i = 0;
        Collections.sort(check);
        for(int num : check){
            if(num != ++i) return false;
        }
        return true;
    }
    @Override
    protected void gameLoop(){
        
    }
    
}
