package com.example.androiddev.MainClasses;

public class History {
    private String patientSSN;
    private final String tos;
    private String time;
    private String date;
    private String description;

    public History(String patientSSN, String tos, String time, String date, String description){
        this.patientSSN = patientSSN;
        this.tos = tos;
        this.time = time;
        this.date = date;
        this.description = description;
    }

    public String getPatientSSN() {
        return patientSSN;
    }

    public void setPatientSSN(String patientSSN) {
        this.patientSSN = patientSSN;
    }

    public String getTos() {
        return tos;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
