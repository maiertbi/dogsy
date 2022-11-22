package com.dogsy.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.presentation.fragments.UserDogView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.dogsy.R;
import com.google.android.material.slider.RangeSlider;

import java.util.Arrays;
import java.util.List;

public class MatchingActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    List<Float> ownerAgeValues = List.of(new Float(20), new Float(50));
    List<Float> dogAgeValues= List.of(new Float(2), new Float(8));;
    private boolean female = true;
    private boolean male = false;
    private boolean small = true;
    private boolean medium = false;
    private boolean large = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.matching);


        //Matching Filter
        View toolbarView = findViewById(R.id.include);
        ImageButton btn_filter = toolbarView.findViewById(R.id.hamburger);

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder filter = new AlertDialog.Builder(MatchingActivity.this);
                //set Dialog Layout to filter layout
                LayoutInflater inflater = MatchingActivity.this.getLayoutInflater();
                View filterView = inflater.inflate(R.layout.filter, null);
                filter.setView(filterView);

                RangeSlider rl_ownerage = filterView.findViewById(R.id.slider_ownerage);
                RangeSlider rl_dogage = filterView.findViewById(R.id.slider_dogage);

                //apply save state for filter
                rl_ownerage.setValues(ownerAgeValues);
                rl_dogage.setValues(dogAgeValues);

                RadioButton rb_female = filterView.findViewById(R.id.rb_female);
                RadioButton rb_male = filterView.findViewById(R.id.rb_male);
                //apply save state for filter
                if(male)
                    rb_male.setChecked(true);
                if(female)
                    rb_female.setChecked(true);

                rb_male.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(male) {
                            rb_male.setChecked(false);
                            male = false;
                        }
                        else if(!male) {
                            rb_male.setChecked(true);
                            male = true;
                        }
                    }
                });

                rb_female.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(female) {
                            rb_female.setChecked(false);
                            female = false;
                        }
                        else if(!female) {
                            rb_female.setChecked(true);
                            female = true;
                        }
                    }
                });

                ImageButton ib_small = filterView.findViewById(R.id.ib_dogsize1);
                ImageButton ib_medium = filterView.findViewById(R.id.ib_dogsize2);
                ImageButton ib_large = filterView.findViewById(R.id.ib_dogsize3);
                //apply save state for filter
                if(small)
                    ib_small.setColorFilter(Color.argb(255, 0, 0, 0));
                if(medium)
                    ib_medium.setColorFilter(Color.argb(255, 0, 0, 0));
                if(large)
                    ib_large.setColorFilter(Color.argb(255, 0, 0, 0));

                ib_small.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(small){
                            ib_small.setColorFilter(Color.argb(255, 169, 169, 169));
                            small = false;
                        }
                        else if(!small){
                            ib_small.setColorFilter(Color.argb(255, 0, 0, 0));
                            small = true;
                        }
                    }
                });

                ib_medium.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(medium){
                            ib_medium.setColorFilter(Color.argb(255, 169, 169, 169));
                            medium = false;
                        }
                        else if(!medium){
                            ib_medium.setColorFilter(Color.argb(255, 0, 0, 0));
                            medium = true;
                        }
                    }
                });

                ib_large.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(large){
                            ib_large.setColorFilter(Color.argb(255, 169, 169, 169));
                            large = false;
                        }
                        else if(!large){
                            ib_large.setColorFilter(Color.argb(255, 0, 0, 0));
                            large = true;
                        }
                    }
                });


                filter.setCancelable(true);
                filter.setPositiveButton(
                        "Apply",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ownerAgeValues = rl_ownerage.getValues();//min and max value from range slider for owner age
                                dogAgeValues = rl_dogage.getValues();//min and max value from range slider for dog age
                                dialog.cancel();
                            }
                        });

                filter.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog showFilter = filter.create();
                showFilter.show();
            }
        });


        UserDogView fragment = UserDogView.newInstance(-1);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_matching, fragment).commit();



        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.matching:
                    return true;

                case R.id.chatting:
                    startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
            }
            return false;
        });
    }
}