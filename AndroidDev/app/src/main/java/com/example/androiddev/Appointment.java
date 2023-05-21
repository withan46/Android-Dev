package com.example.androiddev;

public class Appointment {

    private String patient_ssn;
    private String patient_name;
    private int id;
    private String date;
    private String time;
    private String tos;


    public Appointment(String ssn, String name, String appointment_date, String appointment_time, String appointment_note, int appointment_id){
        this.patient_ssn = ssn;
        this.patient_name = name;


        this.id = appointment_id;
        this.date = appointment_date;
        this.time = appointment_time;
        this.tos = appointment_note;
    }


    public void setSSN(String ssn){ this.patient_ssn = ssn;}

    public void setPatient_name(String patient_name){ this.patient_name = patient_name;}

    public void setDate(String date){ this.date = date;}
    public void setTime(String time){this.time = time;}
    public void setTos(String tos){this.tos = tos;}
    public void setAppointment_id(int appointment_id){this.id = appointment_id;}

    public String getSsn() {return patient_ssn;}



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



}
