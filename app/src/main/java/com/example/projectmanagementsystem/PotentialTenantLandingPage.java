package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PotentialTenantLandingPage extends AppCompatActivity {

    boolean isAdmin;
    String emailUser, passwordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_potential_tenant_landing_page);

        isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        emailUser = getIntent().getStringExtra("email");
        passwordUser = getIntent().getStringExtra("password");

        CardView listingPCard = findViewById(R.id.tenantPlisting);
        CardView contactCard = findViewById(R.id.tenantPContact);
        CardView profileCard = findViewById(R.id.tenantPProfile);
        CardView settingsCard = findViewById(R.id.tenantPsettings);

        listingPCard.setOnClickListener(view -> {
            // Navigate to Notification Activity and pass the isAdmin flag
            Intent intent = new Intent(PotentialTenantLandingPage.this, Listings.class);
            intent.putExtra("isAdmin", isAdmin);
            startActivity(intent);
        });


        contactCard.setOnClickListener(view -> {
            // Navigate to RentHome Activity
            Intent intent = new Intent(PotentialTenantLandingPage.this, contactPage.class);
            startActivity(intent);
        });



        profileCard.setOnClickListener(view -> {
            // Navigate to RentHome Activity
            Intent intent = new Intent(PotentialTenantLandingPage.this, ProfileActivity.class);
            intent.putExtra("email", emailUser);
            intent.putExtra("password", passwordUser);
            startActivity(intent);
        });



        settingsCard.setOnClickListener(view -> {
            // Navigate to RentHome Activity
            Intent intent = new Intent(PotentialTenantLandingPage.this, TermsAndConditionsActivity.class);
            startActivity(intent);
        });


    }
}