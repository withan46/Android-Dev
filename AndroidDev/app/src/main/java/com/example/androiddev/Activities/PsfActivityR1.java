package com.example.androiddev.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.androiddev.MainActivity;
import com.example.androiddev.R;

public class PsfActivityR1 extends Activity {

    private String ip;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pfs_activity);

        String name = getIntent().getStringExtra("name");
        this.ip = getIntent().getStringExtra("Ip");
        TextView helloTxt = findViewById(R.id.hello);
        helloTxt.setText("Hello " + name + " !");
    }

    public void logOutLink(View view){
        Intent intent = new Intent(PsfActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

    public void addPhysiotherapyOnClick(View view){
        Intent intent = new Intent(PsfActivityR1.this, RegisterClinicR1.class);
        intent.putExtra("Ip", this.ip);
        startActivity(intent);
    }
    public void createServiceOnClick(View view){
        Intent intent = new Intent(PsfActivityR1.this, SelectClinicR21.class);
        intent.putExtra("Ip", this.ip);
        startActivity(intent);
    }
}
