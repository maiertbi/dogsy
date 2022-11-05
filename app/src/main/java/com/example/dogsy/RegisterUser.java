package com.example.dogsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogsy.classes.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterUser extends AppCompatActivity {
    private int userId = 0;
    private String userMail;
    private String userPassword;
    private String userName;
    private String userBirthday;
    private String userGender;
    private String userBio;
    private String userHometown;
    private String userLocation;
    private String userPark;
    private ArrayList<Integer> pictureId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        //set spinner values
        List<String> genders = new ArrayList<String>();
        Spinner gender = (Spinner) findViewById(R.id.s_gender);
        genders.add("male");
        genders.add("female");
        genders.add("non-binary");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        //get data from registerPassword class
        Intent passwordIntent = getIntent();
        userMail = passwordIntent.getStringExtra("mail");
        userPassword = passwordIntent.getStringExtra("password");

        ImageButton ib = (ImageButton) findViewById(R.id.ib_usersubmit);
        ib.setOnClickListener(new View.OnClickListener() {
            EditText et_name = (EditText) findViewById(R.id.et_fn);
            EditText et_dd = (EditText) findViewById(R.id.et_dd);
            EditText et_mm = (EditText) findViewById(R.id.et_mm);
            EditText et_yyyy = (EditText) findViewById(R.id.et_yyyy);
            ImageButton ib_userPic1 = (ImageButton) findViewById(R.id.ib_userpic1);
            ImageButton ib_userPic2 = (ImageButton) findViewById(R.id.ib_userpic2);
            ImageButton ib_userPic3 = (ImageButton) findViewById(R.id.ib_userpic3);
            EditText et_bio = (EditText) findViewById(R.id.et_userbio);
            EditText et_hometown = (EditText)  findViewById(R.id.et_userhometown);
            EditText et_location = (EditText) findViewById(R.id.et_userlocation);
            EditText et_park = (EditText) findViewById(R.id.et_userpark);
            @Override
            public void onClick(View view) {
                userName = et_name.getText().toString();
                userBirthday = et_dd.getText().toString() + "." + et_mm.getText().toString() + "." + et_yyyy.getText().toString();
                userGender = gender.getSelectedItem().toString();
                userBio = et_bio.getText().toString();
                userHometown = et_hometown.getText().toString();
                userLocation = et_location.getText().toString();
                userPark = et_park.getText().toString();
                Intent registerUser = new Intent(RegisterUser.this, RegisterDog.class);
                startActivity(registerUser);
                finish();
            }
        });
    }
}
