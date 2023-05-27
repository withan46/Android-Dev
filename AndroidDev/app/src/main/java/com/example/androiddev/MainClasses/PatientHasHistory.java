package com.example.androiddev.MainClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.androiddev.R;

import java.util.List;

public class PatientHasHistory implements Parcelable {
    private String name;
    private String email;
    private final String ssn;
    private final String phoneNumber;
    private final String Case;
    private final String nextAppointment;
    private String nextAppointmentTime;
    private final List<String> history;

    public PatientHasHistory(String name, String email, String ssn, String phoneNumber, String nextAppointment, String nextAppointmentTime, String Case, List<String> history) {
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

    public String getNextAppointmentTime() {
        return nextAppointmentTime;
    }

    public String getCase() {
        return Case;
    }

    public List<String> getHistory() {
        return history;
    }

    // public Date getDate() { return date; }

    // Methods required by Parcelable interface
    protected PatientHasHistory(Parcel in) {
        name = in.readString();
        email = in.readString();
        ssn = in.readString();
        phoneNumber = in.readString();
        nextAppointment = in.readString();
        Case = in.readString();
        history = in.createStringArrayList();
    }

    public static final Creator<PatientHasHistory> CREATOR = new Creator<PatientHasHistory>() {
        @Override
        public PatientHasHistory createFromParcel(Parcel in) {
            return new PatientHasHistory(in);
        }

        @Override
        public PatientHasHistory[] newArray(int size) {
            return new PatientHasHistory[size];
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

    public int getImageResource() {
        return R.mipmap.human_icon;
    }
}
