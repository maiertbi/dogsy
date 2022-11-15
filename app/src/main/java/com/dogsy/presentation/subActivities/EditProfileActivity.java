package com.dogsy.presentation.subActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dogsy.R;
import com.dogsy.presentation.ChatActivity;
import com.dogsy.presentation.MatchingActivity;
import com.dogsy.presentation.ProfileActivity;
import com.dogsy.presentation.fragments.EditAllDogs;
import com.dogsy.presentation.fragments.EditUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class EditProfileActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        Button btn_editUser = findViewById(R.id.button_editUser);
        Button btn_editAllDogs = findViewById(R.id.button_editAllDogs);

        EditAllDogs fragment_allDogs = EditAllDogs.newInstance(-1);
        EditUser fragment_edituser = EditUser.newInstance(-1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout_edit, fragment_edituser).commit();


        btn_editUser.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout_edit, fragment_edituser)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") //name can be null
                    .commit();
        });

        btn_editAllDogs.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout_edit, fragment_allDogs)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") //name can be null
                    .commit();
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
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