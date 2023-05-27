package com.example.androiddev.MainClasses;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Patient implements Parcelable {

    private String ssn;
    private String email;
    private final String name;
    private String phoneNumber;
    private String vatRegNumber;


    public Patient(String ssn, String email, String name, String phoneNumber, String vatRegNumber){
        this.ssn = ssn;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vatRegNumber = vatRegNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVatRegNumber() {
        return vatRegNumber;
    }

    public void setVatRegNumber(String vatRegNumber) {
        this.vatRegNumber = vatRegNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(ssn);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(vatRegNumber);
    }

    // Methods required by Parcelable interface
    protected Patient(Parcel in) {
        name = in.readString();
        email = in.readString();
        ssn = in.readString();
        phoneNumber = in.readString();
        vatRegNumber = in.readString();
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
}
