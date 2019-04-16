package com.example.zheng.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void farmerbutton(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, FarmerActivity.class);

        startActivity(intent);
    }

    public void puzzlebutton(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, PuzzleActivity.class);

        startActivity(intent);
    }
}
