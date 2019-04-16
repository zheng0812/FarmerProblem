package com.example.zheng.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FarmerGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_game);
        farmer= new FarmerProblem();
        String intial = farmer.getInitialState().toString();
        text =  findViewById(R.id.textView);
        text.setText(intial);
        dis = findViewById(R.id.textView1);
        solveb = findViewById(R.id.solvebox);
        solver = new AStarSolver(farmer);
        solverA = new SolvingAssistant(farmer);
        solve=  findViewById(R.id.solve1);
        next = findViewById(R.id.next1);
        next.setEnabled(false);

    }

    public void selfbutton(View view) {
        // Do something in response to button
        //Intent intent = new Intent(this, FarmerGameActivity.class);
       // FarmerProblem Farmer = new FarmerProblem();
        //String self = farmer.getMover().
        //solver.tryMove("Farmer Goes Alone");

        dis.setText("");
        State next=farmer.mover.doMove("Farmer Goes Alone", farmer.getCurrentState());
        //text =  findViewById(R.id.textView);
        if(next != null) {
            farmer.setCurrentState(next);
            text.setText(farmer.getCurrentState().toString());
            if(farmer.getCurrentState().equals(farmer.getFinalState())){
                dis.setText("You Solved the Problem!");
            }
        }
        else{
            dis.setText("Illegal Move!");
        }

       // text.setText("click");

       // display(farmer);
    }
    public void wolfbutton(View view){

        dis.setText("");
        State next=farmer.mover.doMove("Farmer Takes Wolf", farmer.getCurrentState());
        if(next != null){
            farmer.setCurrentState(next);
            text.setText(farmer.getCurrentState().toString());
            if(farmer.getCurrentState().equals((farmer.getFinalState()))){
                dis.setText("You Solved the Problem!");
            }
        }
        else{
            dis.setText("Illegal Move!");
        }

    }
    public void goatbutton(View view){
        dis.setText("");
        State next=farmer.mover.doMove("Farmer Takes Goat", farmer.getCurrentState());
        if(next != null){
            farmer.setCurrentState(next);
            text.setText(farmer.getCurrentState().toString());
            if(farmer.getCurrentState().equals(farmer.getFinalState())){
                dis.setText("You Solved the Problem!");
            }
        }
        else{
            dis.setText("Illegal Move!");
        }

    }
    public void cabbutton(View view){
        dis.setText("");
        State next=farmer.mover.doMove("Farmer Takes Cabbage", farmer.getCurrentState());
        if(next != null){
            farmer.setCurrentState(next);
            text.setText(farmer.getCurrentState().toString());
            if(farmer.getCurrentState().equals(farmer.getFinalState())){
                dis.setText("You Solved the Problem!");
            }
        }
        else{
            dis.setText("Illegal Move!");
        }

    }
    public void solvebutton(View view){
        solver.solve();
        (solution = solver.getSolution()).next().getData();
        solveb.setText(solver.getStatistics().toString());

        solve.setEnabled(false);
        next.setEnabled(true);
    }
    public void nextbutton(View view){
        //solution = solver.getSolution();
        if (solution.hasNext())
        {
            Vertex v = solution.next();
            State s = (State)v.getData();
            solverA.update(s);
            if (solverA.isProblemSolved()) {
                dis.setText("You solved the problem");
            }

            text.setText(farmer.getCurrentState().toString());
        }
    }
    public void reset(View view){
        solverA.reset();
        dis.setText("");
        text.setText(farmer.getCurrentState().toString());
        solve.setEnabled(true);
        solveb.setText("");
        next.setEnabled(false);
    }



    private FarmerProblem farmer;
    private TextView text,dis,solveb;
    private SolvingAssistant solverA;
    private Solver solver;
    private Solution solution;
    private Button solve,next;

}
