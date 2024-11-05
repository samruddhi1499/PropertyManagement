package com.example.projectmanagementsystem;

import android.content.Intent;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RepairHome extends AppCompatActivity {
    //changed
    //changed
    private Button[] approveButtons;
    private Button[] doneButtons;  // Done buttons array
    private ImageButton[] scheduleButtons;
    private Button goBackButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_home); // Ensure correct layout

        // Initialize buttons
        approveButtons = new Button[]{
                findViewById(R.id.listApproveButton1),
                findViewById(R.id.listApproveButton2),
                findViewById(R.id.listApproveButton3),
                findViewById(R.id.listApproveButton4),
                findViewById(R.id.listApproveButton5)
        };

        doneButtons = new Button[]{
                findViewById(R.id.listApprovedButton1),
                findViewById(R.id.listApprovedButton2),
                findViewById(R.id.listApprovedButton3),
                findViewById(R.id.listApprovedButton4),
                findViewById(R.id.listApprovedButton5)
        };

        scheduleButtons = new ImageButton[]{
                findViewById(R.id.listScheduleButton1),
                findViewById(R.id.listScheduleButton2),
                findViewById(R.id.listScheduleButton3),
                findViewById(R.id.listScheduleButton4),
                findViewById(R.id.listScheduleButton5)
        };

        // Go back button
        goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(v -> {
            // Go back to AdminLandingPage
            startActivity(new Intent(RepairHome.this, AdminLandingPage.class));
        });

        // Hide schedule and done buttons initially
        for (ImageButton scheduleButton : scheduleButtons) {
            scheduleButton.setVisibility(View.GONE);
        }

        for (Button doneButton : doneButtons) {
            doneButton.setVisibility(View.GONE);  // Hide Done buttons initially
        }

        // Set up listeners for approve buttons
        for (int i = 0; i < approveButtons.length; i++) {
            int index = i;
            approveButtons[i].setOnClickListener(v -> {
                // Hide the approve button and show the schedule button
                approveButtons[index].setVisibility(View.GONE);
                scheduleButtons[index].setVisibility(View.VISIBLE);
            });
        }

        // Set up listeners for schedule buttons
        for (int i = 0; i < scheduleButtons.length; i++) {
            int index = i;
            scheduleButtons[i].setOnClickListener(v -> openDateTimePicker(index));
        }

        // Set up listeners for done buttons
        for (int i = 0; i < doneButtons.length; i++) {
            int index = i;
            doneButtons[i].setOnClickListener(v -> {
                // After marking the task as done, you may want to perform some action
                doneButtons[index].setVisibility(View.GONE);  // Hide Done button
                scheduleButtons[index].setEnabled(false);  // Disable schedule button to prevent rescheduling

                // Logic for marking the task as complete (e.g., updating database, changing task status)
            });
        }
    }

    private void openDateTimePicker(int index) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(RepairHome.this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    // Open time picker after selecting a date
                    TimePickerDialog timePickerDialog = new TimePickerDialog(RepairHome.this,
                            (timeView, hourOfDay, minute) -> {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                // Make Done button visible
                                doneButtons[index].setVisibility(View.VISIBLE);
                                scheduleButtons[index].setEnabled(false);  // Disable the schedule button
                            },
                            calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
