package com.example.zheng.android;

//import framework.graph.Vertex;
//import framework.problem.Mover;
//import framework.problem.Problem;
//import framework.problem.State;
//import java.util.LinkedList;
//import java.util.List;


import java.util.*;

/* imports go here */

/**
 * This class represents a state space solver by extending the abstract
 * Solver class.
 * @author Your name and section here
 */
public class StateSpaceSolver extends Solver {
    
    /**
     * Creates a BFS or DFS state space solver.
     * This constructor should set the queue to a double-ended queue (DEQUE)
     * and set the statistics header.
     * @param problem the problem being solved
     * @param bfs a boolean flag indicating whether BFS is to be performed
     */
    public StateSpaceSolver(Problem problem, boolean bfs) {
        super(problem);
        /* you must provide */
        super.setQueue(new LinkedList<Vertex>());
        this.bfs=bfs;
        if(bfs){
            super.getStatistics().setHeader("Breadth-First State Space Search");
            }
        else{
            super.getStatistics().setHeader("Depth-First State Space Search");
        }
   
    }
    /**
     * Adds a vertex to the DEQUE.
     * If BFS is being performed, the vertex should be added to the
     * end of the DEQUE, so the DEQUE acts like an ordinary queue.
     * If DFS is being performed, the vertex should be added to the
     * front of the DEQUE, so the DEQUE acts like a stack.
     * @param v 
     */
    @Override
    public void add(Vertex v) {
	/* you must provide */
        if(bfs){
            ((LinkedList)this.getQueue()).addLast(v);
        }
        else{
            ((LinkedList)this.getQueue()).addFirst(v);
        }
    }
    
    /**
     * This method implements the expand operation required by the 
     * state space search algorithm:

       Expand(u)
          children = {}
          for each name âˆˆ moveNames do
             child = mover.doMove(name, u)
             if child â‰  null and not OccursOnPath(child, u)
                then d[child] = d[u] + 1
                     pred[child] = u
                     add child to children
          return children

     * @param u the vertex being expanded
     * @return 
     */
    @Override
    public List<Vertex> expand(Vertex u) {
        List<Vertex> children = new LinkedList<>();
        Vertex child;
        for(String name: super.getProblem().getMover().getMoveNames()){
            child = new Vertex(super.getProblem().getMover().doMove(name, (State)u.getData()));
            if(child.getData() != null && !occursOnPath(child,u)){
                child.setDistance(u.getDistance()+1);
                child.setPredecessor(u);
                children.add(child);
            }
            
        }
        return children;
	/* you must provide */
    }

    /**
     * Checks whether a given vertex appears on the predecessor path
     * of a given ancestor.
     * @param v the vertex to check
     * @param ancestor the ancestor vertex of v
     * @return true if v occurs on the predecessor path of ancestor,
     * false otherwise
     */
    public static boolean occursOnPath(Vertex v, Vertex ancestor) {
        return ancestor != null && (v.equals(ancestor) || occursOnPath(v, ancestor.getPredecessor()));

	/* you must provide */
    }
    
    /* private instance fields here */
    private final boolean bfs;
}
