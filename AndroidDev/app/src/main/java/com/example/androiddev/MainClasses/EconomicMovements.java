package com.example.androiddev.MainClasses;
public class EconomicMovements {

    private String date;
    private String description;
    private final double cost;

    public EconomicMovements(String date, String description, double cost) {
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

}
