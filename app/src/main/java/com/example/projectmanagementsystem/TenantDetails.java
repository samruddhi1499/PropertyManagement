package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmanagementsystem.databinding.ActivityTenantDetailsBinding;

public class TenantDetails extends AppCompatActivity {

    ActivityTenantDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTenantDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get data from the Intent
        Intent intent = this.getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String address = intent.getStringExtra("address");
            int duration = intent.getIntExtra("duration", 0); // Default value is 0 if not passed
            int rent = intent.getIntExtra("rent", 0); // Default value is 0 if not passed

            // Update UI with the data
            if (name != null) binding.detailName.setText(name);
            if (address != null) binding.detailAddress.setText(address);
            binding.detailDuration.setText(String.format("%d months", duration)); // Example: "12 months"
            binding.detailRent.setText(String.format("$%d", rent)); // Example: "$1200"
        }
    }
}
