package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RentHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_home);  // Set your layout

        // Initialize the button
        Button goBackButton = findViewById(R.id.goRentBackButton);

        // Set the click listener for the "Go Back To Home" button
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the Landing Page
                Intent intent = new Intent(RentHome.this, AdminLandingPage.class); // Replace LandingPageActivity with your actual activity class name
                startActivity(intent);
                finish(); // Optionally finish the current activity so it doesn't stay in the back stack
            }
        });
    }
}

