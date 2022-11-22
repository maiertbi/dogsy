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
        EditText et_dogname = (EditText) findViewById(R.id.et_dogname);
        EditText et_dogage = (EditText) findViewById(R.id.et_age);
        EditText et_dogbreed = (EditText) findViewById(R.id.et_breed);
        EditText et_dogbio = (EditText) findViewById(R.id.et_bio);
        ImageButton ib_dogPic1 = (ImageButton) findViewById(R.id.ib_dogpic1);
        ImageButton ib_dogPic2 = (ImageButton) findViewById(R.id.ib_dogpic2);
        ImageButton ib_dogPic3 = (ImageButton) findViewById(R.id.ib_dogpic3);
        ImageButton ib_dogsmall = (ImageButton) findViewById(R.id.ib_dogsize1);
        ImageButton ib_dogmedium = (ImageButton) findViewById(R.id.ib_dogsize2);
        ImageButton ib_doglarge = (ImageButton) findViewById(R.id.ib_dogsize3);
        RadioButton rb_male = findViewById(R.id.rb_male);
        RadioButton rb_female = findViewById(R.id.rb_female);

        //define dogsize, change image tints
        ib_dogsmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib_dogsmall.setColorFilter(Color.argb(255, 0, 0, 0));//Hex: #000000
                ib_dogmedium.setColorFilter(Color.argb(255, 169, 169, 169));//Hex:"#A9A9A9"
                ib_doglarge.setColorFilter(Color.argb(255, 169, 169, 169));
                dogSize = Dog.DogSize.SMALL;
            }
        });

        ib_dogmedium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ib_dogsmall.setColorFilter(Color.argb(255, 169, 169, 169));
                ib_dogmedium.setColorFilter(Color.argb(255, 0, 0, 0));
                ib_doglarge.setColorFilter(Color.argb(255, 169, 169, 169));
                dogSize = Dog.DogSize.MEDIUM;
            }
        });

        ib_doglarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib_dogsmall.setColorFilter(Color.argb(255, 169, 169, 169));
                ib_dogmedium.setColorFilter(Color.argb(255, 169, 169, 169));
                ib_doglarge.setColorFilter(Color.argb(255, 0, 0, 0));
                dogSize = Dog.DogSize.LARGE;
            }
        });

        rb_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_female.isChecked())
                    rb_female.setChecked(false);
                dogGender = Dog.DogGender.MALE;
            }
        });

        rb_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_male.isChecked())
                    rb_male.setChecked(false);
                dogGender = Dog.DogGender.FEMALE;
            }
        });


        //on add more dogs button click
        ImageButton ibAddDog = (ImageButton) findViewById(R.id.ib_adddog);
        ibAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogName = et_dogname.getText().toString();
                //dogAge = Integer.valueOf(et_dogage.getText().toString());
                dogBreed = et_dogbreed.getText().toString();

                dogBio = et_dogbio.getText().toString();
                /*
                Code to add dog to db
                 */
                Intent registerDog = new Intent(RegisterDog.this, RegisterDog.class);
                startActivity(registerDog);
                finish();
            }
        });

        //Finish process and continue to app
        ImageButton ibSubmit = (ImageButton) findViewById(R.id.ib_submit);
        ibSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dogName = et_dogname.getText().toString();
                dogAge = Integer.parseInt(et_dogage.getText().toString());
                dogBreed = et_dogbreed.getText().toString();
                dogBio = et_dogbio.getText().toString();

                //TODO add dog to db

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
                Intent swipingScreen = new Intent(RegisterDog.this, MatchingActivity.class);
                startActivity(swipingScreen);
                finish();

            }
        });
    }
}
