package com.dogsy.presentation.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dogsy.R;
import com.dogsy.presentation.start.RegisterMail;
import com.dogsy.presentation.start.RegisterUser;
import com.dogsy.presentation.start.StartScreen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EditUser extends Fragment implements View.OnClickListener {

    private static final String USER_ID = "userId";

    // TODO set variables from current user from db
    // gender and dob is not editable
    private String userName = "David";
    private String userDob = "16.03.2001";
    private String userGender = "male";
    private String userBio = "Love Phoenix Park";
    private String userCountry = "Ireland";
    private String userHometown = "Dublin";
    private Bitmap pictureBitmap1;
    private Bitmap pictureBitmap2;
    private Bitmap pictureBitmap3;

    View view;

    int SELECT_PICTURE1 = 100;
    int SELECT_PICTURE2 = 200;
    int SELECT_PICTURE3 = 300;

    private Integer userId;

    public EditUser() {
        // Required empty public constructor
    }

    public static EditUser newInstance(Integer userId) {
        EditUser fragment = new EditUser();
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        Button btn_apply = view.findViewById(R.id.button_apply);
        btn_apply.setOnClickListener(this);
        Button btn_logout = view.findViewById(R.id.button_logout);
        btn_logout.setOnClickListener(this);
        Button btn_delete = view.findViewById(R.id.button_delete);
        btn_delete.setOnClickListener(this);

        ImageButton ib_pic1 = view.findViewById(R.id.ib_userpic1);
        ib_pic1.setOnClickListener(this);
        ImageButton ib_pic2 = view.findViewById(R.id.ib_userpic2);
        ib_pic2.setOnClickListener(this);
        ImageButton ib_pic3 = view.findViewById(R.id.ib_userpic3);
        ib_pic3.setOnClickListener(this);

        EditText text_name = view.findViewById(R.id.et_name);
        TextView text_dob = view.findViewById(R.id.text_dob);
        TextView text_gender = view.findViewById(R.id.text_gender);
        EditText text_bio = view.findViewById(R.id.et_bio);
        EditText text_hometown = view.findViewById(R.id.et_hometown);
        EditText text_country = view.findViewById(R.id.et_country);

        //TODO set images from user
        text_name.setText(userName);
        text_dob.setText(userDob);
        text_gender.setText(userGender);
        text_bio.setText(userBio);
        text_hometown.setText(userHometown);
        text_country.setText(userCountry);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_apply:
                EditText text_name = view.findViewById(R.id.et_name);
                userName = text_name.getText().toString();
                EditText text_bio = view.findViewById(R.id.et_bio);
                userBio = text_bio.getText().toString();
                EditText text_hometown = view.findViewById(R.id.et_hometown);
                userHometown = text_hometown.getText().toString();
                EditText text_country = view.findViewById(R.id.et_country);
                userCountry = text_country.getText().toString();
                //TODO update users data in db
                break;
            case R.id.button_logout:
                //TODO you can go back to the profile screen after logging out
                Intent startScreen = new Intent(getActivity(), StartScreen.class);
                startActivity(startScreen);
                getActivity().finish();
                break;
            case R.id.button_delete:
                //TODO delete profile and intent to login screen
                break;
        }
    }

}