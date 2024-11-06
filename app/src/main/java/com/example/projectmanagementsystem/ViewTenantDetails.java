package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectmanagementsystem.databinding.ActivityViewTenantDetailsBinding;

public class ViewTenantDetails extends AppCompatActivity {
    ActivityViewTenantDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_tenant_details);
        Intent intent = this.getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            int address = intent.getIntExtra("address", R.string.Tenant1Address);
            int duration = intent.getIntExtra("duration", R.string.Tenant1);
            int rent = intent.getIntExtra("rent", R.string.Tenant1Rent);
            binding.detailName.setText(name);
            binding.detailAddress.setText(address);
            binding.detailDuration.setText(duration);
            binding.detailRent.setText(rent);
        }
    }
}