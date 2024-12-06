package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TenantPayment extends AppCompatActivity {

    private EditText tenantName, paymentAmount, paymentDate;
    private Spinner paymentMethod;
    private Button payNowButton;
    private DatabaseReference rentDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_payment);

        // Initialize views
        tenantName = findViewById(R.id.tenantName);
        paymentAmount = findViewById(R.id.paymentAmount);
        paymentDate = findViewById(R.id.paymentDate);
        paymentMethod = findViewById(R.id.paymentMethod);
        payNowButton = findViewById(R.id.payNowButton);

        // Initialize Firebase database reference
        rentDatabaseReference = FirebaseDatabase.getInstance().getReference("rent_items");

        // Handle Pay Now button click
        payNowButton.setOnClickListener(v -> {
            String name = tenantName.getText().toString().trim();
            if (!name.isEmpty()) {
                markRentAsPaidByName(name);
            } else {
                Toast.makeText(this, "Please enter the tenant's name.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void markRentAsPaidByName(String name) {
        // Query Firebase to find the record with the given name
        rentDatabaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Update the `paid` field to true
                        snapshot.getRef().child("paid").setValue(true)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(TenantPayment.this, "Payment successful!", Toast.LENGTH_SHORT).show();
                                        finish(); // Close the activity
                                    } else {
                                        Toast.makeText(TenantPayment.this, "Payment failed. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(TenantPayment.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    }
                } else {
                    Toast.makeText(TenantPayment.this, "No record found for the tenant's name.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(TenantPayment.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
