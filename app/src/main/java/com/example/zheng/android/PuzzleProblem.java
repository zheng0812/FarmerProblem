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
public class PuzzleProblem extends Problem
{   
    public PuzzleProblem() 
    {
        super.setName("8-Puzzle");
        super.setMover(new PuzzleMover());
        super.setIntroduction(INTRO);
        super.setInitialState(PuzzleProblem.START);
        super.setCurrentState(super.getInitialState());
        super.setFinalState(PuzzleProblem.END);
        
        this.Benchmark1 = new Benchmark();
        Benchmark1.setBenchMarkName("Bench 1: 5 Moves");
        Benchmark1.setStart(PuzzleProblem.START);
        Benchmark1.setGoal(PuzzleProblem.END);
        this.Benchmark2 = new Benchmark();
        Benchmark2.setBenchMarkName("Bench 2: 10 Moves");
        Benchmark2.setStart(PuzzleProblem.START2);
        Benchmark2.setGoal(PuzzleProblem.END);
        this.Benchmark3 = new Benchmark();
        Benchmark3.setBenchMarkName("Bench 3: 13 Moves");
        Benchmark3.setStart(PuzzleProblem.START3);
        Benchmark3.setGoal(PuzzleProblem.END);
        this.Benchmark4 = new Benchmark();
        Benchmark4.setBenchMarkName("Bench 4: 18 Moves");
        Benchmark4.setStart(PuzzleProblem.START4);
        Benchmark4.setGoal(PuzzleProblem.END);
        this.Benchmark5 = new Benchmark();
        Benchmark5.setBenchMarkName("Bench 5: 20 Moves");
        Benchmark5.setStart(PuzzleProblem.START5);
        Benchmark5.setGoal(PuzzleProblem.END);
        this.Benchmark6 = new Benchmark();
        Benchmark6.setBenchMarkName("Bench 6: 24 Moves");
        Benchmark6.setStart(PuzzleProblem.START6);
        Benchmark6.setGoal(PuzzleProblem.END);
        this.Benchmark7 = new Benchmark();
        Benchmark7.setBenchMarkName("Bench 7: 30 Moves");
        Benchmark7.setStart(PuzzleProblem.START7);
        Benchmark7.setGoal(PuzzleProblem.END);
        this.Benchmark8 = new Benchmark();
        Benchmark8.setBenchMarkName("Bench 8: 30 Moves");
        Benchmark8.setStart(PuzzleProblem.START8);
        Benchmark8.setGoal(PuzzleProblem.END);
        super.getBenchmarks().add(Benchmark1);
        super.getBenchmarks().add(Benchmark2);
        super.getBenchmarks().add(Benchmark3);
        super.getBenchmarks().add(Benchmark4);
        super.getBenchmarks().add(Benchmark5);
        super.getBenchmarks().add(Benchmark6);
        super.getBenchmarks().add(Benchmark7);
        super.getBenchmarks().add(Benchmark8);
    }
    
    
    /**
     * static set new puzzle to the configuration meant to be followed.
     */
    static 
    {
        START = new PuzzleState(new int[][] { { 2, 8, 3 }, { 1, 6, 4 }, 
            { 7, 0, 5 } });
        END = new PuzzleState(new int[][] { { 1, 2, 3 }, { 8, 0, 4 }, 
            { 7, 6, 5 } });
        
        START2 = new PuzzleState(new int[][] { { 3, 6, 4 }, { 1, 0, 2 }, 
            { 8, 7, 5 } });
        START3 = new PuzzleState(new int[][] { { 3, 0, 4 }, { 1, 6, 5 }, 
            { 8, 2, 7 } });
        START4 = new PuzzleState(new int[][] { { 2, 1, 3 }, { 8, 0, 4 }, 
            { 6, 7, 5 } });
        START5 = new PuzzleState(new int[][] { { 2, 8, 3 }, { 1, 6, 4 }, 
            { 7, 0, 5 } });
        START6 = new PuzzleState(new int[][] { { 4, 2, 0 }, { 8, 3, 6 }, 
            { 7, 5, 1 } });
        START7 = new PuzzleState(new int[][] { { 5, 6, 7 }, { 4, 0, 8 }, 
            { 3, 2, 1 } });
        START8 = new PuzzleState(new int[][] { { 5, 6, 7 }, { 4, 0, 8 }, 
            { 3, 2, 1 } });

        
    }
    private static final PuzzleState START;
    private static final PuzzleState END;
    private static final PuzzleState START2;
    private static final PuzzleState START3;
    private static final PuzzleState START4; 
    private static final PuzzleState START5;  
    private static final PuzzleState START6;    
    private static final PuzzleState START7;    
    private static final PuzzleState START8;
    
    
    private static final String INTRO = "You are given a board in which "
            + "numbered "
            + "tiles can slide around. There is one blank space that holds "
            + "no tile.  "
            + "A legal move consists of sliding a tile into the blank space "
            + "if the "
            + "tile is adjacent to it. The goal is to move tiles around "
            + "until the "
            + "board looks like the goal state below.";
    private final Benchmark Benchmark1;
    private final Benchmark Benchmark2;
    private final Benchmark Benchmark3;
    private final Benchmark Benchmark4;
    private final Benchmark Benchmark5;
    private final Benchmark Benchmark6;
    private final Benchmark Benchmark7;
    private final Benchmark Benchmark8;
}
