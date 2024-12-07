package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RepairRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_request); // Layout with repair list

        // Set up click listeners for each repair type
        setCardClickListener(R.id.acRepairCard, "AC Repair");
        setCardClickListener(R.id.mwRepairCard, "Microwave Repair");
        setCardClickListener(R.id.kitchenRepairCard, "Kitchen Repair");
        setCardClickListener(R.id.plumbingCard, "Plumbing");
        setCardClickListener(R.id.wmRepairCard, "Washing Machine Repair");
        setCardClickListener(R.id.pestControlCard, "Pest Control");
    }

    private void setCardClickListener(int cardViewId, String repairType) {
        CardView cardView = findViewById(cardViewId);
        cardView.setOnClickListener(v -> {
            // Navigate to RepairFormActivity and pass the repair type
            Intent intent = new Intent(RepairRequest.this, RepairRequestActivity.class);
            intent.putExtra("REPAIR_TYPE", repairType);
            startActivity(intent);
        });
    }
}
