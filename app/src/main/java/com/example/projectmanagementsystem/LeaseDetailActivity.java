package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LeaseDetailActivity extends AppCompatActivity {

    TextView leaseDetailName, leaseDetailAge, leaseDetailContact, leaseDetailRoomNo,
            leaseDetailEmail, leaseDetailStartDate, leaseDetailEndDate, leaseDetailRentAmount,
            leaseDetailDeposit, leaseDetailPaymentStatus, leaseDetailSpecialConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease_detail);

        // Initialize TextViews
        leaseDetailName = findViewById(R.id.leaseDetailName);
        leaseDetailAge = findViewById(R.id.leaseDetailAge);
        leaseDetailContact = findViewById(R.id.leaseDetailContact);
        leaseDetailRoomNo = findViewById(R.id.leaseDetailRoomNo);
        leaseDetailEmail = findViewById(R.id.leaseDetailEmail);
        leaseDetailStartDate = findViewById(R.id.leaseDetailStartDate);
        leaseDetailEndDate = findViewById(R.id.leaseDetailEndDate);
        leaseDetailRentAmount = findViewById(R.id.leaseDetailRentAmount);
        leaseDetailDeposit = findViewById(R.id.leaseDetailDeposit);
        leaseDetailPaymentStatus = findViewById(R.id.leaseDetailPaymentStatus);
        leaseDetailSpecialConditions = findViewById(R.id.leaseDetailSpecialConditions);

        // Get data passed from LeaseAdapter
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            leaseDetailName.setText(extras.getString("name", "N/A"));
            leaseDetailAge.setText("Age: " + extras.getString("age", "N/A"));
            leaseDetailContact.setText("Contact: " + extras.getString("contact", "N/A"));
            leaseDetailRoomNo.setText("Room: " + extras.getString("roomNo", "N/A"));
            leaseDetailEmail.setText("Email: " + extras.getString("email", "N/A"));
            leaseDetailStartDate.setText("Start Date: " + extras.getString("startDate", "N/A"));
            leaseDetailEndDate.setText("End Date: " + extras.getString("endDate", "N/A"));
            leaseDetailRentAmount.setText("Rent Amount: $" + extras.getString("rentAmount", "0.00"));
            leaseDetailDeposit.setText("Deposit: $" + extras.getString("deposit", "0.00"));
            leaseDetailPaymentStatus.setText("Payment Status: " + extras.getString("paymentStatus", "Unknown"));
            leaseDetailSpecialConditions.setText("Special Conditions: " + extras.getString("specialConditions", "None"));
        } else {
            // Handle the case when no data is passed
            leaseDetailName.setText("No details available.");
        }

        // Back button functionality
        findViewById(R.id.backButton).setOnClickListener(v -> onBackPressed());
    }
}
