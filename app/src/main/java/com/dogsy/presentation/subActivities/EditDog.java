package com.dogsy.presentation.subActivities;

import androidx.appcompat.app.AppCompatActivity;
import com.dogsy.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditDog extends AppCompatActivity {

    private String dogName = "Bobby";
    private String dogAge = "4";
    private String dogGender = "male";
    private String dogBio = "loves to play and long walks, very energetic";
    private String dogBreed = "German Shepherd";
    private char dogSize = 'm';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dog);

        EditText text_name =findViewById(R.id.et_name);
        text_name.setText(dogName);
        EditText text_age = findViewById(R.id.text_age);
        text_age.setText(dogAge);
        EditText text_breed = findViewById(R.id.text_breed);
        text_breed.setText(dogBreed);
        EditText text_gender = findViewById(R.id.text_gender);
        text_gender.setText(dogGender);
        EditText text_bio = findViewById(R.id.text_bio);
        text_bio.setText(dogBio);

        ImageButton ib_dogsmall = (ImageButton) findViewById(R.id.ib_dogsize1);
        ImageButton ib_dogmedium = (ImageButton) findViewById(R.id.ib_dogsize2);
        ImageButton ib_doglarge = (ImageButton) findViewById(R.id.ib_dogsize3);

        if(dogSize == 's')
            ib_dogsmall.setColorFilter(Color.argb(255, 0, 0, 0));
        else if (dogSize == 'm')
            ib_dogmedium.setColorFilter(Color.argb(255, 0, 0, 0));
        else if (dogSize == 'l')
            ib_doglarge.setColorFilter(Color.argb(255, 0, 0, 0));

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

        Button btn_apply = findViewById(R.id.button_apply);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogName = text_name.getText().toString();
                dogAge = text_age.getText().toString();
                dogBreed = text_breed.getText().toString();
                dogBio = text_bio.getText().toString();
                //TODO update dog with new data, dont forget dogsize
            }
        });
        Button btn_delete = findViewById(R.id.button_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO delete dog
            }
        });
    }
}