package com.example.projectmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    // Duration constants (in milliseconds)
    private static final int ANIMATION_DURATION = 1500;
    private static final int SPLASH_DISPLAY_LENGTH = 3000; // total duration before moving on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the splash screen full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        TextView splashTitle = findViewById(R.id.splash_title);

        // Animate the splash title: fade in and scale up
        splashTitle.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(ANIMATION_DURATION)
                .setStartDelay(300)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        // Navigate to the next activity after a short delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Change LoginActivity.class to your next activity class
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
