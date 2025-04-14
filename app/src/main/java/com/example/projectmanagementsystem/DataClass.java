package com.example.projectmanagementsystem;

public class DataClass {
    private String imageURL, title, rent, details;

    public DataClass(){

    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public DataClass(String imageURL, String title, String rent, String details) {
        this.imageURL = imageURL;
        this.title = title;
        this.rent = rent;
        this.details = details;
    }
}