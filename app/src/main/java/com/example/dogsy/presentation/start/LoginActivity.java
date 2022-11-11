package com.example.dogsy.presentation.start;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogsy.R;
import com.example.dogsy.infrastructure.service.UserService;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // TODO: Remove, just for testing.
        UserService.instance.signOut();


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

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
            /*backend:
            else if(!User.userExists(email)) {
                tryAgain.setText("Email does not exist.\nPlease try again.");
                return;
            }
            else if(!User.checkPassword(email,password)) {
                tryAgain.setText("Incorrect password.\nPlease try again.");
                return;
            }*/
            UserService.instance
                    .signInUser(email, password);


            // TODO: make intent connection (you have to replace NEW_ACTIVITY)

            /*
            Intent intent = new Intent(getApplicationContext(), NEW_ACTIVITY.class);
            intent.putExtra("mail", email);
            intent.putExtra("password", password);
            startActivity(intent);
            finish();
            */

        });
    }
}
