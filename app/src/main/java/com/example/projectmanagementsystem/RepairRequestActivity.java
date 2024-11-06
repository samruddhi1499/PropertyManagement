package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RepairRequestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_request_form); // Make sure this matches the layout XML for the detail page

        // Get the service name from the intent
        String serviceName = getIntent().getStringExtra("SERVICE_NAME");

        // Set the service name in a TextView (update the ID to match your layout)
        TextView serviceTextView = findViewById(R.id.serviceNameTextView); // Replace with actual ID in your layout
        if (serviceTextView != null && serviceName != null) {
            serviceTextView.setText(serviceName);
        }
    }
}
