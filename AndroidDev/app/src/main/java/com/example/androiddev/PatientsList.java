/*package com.example.androiddev;

import java.util.ArrayList;

public class PatientsList {

    public PatientsList(String ip) {
        String url= "http://"+ip+"/carsDBServices/populateDropDown.php";
        ArrayList<Patient> patientsList = new ArrayList<>();

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            patientsList = okHttpHandler.populateScrollView(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}*/
