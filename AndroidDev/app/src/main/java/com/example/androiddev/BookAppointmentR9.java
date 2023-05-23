package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BookAppointmentR9 extends AppCompatActivity {

    private ArrayList<Button> hourButtons, totalHourButtons, totalDayButtons;

    private Button bookAppointment;

    private Drawable defaultBackground;

    private Drawable defaultHourBtnBackground;

    private Spinner dropdown;

    private WeeklyPlan weeklyPlan;

    private OkHttpHandler okHttpHandler;

    //Will be received from previous R and IP from Main activity//
    private String myIP;
    private String patient_ssn;
    //----------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_r9_book_appointment);

        //Receiving data from previous (r9first) clinicVAT//
        Intent intent = getIntent();
        String clinicVAT = intent.getStringExtra("clinicVAT");
        //------------------------------------------------//

        //Will be received from previous R and IP from Main activity//
        this.myIP = "192.168.2.4";
        this.patient_ssn = "111111111";
        //----------------------------------------------------------//

        //Declaration of variables (Buttons, Background, etc)//
        this.defaultBackground = ContextCompat.getDrawable(this, R.drawable.weekplanbuttondefault);
        this.defaultHourBtnBackground = ContextCompat.getDrawable(this, R.drawable.hour_button_default);

        final String[] selectedIndex = new String[1];
        final String[] selectedHour = new String[1];
        final String[] selectedServiceName = new String[1];

        this.weeklyPlan = new WeeklyPlan();
        this.okHttpHandler = new OkHttpHandler();

        Button tenHourBtn = findViewById(R.id.tenAM_button);
        Button elevenHourBtn = findViewById(R.id.elevenAM_button);
        Button twelveHourBtn = findViewById(R.id.twelvePM_button);
        Button thirteenHourBtn = findViewById(R.id.thirteenPM_button);
        Button fourteenHourBtn = findViewById(R.id.fourteenPM_button);
        Button fifthteenHourBtn = findViewById(R.id.fifthteenPM_button);
        Button sixteenHourBtn = findViewById(R.id.sixteenPM_button);
        Button seventeenHourBtn = findViewById(R.id.seventeenPM_button);

        Button firstBtn = findViewById(R.id.day1_button);
        Button secondBtn = findViewById(R.id.day2_button);
        Button thirdBtn = findViewById(R.id.day3_button);
        Button fourthBtn = findViewById(R.id.day4_button);
        Button fifthBtn = findViewById(R.id.day5_button);
        Button sixthBtn = findViewById(R.id.day6_button);
        Button seventhBtn = findViewById(R.id.day7_button);

        this.bookAppointment = findViewById(R.id.book_appointment);

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

        this.totalDayButtons = new ArrayList<>();
        this.totalDayButtons.add(firstBtn);
        this.totalDayButtons.add(secondBtn);
        this.totalDayButtons.add(thirdBtn);
        this.totalDayButtons.add(fourthBtn);
        this.totalDayButtons.add(fifthBtn);
        this.totalDayButtons.add(sixthBtn);
        this.totalDayButtons.add(seventhBtn);
        //---------------------------------------------------//

        //Setting month text//
        TextView monthTextView = findViewById(R.id.Month_textView);
        monthTextView.setText(getCurrentMonth());
        //------------------//

        //Getting the name of the days//
        String[] nameDay;
        nameDay = getNameDays();
        //------------//

        //deleting past saved appointments//
        deletePastAppointments();
        //--------------------------------//

        //Setting Listeners to Buttons//
        setTotalDaysListener(clinicVAT, nameDay, selectedIndex);
        setHourDaysListener(selectedHour);
        //----------------------------//

        //Filling services dropdown with services names and also the prices//
        List<Service> services;
        List<String> serviceNames = new ArrayList<>();
        serviceNames.add("");
        List<Double> servicePrices = new ArrayList<>();
        servicePrices.add(0.0);
        services = getServices(clinicVAT);

        for(Service service: services){
            serviceNames.add(service.getName());
            servicePrices.add(service.getPrice());
        }

        TextView placeholderText = findViewById(R.id.placeholder_service_dropdown);
        dropdown = findViewById(R.id.servicedropdown);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, serviceNames);
        dropdown.setAdapter(arrayAdapter);
        //-----------------------------------------------------------------//

        //Applying Listener to dropdown of services//
        setDropDownListener(selectedServiceName, servicePrices, placeholderText);
        //-----------------------------------------//

        //Book appointment Button adding listener//
        bookAppointmentListener(selectedIndex, selectedHour, selectedServiceName, clinicVAT);
        //---------------------------------------//
    }

    private void addAppointments(ArrayList<AppointmentR9> appointments, WeeklyPlan weeklyPlan, int index) {
        ArrayList<Button> tempArray = this.hourButtons;
        for(AppointmentR9 app : appointments) {
            if(weeklyPlan.getName(index, app.getDate())) {
                checkAcceptedTimes(app, tempArray);
            }
        }
    }

    //--This method checks if the days have some appointment in a specific hour. If it does, the button becomes disabled and red--//
    private void checkAcceptedTimes(AppointmentR9 app, ArrayList<Button> tempArray) {
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

    //--Setting the background of the button if it has an appointment on this specific hour-//
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

    //--Resetting the background of the buttons if the day changes-//
    private void resetWeeklyButtonClicked() {

        for(Button btn : this.totalDayButtons) {
            btn.setBackground(this.defaultBackground);
        }
    }

    //--Resetting the background of the hours buttons while trying to pick 1 hour and not more than 1-//
    private void resetHourButtonClicked() {
        for (Button btn : this.hourButtons) {

            btn.setEnabled(true);
            btn.setClickable(true);
            btn.setBackground(this.defaultHourBtnBackground);
        }
    }

    //--Deleting past appointments-//
    private void deletePastAppointments() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                okHttpHandler.deletePastAppointments("http://" + myIP + "/flexFitDBServices/r9DeleteAppointments.php?localDate=" + LocalDate.now());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //--Receiving all the appointments-//
    private ArrayList<AppointmentR9> getAppointments(String clinicVAT) {
        ArrayList<AppointmentR9> appointments;

        try {
            appointments = okHttpHandler.getClinicAppointments("http://" + myIP + "/flexFitDBServices/r9GetAppointments.php?clinicVATNumber=" + clinicVAT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return appointments;
    }

    //--Receiving all the services-//
    private ArrayList<Service> getServices(String clinicVAT) {
        ArrayList<Service> services;

        try {
            services = okHttpHandler.populateServiceDropDown("http://" + myIP + "/flexFitDBServices/r9GetServices.php?clinicVATNumber=" + clinicVAT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return services;
    }

    //--Receiving all days name-//
    private String[] getNameDays() {
        String[] nameDays;
        nameDays = this.weeklyPlan.getWeekDays();

        return nameDays;
    }

    //--Receiving current month-//
    private String getCurrentMonth() {
        return this.weeklyPlan.getCurrentMonth();
    }

    private void setTotalDaysListener(String clinicVAT, String[] nameDay, String[] selectedIndex) {
        ArrayList<AppointmentR9> appointments = getAppointments(clinicVAT);

        for(int i=0;i<this.totalDayButtons.size();i++) {
            int index = i;
            this.totalDayButtons.get(i).setText(nameDay[i]);
            this.totalDayButtons.get(i).setOnClickListener(v -> {
                resetWeeklyButtonClicked();
                this.hourButtons = new ArrayList<>(this.totalHourButtons);
                resetHourButtonClicked();

                this.totalDayButtons.get(index).setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.weekplanbuttonpressed));
                selectedIndex[0] = String.valueOf(index);
                addAppointments(appointments, weeklyPlan, index);
            });
        }
    }

    private void setHourDaysListener(String[] selectedHour) {
        for(Button btn : this.hourButtons) {
            btn.setOnClickListener(v -> {
                resetHourButtonClicked();
                btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.hour_button_pressed));
                selectedHour[0] = btn.getText().toString();
            });
        }
    }

    private void setDropDownListener(String[] selectedServiceName, List<Double> servicePrices, TextView placeholderText) {
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
    }

    private void bookAppointmentListener(String[] selectedIndex, String[] selectedHour, String[] selectedServiceName, String clinicVAT ) {
        this.bookAppointment.setOnClickListener(v ->
                {
                    if(selectedIndex[0] == null || selectedHour[0] == null || selectedServiceName[0].equals("")){
                        Toast.makeText(getApplicationContext(), "Invalid input! Try to select day, hour and service", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String date = weeklyPlan.getDate(Integer.parseInt(selectedIndex[0]));
                        try {
                            boolean flag = okHttpHandler.bookAppointment("http://" + myIP + "/flexFitDBServices/r9BookAppointment.php?time=" + selectedHour[0] + "&date=" + date + "&tos=" + selectedServiceName[0] + "&clinicVATNumber="  + clinicVAT + "&patient_ssn=" + this.patient_ssn + "&accepted=False");

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
                }
        );
    }
}