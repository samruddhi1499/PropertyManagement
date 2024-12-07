package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmanagementsystem.databinding.ActivityListTenantByAdminBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListTenantByAdmin extends AppCompatActivity {

    private ActivityListTenantByAdminBinding binding;
    private ListAdapter listAdapter;
    private final ArrayList<TenantHelper> tenantList = new ArrayList<>();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListTenantByAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("tenants");

        // Initialize adapter and set it to the ListView
        listAdapter = new ListAdapter(this, tenantList);
        binding.listview.setAdapter(listAdapter);

        // Fetch tenants from Firebase
        fetchTenantsFromFirebase();

        // Handle list item clicks
        binding.listview.setOnItemClickListener((adapterView, view, position, id) -> {
            // Get clicked tenant data
            TenantHelper selectedTenant = tenantList.get(position);

            // Pass tenant details to TenantDetails activity
            Intent intent = new Intent(ListTenantByAdmin.this, TenantDetails.class);
            intent.putExtra("tenantId", selectedTenant.getId()); // Pass tenantId
            intent.putExtra("name", selectedTenant.getName());
            intent.putExtra("address", selectedTenant.getAddress());
            intent.putExtra("duration", selectedTenant.getDuration());
            intent.putExtra("rent", selectedTenant.getRent());
            intent.putExtra("phone", selectedTenant.getPhone()); // Include phone
            startActivity(intent);
        });

        // Add Tenant button
        binding.addTenantButton.setOnClickListener(view -> {
            Intent intent = new Intent(ListTenantByAdmin.this, AddTenant.class);
            startActivity(intent);
        });
    }

    private void fetchTenantsFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Clear the list to avoid duplicates
                tenantList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Get the tenantId (Firebase key)
                    String tenantId = dataSnapshot.getKey();

                    // Convert snapshot to TenantHelper object
                    TenantHelper tenant = dataSnapshot.getValue(TenantHelper.class);

                    if (tenant != null && tenantId != null) {
                        // Set the tenantId in the TenantHelper object
                        tenant.setId(tenantId);

                        // Add TenantHelper to the list
                        tenantList.add(tenant);
                    }
                }

                // Notify adapter of data changes
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle database error
                Toast.makeText(ListTenantByAdmin.this, "Failed to fetch tenants: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

}
}
