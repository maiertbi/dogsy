package com.dogsy.presentation.subActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.dogsy.R;
import com.dogsy.presentation.ChatActivity;
import com.dogsy.presentation.MatchingActivity;
import com.dogsy.presentation.ProfileActivity;
import com.dogsy.presentation.fragments.EditAllDogs;
import com.dogsy.presentation.fragments.EditOwner;
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

        View ownerUnderline = findViewById(R.id.view_owner);
        View dogUnderline = findViewById(R.id.view_dog);

        // TODO: DB - insert param for list of all dogs
        EditAllDogs fragment_allDogs = EditAllDogs.newInstance(-1);

        // TODO: DB - insert param for user data
        EditOwner fragmentEdituser = EditOwner.newInstance(-1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout_editprofile, fragmentEdituser).commit();


        btn_editUser.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout_editprofile, fragmentEdituser)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") //name can be null
                    .commit();
            ownerUnderline.setBackgroundResource(R.color.primaryColor);
            dogUnderline.setBackgroundResource(R.color.white);
        });

        btn_editAllDogs.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout_editprofile, fragment_allDogs)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") //name can be null
                    .commit();
            ownerUnderline.setBackgroundResource(R.color.white);
            dogUnderline.setBackgroundResource(R.color.primaryColor);
        });

        ImageButton btn_cancel = findViewById(R.id.button_cancel);
        btn_cancel.setOnClickListener(v -> finish());

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