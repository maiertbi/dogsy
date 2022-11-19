package com.dogsy.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.presentation.fragments.UserDogView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.dogsy.R;
import com.google.android.material.slider.RangeSlider;

import java.util.List;

public class MatchingActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

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

                filter.setCancelable(true);
                filter.setPositiveButton(
                        "Apply",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                RangeSlider rl_ownerage = findViewById(R.id.slider_ownerage);
                                List<Float> ownerAgeValues = rl_ownerage.getValues();//min and max value from range slider for owner age
                                RangeSlider rl_dogage = findViewById(R.id.slider_dogage);
                                List<Float> dogAgeValues = rl_dogage.getValues();//min and max value from range slider for dog age
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