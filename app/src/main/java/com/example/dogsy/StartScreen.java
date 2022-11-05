package com.example.dogsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {
    Button log_in;
    Button sign_up;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.start_screen);


        // log in button
        log_in = findViewById(R.id.button_login);

        // if click the log in button, move to login_activity.
        log_in.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        // sign up button
        sign_up = findViewById(R.id.button_signup);

        // if click the sign_up button, move to sign_up page.
        sign_up.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterMail.class);
            startActivity(intent);
        });
    }
}