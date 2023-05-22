package com.example.androiddev;

import static com.example.androiddev.calendarUtilsR6.daysInWeekArray;
import static com.example.androiddev.calendarUtilsR6.monthYearFromDate;
import static com.example.androiddev.calendarUtilsR6.selectedDate;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class mainActivityR6 extends AppCompatActivity implements calendarAdapterR6.OnItemListener{
    ArrayList<createAppointmentItemsR6> appointments = new ArrayList<>(); // ArrayList to store appointment items
    private TextView monthYearText; // TextView to display month and year
    private RecyclerView calendarRecyclerView; // RecyclerView to display the calendar
    private String myIP, clinic_vat_number; // Strings for IP address and clinic VAT number
    private ArrayList<weeklyPlannerDataR6> weeklyPlannerDatumR6s; // ArrayList to store weekly planner data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_planner_r6);
        this.myIP = "192.168.0.103"; // Initialize IP address
        this.clinic_vat_number = "10000"; // Initialize clinic VAT number
        weeklyPlannerDatumR6s = new ArrayList<>(); // Initialize weekly planner data ArrayList

        initWidgets(); // Initialize UI widgets
        setWeekView(); // Set up the week view for the calendar
    }

    private void initWidgets() {
        // Get reference to the calendar RecyclerView
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        // Get reference to the month and year TextView
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setWeekView() {
        // Set the month and year text based on the selected date
        monthYearText.setText(monthYearFromDate(calendarUtilsR6.selectedDate));
        // Get the list of days in the selected week
        ArrayList<LocalDate> days = daysInWeekArray(calendarUtilsR6.selectedDate);

        // Create a calendar adapter with the days and item click listener
        calendarAdapterR6 calendarAdapterR6 = new calendarAdapterR6(days, this::onItemClick);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        layoutManager.setAlignItems(AlignItems.CENTER);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);

        // Set the layout manager for the calendar RecyclerView
        calendarRecyclerView.setLayoutManager(layoutManager);
        // Set the calendar adapter to the RecyclerView
        calendarRecyclerView.setAdapter(calendarAdapterR6);
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        //dont delete it
    }


    private void setAppointments(String searchingDate) throws IOException {
        OkHttpHandlerR6 okHttpHandler = new OkHttpHandlerR6();
        String url = "http://" + myIP + "/weeklyPlannerR6.php?date=" + '"' +searchingDate + '"'
                + "&clinic=" + clinic_vat_number;

        // Retrieve the weekly planner data from the server using OkHttpHandler
        weeklyPlannerDatumR6s = okHttpHandler.populateWeeklyPlanner(url);

        // Iterate through the retrieved weekly planner data and create appointment items
        for (int i = 0; i < weeklyPlannerDatumR6s.size(); i++) {
            if (weeklyPlannerDatumR6s.get(i).getTime() != null && weeklyPlannerDatumR6s.get(i).getDescription() != null
            && weeklyPlannerDatumR6s.get(i).getPatient_name() != null) {
                appointments.add(new createAppointmentItemsR6(weeklyPlannerDatumR6s.get(i).getTime(),
                        weeklyPlannerDatumR6s.get(i).getPatient_name(), weeklyPlannerDatumR6s.get(i).getDescription(), searchingDate));
            }
            else {
                appointments.add(new createAppointmentItemsR6("none", "None", "None", searchingDate));
            }
        }

        // Set up the RecyclerView to display the appointments
        RecyclerView recyclerView = findViewById(R.id.appointmentsRecyclerView);
        recyclerViewAdapterR6 adapter = new recyclerViewAdapterR6(this, appointments);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void HandleButtonClick(View view)
    {
        Button button = (Button) view;
        TextView textView = findViewById(R.id.monthYearTV);
        DateFormat inputFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        String inputDate = button.getText().toString() + " " + textView.getText().toString();
        try {
            Date date = inputFormat.parse(inputDate);
            String outputDate = outputFormat.format(date);

            appointments.clear();

            // Update appointments based on the selected date
            setAppointments(outputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void previousWeekAction(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.minusWeeks(1);
        }
        setWeekView();
    }

    public void nextWeekAction(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.plusWeeks(1);
        }
        setWeekView();
    }

}