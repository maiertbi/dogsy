package com.dogsy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.presentation.fragments.UserDogView;
import com.dogsy.presentation.start.RegisterDog;
import com.dogsy.presentation.subActivities.EditProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.dogsy.R;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        UserDogView fragment = UserDogView.newInstance(-1);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_profile, fragment).commit();

        Button btn_editProfile = findViewById(R.id.button_edit_profile);
        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfile = new  Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(editProfile);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.profile:
                    return true;

                case R.id.matching:
                    startActivity(new Intent(getApplicationContext(), MatchingActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
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