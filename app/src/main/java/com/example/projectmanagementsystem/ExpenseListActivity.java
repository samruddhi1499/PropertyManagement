package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Map;

public class ExpenseListActivity extends AppCompatActivity {

    RecyclerView expenseRecyclerView;
    FloatingActionButton fabAddExpense;
    ArrayList<Map<String, String>> expenseList;
    ExpenseAdapter adapter;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        expenseRecyclerView = findViewById(R.id.expenseRecyclerView);
        fabAddExpense = findViewById(R.id.fabAddExpense);

        dbRef = FirebaseDatabase.getInstance().getReference("expenses");
        expenseList = new ArrayList<>();

        expenseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseAdapter(this, expenseList);
        expenseRecyclerView.setAdapter(adapter);

        // Load data
        loadExpenses();

        // FAB opens ExpenseActivity
        fabAddExpense.setOnClickListener(v -> {
            Intent intent = new Intent(ExpenseListActivity.this, ExpenseActivity.class);
            startActivity(intent);
        });
    }

    private void loadExpenses() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                expenseList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Map<String, String> expense = (Map<String, String>) snap.getValue();
                    expenseList.add(expense);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ExpenseListActivity.this, "Failed to load", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
