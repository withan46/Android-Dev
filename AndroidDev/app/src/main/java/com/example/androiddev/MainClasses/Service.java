package com.example.androiddev.MainClasses;

public class Service {

    private String code;
    private String name;

    private String description;
    private final Double price;

    private int clinicVATNumber;

    public Service(String code, String name, String description, Double price, int clinicVATNumber) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.clinicVATNumber = clinicVATNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public int getClinicVATNumber() {
        return clinicVATNumber;
    }

    public void setClinicVATNumber(int clinicVATNumber) {
        this.clinicVATNumber = clinicVATNumber;
    }
}
