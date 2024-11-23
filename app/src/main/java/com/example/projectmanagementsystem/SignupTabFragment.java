package com.example.projectmanagementsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectmanagementsystem.*;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {

    EditText signupEmail, signupPassword, confirmSignupPassword;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_tab, container, false);

        signupEmail = view.findViewById(R.id.signup_email);
        signupPassword = view.findViewById(R.id.signup_password);
        confirmSignupPassword = view.findViewById(R.id.signup_confirm);
        signupButton = view.findViewById(R.id.signup_button);

        signupButton.setOnClickListener(v -> {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("users");

            String email = signupEmail.getText().toString().trim();
            String password = signupPassword.getText().toString().trim();
            String confirmPassword = confirmSignupPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                String sanitizedEmail = email.replace(".", ","); // Sanitize email for Firebase key
                FirebaseHelper helperClass = new FirebaseHelper(email, password);

                reference.child(sanitizedEmail).setValue(helperClass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(requireContext(), "Signup successful!", Toast.LENGTH_SHORT).show();
                        ((MainActivity) requireActivity()).viewPager2.setCurrentItem(0); // Switch to Login tab
                    } else {
                        Toast.makeText(requireContext(), "Signup failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}
