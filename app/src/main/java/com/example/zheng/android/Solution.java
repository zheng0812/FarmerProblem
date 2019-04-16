/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.zheng.android;


import java.util.Stack;


/**
 *
 * @author zheng
 */
public class Solution {

    /**
     * Creates an object that represents a path from a start
     * vertex to an end vertex in a problem solving domain.
     * This constructor will be called after a search has been
     * initiated on the start vertex.
     * If a path from start to end exists, it can be constructed
     * from the predecessor information stored in the end vertex.
     * @param start the start vertex
     * @param end the end vertex
     */
    public Solution(Vertex start, Vertex end) {
        /* you must provide */
       stack.push(end);
       Vertex temp = end.getPredecessor();
       while(temp!= start){
           stack.push(temp);
           temp = temp.getPredecessor();
       }
        stack.push(start);    
        
        
        
    }

    /**
     * Gets the length of the solution, that is, the number of moves
     * to get to the end state from the start.
     * @return the solution length
     */
    public int getLength() {
        /* you must provide */
        return stack.size()-1;
        
    }
    
    /**
     * Checks whether there are more vertices in this solution.
     * @return true if there are more vertices in this solution, false
     * otherwise
     */
    public boolean hasNext() {
        /* you must provide */
        if (stack.size() -1 >=0)
        {
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Removes and returns the next vertex in this solution.
     * @return the next vertex in this solution
     * @throws RuntimeException if there are no more vertices
     */
    public Vertex next() {
        /* you must provide */
        try{
            return stack.pop();
        }
        catch(RuntimeException ex){
        throw new RuntimeException (ex);
        }
        
       
        
       
    }
    
    /* private instance fields and methods here */

    private final Stack<Vertex> stack= new Stack();
  
}
