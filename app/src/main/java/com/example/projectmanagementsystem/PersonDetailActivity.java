package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        // Get data from intent
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        String contact = getIntent().getStringExtra("contact");
        String roomNo = getIntent().getStringExtra("roomNo");
        String email = getIntent().getStringExtra("email");

        // Set data to TextViews
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView ageTextView = findViewById(R.id.ageTextView);
        TextView contactTextView = findViewById(R.id.contactTextView);
        TextView roomNoTextView = findViewById(R.id.roomNoTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);

        nameTextView.setText(name);
        ageTextView.setText(age);
        contactTextView.setText(contact);
        roomNoTextView.setText(roomNo);
        emailTextView.setText(email);
    }
    }
