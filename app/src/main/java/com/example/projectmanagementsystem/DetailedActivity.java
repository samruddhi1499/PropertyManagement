package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedActivity extends AppCompatActivity {

    TextView detailName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_tenant);

        detailName = findViewById(R.id.detailName);

        // Get the tenant name from the intent and set it to detailName TextView
        String tenantName = getIntent().getStringExtra("tenantName");
        if (tenantName != null) {
            detailName.setText(tenantName);
        }
    }
}
