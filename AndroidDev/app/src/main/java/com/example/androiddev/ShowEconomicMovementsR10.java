package com.example.androiddev;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ShowEconomicMovementsR10 extends AppCompatActivity {

    private ArrayList<Movement> movements = new ArrayList<>();

    private String patient_ssn;

    private String myIP;

    private RecyclerView movementsRecView;

    private ArrayList<Movement> selectedMovements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        this.myIP = intent.getStringExtra("Ip");
        this.patient_ssn = intent.getStringExtra("patient_ssn");

        OkHttpHandler okHttpHandler = new OkHttpHandler();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_economic_movements_r10);

        Spinner spinner = findViewById((R.id.period));
        spinner.setSelection(0,false);
        TextView placeholderText = findViewById(R.id.placeholder_text);

        List<String> periods = new ArrayList<>();
        periods.add("");
        periods.add("Last Month");
        periods.add("Last Year");
        periods.add("From the start");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, periods);
        spinner.setAdapter(arrayAdapter);

        try {
            String url = "http://" + myIP + "/flexFitDBServices/getEconomicMovementsR10.php?patient_ssn=" + patient_ssn;
            movements = okHttpHandler.populateEconimicMovements(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if(id==0) {
                    spinner.setSelection(0,false);
                    placeholderText.setVisibility(View.VISIBLE);
                    return;
                }
                placeholderText.setVisibility(View.GONE);

                String data = spinner.getItemAtPosition(position).toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date2;
                    Date date1;

                    String date = "";
                    selectedMovements = new ArrayList<>();

                    if(data.equals("Last Month"))
                    {
                        date = LocalDate.now().plusMonths(-1).toString();
                    }
                    else if(data.equals("Last Year"))
                    {
                        date = LocalDate.now().plusYears(-1).toString();
                    }
                    else if(data.equals("From the start"))
                    {
                        date = "1960-01-01";
                    }
                    try {
                        date2 = dateFormat.parse(date);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    for(Movement m: movements)
                    {
                        try {
                            date1 = dateFormat.parse(m.getDate());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        if(date2.compareTo(date1)<=0)
                        {
                            selectedMovements.add(m);
                        }
                    }
                    movementsRecView = findViewById(R.id.movementsRecyclerView);
                    EconomicMovementsR10Adapter adapter = new EconomicMovementsR10Adapter();
                    adapter.setMovements(selectedMovements);
                    movementsRecView.setAdapter(adapter);
                    movementsRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                placeholderText.setVisibility(View.VISIBLE);
            }
        });
    }


}