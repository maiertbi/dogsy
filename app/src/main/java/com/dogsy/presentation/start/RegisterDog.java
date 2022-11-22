package com.dogsy.presentation.start;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.R;
import com.dogsy.application.service.DogService;
import com.dogsy.domain.model.Dog;

import java.util.Collections;
import java.util.Set;
import com.dogsy.presentation.MatchingActivity;
import com.dogsy.presentation.fragments.UserDogView;


public class RegisterDog extends AppCompatActivity {
    private String dogName;
    private int dogAge;
    private String dogBreed;
    private Dog.DogGender dogGender;
    private String dogBio;
    private Dog.DogSize dogSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_dog);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        EditText et_dogname = findViewById(R.id.et_dogname);
        EditText et_dogage = findViewById(R.id.et_age);
        EditText et_dogbreed = findViewById(R.id.et_breed);
        EditText et_dogbio = findViewById(R.id.et_bio);
        
        ImageButton ib_dogPic1 = findViewById(R.id.ib_dogpic1);
        ImageButton ib_dogPic2 = findViewById(R.id.ib_dogpic2);
        ImageButton ib_dogPic3 = findViewById(R.id.ib_dogpic3);
        
        ImageButton ib_dogsmall = findViewById(R.id.ib_dogsize1);
        ImageButton ib_dogmedium = findViewById(R.id.ib_dogsize2);
        ImageButton ib_doglarge = findViewById(R.id.ib_dogsize3);
        
        RadioButton rb_male = findViewById(R.id.rb_male);
        RadioButton rb_female = findViewById(R.id.rb_female);

        int colorSelected = Color.argb(255, 0, 0, 0); // #000000
        int colorUnselected = Color.argb(255, 169, 169, 169); // #A9A9A9

        //define dogsize, change image tints
        ib_dogsmall.setOnClickListener(view -> {
            ib_dogsmall.setColorFilter(colorSelected);
            ib_dogmedium.setColorFilter(colorUnselected);
            ib_doglarge.setColorFilter(colorUnselected);
            dogSize = Dog.DogSize.SMALL;
        });

        ib_dogmedium.setOnClickListener(view -> {
            ib_dogsmall.setColorFilter(colorUnselected);
            ib_dogmedium.setColorFilter(colorSelected);
            ib_doglarge.setColorFilter(colorUnselected);
            dogSize = Dog.DogSize.MEDIUM;
        });

        ib_doglarge.setOnClickListener(view -> {
            ib_dogsmall.setColorFilter(colorUnselected);
            ib_dogmedium.setColorFilter(colorUnselected);
            ib_doglarge.setColorFilter(colorSelected);
            dogSize = Dog.DogSize.LARGE;
        });

        rb_male.setOnClickListener(v -> {
            if(rb_female.isChecked())
                rb_female.setChecked(false);
            dogGender = Dog.DogGender.MALE;
        });

        rb_female.setOnClickListener(v -> {
            if(rb_male.isChecked())
                rb_male.setChecked(false);
            dogGender = Dog.DogGender.FEMALE;
        });


        //on add more dogs button click
        ImageButton ibAddDog = findViewById(R.id.ib_adddog);
        ibAddDog.setOnClickListener(view -> {

            addDog(et_dogname.getText().toString(),
                    Integer.parseInt(et_dogage.getText().toString()),
                    et_dogbreed.getText().toString(),
                    et_dogbio.getText().toString());

            Intent registerDog = new Intent(RegisterDog.this, RegisterDog.class);
            startActivity(registerDog);
            finish();
        });

        //Finish process and continue to app
        ImageButton ibSubmit = findViewById(R.id.ib_submit);
        ibSubmit.setOnClickListener(view -> {

            addDog(et_dogname.getText().toString(),
                    Integer.parseInt(et_dogage.getText().toString()),
                    et_dogbreed.getText().toString(),
                    et_dogbio.getText().toString());

            Intent swipingScreen = new Intent(RegisterDog.this, MatchingActivity.class);
            startActivity(swipingScreen);
            finish();
        });
    }

    private void addDog(String dogName, Integer dogAge, String dogBreed, String dogBio) {
        // TODO: DB - add dog to db - userID missing?
        // Code to add dog to db
        DogService.instance.addDog(
                dogName,
                dogAge,
                dogGender,
                dogSize,
                dogBreed,
                dogBio,
                Set.of(),// TODO: Add Dog personalities
                Collections.emptyList() //TODO: Pass dog pictures
        );
    }
}
