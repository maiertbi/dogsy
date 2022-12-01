package com.dogsy.presentation.start;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.application.service.UserService;
import com.dogsy.presentation.MatchingActivity;
import com.dogsy.R;

import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // TODO: DB - Remove next line. This is just for testing.
        UserService.instance.signOut();

        ImageButton ib = findViewById(R.id.ib_signin);
        EditText et_email = findViewById(R.id.et_loginEmail);
        EditText et_password = findViewById(R.id.et_loginPassword);
        TextView tryAgain = findViewById(R.id.t2_tryAgain);

        ib.setOnClickListener(view -> {
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();

            if(email.isEmpty() || password.isEmpty()) {
                tryAgain.setText("Please enter your email and password.");
                return;
            }

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                tryAgain.setText("Please enter your email.");
                // Toast.makeText(LoginActivity.this,"Please enter the correct email.",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                UserService.instance.signInUser(email, password);
                // TODO: DB - error handling ("Wrong user or password")

                startActivity(new Intent(getApplicationContext(), MatchingActivity.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }

        });


    }

    // handles back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
