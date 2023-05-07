package com.example.androiddev;

import android.os.Build;

import java.time.LocalDate;

public class WeeklyPlan {

    public LocalDate localDate;

    public WeeklyPlan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.localDate = LocalDate.now();
        }
    }

    public String getCurrentMonth() {
        String month, capitalizedMonth = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            month = localDate.getMonth().toString();
            capitalizedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        }
        return capitalizedMonth;
    }

    public String[] getWeekDays() {
        Integer[] days = new Integer[7];
        String[] nameDay = new String[7];

        for (int i = 0; i < 7; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                days[i] = localDate.plusDays(i).getDayOfMonth();
                String tempName = localDate.plusDays(i).getDayOfWeek().toString().substring(0, 3);
                nameDay[i] = tempName + "\n" + days[i].toString();
            }
        }
        return nameDay;
    }

}
