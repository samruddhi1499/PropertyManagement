package com.example.projectmanagementsystem;

public class TenantHelper {

    private String name;
    private String address; // Address as String
    private int duration;
    private int rent;
    private String id;

    private String phone; // Added phone field

    // Constructor with all parameters
    public TenantHelper(String name, String address, int duration, int rent, String phone) {
        this.name = name;
        this.address = address;
        this.duration = duration;
        this.rent = rent;
        this.phone = phone; // Initialize phone
    }

    // Default constructor
    public TenantHelper() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
