package com.example.projectmanagementsystem;

public class FirebaseHelper {


    String email, password;


    public FirebaseHelper(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public FirebaseHelper() {
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
