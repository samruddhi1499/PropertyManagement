package com.example.projectmanagementsystem;

public class FirebaseHelper {


    String email, password;
    Boolean isChecked;


    public FirebaseHelper(String email, String password, Boolean isChecked) {
        this.email = email;
        this.password = password;
        this.isChecked = isChecked;
    }

    public FirebaseHelper() {
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
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
