package com.example.androiddev;

public class Service {

    private String code;
    private String name;
    private String description;
    private float price;
    private String clinic_vat_number;

    public Service(String code, String name, String description, float price, String clinic_vat_number) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.clinic_vat_number = clinic_vat_number;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getClinic_vat_number() {
        return clinic_vat_number;
    }
}
