package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LeaseTracking extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LeaseAdapter leaseAdapter;
    private List<Lease> leaseList;
    private ProgressBar progressBar; // ProgressBar for loading indicator
    private Button goLeaseBackButton; // Go Back button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lease_tracking);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progressBar); // Initialize ProgressBar
        leaseList = new ArrayList<>();
        leaseAdapter = new LeaseAdapter(leaseList);

        goLeaseBackButton = findViewById(R.id.goLeaseBackButton); // Initialize Go Back Button

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(leaseAdapter);

        // Show the ProgressBar initially
        progressBar.setVisibility(View.VISIBLE);

        // Fetch lease data from Firebase
        fetchLeaseDataFromFirebase();

        // Set click listener for the Go Back Button
        goLeaseBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the data from the intent
                boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);
                String emailUser = getIntent().getStringExtra("email");
                String passwordUser = getIntent().getStringExtra("password");

                // Create an Intent to go back to AdminLandingPage
                Intent intent = new Intent(LeaseTracking.this, AdminLandingPage.class);
                intent.putExtra("isAdmin", isAdmin);
                intent.putExtra("email", emailUser);
                intent.putExtra("password", passwordUser);

                // Start AdminLandingPage activity
                startActivity(intent);
                finish(); // Finish the LeaseTracking activity
            }
        });
    }

    private void fetchLeaseDataFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("leases");

        // Order by end date to get the soonest expiring leases top(6)
        databaseReference.orderByChild("endDate").limitToFirst(6).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                leaseList.clear(); // Clear existing data
                for (DataSnapshot leaseSnapshot : snapshot.getChildren()) {
                    Lease lease = leaseSnapshot.getValue(Lease.class);
                    if (lease != null) {
                        leaseList.add(lease); // Add new lease to list
                    }
                }

                // Hide the ProgressBar after data is loaded
                progressBar.setVisibility(View.GONE);

                if (leaseList.isEmpty()) {
                    Toast.makeText(LeaseTracking.this, "No leases found.", Toast.LENGTH_SHORT).show();
                }

                leaseAdapter.notifyDataSetChanged(); // Notify the adapter to update the UI
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hide the ProgressBar if data fetching fails
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LeaseTracking.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
