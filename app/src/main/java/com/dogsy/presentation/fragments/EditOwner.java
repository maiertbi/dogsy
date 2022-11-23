package com.dogsy.presentation.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dogsy.R;
import com.dogsy.presentation.ProfileActivity;
import com.dogsy.presentation.start.StartScreen;

public class EditOwner extends Fragment implements View.OnClickListener {

    private static final String USER_ID = "userId";

    // TODO: DB - delete the sample data (David, 16.03.2001, ...)
    // gender and dob is not editable
    private Integer userId;
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


    public EditOwner() {
    }

    // TODO - change params so the userID gets called
    public static EditOwner newInstance(Integer userId) {
        EditOwner fragment = new EditOwner();
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

        // TODO: DB - get User and save it to variables
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_owner, container, false);

        Button btn_apply = view.findViewById(R.id.button_apply);
        Button btn_logout = view.findViewById(R.id.button_logout);
        Button btn_delete = view.findViewById(R.id.button_delete);
        btn_apply.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        ImageButton ib_pic1 = view.findViewById(R.id.ib_userpic1);
        ImageButton ib_pic2 = view.findViewById(R.id.ib_userpic2);
        ImageButton ib_pic3 = view.findViewById(R.id.ib_userpic3);
        ib_pic1.setOnClickListener(this);
        ib_pic2.setOnClickListener(this);
        ib_pic3.setOnClickListener(this);

        EditText text_name = view.findViewById(R.id.et_name);
        TextView text_dob = view.findViewById(R.id.text_dob);
        TextView text_gender = view.findViewById(R.id.text_gender);
        EditText text_bio = view.findViewById(R.id.et_bio);
        EditText text_hometown = view.findViewById(R.id.et_hometown);
        EditText text_country = view.findViewById(R.id.et_country);

        text_name.setText(userName);
        text_dob.setText(userDob);
        text_gender.setText(userGender);
        text_bio.setText(userBio);
        text_hometown.setText(userHometown);
        text_country.setText(userCountry);

        // TODO: DB - set the already existing images

        // TODO: FABIAN - make the pictures changable!

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        switch (v.getId()){
            case R.id.ib_userpic1:
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE1);
                break;
            case R.id.ib_userpic2:
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE2);
                break;
            case R.id.ib_userpic3:
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE3);
                break;
            case R.id.button_apply:
                EditText text_name = view.findViewById(R.id.et_name);
                userName = text_name.getText().toString();
                EditText text_bio = view.findViewById(R.id.et_bio);
                userBio = text_bio.getText().toString();
                EditText text_hometown = view.findViewById(R.id.et_hometown);
                userHometown = text_hometown.getText().toString();
                EditText text_country = view.findViewById(R.id.et_country);
                userCountry = text_country.getText().toString();

                // TODO: cant get the onActivityResult working here to access the choosen pictures maybe somebody else knows how to (Code for that is already in RegisterUser) -Fabian

                // TODO: DB - update users data in db
                intent = new Intent(getActivity(), ProfileActivity.class);
                break;
            case R.id.button_logout:
                // TODO: DB - log out User
                intent = new Intent(getActivity(), StartScreen.class);
                break;
            case R.id.button_delete:
                // TODO: DB - delete user
                // intent = new Intent(getActivity(), StartScreen.class);
                break;
        }
        startActivity(intent);
        getActivity().finish();
    }


}