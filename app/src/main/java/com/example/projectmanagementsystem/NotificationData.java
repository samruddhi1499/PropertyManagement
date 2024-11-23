package com.example.projectmanagementsystem;

public class NotificationData {
    private String title;
    private String time;

    // Default constructor required for Firebase
    public NotificationData() {
    }

    public NotificationData(String name, String time) {
        this.title = title;
        this.time = time;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
