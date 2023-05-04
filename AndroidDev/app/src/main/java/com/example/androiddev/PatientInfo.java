package com.example.androiddev;

public class PatientInfo {
    private String patientName;
    private String appointmentDate;
    private String appointmentTime;

    public PatientInfo(String patientName, String appointmentDate, String appointmentTime){
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }


    public String getPatientName(){return this.patientName;}
    public String getAppointmentDate(){return this.appointmentDate;}
    public String getAppointmentTime(){return this.appointmentTime;}

    public void addAPatient(String patientName, String appointmentDate, String appointmentTime){
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }



}
