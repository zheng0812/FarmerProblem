/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.zheng.android;



/**
 * A graph for the FWGC problem.
 *
 * @author Calvin Zheng
 */
public class FarmerGraph extends Graph {

    public FarmerGraph() {
        /* you must provide */
        addEdge(v1, v2);
        addEdge(v2, v1);
        addEdge(v2, v3);
        addEdge(v3, v2);
        addEdge(v3, v4);
        addEdge(v4, v3);
        addEdge(v4, v5);
        addEdge(v5, v4);
        addEdge(v3, v6);
        addEdge(v6, v3);
        addEdge(v6, v7);
        addEdge(v7, v6);
        addEdge(v5, v8);
        addEdge(v8, v5);
        addEdge(v7, v8);
        addEdge(v8, v7);
        addEdge(v8, v9);
        addEdge(v9, v8);
        addEdge(v9, v10);
        addEdge(v10, v9);

    }

    public Vertex getStart() {
        /* you must provide */
        return v1;
    }

    public Vertex getEnd() {
        /* you must provide */
        return v10;
    }

    /* private fields and methods follow */
    private Vertex v1 = new Vertex(new FarmerState("West",
            "West",
            "West",
            "West"));
    private Vertex v2 = new Vertex(new FarmerState("East",
            "West",
            "East",
            "West"));
    private Vertex v3 = new Vertex(new FarmerState("West",
            "West",
            "East",
            "West"));
    private Vertex v4 = new Vertex(new FarmerState("East",
            "East",
            "East",
            "West"));
    private Vertex v5 = new Vertex(new FarmerState("West",
            "East",
            "West",
            "West"));
    private Vertex v6 = new Vertex(new FarmerState("East",
            "West",
            "East",
            "East"));
    private Vertex v7 = new Vertex(new FarmerState("West",
            "West",
            "West",
            "East"));
    private Vertex v8 = new Vertex(new FarmerState("East",
            "East",
            "West",
            "East"));
    private Vertex v9 = new Vertex(new FarmerState("West",
            "East",
            "West",
            "East"));
    private Vertex v10 = new Vertex(new FarmerState("East",
            "East",
            "East",
            "East"));

}
