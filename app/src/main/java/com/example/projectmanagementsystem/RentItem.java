package com.example.projectmanagementsystem;

public class RentItem {
    private String name;
    private String room;
    private boolean paid;
    private int imageResId;

    public RentItem() {
        // Default constructor required for Firebase
    }

    public RentItem(String name, String room, boolean paid, int imageResId) {
        this.name = name;
        this.room = room;
        this.paid = paid;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getImageResId() {
        return imageResId;
    }
}
