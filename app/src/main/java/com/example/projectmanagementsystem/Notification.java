package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmanagementsystem.databinding.ActivityNotificationBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    ActivityNotificationBinding binding;
    NotificationAdapter listAdapter;
    ArrayList<NotificationData> dataArrayList = new ArrayList<>();
    DatabaseReference databaseReference;
    boolean isAdmin;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve the isAdmin value from the intent
        isAdmin = getIntent().getBooleanExtra("isAdmin", false);

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("notifications");

        // Initialize adapter
        listAdapter = new NotificationAdapter(Notification.this, dataArrayList);
        binding.notificationView.setAdapter(listAdapter);

        // Load data from Firebase
        fetchNotificationsFromFirebase();

        // Floating Action Button to add notifications
        fab = findViewById(R.id.fab);

        // Set FAB visibility based on isAdmin
        if (!isAdmin) {
            fab.setVisibility(View.GONE); // Hide FAB if not admin
        }

        // FAB click listener for admin users
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(Notification.this, AddNotification.class);
            startActivity(intent);
        });
    }

    private void fetchNotificationsFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Clear the list to avoid duplicates
                dataArrayList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Convert snapshot to NotificationData object
                    NotificationData notification = dataSnapshot.getValue(NotificationData.class);
                    if (notification != null) {
                        dataArrayList.add(notification);
                    }
                }

                // Notify adapter of data changes
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Notification", "Failed to fetch data", error.toException());
            }
        });
    }
}
