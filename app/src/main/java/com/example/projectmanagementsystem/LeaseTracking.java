package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LeaseTracking extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lease_tracking);
        setupPersonClickListeners();

    }

    private void setupPersonClickListeners() {
        RelativeLayout person1 = findViewById(R.id.relativeLayout1);
        RelativeLayout person2 = findViewById(R.id.relativeLayout2);
        RelativeLayout person3 = findViewById(R.id.relativeLayout3);
        RelativeLayout person4 = findViewById(R.id.relativeLayout4);
        RelativeLayout person5 = findViewById(R.id.relativeLayout5);

        person1.setOnClickListener(v -> openPersonDetail(
                "John", "25", "1234567890", "Room 101", "john@example.com",
                "2023-01-01", "2024-01-01", "1500", "500", "Paid", "No pets allowed"
        ));
        person2.setOnClickListener(v -> openPersonDetail(
                "Jane", "30", "0987654321", "Room 102", "jane@example.com",
                "2023-03-01", "2024-03-01", "1800", "600", "Pending", "Smoking prohibited"
        ));
        person3.setOnClickListener(v -> openPersonDetail(
                "Alex", "28", "1122334455", "Room 103", "alex@example.com",
                "2023-05-01", "2024-05-01", "1600", "550", "Paid", "Renewal possible"
        ));
        person4.setOnClickListener(v -> openPersonDetail(
                "Emma", "22", "2233445566", "Room 104", "emma@example.com",
                "2023-07-01", "2024-07-01", "1700", "500", "Pending", "Late fee applicable"
        ));
        person5.setOnClickListener(v -> openPersonDetail(
                "Martha", "27", "3344556677", "Room 105", "martha@example.com",
                "2023-09-01", "2024-09-01", "1900", "700", "Paid", "Lease cannot be sublet"
        ));
    }

    private void openPersonDetail(String name, String age, String contact, String roomNo, String email,
                                  String startDate, String endDate, String rentAmount, String deposit,
                                  String paymentStatus, String specialConditions) {
        Intent intent = new Intent(LeaseTracking.this, PersonDetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        intent.putExtra("contact", contact);
        intent.putExtra("roomNo", roomNo);
        intent.putExtra("email", email);
        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);
        intent.putExtra("rentAmount", rentAmount);
        intent.putExtra("deposit", deposit);
        intent.putExtra("paymentStatus", paymentStatus);
        intent.putExtra("specialConditions", specialConditions);
        startActivity(intent);
    }

}
