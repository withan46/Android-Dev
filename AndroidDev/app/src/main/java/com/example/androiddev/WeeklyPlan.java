package com.example.androiddev;

import android.os.Build;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class WeeklyPlan {

    private LocalDate localDate;

    private ArrayList<String> dates;

    public WeeklyPlan() {
        this.dates = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.localDate = LocalDate.now();
        }
    }

    //--This method returns current local month--//
    public String getCurrentMonth() {
        String month, capitalizedMonth = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            month = localDate.getMonth().toString();
            capitalizedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        }
        return capitalizedMonth;
    }

    //--This method gets the names and the days of the next 7 days (SUN 19)--//
    public String[] getWeekDays() {
        Integer[] days = new Integer[7];
        String[] nameDay = new String[7];

        for (int i = 0; i < 7; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                days[i] = localDate.plusDays(i).getDayOfMonth();
                String tempName = localDate.plusDays(i).getDayOfWeek().toString().substring(0, 3);
                nameDay[i] = tempName + "\n" + days[i].toString();
                dates.add(localDate.plusDays(i).toString());
            }
        }
        return nameDay;
    }

    public boolean getName(int index, String dateString) {

        return dateString.equals(dates.get(index));
    }

    public String getDate(int index) {
        return this.dates.get(index);
    }
}
