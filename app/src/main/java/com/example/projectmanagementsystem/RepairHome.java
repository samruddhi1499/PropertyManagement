package com.example.projectmanagementsystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RepairHome extends AppCompatActivity {

    private RecyclerView repairRecyclerView;
    private RepairAdapter repairAdapter;
    private ArrayList<RepairModel> repairList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_home);

        repairRecyclerView = findViewById(R.id.repairRecyclerView);
        repairRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        repairList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("admin_repairs");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                repairList.clear();
                for (DataSnapshot repairSnapshot : snapshot.getChildren()) {
                    RepairModel repair = repairSnapshot.getValue(RepairModel.class);
                    if (repair != null) {
                        repair.setId(repairSnapshot.getKey());
                        repairList.add(repair);
                    }
                }
                repairAdapter = new RepairAdapter(RepairHome.this, repairList, databaseReference);
                repairRecyclerView.setAdapter(repairAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
