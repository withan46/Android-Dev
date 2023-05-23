package com.example.androiddev;

public class UsersR1 {

    protected String email;
    protected String password;

    protected String name;
    public UsersR1(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNames() { return name;}
}
