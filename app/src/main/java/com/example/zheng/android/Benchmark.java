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
public class Benchmark{
//    public Benchmark(State start,State goal,String name){
//        s=start;
//        g=goal;
//        n=name;
//    }
//    public State getStart(){
//        return s;
//    }
//    public State getGoal(){
//        return g;
//    }
//    public String getName(){
//        return n;
//    }
//    @Override 
//    public String toString(){
//    return n;
//    };
//    
//    private final State s;
//    private final State g;
//    private final String n;
//}

    @Override
    public String toString(){
      return this.benchmark;  
    }
    /**
     * 
     * @return the given benchmark. 
     * although we are asked to have the toString do the same thing.
     * I plan to use both accordingly.
     */
    public String getBenchMarkName() {
        return benchmark;
    }
    /**
     * 
     * @param benchmark mutator for the name.
     */
    public void setBenchMarkName(String benchmark){
        this.benchmark = benchmark;
    }
    /**
    * getter for the start state.
    * @return the start
    */
    public State getStartState(){
        return this.start;
    }
    /**
     * setter for the start state.
     * @param start 
     */
    public void setStart(State start){
        this.start = start;
    }
    /**
     * 
     * @return  the goal state
     */
    public State getGoalState(){
        return this.goal;
    }
    /**
     * 
     * @param goal mutator for the goal.
     */
    public void setGoal(State goal){
        this.goal= goal;
    }
    private String benchmark;
    private State start;
    private State goal;
}





