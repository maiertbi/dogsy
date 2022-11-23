package com.dogsy.presentation.start;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.application.service.UserService;
import com.dogsy.presentation.MatchingActivity;
import com.dogsy.R;


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

            UserService.instance.signInUser(email, password);
            // TODO: DB - error handling ("Wrong user or password")

            startActivity(new Intent(getApplicationContext(), MatchingActivity.class));
        });
    }
}
