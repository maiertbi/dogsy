package com.example.dogsy.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.dogsy.R;

public class StartScreen extends AppCompatActivity {
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button btn_login = findViewById(R.id.button_login);
        Button btn_signup = findViewById(R.id.button_signup);

        btn_login.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        btn_signup.setOnClickListener(v -> startActivity(new Intent(this, RegisterMail.class)));
    }
}