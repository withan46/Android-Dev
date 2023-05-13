package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class R5_1_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r512);

        TextView myTextView = findViewById(R.id.allPatientsButton);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(R5_1_2.this, R5_1.class);
                startActivity(intent);
            }
        });
    }

    public void goToAppointments(View view) {
        Intent intent = new Intent(this, R5_1_2.class);
        startActivity(intent);
    }
    public void goToAll(View view) {
        Intent intent = new Intent(this, R5_1.class);
        startActivity(intent);
    }
}