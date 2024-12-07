package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TenantLandingPage extends AppCompatActivity {
    boolean isAdmin;
    String emailUser, passwordUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_landing_page);
        CardView payTenantRent = findViewById(R.id.tenantPayRent);
        isAdmin = getIntent().getBooleanExtra("isAdmin", false);

        emailUser = getIntent().getStringExtra("email");
        passwordUser = getIntent().getStringExtra("password");
        // Set the OnClickListener
        payTenantRent.setOnClickListener(view -> {
            // Start RepairRequest activity
            Intent intent = new Intent(TenantLandingPage.this, TenantPayment.class);
            startActivity(intent);
        });

        // Find the CardView by ID
        CardView tenantRepairCard = findViewById(R.id.tenantRepair);

        // Set the OnClickListener
        tenantRepairCard.setOnClickListener(view -> {
            // Start RepairRequest activity
            Intent intent = new Intent(TenantLandingPage.this, RepairRequest.class);
            startActivity(intent);
        });

        CardView tenantNotification = findViewById(R.id.tenantNotification);

        // Set the OnClickListener
        tenantNotification.setOnClickListener(view -> {
            // Start RepairRequest activity
            Intent intent = new Intent(TenantLandingPage.this, Notification.class);
            startActivity(intent);
        });

        CardView profile = findViewById(R.id.tenantProfile);
        profile.setOnClickListener(view -> {
            Intent intent = new Intent(TenantLandingPage.this, ProfileActivity.class);
            intent.putExtra("email", emailUser);
            intent.putExtra("password", passwordUser);
            startActivity(intent);
        });

    }
}