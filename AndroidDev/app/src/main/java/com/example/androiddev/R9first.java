package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class R9first extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r9);

        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> clinics = new ArrayList<>();
        clinics.add("");
        clinics.add("clinic 1");
        clinics.add("clinic 2");
        clinics.add("clinic 3");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clinics);
        spinner.setAdapter(arrayAdapter);

        spinner = findViewById(R.id.spinner);
        spinner.setSelection(0, false);

        // Set the default placeholder text for the spinner
        TextView placeholderText = findViewById(R.id.placeholder_text);

        // Set an onItemSelectedListener to update the placeholder text when an item is selected
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView selectedText = (TextView) view;

                if(id == 0) {
                    spinner.setSelection(0, false);
                    placeholderText.setVisibility(View.VISIBLE);
                    return;
                }
                placeholderText.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                placeholderText.setVisibility(View.VISIBLE);
            }
        });
    }


}