package com.dogsy.presentation.start;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.R;

public class RegisterPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_password);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageButton ib = findViewById(R.id.ib_reguserpassword);
        EditText et1 = findViewById(R.id.et_reguserpassword1);
        EditText et2 = findViewById(R.id.et_reguserpassword2);
        TextView tv1 = findViewById(R.id.tv_wrongPassword);

        ib.setOnClickListener(view -> {
            String userPassword1 = et1.getText().toString();
            String userPassword2 = et2.getText().toString();

            // TODO: add regex check to the if clause (https://www.w3schools.com/java/java_regex.asp)
            if(!(userPassword1.equals(userPassword2)) || userPassword1.isEmpty()){
                tv1.setText("Please enter valid and matching passwords.");
                return;
            }

            Intent registerUser = new Intent(getApplicationContext(), RegisterUser.class);
            registerUser.putExtra("mail", getIntent().getStringExtra("mail"));
            registerUser.putExtra("password", userPassword1);
            startActivity(registerUser);
        });
    }
}
