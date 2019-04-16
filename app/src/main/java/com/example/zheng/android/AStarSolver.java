package com.example.zheng.android;

import java.util.*;




public class AStarSolver extends StateSpaceSolver {

    /**
     * Creates an A* solver.
     * This constructor should set the queue to a priority queue (PQ)
     * and set the statistics header.
     * @param problem
     */
    public AStarSolver(Problem problem) {
        super(problem, false);
        super.getStatistics().setHeader("A Star Search");
        queue = new PriorityQueue<>(getComparator());
        super.setQueue(queue);
    }

    /**
     * Adds a vertex to the PQ.
     * @param v the vertex to be added
     */
    @Override
    public void add(Vertex v) {
        queue.add(v);
    }

    /**
     * Creates a comparator object that compares vertices for ordering
     * in a PQ during A* search.
     * This should be used when creating the PQ.
     * @return the comparator object
     */
    public final Comparator<Vertex> getComparator() {
        return (Vertex vector1, Vertex vector2) ->
        {
            State goal = getProblem().getFinalState();
            State s1 = (State) vector1.getData();
            State s2 = (State) vector2.getData();
            int i = s1.getHeuristic(goal) + vector1.getDistance();
            int p = s2.getHeuristic(goal) + vector2.getDistance();
            return i-p;
        };
    }

    private PriorityQueue queue;
}
