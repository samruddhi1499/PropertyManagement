package com.example.projectmanagementsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;



public class ProfileActivity extends AppCompatActivity {

    TextView profileEmail, profilePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileEmail = findViewById(R.id.profileUsername);
        profilePassword = findViewById(R.id.profilePassword);

        showAllUserData();

    }

    public void showAllUserData(){
        Intent intent = getIntent();
        String emailUser = intent.getStringExtra("email");
        String passwordUser = intent.getStringExtra("password");

        profileEmail.setText(emailUser);
        profilePassword.setText(passwordUser);
    }

}