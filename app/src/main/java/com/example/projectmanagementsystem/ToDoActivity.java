package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;

import java.util.*;

public class ToDoActivity extends AppCompatActivity {

    EditText editTask;
    Button btnAddTask;
    RecyclerView todoRecyclerView;
    ArrayList<Map<String, Object>> taskList;
    ToDoAdapter adapter;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        editTask = findViewById(R.id.editTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        todoRecyclerView = findViewById(R.id.todoRecyclerView);

        dbRef = FirebaseDatabase.getInstance().getReference("todoTasks");
        taskList = new ArrayList<>();

        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ToDoAdapter(this, taskList);
        todoRecyclerView.setAdapter(adapter);

        btnAddTask.setOnClickListener(v -> addTask());

        loadTasks();
    }

    private void addTask() {
        String taskTitle = editTask.getText().toString().trim();
        if (TextUtils.isEmpty(taskTitle)) {
            Toast.makeText(this, "Enter a task", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = dbRef.push().getKey();
        Map<String, Object> task = new HashMap<>();
        task.put("id", id);
        task.put("title", taskTitle);
        task.put("completed", false);

        dbRef.child(id).setValue(task);
        editTask.setText("");
    }

    private void loadTasks() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Map<String, Object> task = (Map<String, Object>) snap.getValue();
                    taskList.add(task);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ToDoActivity.this, "Failed to load tasks", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteTask(String taskId) {
        dbRef.child(taskId).removeValue();
    }

    public void toggleTaskCompletion(String taskId, boolean isChecked) {
        dbRef.child(taskId).child("completed").setValue(isChecked);
    }
}
