package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final String myIP = "192.168.0.132";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }


    public void registerLink(View view){
        Intent intent = new Intent(MainActivity.this, AllPatients_R5.class);
        intent.putExtra("ip", myIP);
        startActivity(intent);
    }
}