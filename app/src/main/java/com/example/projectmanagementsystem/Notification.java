package com.example.projectmanagementsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmanagementsystem.databinding.ActivityNotificationBinding;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    ActivityNotificationBinding binding;
    NotificationAdapter listAdapter;
    ArrayList<NotificationData> dataArrayList = new ArrayList<>();
    NotificationData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] nameList = {"Notification 1", "Notification 2", "Notification 3"};
        String[] timeList = {"11:30 PM", "4:15 AM", "12:56 PM"};

        for (int i = 0; i < nameList.length; i++) {
            listData = new NotificationData(nameList[i], timeList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new NotificationAdapter(Notification.this, dataArrayList);
        binding.notificationView.setAdapter(listAdapter);

    }
}
