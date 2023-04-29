package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r6_weekly_planner);
    }


    public void registerLink(View view){
        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
        startActivity(intent);
    }
}