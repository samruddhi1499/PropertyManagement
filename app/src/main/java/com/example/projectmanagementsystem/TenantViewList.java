package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TenantViewList extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tenant_view_list);
        listView = findViewById(R.id.listview);


        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected tenant name
                // Start DetailedActivity and pass the tenant name
                Intent intent = new Intent(TenantViewList.this, DetailedActivity.class);

                startActivity(intent);
            }
        });

        Button addTenant = findViewById(R.id.addTenantButton);
        addTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTenantForm(view);
            }
        });
    }

    public void AddTenantForm(View view) {
        Intent intent = new Intent(TenantViewList.this, AddNewTenant.class);

        startActivity(intent);
    }
}