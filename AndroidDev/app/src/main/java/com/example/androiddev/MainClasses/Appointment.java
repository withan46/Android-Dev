package com.example.androiddev.MainClasses;

public class Appointment {
    private final String patient_name;
    private final int id;
    private String date;
    private String time;
    private final String tos;
    private final boolean accepted;


    public Appointment(String ssn, String name, String appointment_date, String appointment_time, String appointment_note, int appointment_id, boolean accepted){
        this.patient_name = name;


        this.id = appointment_id;
        this.date = appointment_date;
        this.time = appointment_time;
        this.tos = appointment_note;
        this.accepted = accepted;
    }


    public void setDate(String date){ this.date = date;}
    public void setTime(String time){this.time = time;}


    public String getPatient_name() {
        return patient_name;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getTos() {
        return tos;
    }

    public int getAppointment_id(){
        return id;
    }

    public int getId() {
        return id;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
