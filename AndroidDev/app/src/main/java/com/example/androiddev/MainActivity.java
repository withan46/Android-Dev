package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String myIP = "192.168.178.86";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void registerLink(View view){
        Intent intent = new Intent(MainActivity.this, SelectClinicR21.class);
        intent.putExtra("ip", myIP);
        startActivity(intent);
    }
}