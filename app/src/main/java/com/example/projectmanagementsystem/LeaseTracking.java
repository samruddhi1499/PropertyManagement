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

        person1.setOnClickListener(v -> openPersonDetail("John", "25", "1234567890", "Room 101", "john@example.com"));
        person2.setOnClickListener(v -> openPersonDetail("Jane", "30", "0987654321", "Room 102", "jane@example.com"));
        person3.setOnClickListener(v -> openPersonDetail("Alex", "28", "1122334455", "Room 103", "alex@example.com"));
        person4.setOnClickListener(v -> openPersonDetail("Emma", "22", "2233445566", "Room 104", "emma@example.com"));
        person5.setOnClickListener(v -> openPersonDetail("Martha", "27", "3344556677", "Room 105", "martha@example.com"));
    }

    private void openPersonDetail(String name, String age, String contact, String roomNo, String email) {
        Intent intent = new Intent(LeaseTracking.this, PersonDetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        intent.putExtra("contact", contact);
        intent.putExtra("roomNo", roomNo);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
