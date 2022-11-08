package com.example.dogsy;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterDog extends AppCompatActivity {
    private int dogId;
    private String dogName;
    private int dogAge;
    private String dogBreed;
    private char dogGender;
    private String dogBio;
    private char dogSize;//s = small; m = medium; l = large


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_dog);
        getSupportActionBar().hide();

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


        //define dogsize, change image tints
        ib_dogsmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib_dogsmall.setColorFilter(Color.argb(255, 0, 0, 0));//Hex: #000000
                ib_dogmedium.setColorFilter(Color.argb(255, 169, 169, 169));//Hex:"#A9A9A9"
                ib_doglarge.setColorFilter(Color.argb(255, 169, 169, 169));
                dogSize = 's';
            }
        });

        ib_dogmedium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ib_dogsmall.setColorFilter(Color.argb(255, 169, 169, 169));
                ib_dogmedium.setColorFilter(Color.argb(255, 0, 0, 0));
                ib_doglarge.setColorFilter(Color.argb(255, 169, 169, 169));
                dogSize = 'm';
            }
        });

        ib_doglarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib_dogsmall.setColorFilter(Color.argb(255, 169, 169, 169));
                ib_dogmedium.setColorFilter(Color.argb(255, 169, 169, 169));
                ib_doglarge.setColorFilter(Color.argb(255, 0, 0, 0));
                dogSize = 'l';
            }
        });


        //add more dogs
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
                //dogAge = Integer.valueOf(et_dogage.getText().toString());
                dogBreed = et_dogbreed.getText().toString();
                dogBio = et_dogbio.getText().toString();

                /*

                Code to add dog to db

                 */

                //Intent swipingScreen = new Intent(RegisterDog.this, swipingScreen.class);
                //startActivity(swiping Screen);
                //finish();
            }
        });
    }



}
