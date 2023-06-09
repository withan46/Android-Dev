package com.example.androiddev.Activities;

import static com.example.androiddev.Adapters.CalendarUtilsR6.daysInWeekArray;
import static com.example.androiddev.Adapters.CalendarUtilsR6.monthYearFromDate;
import static com.example.androiddev.Adapters.CalendarUtilsR6.selectedDate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddev.Adapters.CalendarAdapterR6;
import com.example.androiddev.Adapters.CalendarUtilsR6;
import com.example.androiddev.HelpfulClasses.CreateAppointmentItemsR6;
import com.example.androiddev.OkHttpHandler;
import com.example.androiddev.R;
import com.example.androiddev.Adapters.recyclerViewAdapterR6;
import com.example.androiddev.HelpfulClasses.weeklyPlannerDataR6;
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

public class WeeklyPlannerR6 extends AppCompatActivity implements CalendarAdapterR6.OnItemListener {
    ArrayList<CreateAppointmentItemsR6> appointments = new ArrayList<>(); // ArrayList to store appointment items
    private TextView monthYearText; // TextView to display month and year
    private RecyclerView calendarRecyclerView; // RecyclerView to display the calendar
    private String myIP, clinic_vat_number; // Strings for IP address and clinic VAT number
    private ArrayList<weeklyPlannerDataR6> weeklyPlannerDatumR6s; // ArrayList to store weekly planner data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_planner_r6);
        Intent intent = getIntent();

        this.myIP = intent.getStringExtra("Ip"); // Initialize IP address
        this.clinic_vat_number = intent.getStringExtra("clinic_vat_number"); // Initialize clinic VAT number
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
        monthYearText.setText(monthYearFromDate(CalendarUtilsR6.selectedDate));
        // Get the list of days in the selected week
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtilsR6.selectedDate);

        // Create a calendar adapter with the days and item click listener
        CalendarAdapterR6 calendarAdapterR6 = new CalendarAdapterR6(days, this::onItemClick);

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
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        String url = "http://" + myIP + "/flexFitDBServices/weeklyPlannerR6.php?date=" + '"' +searchingDate + '"'
                + "&clinic=" + clinic_vat_number;

        // Retrieve the weekly planner data from the server using OkHttpHandler
        weeklyPlannerDatumR6s = okHttpHandler.populateWeeklyPlanner(url);

        // Iterate through the retrieved weekly planner data and create appointment items
        for (int i = 0; i < weeklyPlannerDatumR6s.size(); i++) {
            if (weeklyPlannerDatumR6s.get(i).getTime() != null && weeklyPlannerDatumR6s.get(i).getDescription() != null
            && weeklyPlannerDatumR6s.get(i).getPatient_name() != null) {
                appointments.add(new CreateAppointmentItemsR6(weeklyPlannerDatumR6s.get(i).getTime(),
                        weeklyPlannerDatumR6s.get(i).getPatient_name(), weeklyPlannerDatumR6s.get(i).getDescription(), searchingDate));
            }
            else {
                appointments.add(new CreateAppointmentItemsR6("none", "None", "None", searchingDate));
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