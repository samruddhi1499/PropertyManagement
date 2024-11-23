package com.example.projectmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NotificationAdapter extends ArrayAdapter<NotificationData> {

    public NotificationAdapter(@NonNull Context context, ArrayList<NotificationData> dataArrayList) {
        super(context, R.layout.notification_list, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        NotificationData notificationData = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.notification_list, parent, false);
        }

        TextView listName = view.findViewById(R.id.NotificationlistName);
        TextView listTime = view.findViewById(R.id.NotificationlistTime);

        if (notificationData != null) {
            listName.setText(notificationData.getTitle());
            listTime.setText(notificationData.getTime());
        }

        return view;
    }
}
