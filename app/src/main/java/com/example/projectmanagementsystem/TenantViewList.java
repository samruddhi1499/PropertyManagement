package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectmanagementsystem.databinding.ActivityTenantViewListBinding;

import java.util.ArrayList;

public class TenantViewList extends AppCompatActivity {

    ActivityTenantViewListBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tenant_view_list);
        int[] imageList = {R.drawable.user, R.drawable.user, R.drawable.user};
        int[] addressList = {R.string.Tenant1Address, R.string.Tenant2Address,R.string.Tenant3Address};
        int[] durationList = {R.string.Tenant1, R.string.Tenant2, R.string.Tenant3};
        int[] rent = {R.string.Tenant1Rent, R.string.Tenant2Rent, R.string.Tenant3Rent};
        String[] nameList = {"Max", "Jess", "Carter"};
        for (int i = 0; i < imageList.length; i++){
            listData = new ListData(nameList[i], addressList[i], durationList[i], rent[i], imageList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(TenantViewList.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TenantViewList.this, ViewTenantDetails.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("address", addressList[i]);
                intent.putExtra("duration", durationList[i]);
                intent.putExtra("rent", rent[i]);
                startActivity(intent);
            }
        });

        Button addTenant = findViewById(R.id.addTenantButton);
        addTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTenantForm(view);
            }
        });
    }

    public void AddTenantForm(View view) {
        Intent intent = new Intent(TenantViewList.this, AddNewTenant.class);

        startActivity(intent);
    }
}