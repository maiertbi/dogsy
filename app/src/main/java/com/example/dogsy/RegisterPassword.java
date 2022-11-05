package com.example.dogsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterPassword extends AppCompatActivity {
    private String userMail;
    private String userPassword1;
    private String userPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_password);

        //get data from RegisterMail
        Intent mailIntent = getIntent();
        userMail = mailIntent.getStringExtra("mail");

        ImageButton ib = (ImageButton) findViewById(R.id.ib_reguserpassword);
        ib.setOnClickListener(new View.OnClickListener() {
            EditText et1 = (EditText) findViewById(R.id.et_reguserpassword1);
            EditText et2 = (EditText) findViewById(R.id.et_reguserpassword2);
            TextView tv1 = (TextView) findViewById(R.id.tv_wrongPassword);

            @Override
            public void onClick(View view) {
                userPassword1 = et1.getText().toString();
                userPassword2 = et2.getText().toString();
                if(!userPassword1.equals(userPassword2)){
                    tv1.setText("Passwords don't match");
                }
                else if(userPassword1.isEmpty() || userPassword2.isEmpty()){
                    tv1.setText("Please enter a password");
                }
                else{
                    Intent registerUser = new Intent(RegisterPassword.this, RegisterUser.class);
                    registerUser.putExtra("mail", userMail);
                    registerUser.putExtra("password", userPassword1);
                    startActivity(registerUser);
                    finish();
                }
            }
        });
    }
}
