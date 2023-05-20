package com.example.androiddev;

public class Patient {

    private String name;
    private String email;
    private int phoneNumber;
    private int SSN;

    public Patient(String name, String email, int phoneNumber, int SSN) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.SSN = SSN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }
}
