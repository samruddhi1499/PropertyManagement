package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;
public class LoginTabFragment extends Fragment {
    EditText username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);
        username = view.findViewById(R.id.login_email);

        // Find the button and set the onClick listener
        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the latest user input here
                String user = username.getText().toString();
                login(v, user);
            }
        });

        return view;
    }

    public void login(View view, String user) {
        // Use requireActivity() to get the activity context
        if(user.equals("admin")){
            Intent intent = new Intent(requireActivity(), AdminLandingPage.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(requireActivity(), TenantLandingPage.class);
            startActivity(intent);
        }
    }
}
