package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminLandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_landing_page);

        // Find the CardView by ID
        CardView leaseCard = findViewById(R.id.downloadCard);

        // Set the OnClickListener
        leaseCard.setOnClickListener(view -> {
            Intent intent = new Intent(AdminLandingPage.this, LeaseTracking.class);
            startActivity(intent);
        });
        // Find the Repair CardView by ID
        CardView repairCard = findViewById(R.id.docCard);
        repairCard.setOnClickListener(view -> {
            Intent intent = new Intent(AdminLandingPage.this, RepairHome.class);
            startActivity(intent);
        });

    }
}