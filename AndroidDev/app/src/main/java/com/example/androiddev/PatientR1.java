package com.example.androiddev;

public class PatientR1 {

    private String ssn;
    private String email;
    private String phoneNumber;


    private String vatRegNumber;
    public PatientR1(String ssn, String email, String name, String phoneNumber, String vatRegNumber){
        this.ssn = ssn;
        this.email = email;
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

}
