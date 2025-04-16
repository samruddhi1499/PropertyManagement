package com.example.projectmanagementsystem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class ExpenseActivity extends AppCompatActivity {


    EditText expenseName, expenseAmount, expenseDate;
    Button saveExpenseButton;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        expenseName = findViewById(R.id.expenseName);
        expenseAmount = findViewById(R.id.expenseAmount);
        expenseDate = findViewById(R.id.expenseDate);
        saveExpenseButton = findViewById(R.id.saveExpenseButton);

        // Firebase DB reference
        databaseReference = FirebaseDatabase.getInstance().getReference("expenses");

        // Open date picker on clicking date field
        expenseDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(this, (view, y, m, d) ->
                    expenseDate.setText((m + 1) + "/" + d + "/" + y), year, month, day);
            datePicker.show();
        });

        // Save data to Firebase
        saveExpenseButton.setOnClickListener(v -> saveExpenseToFirebase());
    }

    private void saveExpenseToFirebase() {
        String name = expenseName.getText().toString().trim();
        String amount = expenseAmount.getText().toString().trim();
        String date = expenseDate.getText().toString().trim();

        if (name.isEmpty() || amount.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = databaseReference.push().getKey();

        Map<String, String> expense = new HashMap<>();
        expense.put("name", name);
        expense.put("amount", amount);
        expense.put("date", date);

        databaseReference.child(id).setValue(expense).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Expense saved", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
            }
        });
    }

}