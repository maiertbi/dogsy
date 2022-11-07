package com.example.dogsy;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogsy.classes.User;

public class LoginActivity extends AppCompatActivity {
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        ImageButton ib = (ImageButton) findViewById(R.id.ib_signin);
        ib.setOnClickListener(new View.OnClickListener() {
            EditText et_email = (EditText) findViewById(R.id.et_loginEmail);
            EditText et_password = (EditText) findViewById(R.id.et_loginPassword);
            TextView tryAgain = (TextView) findViewById(R.id.t2_tryAgain);

            @Override
            public void onClick(View view) {
                email = et_email.getText().toString();
                password = et_password.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    tryAgain.setText("Please enter your email and password.");
                }
                else if(!User.userExists(email)){
                    tryAgain.setText("None exists email.\nPlease try again.");
                }
                else if(!User.checkPassword(email,password)){
                    tryAgain.setText("incorrect password.\nPlease try again.");
                }
                else{
                    tryAgain.setText("successful login! yay!");
//                    Intent login = new Intent(Login.this, RegisterUser.class);
//                    login.putExtra("mail", userMail);
//                    login.putExtra("password", userPassword1);
//                    startActivity(swiping_activity);
//                    finish();
                }
            }
        });
    }
}
