package com.example.androiddev;

import java.util.ArrayList;
import java.util.List;

public class UserListR1 {

    ArrayList<UsersR1> userList = new ArrayList<UsersR1>();

    public UserListR1(String ip){
        String url= "http://"+ip+"/flexFitDBServices/patient_data.php";

        try {
            OkHttpHandlerR1 okHttpHandler = new OkHttpHandlerR1();
            userList = okHttpHandler.patient_data(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllEmails() {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<userList.size(); i++) {
            temp.add(userList.get(i).getEmail());
        }

        return temp;
    }

    public List<String> getAllPassword() {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<userList.size(); i++) {
            temp.add(userList.get(i).getPassword());
        }
        return temp;
    }

    public List<String> getAllNames() {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<userList.size(); i++) {
            temp.add(userList.get(i).getNames());
        }
        return temp;
    }
}
