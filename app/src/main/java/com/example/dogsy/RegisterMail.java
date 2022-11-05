package com.example.dogsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterMail extends AppCompatActivity {
    private String userMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_mail);
        ImageButton ib = (ImageButton) findViewById(R.id.ib_regusermail);
        ib.setOnClickListener(new View.OnClickListener() {
            EditText et = (EditText) findViewById(R.id.et_regusermail);
            TextView tv = (TextView) findViewById(R.id.tv_nomail);
            @Override
            public void onClick(View view) {
                userMail = et.getText().toString();
                if(!userMail.isEmpty()) {
                    Intent passwordScreen = new Intent(RegisterMail.this, RegisterPassword.class);
                    passwordScreen.putExtra("mail", userMail);
                    startActivity(passwordScreen);
                    finish();
                }
                else
                    tv.setText("Please enter an email");
            }
        });
    }
}
