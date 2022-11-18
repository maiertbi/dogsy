package com.dogsy.presentation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dogsy.R;

public class EditUser extends Fragment implements View.OnClickListener {

    private static final String USER_ID = "userId";

    // TODO set variables from current user
    // gender and dob is not editable
    private String userName = "David";
    private String userDob = "16.03.2001";
    private String userGender = "male";
    private String userBio = "Love Phoenix Park";
    private String userCountry = "Ireland";
    private String userHometown = "Dublin";

    View view;

    EditText text_name = view.findViewById(R.id.et_name);
    TextView text_dob = view.findViewById(R.id.text_dob);
    TextView text_gender = view.findViewById(R.id.text_gender);
    EditText text_bio = view.findViewById(R.id.et_bio);
    EditText text_hometown = view.findViewById(R.id.et_hometown);
    EditText text_country = view.findViewById(R.id.et_country);

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
                userName = text_name.getText().toString();
                userBio = text_bio.getText().toString();
                userHometown = text_hometown.getText().toString();
                userCountry = text_country.getText().toString();
                //TODO update users data in db
            case R.id.button_logout:
                //TODO intent to login screen
            case R.id.button_delete:
                //TODO delete profile and intent to login screen
        }
    }
}