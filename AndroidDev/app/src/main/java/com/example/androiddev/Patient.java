package com.example.androiddev;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Patient implements Parcelable{
    private String name;
    private String email;
    private String ssn;
    private String phoneNumber;
    private String Case;
    private String nextAppointment;
    private String nextAppointmentTime;
    private List<String> history;

    public Patient(String name, String email, String ssn, String phoneNumber, String nextAppointment, String nextAppointmentTime, String Case, List<String> history) {
        this.name = name;
        this.email = email;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
        this.nextAppointment = nextAppointment;
        this.nextAppointmentTime = nextAppointmentTime;
        this.Case = Case;
        this.history = history;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public String getSsn() {
        return ssn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNextAppointment() {
        return nextAppointment;
    }

    public String getNextAppointmentTime() { return nextAppointmentTime;}

    public String getCase() {
        return Case;
    }

    public List<String> getHistory() {
        return history;
    }

    // public Date getDate() { return date; }

    // Methods required by Parcelable interface
    protected Patient(Parcel in) {
        name = in.readString();
        email = in.readString();
        ssn = in.readString();
        phoneNumber = in.readString();
        nextAppointment = in.readString();
        Case = in.readString();
        history = in.createStringArrayList();
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(ssn);
        dest.writeString(phoneNumber);
        dest.writeString(nextAppointment);
        dest.writeString(Case);
        dest.writeStringList(history);
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNextAppointment(String nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public void setCase(String Case) {
        this.Case = Case;
    }

    public int getImageResource(){
        return R.mipmap.human_icon;
    }
}
