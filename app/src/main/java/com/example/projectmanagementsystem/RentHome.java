package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
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

public class RentHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RentTrackingAdapter adapter;
    private List<RentItem> rentItems;
    private FirebaseDatabase database;
    private DatabaseReference rentItemsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_home);  // Set your layout

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance();
        rentItemsRef = database.getReference("rent_items");  // Reference to "rent_items" in the database

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.rentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the "Go Back" button
        Button goBackButton = findViewById(R.id.goRentBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to Admin Landing Page
                finish();
            }
        });

        // Fetch rent items from Firebase
        fetchRentItems();
    }

    private void fetchRentItems() {
        rentItemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rentItems = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    RentItem rentItem = snapshot.getValue(RentItem.class);
                    rentItems.add(rentItem);
                }

                // Log the number of rent items fetched
                Log.d("RentHome", "Fetched " + rentItems.size() + " rent items.");

                // Update the RecyclerView with the fetched rent items
                if (rentItems.size() > 0) {
                    adapter = new RentTrackingAdapter(RentHome.this, rentItems);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(RentHome.this, "No rent items available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error if data retrieval fails
                Toast.makeText(RentHome.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
