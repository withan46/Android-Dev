package com.example.androiddev;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Patient implements Parcelable{
    private String name;
    private String email;
    private String password;
    private String ssn;
    private String phoneNumber;
    private String Case;
    private String nextAppointment;
    private Integer age;

    // private Date date;

    public Patient(String name, Integer age, String email, String password, String ssn, String phoneNumber, String nextAppointment, String Case) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
        this.nextAppointment = nextAppointment;
        this.Case = Case;
        // this.date = date;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public Integer getAge() {return age;}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public String getCase() {
        return Case;
    }

    // public Date getDate() { return date; }

    // Methods required by Parcelable interface
    protected Patient(Parcel in) {
        name = in.readString();
        //age = in.readInt();
        email = in.readString();
        password = in.readString();
        ssn = in.readString();
        phoneNumber = in.readString();
        nextAppointment = in.readString();
        Case = in.readString();
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
        //dest.writeInt(age);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(ssn);
        dest.writeString(phoneNumber);
        dest.writeString(nextAppointment);
        dest.writeString(Case);
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {this.age = age;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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
