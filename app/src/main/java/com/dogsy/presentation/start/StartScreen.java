package com.dogsy.presentation.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogsy.R;

public class StartScreen extends AppCompatActivity {
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageView logo = findViewById(R.id.imageView_logo);
        TextView title = findViewById(R.id.textView_title);
        Button btn_login = findViewById(R.id.button_login);
        Button btn_signup = findViewById(R.id.button_signup);

        btn_login.setOnClickListener(v -> {startActivity(new Intent(this, LoginActivity.class));overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);});
        btn_signup.setOnClickListener(v -> {startActivity(new Intent(this, RegisterMail.class));overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);});

        // set Animations
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        logoAnim.setStartOffset(200);
        logoAnim.setDuration(1200);

        Animation titleAnim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        titleAnim.setStartOffset(500);
        titleAnim.setDuration(1000);

        Animation btn1Anim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        btn1Anim.setStartOffset(700);
        btn1Anim.setDuration(1000);

        Animation btn2Anim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        btn2Anim.setStartOffset(900);
        btn2Anim.setDuration(1000);

        logo.startAnimation(logoAnim);
        title.startAnimation(titleAnim);
        btn_login.startAnimation(btn1Anim);
        btn_signup.startAnimation(btn2Anim);
    }
}