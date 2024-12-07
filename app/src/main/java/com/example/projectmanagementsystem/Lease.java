package com.example.projectmanagementsystem;

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@IgnoreExtraProperties
public class Lease {
    public String name;
    public int age;
    public String contact;
    public String roomNo;
    public String email;
    public String startDate;
    public String endDate;
    public String rentAmount;
    public String deposit;
    public String paymentStatus;
    public String specialConditions;

    // Default constructor for Firebase
    public Lease() {}

    public Lease(String name, int age, String contact, String roomNo, String email,
                 String startDate, String endDate, String rentAmount, String deposit,
                 String paymentStatus, String specialConditions) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.roomNo = roomNo;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentAmount = rentAmount;
        this.deposit = deposit;
        this.paymentStatus = paymentStatus;
        this.specialConditions = specialConditions;
    }

    // Method to calculate the remaining days until the lease expiry (only number of days left or empty if expired)
    public String calculateRemainingDays() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date endDateParsed = sdf.parse(endDate);  // Parse the end date from the database
            Date currentDate = new Date();  // Get the current date

            // Calculate the difference in milliseconds
            long diffInMillis = endDateParsed.getTime() - currentDate.getTime();

            // Convert milliseconds to days
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);

            // Return the number of days (or leave it empty if expired)
            if (diffInDays > 0) {
                return String.valueOf(diffInDays);  // Return just the number of days left
            } else if (diffInDays == 0) {
                return "0";  // Lease expires today (display 0 days left)
            } else {
                return "";  // Leave it empty if the lease has expired
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";  // Return empty if an error occurs
        }
    }
}
