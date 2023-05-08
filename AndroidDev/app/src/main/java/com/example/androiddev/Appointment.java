package com.example.androiddev;

public class Appointment {
    String time;
    String date;
    String tos;
    int clinic_vat_NUMBER;
    boolean accepted;

    public Appointment(String time, String date, String tos, int clinic_vat_NUMBER, boolean accepted) {
        this.time = time;
        this.date = date;
        this.tos = tos;
        this.clinic_vat_NUMBER = clinic_vat_NUMBER;
        this.accepted = accepted;
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

    public String getTos() {
        return tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    public int getClinic_vat_NUMBER() {
        return clinic_vat_NUMBER;
    }

    public void setClinic_vat_NUMBER(int clinic_vat_NUMBER) {
        this.clinic_vat_NUMBER = clinic_vat_NUMBER;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
