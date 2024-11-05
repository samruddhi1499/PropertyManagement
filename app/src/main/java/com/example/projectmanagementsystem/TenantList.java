package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class TenantList extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_tenant);

        listView = findViewById(R.id.listview);


        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected tenant name
                // Start DetailedActivity and pass the tenant name
                Intent intent = new Intent(TenantList.this, DetailedActivity.class);

                startActivity(intent);
            }
        });
    }
}
