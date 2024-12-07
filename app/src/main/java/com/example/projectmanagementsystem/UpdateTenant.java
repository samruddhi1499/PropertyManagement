package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateTenant extends AppCompatActivity {

    private EditText editName, editAddress, editDuration, editRent, editPhone;
    private Button buttonSave;
    private DatabaseReference databaseReference;
    private String tenantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tenant);

        // Initialize views
        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editDuration = findViewById(R.id.editDuration);
        editRent = findViewById(R.id.editRent);
        editPhone = findViewById(R.id.editPhone);
        buttonSave = findViewById(R.id.buttonSave);

        // Get data from Intent
        tenantId = getIntent().getStringExtra("tenantId");
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        int duration = getIntent().getIntExtra("duration", 0);
        int rent = getIntent().getIntExtra("rent", 0);
        String phone = getIntent().getStringExtra("phone");

        // Populate fields with existing data
        editName.setText(name);
        editAddress.setText(address);
        editDuration.setText(String.valueOf(duration));
        editRent.setText(String.valueOf(rent));
        editPhone.setText(phone);

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("tenants");

        // Save updated tenant data
        buttonSave.setOnClickListener(v -> {
            String updatedName = editName.getText().toString().trim();
            String updatedAddress = editAddress.getText().toString().trim();
            String updatedPhone = editPhone.getText().toString().trim();

            int updatedDuration;
            int updatedRent;

            try {
                updatedDuration = Integer.parseInt(editDuration.getText().toString().trim());
                updatedRent = Integer.parseInt(editRent.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(UpdateTenant.this, "Invalid duration or rent value", Toast.LENGTH_SHORT).show();
                return;
            }

            if (updatedName.isEmpty() || updatedAddress.isEmpty() || updatedPhone.isEmpty()) {
                Toast.makeText(UpdateTenant.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Update data in Firebase
            TenantHelper updatedTenant = new TenantHelper(updatedName, updatedAddress, updatedDuration, updatedRent, updatedPhone);
            if (tenantId != null) {
                databaseReference.child(tenantId).setValue(updatedTenant)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdateTenant.this, "Tenant updated successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateTenant.this, ListTenantByAdmin.class);
                                startActivity(intent);
                                finish();                            } else {
                                Toast.makeText(UpdateTenant.this, "Failed to update tenant", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(e ->
                                Toast.makeText(UpdateTenant.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                        );
            } else {
                Toast.makeText(UpdateTenant.this, "Tenant ID is missing", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
