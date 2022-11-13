package com.dogsy.presentation.start;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.application.service.ProfileService;
import com.dogsy.R;
import com.dogsy.domain.model.Dog;
import com.dogsy.domain.model.User;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class RegisterUser extends AppCompatActivity {
    //private int userId = 0;
    private String userMail;
    private String userPassword;
    //private ArrayList<Integer> pictureId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        // TODO: Remove, just for testing.
        ProfileService.instance.signOut();

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
        Spinner gender = (Spinner) findViewById(R.id.s_gender);

        List<String> genders = Arrays.stream(User.Gender.values())
                .map(Enum::toString)
                .map(String::toLowerCase)
                .collect(toList());

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);


        ImageButton ib = findViewById(R.id.ib_usersubmit);
        ib.setOnClickListener(view -> {
            String userName = et_name.getText().toString();
            String userBirthday = et_dd.getText().toString() + "." + et_mm.getText().toString() + "." + et_yyyy.getText().toString();
            String userGender = gender.getSelectedItem().toString().toUpperCase();
            String userBio = et_bio.getText().toString();
            String userHometown = et_hometown.getText().toString();
            String userLocation = et_location.getText().toString();
            String userPark = et_park.getText().toString();

            // passing input data to UserService
            ProfileService.instance.registerUser(userMail, userPassword, userName, userBirthday, userGender, userBio, userHometown, userLocation, userPark);

            // ProfileService.instance.addDog("Doggo", 44, "MALE", true, "SMALL", "YES", "I am a dog.", Set.of("ACTIVE", "DOMINANT"));

            Intent intent = new Intent(getApplicationContext(), RegisterDog.class);
            startActivity(intent);
        });
    }
}
