package com.example.androiddev;

public class AcceptedAppointments {
    private String patientName;
    private String appointmentDate;
    private String appointmentTime;
    private String typeOfService;
    private String description;
    private String ssn;


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

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
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

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
