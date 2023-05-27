package com.example.androiddev.MainClasses;

public class AcceptedAppointments {
    private String patientName;
    private final String appointmentDate;
    private final String appointmentTime;
    private final String typeOfService;
    private String description;
    private final String ssn;


    public AcceptedAppointments(String patientName, String ssn, String appointmentDate, String appointmentTime,
                                String typeOfService, String description){
        this.patientName = patientName;
        this.ssn = ssn;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.typeOfService = typeOfService;
        this.description = description;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSsn() {
        return ssn;
    }

}
