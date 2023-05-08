package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class R9second extends AppCompatActivity {

    private ArrayList<Button> hourButtons, totalHourButtons, totalDayButtons;

    private Drawable defaultBackground;

    private Drawable defaultHourBtnBackground;

    private Spinner dropdown;

    private final String myIP = "192.168.1.5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_r9second);

        Intent intent = getIntent();
        String clinicVAT = intent.getStringExtra("clinicVAT");
        
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        ArrayList<Appointment> appointments;

        try {
            appointments = okHttpHandler.getClinicAppointments("http://" + myIP + "/r9SecondAppointments.php?clinicVATNumber=" + clinicVAT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String[] nameDay;
        final String[] selectedIndex = new String[1];
        final String[] selectedHour = new String[1];
        final String[] selectedServiceName = new String[1];
        WeeklyPlan weeklyPlan = new WeeklyPlan();

        this.defaultBackground = ContextCompat.getDrawable(this, R.drawable.weekplanbuttondefault);
        this.defaultHourBtnBackground = ContextCompat.getDrawable(this, R.drawable.hour_button_default);

        Button tenHourBtn = findViewById(R.id.tenAM_button);
        Button elevenHourBtn = findViewById(R.id.elevenAM_button);
        Button twelveHourBtn = findViewById(R.id.twelvePM_button);
        Button thirteenHourBtn = findViewById(R.id.thirteenPM_button);
        Button fourteenHourBtn = findViewById(R.id.fourteenPM_button);
        Button fifthteenHourBtn = findViewById(R.id.fifthteenPM_button);
        Button sixteenHourBtn = findViewById(R.id.sixteenPM_button);
        Button seventeenHourBtn = findViewById(R.id.seventeenPM_button);

        this.totalHourButtons = new ArrayList<>();
        this.totalHourButtons.add(tenHourBtn);
        this.totalHourButtons.add(elevenHourBtn);
        this.totalHourButtons.add(twelveHourBtn);
        this.totalHourButtons.add(thirteenHourBtn);
        this.totalHourButtons.add(fourteenHourBtn);
        this.totalHourButtons.add(fifthteenHourBtn);
        this.totalHourButtons.add(sixteenHourBtn);
        this.totalHourButtons.add(seventeenHourBtn);

        this.hourButtons = new ArrayList<>(this.totalHourButtons);

        Button bookAppointment = findViewById(R.id.book_appointment);

        Button firstBtn = findViewById(R.id.Monday_button);
        Button secondBtn = findViewById(R.id.Tuesday_button);
        Button thirdBtn = findViewById(R.id.Wednesday_button);
        Button fourthBtn = findViewById(R.id.Thursday_button);
        Button fifthBtn = findViewById(R.id.Friday_button);
        Button sixthBtn = findViewById(R.id.Saturday_button);
        Button seventhBtn = findViewById(R.id.Sunday_button);

        this.totalDayButtons = new ArrayList<>();

        this.totalDayButtons.add(firstBtn);
        this.totalDayButtons.add(secondBtn);
        this.totalDayButtons.add(thirdBtn);
        this.totalDayButtons.add(fourthBtn);
        this.totalDayButtons.add(fifthBtn);
        this.totalDayButtons.add(sixthBtn);
        this.totalDayButtons.add(seventhBtn);

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

        for(int i=0;i<this.totalDayButtons.size();i++) {
            int index = i;
            this.totalDayButtons.get(i).setOnClickListener(v -> {
                resetWeeklyButtonClicked();
                this.hourButtons = new ArrayList<>(this.totalHourButtons);
                resetHourButtonClicked();

                this.totalDayButtons.get(index).setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
                selectedIndex[0] = String.valueOf(index);
                addAppointments(appointments, weeklyPlan, index);
            });
        }

        for(Button btn : this.hourButtons) {
            btn.setOnClickListener(v -> {

                resetHourButtonClicked();
                btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
                selectedHour[0] = btn.getText().toString();
            });
        }
        
        List<Service> services;
        List<String> serviceNames = new ArrayList<>();
        serviceNames.add("");
        List<Double> servicePrices = new ArrayList<>();
        servicePrices.add(0.0);

        try {
            services = okHttpHandler.populateServiceDropDown("http://" + myIP + "/r9SecondServices.php?clinicVATNumber=" + clinicVAT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for(Service service: services){
            serviceNames.add(service.getName());
            servicePrices.add(service.getPrice());
        }

        TextView placeholderText = findViewById(R.id.placeholder_service_dropdown);
        dropdown = findViewById(R.id.servicedropdown);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, serviceNames);
        dropdown.setAdapter(arrayAdapter);

        dropdown = findViewById(R.id.servicedropdown);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView selectedText = (TextView) view;
                TextView selectedService = findViewById(R.id.Total_textView);
                selectedServiceName[0] = selectedText.getText().toString();

                if(id == 0) {
                    selectedService.setText("Total:");
                    dropdown.setSelection(0, false);
                    placeholderText.setVisibility(View.VISIBLE);
                    return;
                }
                String formattedTotal = "Total: " + String.format("%,.2f", servicePrices.get((int) id));
                selectedService.setText(formattedTotal);
                placeholderText.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                placeholderText.setVisibility(View.VISIBLE);
            }
        });

        bookAppointment.setOnClickListener(v ->
            {
                String date = weeklyPlan.getDate(Integer.parseInt(selectedIndex[0]));
                try {
                    boolean flag = okHttpHandler.bookAppointment("http://" + myIP + "/r9SecondBookAppointment.php?time=" + selectedHour[0] + "&date=" + date + "&tos=" + selectedServiceName[0] + "&clinicVATNumber=" + clinicVAT + "&accepted=False");

                    if(flag) {
                        Toast.makeText(getApplicationContext(), "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Something went wrong!Try again later", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }

    private void addAppointments(ArrayList<Appointment> appointments, WeeklyPlan weeklyPlan, int index) {
        ArrayList<Button> tempArray = this.hourButtons;
        for(Appointment app : appointments) {
            if(weeklyPlan.getName(index, app.getDate())) {
                checkAcceptedTimes(app, tempArray);
            }
        }
    }

    private void checkAcceptedTimes(Appointment app, ArrayList<Button> tempArray) {

        Iterator<Button> iter = tempArray.iterator();
        while(iter.hasNext()) {
            Button btn = iter.next();
            if(btn.getText().equals(app.getTime())) {
                if(setButtonBG(btn, app.isAccepted())) {
                    iter.remove();
                }
            }
        }

    }

    private boolean setButtonBG(Button button, Boolean isAccepted) {
        if(isAccepted) {
            button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_disabled));
            button.setEnabled(false);
            return true;
        }
        else {
            button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_default));
            return false;
        }
    }

    private void resetWeeklyButtonClicked() {

        for(Button btn : this.totalDayButtons) {
            btn.setBackground(this.defaultBackground);
        }
    }

    private void resetHourButtonClicked() {
        for (Button btn : this.hourButtons) {

            btn.setEnabled(true);
            btn.setClickable(true);
            btn.setBackground(this.defaultHourBtnBackground);
        }
    }
}