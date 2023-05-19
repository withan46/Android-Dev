package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {

    private final String myIP = "192.168.1.3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void registerLink(View view){
        Intent intent = new Intent(MainActivity.this, CreatePatientR3.class);
        intent.putExtra("ip",myIP);
        startActivity(intent);
    }
}