package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class R9second extends AppCompatActivity {

    private Button firstBtn;
    private Button secondBtn;
    private Button thirdBtn;
    private Button fourthBtn;
    private Button fifthBtn;
    private Button sixthBtn;
    private Button seventhBtn;

    private Button tenHourBtn;
    private Button elevenHourBtn;
    private Button twelveHourBtn;
    private Button thirteenHourBtn;
    private Button fourteenHourBtn;
    private Button fifthteenHourBtn;
    private Button sixteenHourBtn;
    private Button seventeenHourBtn;

    private Button bookAppointment;

    private Drawable defaultBackground;

    private Drawable defaultHourBtnBackground;

    private Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_r9second);

        String[] nameDay;
        final String[] selectedDate = new String[1];
        WeeklyPlan weeklyPlan = new WeeklyPlan();

        this.defaultBackground = ContextCompat.getDrawable(this, R.drawable.weekplanbuttondefault);

        this.firstBtn = findViewById(R.id.Monday_button);
        this.secondBtn = findViewById(R.id.Tuesday_button);
        this.thirdBtn = findViewById(R.id.Wednesday_button);
        this.fourthBtn = findViewById(R.id.Thursday_button);
        this.fifthBtn = findViewById(R.id.Friday_button);
        this.sixthBtn = findViewById(R.id.Saturday_button);
        this.seventhBtn = findViewById(R.id.Sunday_button);

        TextView monthTextView = findViewById(R.id.Month_textView);

        monthTextView.setText(weeklyPlan.getCurrentMonth());

        nameDay = weeklyPlan.getWeekDays();

        firstBtn.setText(nameDay[0]);
        secondBtn.setText(nameDay[1]);
        thirdBtn.setText(nameDay[2]);
        fourthBtn.setText(nameDay[3]);
        fifthBtn.setText(nameDay[4]);
        sixthBtn.setText(nameDay[5]);
        seventhBtn.setText(nameDay[6]);

        firstBtn.setOnClickListener(v -> {
            resetWeeklyButtonClicked();

            firstBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
            selectedDate[0] = firstBtn.getText().toString().replace("\n", " ");
        });

        secondBtn.setOnClickListener(v -> {
            resetWeeklyButtonClicked();

            secondBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
            selectedDate[0] = secondBtn.getText().toString().replace("\n", " ");
        });

        thirdBtn.setOnClickListener(v -> {
            resetWeeklyButtonClicked();

            thirdBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
            selectedDate[0] = thirdBtn.getText().toString().replace("\n", " ");
        });

        fourthBtn.setOnClickListener(v -> {
            resetWeeklyButtonClicked();

            fourthBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
            selectedDate[0] = fourthBtn.getText().toString().replace("\n", " ");
        });

        fifthBtn.setOnClickListener(v -> {
            resetWeeklyButtonClicked();

            fifthBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
            selectedDate[0] = fifthBtn.getText().toString().replace("\n", " ");
        });

        sixthBtn.setOnClickListener(v -> {
            resetWeeklyButtonClicked();

            sixthBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
            selectedDate[0] = sixthBtn.getText().toString().replace("\n", " ");
        });

        seventhBtn.setOnClickListener(v -> {
            resetWeeklyButtonClicked();

            seventhBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
            selectedDate[0] = seventhBtn.getText().toString().replace("\n", " ");
        });

        final String[] selectedHour = new String[1];
        final String[] selectedServiceName = new String[1];
        final String[] selectedServiceTotalAmount = new String[1];
        List<Service> services = new ArrayList<>();
        List<String> serviceNames = new ArrayList<>();
        services.add(new Service("", 0.00));
        services.add(new Service("service1", 49.99));
        services.add(new Service("service2", 69.99));
        services.add(new Service("service3", 29.99));
        for(Service service: services){
            serviceNames.add(service.type);
        }

        TextView placeholderText = findViewById(R.id.placeholder_service_dropdown);
        dropdown = (Spinner) findViewById(R.id.servicedropdown);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, serviceNames);
        dropdown.setAdapter(arrayAdapter);

        dropdown = findViewById(R.id.servicedropdown);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView selectedText = (TextView) view;
                TextView selectedService = (TextView) findViewById(R.id.Total_textView);
                selectedServiceName[0] = selectedText.getText().toString();
                selectedServiceTotalAmount[0] = services.get((int) id).price.toString();

                System.out.println(id);

                if(id == 0) {
                    selectedService.setText("Total:");
                    dropdown.setSelection(0, false);
                    placeholderText.setVisibility(View.VISIBLE);
                    return;
                }
                selectedService.setText("Total: " + services.get((int) id).price.toString());
                placeholderText.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                placeholderText.setVisibility(View.VISIBLE);
            }
        });

        this.tenHourBtn = findViewById(R.id.tenAM_button);
        this.elevenHourBtn = findViewById(R.id.elevenAM_button);
        this.twelveHourBtn = findViewById(R.id.twelvePM_button);
        this.thirteenHourBtn = findViewById(R.id.thirteenPM_button);
        this.fourteenHourBtn = findViewById(R.id.fourteenPM_button);
        this.fifthteenHourBtn = findViewById(R.id.fifthteenPM_button);
        this.sixteenHourBtn = findViewById(R.id.sixteenPM_button);
        this.seventeenHourBtn = findViewById(R.id.seventeenPM_button);

        this.bookAppointment = findViewById(R.id.book_appointment);


        this.defaultHourBtnBackground = ContextCompat.getDrawable(this, R.drawable.hour_button_default);

        tenHourBtn.setOnClickListener(v -> {
            resetHourButtonClicked();

            tenHourBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
            selectedHour[0] = tenHourBtn.getText().toString();
        });

        elevenHourBtn.setOnClickListener(v -> {
            resetHourButtonClicked();

            elevenHourBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
            selectedHour[0] = elevenHourBtn.getText().toString();
        });

        twelveHourBtn.setOnClickListener(v -> {
            resetHourButtonClicked();

            twelveHourBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
            selectedHour[0] = twelveHourBtn.getText().toString();
        });

        thirteenHourBtn.setOnClickListener(v -> {
            resetHourButtonClicked();

            thirteenHourBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
            selectedHour[0] = thirteenHourBtn.getText().toString();
        });

        fourteenHourBtn.setOnClickListener(v -> {
            resetHourButtonClicked();

            fourteenHourBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
            selectedHour[0] = fourteenHourBtn.getText().toString();
        });

        fifthteenHourBtn.setOnClickListener(v -> {
            resetHourButtonClicked();

            fifthteenHourBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
            selectedHour[0] = fifthteenHourBtn.getText().toString();
        });

        sixteenHourBtn.setOnClickListener(v -> {
            resetHourButtonClicked();

            sixteenHourBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
            selectedHour[0] = sixteenHourBtn.getText().toString();
        });

        seventeenHourBtn.setOnClickListener(v -> {
            resetHourButtonClicked();

            seventeenHourBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
            selectedHour[0] = seventeenHourBtn.getText().toString();

        });

        bookAppointment.setOnClickListener(v -> {

            System.out.println("The appointment is on the way! Here is your details: " + selectedDate[0] + " " + selectedHour[0] + " " + selectedServiceName[0] + " it will cost you: " + selectedServiceTotalAmount[0]);
        });

    }

    private void resetWeeklyButtonClicked() {
        firstBtn.setBackground(this.defaultBackground);
        secondBtn.setBackground(this.defaultBackground);
        thirdBtn.setBackground(this.defaultBackground);
        fourthBtn.setBackground(this.defaultBackground);
        fifthBtn.setBackground(this.defaultBackground);
        sixthBtn.setBackground(this.defaultBackground);
        seventhBtn.setBackground(this.defaultBackground);
    }

    private void resetHourButtonClicked() {
        this.tenHourBtn.setBackground(this.defaultHourBtnBackground);
        this.elevenHourBtn.setBackground(this.defaultHourBtnBackground);
        this.twelveHourBtn.setBackground(this.defaultHourBtnBackground);
        this.thirteenHourBtn.setBackground(this.defaultHourBtnBackground);
        this.fourteenHourBtn.setBackground(this.defaultHourBtnBackground);
        this.fifthteenHourBtn.setBackground(this.defaultHourBtnBackground);
        this.sixteenHourBtn.setBackground(this.defaultHourBtnBackground);
        this.seventeenHourBtn.setBackground(this.defaultHourBtnBackground);
    }
}