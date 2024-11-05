package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RepairRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_request); // Main screen layout with cards

        // Card view references
        CardView acRepairCard = findViewById(R.id.acRepairCard);

        // Set click listeners on each card
        acRepairCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRepairRequestPage("AC Repair"); // Pass the service name as needed
            }
        });
    }

    // Method to open RepairRequestActivity with the service name
    private void openRepairRequestPage(String serviceName) {
        Intent intent = new Intent(RepairRequest.this, RepairRequestActivity.class);
        intent.putExtra("SERVICE_NAME", serviceName);
        startActivity(intent);
    }
}
