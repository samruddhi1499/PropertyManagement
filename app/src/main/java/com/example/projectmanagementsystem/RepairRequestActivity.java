package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RepairRequestActivity extends AppCompatActivity {

    private EditText roomNoEditText, titleEditText, descriptionEditText;
    private Button submitButton;
    private TextView serviceNameTextView;

    private DatabaseReference adminDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_request_form); // Layout with your form design

        // Initialize Firebase reference for admin repairs node
        adminDatabaseRef = FirebaseDatabase.getInstance().getReference("admin_repairs");

        // Get the repair type from the intent
        String repairType = getIntent().getStringExtra("SERVICE_NAME");

        // Initialize form fields
        serviceNameTextView = findViewById(R.id.serviceNameTextView);
        roomNoEditText = findViewById(R.id.roomNoEditText); // Replace with actual ID from your design
        titleEditText = findViewById(R.id.titleEditText); // Replace with actual ID from your design
        descriptionEditText = findViewById(R.id.descriptionEditText); // Replace with actual ID from your design
        submitButton = findViewById(R.id.submitButton); // Replace with actual ID from your design

        // Set the service name in the TextView
        if (repairType != null) {
            serviceNameTextView.setText(repairType);
        }

        // Set up the submit button
        submitButton.setOnClickListener(v -> {
            String roomNo = roomNoEditText.getText().toString().trim();
            String title = titleEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();

            if (roomNo.isEmpty() || title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                saveRepairRequest(repairType, roomNo, title, description);
            }
        });
    }

    private void saveRepairRequest(String repairType, String roomNo, String title, String description) {
        // Generate a unique ID for the repair request
        String repairId = adminDatabaseRef.push().getKey();

        // Create a HashMap to store repair data
        HashMap<String, String> repairData = new HashMap<>();
        repairData.put("repairType", repairType);
        repairData.put("roomNo", roomNo);
        repairData.put("title", title);
        repairData.put("description", description);
        repairData.put("status", "pending"); // Default status


        // Save the data under the unique ID
        adminDatabaseRef.child(repairId).setValue(repairData)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Repair request submitted successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the form and return to the previous page
                })
                .addOnFailureListener(e -> {
                    Log.e("Firebase", "Error saving repair request", e);
                    Toast.makeText(this, "Failed to submit repair request", Toast.LENGTH_SHORT).show();
                });
    }
}
