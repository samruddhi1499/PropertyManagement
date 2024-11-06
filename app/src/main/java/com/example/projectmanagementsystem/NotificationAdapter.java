package com.example.projectmanagementsystem;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotificationAdapter extends ArrayAdapter<NotificationData> {
    public NotificationAdapter(@NonNull Context context, ArrayList<NotificationData> dataArrayList) {
        super(context, R.layout.notification_list, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        NotificationData notificationData = getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.notification_list, parent, false);
        }

        TextView listName = view.findViewById(R.id.listName);
        TextView listTime = view.findViewById(R.id.listTime);

        listName.setText(notificationData.name);
        listTime.setText(notificationData.time);

        return view;
    }
}
