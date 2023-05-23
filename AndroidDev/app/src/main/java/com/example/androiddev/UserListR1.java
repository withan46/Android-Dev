package com.example.androiddev;

import java.util.ArrayList;
import java.util.List;

public class UserListR1 {

    ArrayList<UsersR1> userList = new ArrayList<UsersR1>();

    public UserListR1(String ip){
        String url= "http://"+ip+"/flexFitDBServices/user_data.php";

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
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
