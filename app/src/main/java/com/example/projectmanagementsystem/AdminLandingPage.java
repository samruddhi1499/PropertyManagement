package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminLandingPage extends AppCompatActivity {

    boolean isAdmin;
    String emailUser, passwordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_landing_page);

        // Receive the data from the intent
        isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        emailUser = getIntent().getStringExtra("email");
        passwordUser = getIntent().getStringExtra("password");

        // Find the CardViews by ID
        CardView leaseCard = findViewById(R.id.leaseCard);
        CardView repairCard = findViewById(R.id.repairAdminCard);
        CardView rentCard = findViewById(R.id.rentsCard);
        CardView notificationCard = findViewById(R.id.notificationAdminCard);
        CardView tenantList = findViewById(R.id.tenantCard);
        CardView profile = findViewById(R.id.profileCard);

        // Set onClickListeners for each CardView
        leaseCard.setOnClickListener(view -> {
            // Navigate to LeaseTracking Activity and pass the required data
            Intent intent = new Intent(AdminLandingPage.this, LeaseTracking.class);
            intent.putExtra("isAdmin", isAdmin);
            intent.putExtra("email", emailUser);
            intent.putExtra("password", passwordUser);
            startActivity(intent);
        });

        repairCard.setOnClickListener(view -> {
            // Navigate to RepairHome Activity
            Intent intent = new Intent(AdminLandingPage.this, RepairHome.class);
            startActivity(intent);
        });

        rentCard.setOnClickListener(view -> {
            // Navigate to RentHome Activity
            Intent intent = new Intent(AdminLandingPage.this, RentHome.class);
            startActivity(intent);
        });

        notificationCard.setOnClickListener(view -> {
            // Navigate to Notification Activity and pass the isAdmin flag
            Intent intent = new Intent(AdminLandingPage.this, Notification.class);
            intent.putExtra("isAdmin", isAdmin);
            startActivity(intent);
        });

        tenantList.setOnClickListener(view -> {
            // Navigate to ListTenantByAdmin Activity
            Intent intent = new Intent(AdminLandingPage.this, ListTenantByAdmin.class);
            startActivity(intent);
        });

        profile.setOnClickListener(view -> {
            // Navigate to ProfileActivity and pass user credentials
            Intent intent = new Intent(AdminLandingPage.this, ProfileActivity.class);
            intent.putExtra("email", emailUser);
            intent.putExtra("password", passwordUser);
            startActivity(intent);
        });
    }
}
