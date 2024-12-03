package com.example.projectmanagementsystem;

public class RentItem {

    private String name;
    private String room;
    private int imageResId;
    private boolean paid;

    // Default constructor is required for Firebase
    public RentItem() {
    }

    // Constructor to initialize the object
    public RentItem(String name, String room, int imageResId, boolean paid) {
        this.name = name;
        this.room = room;
        this.imageResId = imageResId;
        this.paid = paid;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
