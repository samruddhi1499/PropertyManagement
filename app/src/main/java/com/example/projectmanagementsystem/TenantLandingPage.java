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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_landing_page);


        // Find the CardView by ID
        CardView tenantRepairCard = findViewById(R.id.tenantRepair);

        // Set the OnClickListener
        tenantRepairCard.setOnClickListener(view -> {
            // Start RepairRequest activity
            Intent intent = new Intent(TenantLandingPage.this, RepairRequest.class);
            startActivity(intent);
        });
    }
}