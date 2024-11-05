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
        CardView mwRepairCard = findViewById(R.id.mwRepairCard);
        CardView kitchenRepairCard = findViewById(R.id.kitchenRepairCard);
        CardView plumbingCard = findViewById(R.id.plumbingCard);
        CardView wmRepairCard = findViewById(R.id.wmRepairCard);
        CardView pestControlCard = findViewById(R.id.pestControlCard);

        // Set click listeners on each card
        acRepairCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRepairRequestPage("AC Repair"); // Pass the service name as needed
            }
        });
        mwRepairCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRepairRequestPage("Microwave Repair"); // Pass the service name as needed
            }
        });
        kitchenRepairCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRepairRequestPage("Kitchen Repair & Installation"); // Pass the service name as needed
            }
        });
        plumbingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRepairRequestPage("Plumbing"); // Pass the service name as needed
            }
        });
        wmRepairCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRepairRequestPage("Washing Machine Repair"); // Pass the service name as needed
            }
        });
        pestControlCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRepairRequestPage("Pest Control"); // Pass the service name as needed
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
