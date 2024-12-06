package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTenant extends AppCompatActivity {

    EditText name, phone, leaseDuration, address, rent;
    Button submitButton;
    FirebaseDatabase database;
    DatabaseReference tenantReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant);

        // Initialize views
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        leaseDuration = findViewById(R.id.leaseDuration);
        address = findViewById(R.id.address);
        rent = findViewById(R.id.rent);
        submitButton = findViewById(R.id.submitButton);

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance();
        tenantReference = database.getReference("tenants");

        // Set button click listener
        submitButton.setOnClickListener(v -> {
            // Retrieve input values
            String tenantName = name.getText().toString().trim();
            String tenantPhone = phone.getText().toString().trim();
            String tenantLeaseDurationStr = leaseDuration.getText().toString().trim();
            String tenantAddress = address.getText().toString().trim();
            String tenantRentStr = rent.getText().toString().trim();

            // Validate inputs
            if (tenantName.isEmpty() || tenantPhone.isEmpty() || tenantLeaseDurationStr.isEmpty() ||
                    tenantAddress.isEmpty() || tenantRentStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Convert numeric inputs
            int tenantLeaseDuration, tenantRent;
            try {
                tenantLeaseDuration = Integer.parseInt(tenantLeaseDurationStr);
                tenantRent = Integer.parseInt(tenantRentStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid input for lease duration or rent. Please enter numeric values.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create tenant helper object
            TenantHelper tenantHelper = new TenantHelper(tenantName, tenantAddress, tenantLeaseDuration, tenantRent, tenantPhone);

            // Generate a unique key for each tenant
            String tenantId = tenantReference.push().getKey();
            if (tenantId != null) {
                tenantReference.child(tenantId).setValue(tenantHelper).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Tenant added successfully!", Toast.LENGTH_SHORT).show();
                        // Reset input fields
                        name.setText("");
                        phone.setText("");
                        leaseDuration.setText("");
                        address.setText("");
                        rent.setText("");
                        Intent intent = new Intent(AddTenant.this, ListTenantByAdmin.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to add tenant. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e ->
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            } else {
                Toast.makeText(this, "Failed to generate tenant ID.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

