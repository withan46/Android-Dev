package com.example.androiddev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class PsfActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pfs_activity);
    }

    public void logOutLink(View view){
        Intent intent = new Intent(PsfActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void addPhysiotherapyOnClick(View view){
        Intent intent = new Intent(PsfActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void createServiceOnClick(View view){
        Intent intent = new Intent(PsfActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void viewPhysiotherapyOnClick(View view){
        Intent intent = new Intent(PsfActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
