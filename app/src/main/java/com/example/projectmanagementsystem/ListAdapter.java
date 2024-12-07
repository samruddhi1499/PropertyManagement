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

public class ListAdapter extends ArrayAdapter<TenantHelper> {

    public ListAdapter(@NonNull Context context, ArrayList<TenantHelper> tenantList) {
        super(context, R.layout.list_item, tenantList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        TenantHelper tenant = getItem(position);

        if (tenant == null) {
            return new View(getContext()); // Return empty view if data is null
        }

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Initialize UI components
        TextView listName = view.findViewById(R.id.listName);
        TextView listAddress = view.findViewById(R.id.listAddress);

        // Set data to views
        listName.setText(tenant.getName() != null ? tenant.getName() : "Unknown");
        listAddress.setText(tenant.getAddress() != null ? tenant.getAddress() : "No Address Provided");

        return view;
    }
}
