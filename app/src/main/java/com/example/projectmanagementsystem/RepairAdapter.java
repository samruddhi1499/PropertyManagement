package com.example.projectmanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanagementsystem.R;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.RepairViewHolder> {

    private Context context;
    private ArrayList<RepairModel> repairList;
    private DatabaseReference databaseReference;

    public RepairAdapter(Context context, ArrayList<RepairModel> repairList, DatabaseReference databaseReference) {
        this.context = context;
        this.repairList = repairList;
        this.databaseReference = databaseReference;
    }

    @NonNull
    @Override
    public RepairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.repair_item, parent, false);
        return new RepairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairViewHolder holder, int position) {
        RepairModel repair = repairList.get(position);

        // Populate views with data
        holder.nameTextView.setText("Room No: " + repair.getRoomNo());
        holder.titleTextView.setText(repair.getTitle());
        holder.descriptionTextView.setText(repair.getDescription());

        // Handle button visibility based on status
        if ("pending".equals(repair.getStatus())) {
            holder.approveButton.setVisibility(View.VISIBLE);
            holder.doneButton.setVisibility(View.GONE);
            holder.scheduleButton.setVisibility(View.GONE);
        } else if ("approved".equals(repair.getStatus())) {
            holder.approveButton.setVisibility(View.GONE);
            holder.scheduleButton.setVisibility(View.VISIBLE);
            holder.doneButton.setVisibility(View.GONE);
        } else if ("scheduled".equals(repair.getStatus())) {
            holder.approveButton.setVisibility(View.GONE);
            holder.scheduleButton.setVisibility(View.GONE);
            holder.doneButton.setVisibility(View.VISIBLE);
        } else if ("done".equals(repair.getStatus())) {
            holder.approveButton.setVisibility(View.GONE);
            holder.scheduleButton.setVisibility(View.GONE);
            holder.doneButton.setVisibility(View.GONE); // No action needed
        }

        // Approve button logic
        holder.approveButton.setOnClickListener(v -> {
            databaseReference.child(repair.getId()).child("status").setValue("approved");
            repair.setStatus("approved");
            notifyItemChanged(holder.getAdapterPosition()); // Refresh the item
        });

        // Schedule button logic
        holder.scheduleButton.setOnClickListener(v -> openDateTimePicker(repair, holder));

        // Done button logic
        holder.doneButton.setOnClickListener(v -> {
            databaseReference.child(repair.getId()).child("status").setValue("done");
            repair.setStatus("done");
            notifyItemChanged(holder.getAdapterPosition()); // Refresh the item
        });
    }

    private void openDateTimePicker(RepairModel repair, RepairViewHolder holder) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                            (timeView, hourOfDay, minute) -> {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                // Set scheduled time in Firebase
                                String scheduledTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(calendar.getTime());
                                databaseReference.child(repair.getId()).child("scheduledTime").setValue(scheduledTime);
                                databaseReference.child(repair.getId()).child("status").setValue("scheduled");

                                repair.setStatus("scheduled");
                                notifyItemChanged(holder.getAdapterPosition()); // Refresh the item
                            },
                            calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    @Override
    public int getItemCount() {
        return repairList.size();
    }

    public static class RepairViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, titleTextView, descriptionTextView;
        Button approveButton, doneButton, scheduleButton; // Added scheduleButton

        public RepairViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.listName);
            titleTextView = itemView.findViewById(R.id.listTitle);
            descriptionTextView = itemView.findViewById(R.id.listDescription);
            approveButton = itemView.findViewById(R.id.listApproveButton);
            doneButton = itemView.findViewById(R.id.listDoneButton);
            scheduleButton = itemView.findViewById(R.id.listScheduleButton); // Added binding for scheduleButton
        }
    }

}
