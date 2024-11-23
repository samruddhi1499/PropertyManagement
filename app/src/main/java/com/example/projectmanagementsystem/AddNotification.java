package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNotification extends AppCompatActivity {

    EditText notificationTitle, notificationDescription;
    Button addButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);

        // Initialize views
        notificationTitle = findViewById(R.id.notificationTitle);
        notificationDescription = findViewById(R.id.notificationDes);
        addButton = findViewById(R.id.notificationSubmitButton);

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("notifications"); // Consider plural naming for collections

        // Set button click listener
        addButton.setOnClickListener(v -> {
            // Retrieve input values
            String title = notificationTitle.getText().toString().trim();
            String description = notificationDescription.getText().toString().trim();

            // Validate inputs
            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get current date and time
            String currentDateAndTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());

            // Create notification helper object
            NotificationHelperClass helperClass = new NotificationHelperClass(title, description, currentDateAndTime);

            // Generate a unique key for each notification
            String notificationId = reference.push().getKey();
            if (notificationId != null) {
                reference.child(notificationId).setValue(helperClass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Notification added successfully!", Toast.LENGTH_SHORT).show();
                        // Optionally, reset input fields
                        notificationTitle.setText("");
                        notificationDescription.setText("");
                    } else {
                        Toast.makeText(this, "Failed to add notification. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e ->
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            } else {
                Toast.makeText(this, "Failed to generate notification ID.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
