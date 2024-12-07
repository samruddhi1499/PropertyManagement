package com.example.projectmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RentTrackingAdapter extends RecyclerView.Adapter<RentTrackingAdapter.ViewHolder> {

    private Context context;
    private List<RentItem> rentItemList;

    public RentTrackingAdapter(Context context, List<RentItem> rentItemList) {
        this.context = context;
        this.rentItemList = rentItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the individual item layout
        View view = LayoutInflater.from(context).inflate(R.layout.rent_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Bind data to each item
        RentItem item = rentItemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.roomTextView.setText(item.getRoom());
        holder.imageView.setImageResource(item.getImageResId()); // Assuming the image resource id is stored in the model
        holder.approveButton.setText(item.isPaid() ? "Paid" : "Not Paid");

        // Optional: Set the button color to indicate payment status
        if (item.isPaid()) {
            holder.approveButton.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.approveButton.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    @Override
    public int getItemCount() {
        return rentItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, roomTextView;
        ImageView imageView;
        Button approveButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.listName);
            roomTextView = itemView.findViewById(R.id.listMessage);
            imageView = itemView.findViewById(R.id.listImage);
            approveButton = itemView.findViewById(R.id.listApproveButton);
        }
    }
}
