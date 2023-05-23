package com.example.androiddev;

public class Doctor {

    private String vatRegNum;
    private String name;
    private String surname;
    private String email;


    private String clinicVatNumber;
    public Doctor(String vatRegNum, String name, String surname, String email, String clinicVatNumber) {
        this.vatRegNum = vatRegNum;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.clinicVatNumber = clinicVatNumber;
    }



    public String getVatRegNum() {
        return vatRegNum;
    }

    public void setVatRegNum(String vatRegNum) {
        this.vatRegNum = vatRegNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClinicVatNumber() {
        return clinicVatNumber;
    }

    public void setClinicVatNumber(String clinicVatNumber) {
        this.clinicVatNumber = clinicVatNumber;
    }
}
