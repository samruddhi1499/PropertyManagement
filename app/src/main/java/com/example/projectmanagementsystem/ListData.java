package com.example.projectmanagementsystem;

public class ListData {
    String name;
    int rent, address, duration;
    int image;
    public ListData(String name, int address, int duration, int rent, int image) {
        this.name = name;
        this.address = address;
        this.duration = duration;
        this.rent = rent;
        this.image = image;
    }
}
