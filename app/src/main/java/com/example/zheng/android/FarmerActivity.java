package com.example.zheng.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FarmerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        FarmerProblem farmer = new FarmerProblem();
        TextView text = findViewById(R.id.introduction);
        String introM = farmer.getIntroduction().toString();
        text.setText(introM);

    }

    public void begin1button(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, FarmerGameActivity.class);

        startActivity(intent);
    }

}