package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectClinic extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_clinic);
    }

    public void searchClinic(View view) {
        Intent intent = new Intent(SelectClinic.this, CreateNewService.class);
        startActivity(intent);
    }
}
