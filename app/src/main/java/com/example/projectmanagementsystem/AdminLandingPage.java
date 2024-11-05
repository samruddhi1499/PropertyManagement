package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminLandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_landing_page);
        CardView imageCard = findViewById(R.id.imageCard);

        // Set click listener for the imageCard
        imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start TenantListActivity
                Intent intent = new Intent(AdminLandingPage.this, TenantList.class);
                startActivity(intent);
            }
        });
    }
}