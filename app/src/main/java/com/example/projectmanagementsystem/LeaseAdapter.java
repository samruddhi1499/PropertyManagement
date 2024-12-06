package com.example.projectmanagementsystem;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeaseAdapter extends RecyclerView.Adapter<LeaseAdapter.LeaseViewHolder> {

    private List<Lease> leaseList;

    public LeaseAdapter(List<Lease> leaseList) {
        this.leaseList = leaseList;
    }

    @Override
    public LeaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lease_item, parent, false);
        return new LeaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeaseViewHolder holder, int position) {
        Lease lease = leaseList.get(position);
        holder.nameTextView.setText(lease.name);

        // Calculate remaining days
        String remainingDays = lease.calculateRemainingDays();
        if (remainingDays.isEmpty()) {
            holder.endDateTextView.setText("Lease Expired");
        } else {
            holder.endDateTextView.setText(remainingDays + " days left");
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), LeaseDetailActivity.class);
            // Pass data to LeaseDetailActivity
            intent.putExtra("name", lease.name);
            intent.putExtra("age", lease.age);
            intent.putExtra("contact", lease.contact);
            intent.putExtra("roomNo", lease.roomNo);
            intent.putExtra("email", lease.email);
            intent.putExtra("startDate", lease.startDate);
            intent.putExtra("endDate", lease.endDate);
            intent.putExtra("rentAmount", lease.rentAmount);
            intent.putExtra("deposit", lease.deposit);
            intent.putExtra("paymentStatus", lease.paymentStatus);
            intent.putExtra("specialConditions", lease.specialConditions);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return leaseList.size();
    }

    public static class LeaseViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView endDateTextView;

        public LeaseViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.listName);
            endDateTextView = itemView.findViewById(R.id.listExpiry);
        }
    }
}
