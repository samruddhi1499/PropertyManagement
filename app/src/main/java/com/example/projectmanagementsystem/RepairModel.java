package com.example.projectmanagementsystem;

public class RepairModel {
    private String id;
    private String roomNo;
    private String title;
    private String description;
    private String status; // Add the status field

    // Empty constructor for Firebase
    public RepairModel() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
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

    public String getStatus() { // Add the getter for status
        return status;
    }

    public void setStatus(String status) { // Add the setter for status
        this.status = status;
    }
}
