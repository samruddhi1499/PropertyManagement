package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminLandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_landing_page);

        // Find the CardView by ID for Lease Tracking
        CardView leaseCard = findViewById(R.id.downloadCard);
        leaseCard.setOnClickListener(view -> {
            // Launch the LeaseTracking Activity
            Intent intent = new Intent(AdminLandingPage.this, LeaseTracking.class);
            startActivity(intent);
        });

        // Find the CardView by ID for Repair Home
        CardView repairCard = findViewById(R.id.docCard);
        repairCard.setOnClickListener(view -> {
            // Launch the RepairHome Activity
            Intent intent = new Intent(AdminLandingPage.this, RepairHome.class);
            startActivity(intent);
        });

        // Find the CardView by ID for Rent (videoCard)
        CardView rentCard = findViewById(R.id.videoCard);  // "Rent" card
        rentCard.setOnClickListener(view -> {
            // Launch the Rent Activity (you need to create this activity)
            Intent intent = new Intent(AdminLandingPage.this, RentHome.class); // Replace RentActivity with actual activity name
            startActivity(intent);
        });
    }
}
