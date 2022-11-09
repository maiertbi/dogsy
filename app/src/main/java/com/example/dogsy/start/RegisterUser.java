package com.example.dogsy.start;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogsy.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterUser extends AppCompatActivity {
    private int userId = 0;
    private String userMail;
    private String userPassword;
    private ArrayList<Integer> pictureId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //get data from registerPassword class
        Intent passwordIntent = getIntent();
        userMail = passwordIntent.getStringExtra("mail");
        userPassword = passwordIntent.getStringExtra("password");

        EditText et_name = findViewById(R.id.et_fn);
        EditText et_dd = findViewById(R.id.et_dd);
        EditText et_mm = findViewById(R.id.et_mm);
        EditText et_yyyy = findViewById(R.id.et_yyyy);
        ImageButton ib_userPic1 = findViewById(R.id.ib_userpic1);
        ImageButton ib_userPic2 = findViewById(R.id.ib_userpic2);
        ImageButton ib_userPic3 = findViewById(R.id.ib_userpic3);
        EditText et_bio = findViewById(R.id.et_userbio);
        EditText et_hometown =  findViewById(R.id.et_userhometown);
        EditText et_location = findViewById(R.id.et_userlocation);
        EditText et_park = findViewById(R.id.et_userpark);

        //set spinner values
        List<String> genders = new ArrayList<>();
        Spinner gender = (Spinner) findViewById(R.id.s_gender);
        genders.add("male");
        genders.add("female");
        genders.add("non-binary");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);


        ImageButton ib = findViewById(R.id.ib_usersubmit);
        ib.setOnClickListener(view -> {
            String userName = et_name.getText().toString();
            String userBirthday = et_dd.getText().toString() + "." + et_mm.getText().toString() + "." + et_yyyy.getText().toString();
            String userGender = gender.getSelectedItem().toString();
            String userBio = et_bio.getText().toString();
            String userHometown = et_hometown.getText().toString();
            String userLocation = et_location.getText().toString();
            String userPark = et_park.getText().toString();

            Intent intent = new Intent(getApplicationContext(), RegisterDog.class);
            startActivity(intent);
        });
    }
}
