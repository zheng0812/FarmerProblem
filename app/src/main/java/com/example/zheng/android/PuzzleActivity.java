package com.example.zheng.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        PuzzleProblem puzzle = new PuzzleProblem();
        TextView text = findViewById(R.id.intropuz);
        String introM = puzzle.getIntroduction();
        text.setText(introM);
        String F =puzzle.getFinalState().toString();

        TextView text1 = findViewById(R.id.TV1);
        text1.setText(F);

    }

    public void begin2button(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, PBeginActivity.class);

        startActivity(intent);
    }
}
