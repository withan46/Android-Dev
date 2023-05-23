package com.example.androiddev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PsfActivityR1 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pfs_activity);

        String name = getIntent().getStringExtra("message");
        TextView helloTxt = findViewById(R.id.hello);
        helloTxt.setText("Hello " + name + " !");
    }

    public void logOutLink(View view){
        Intent intent = new Intent(PsfActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

    public void addPhysiotherapyOnClick(View view){
        Intent intent = new Intent(PsfActivityR1.this, MainActivity.class);
        startActivity(intent);
    }
    public void createServiceOnClick(View view){
        Intent intent = new Intent(PsfActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

    public void viewPhysiotherapyOnClick(View view){
        Intent intent = new Intent(PsfActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

}