package com.example.projectmanagementsystem;

import android.content.Context;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    Context context;
    ArrayList<Map<String, String>> expenseList;

    public ExpenseAdapter(Context context, ArrayList<Map<String, String>> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.expense_item, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Map<String, String> expense = expenseList.get(position);

        holder.txtName.setText(expense.get("name"));
        holder.txtAmount.setText("₹ " + expense.get("amount"));
        holder.txtDate.setText(expense.get("date"));
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtAmount, txtDate;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtExpenseName);
            txtAmount = itemView.findViewById(R.id.txtExpenseAmount);
            txtDate = itemView.findViewById(R.id.txtExpenseDate);
        }
    }
}
