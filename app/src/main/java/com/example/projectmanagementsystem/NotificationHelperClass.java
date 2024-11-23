package com.example.projectmanagementsystem;

import java.util.Date;

public class NotificationHelperClass {

    String notificationId, title, description, time;


    public NotificationHelperClass(String title, String description, String time) {
        this.time = time;
        this.description = description;
        this.title = title;
    }

    public NotificationHelperClass() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
