package com.dogsy.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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
    ImageButton btn_like;
    ImageButton btn_dislike;


    List<Float> ownerAgeValues = List.of(20F, 50F);
    List<Float> dogAgeValues= List.of(2F, 8F);
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

        btn_like = findViewById(R.id.button_like);
        btn_dislike = findViewById(R.id.button_dislike);

        //Matching Filter
        View toolbarView = findViewById(R.id.include);
        ImageButton btn_filter = toolbarView.findViewById(R.id.hamburger);

        // TODO: DB - add user id of new user (who can be liked/disliked) as params to .newInstance();
        UserDogView fragment = UserDogView.newInstance(-1);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_matching, fragment).commit();

        btn_like.setOnClickListener(view -> {
            //TODO: DB - call new Fragment with new User
            UserDogView newFragment = UserDogView.newInstance(-1);
            getSupportFragmentManager().beginTransaction().remove(fragment).replace(R.id.frame_layout_matching, newFragment).commit();
        });

        btn_dislike.setOnClickListener(view -> {
            //TODO: DB - call new Fragment with new User
            UserDogView newFragment = UserDogView.newInstance(-1);
            getSupportFragmentManager().beginTransaction().remove(fragment).replace(R.id.frame_layout_matching, newFragment).commit();
        });


        // filtering users
        btn_filter.setOnClickListener(v -> {
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

            rb_male.setOnClickListener(v1 -> {
                if(male) {
                    rb_male.setChecked(false);
                    male = false;
                } else {
                    rb_male.setChecked(true);
                    male = true;
                }
            });

            rb_female.setOnClickListener(v12 -> {
                if(female) {
                    rb_female.setChecked(false);
                    female = false;
                } else {
                    rb_female.setChecked(true);
                    female = true;
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

            ib_small.setOnClickListener(v13 -> {
                if(small){
                    ib_small.setColorFilter(Color.argb(255, 169, 169, 169));
                    small = false;
                } else {
                    ib_small.setColorFilter(Color.argb(255, 0, 0, 0));
                    small = true;
                }
            });

            ib_medium.setOnClickListener(v14 -> {
                if(medium){
                    ib_medium.setColorFilter(Color.argb(255, 169, 169, 169));
                    medium = false;
                } else {
                    ib_medium.setColorFilter(Color.argb(255, 0, 0, 0));
                    medium = true;
                }
            });

            ib_large.setOnClickListener(v15 -> {
                if(large){
                    ib_large.setColorFilter(Color.argb(255, 169, 169, 169));
                    large = false;
                } else {
                    ib_large.setColorFilter(Color.argb(255, 0, 0, 0));
                    large = true;
                }
            });


            filter.setCancelable(true);
            filter.setPositiveButton(
                    "Apply",
                    (dialog, id) -> {
                        ownerAgeValues = rl_ownerage.getValues();//min and max value from range slider for owner age
                        dogAgeValues = rl_dogage.getValues();//min and max value from range slider for dog age

                        //TODO: DB - call new Fragment with new User
                        UserDogView newFragment = UserDogView.newInstance(-1);
                        getSupportFragmentManager().beginTransaction().remove(fragment).replace(R.id.frame_layout_matching, newFragment).commit();

                        dialog.cancel();
                    }
            );

            filter.setNegativeButton(
                    "Cancel",
                    (dialog, id) -> dialog.cancel());

            AlertDialog showFilter = filter.create();
            showFilter.show();
        });



        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;
                case R.id.matching:
                    break;
                case R.id.chatting:
                    startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;
            }
            return true;
        });
    }
}