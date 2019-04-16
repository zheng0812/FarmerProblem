/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.zheng.android;

/**
 *
 * @author zheng
 */

 
/**
 * This class extends the Mover class to implement a problem solving framework 
 * by creating, displaying, and doing the four arithmetic states that is involved 
 * in this application.
 * 
 * @author Calvin Zheng
 */
 
public class PuzzleMover extends Mover {
     
   public static final String T1 = "SLIDE TILE 1";
   public static final String T2 = "SLIDE TILE 2";
   public static final String T3 = "SLIDE TILE 3";
   public static final String T4 = "SLIDE TILE 4";
   public static final String T5 = "SLIDE TILE 5";
   public static final String T6 = "SLIDE TILE 6";
   public static final String T7 = "SLIDE TILE 7";
   public static final String T8 = "SLIDE TILE 8";
   
   public PuzzleMover() {
            super.addMove(T1, s -> tryMove(1,s));
            super.addMove(T2, s -> tryMove(2,s));
            super.addMove(T3, s -> tryMove(3,s));
            super.addMove(T4, s -> tryMove(4,s));
            super.addMove(T5, s->  tryMove(5,s));
            super.addMove(T6, s -> tryMove(6,s));
            super.addMove(T7, s -> tryMove(7,s));
            super.addMove(T8, s -> tryMove(8,s));
        }
     private PuzzleState tryMove(final int tile, final State s) 
    {
        final PuzzleState state = (PuzzleState)s;
        final PuzzleState.Location tileLoc = state.getLocation(tile);
        final PuzzleState.Location blankLoc = state.getLocation(0);
        final int tileRow = tileLoc.getRow();
        final int tileColumn = tileLoc.getColumn();
        final int blankRow = blankLoc.getRow();
        final int blankColumn = blankLoc.getColumn();
        if (tileRow != blankRow && tileColumn != blankColumn) return null; 
        if (tileRow != blankRow + 1 && tileRow != blankRow - 1 && tileColumn != 
                blankColumn + 1 && tileColumn != blankColumn - 1)  return null; 
        
        return new PuzzleState(state, tileLoc, blankLoc);
    }
    /**
     * 
     * @param args Notice that the final rows are set to show as it should be 
     * for this main function.
     */
    public static void main(final String[] args) 
    {
        final int[] row1 = { 2, 8, 3 };
        final int[] row2 = { 1, 6, 4 };
        final int[] row3 = { 7, 0, 5 };
        final PuzzleState state1 = new PuzzleState(new int[][] { row1, row2, row3 });
        System.out.println(state1);
        final PuzzleMover mover = new PuzzleMover();
        for (int tile = 0; tile <= 8; ++tile) 
        {
            System.out.println("For tile = " + tile + " tryMove = " + mover.tryMove(tile, state1));
        }
    }

  }    
   
 

