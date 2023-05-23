package com.example.androiddev;

public class weeklyPlannerDataR6 {
    private String time;
    private String patient_name;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public weeklyPlannerDataR6(String time, String patient_name, String description){
        this.time = time;
        this.patient_name = patient_name;
        this.description = description;

    }
}
