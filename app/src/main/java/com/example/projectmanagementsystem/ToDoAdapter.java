package com.example.projectmanagementsystem;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.TaskViewHolder> {

    Context context;
    ArrayList<Map<String, Object>> taskList;

    public ToDoAdapter(Context context, ArrayList<Map<String, Object>> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Map<String, Object> task = taskList.get(position);
        String title = (String) task.get("title");
        String id = (String) task.get("id");
        boolean completed = (boolean) task.get("completed");

        holder.txtTaskTitle.setText(title);
        holder.checkBoxTask.setChecked(completed);

        holder.checkBoxTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (context instanceof ToDoActivity) {
                ((ToDoActivity) context).toggleTaskCompletion(id, isChecked);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (context instanceof ToDoActivity) {
                ((ToDoActivity) context).deleteTask(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBoxTask;
        TextView txtTaskTitle;
        ImageView btnDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxTask = itemView.findViewById(R.id.checkBoxTask);
            txtTaskTitle = itemView.findViewById(R.id.txtTaskTitle);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
