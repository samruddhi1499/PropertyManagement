package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminLandingPage extends AppCompatActivity {

    boolean isAdmin;
    String emailUser, passwordUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_landing_page);
        isAdmin = getIntent().getBooleanExtra("isAdmin", false);

        emailUser = getIntent().getStringExtra("email");
        passwordUser = getIntent().getStringExtra("password");

        // Find the CardView by ID
        CardView leaseCard = findViewById(R.id.leaseCard);

        // Set the OnClickListener
        leaseCard.setOnClickListener(view -> {
            Intent intent = new Intent(AdminLandingPage.this, LeaseTracking.class);
            startActivity(intent);
        });
        // Find the Repair CardView by ID
        CardView repairCard = findViewById(R.id.repairAdminCard);
        repairCard.setOnClickListener(view -> {
            Intent intent = new Intent(AdminLandingPage.this, RepairHome.class);
            startActivity(intent);
        });

        // Find the Repair CardView by ID
        CardView rentCard = findViewById(R.id.rentsCard);
        rentCard.setOnClickListener(view -> {
            Intent intent = new Intent(AdminLandingPage.this, RentHome.class);
            startActivity(intent);
        });

        // Find the Repair CardView by ID
        CardView notificationCard = findViewById(R.id.notificationAdminCard);
        notificationCard.setOnClickListener(view -> {
            Intent intent = new Intent(AdminLandingPage.this, Notification.class);
            intent.putExtra("isAdmin", isAdmin);
            startActivity(intent);
        });

        CardView tenantList = findViewById(R.id.tenantCard);
        tenantList.setOnClickListener(view -> {
            Intent intent = new Intent(AdminLandingPage.this, ListTenantByAdmin.class);
            startActivity(intent);
        });

        CardView profile = findViewById(R.id.profileCard);
        profile.setOnClickListener(view -> {
            Intent intent = new Intent(AdminLandingPage.this, ProfileActivity.class);
            intent.putExtra("email", emailUser);
            intent.putExtra("password", passwordUser);
            startActivity(intent);
        });

    }
}