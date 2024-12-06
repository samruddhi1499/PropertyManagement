package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmanagementsystem.databinding.ActivityTenantDetailsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TenantDetails extends AppCompatActivity {

    ActivityTenantDetailsBinding binding;
    private DatabaseReference databaseReference;
    private String tenantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTenantDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("tenants");

        // Get data from the Intent
        Intent intent = this.getIntent();
        if (intent != null) {
            tenantId = intent.getStringExtra("tenantId"); // Retrieve tenantId
            String name = intent.getStringExtra("name");
            String address = intent.getStringExtra("address");
            int duration = intent.getIntExtra("duration", 0);
            int rent = intent.getIntExtra("rent", 0);
            String phone = intent.getStringExtra("phone");

            // Update UI with the data
            if (name != null) binding.detailName.setText(name);
            if (address != null) binding.detailAddress.setText(address);
            binding.detailDuration.setText(String.format("%d months", duration));
            binding.detailRent.setText(String.format("$%d", rent));
            if (phone != null) binding.detailPhone.setText(phone);

            // Set Update button action
            binding.buttonUpdate.setOnClickListener(v -> {
                Intent intent1 = new Intent(TenantDetails.this, UpdateTenant.class);
                intent1.putExtra("tenantId", tenantId); // Pass the tenant ID
                intent1.putExtra("name", name);
                intent1.putExtra("address", address);
                intent1.putExtra("duration", duration);
                intent1.putExtra("rent", rent);
                intent1.putExtra("phone", phone); // Pass phone number to UpdateTenant activity
                startActivity(intent1);
            });

            // Set Delete button action
            binding.buttonDelete.setOnClickListener(v -> {
                if (tenantId != null) {
                    deleteTenant(tenantId);
                } else {
                    Toast.makeText(this, "Tenant ID is missing. Unable to delete.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void deleteTenant(String tenantId) {
        databaseReference.child(tenantId).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Tenant deleted successfully!", Toast.LENGTH_SHORT).show();
                // Navigate back to ListTenantByAdmin page
                Intent intent = new Intent(TenantDetails.this, ListTenantByAdmin.class);
                startActivity(intent);
                finish(); // Close the current activity
            } else {
                Toast.makeText(this, "Failed to delete tenant. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
