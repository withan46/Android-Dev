package com.example.androiddev;

public class Clinic {
    private int vatNumber;
    private String name;
    private String address;
    private String phoneNumber;

    private String email;

    public Clinic(int vatNumber, String name, String address, String phoneNumber, String email) {
        this.vatNumber = vatNumber;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(int vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
