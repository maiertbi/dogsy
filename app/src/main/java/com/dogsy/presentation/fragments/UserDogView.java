package com.dogsy.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogsy.R;
import com.google.android.material.imageview.ShapeableImageView;

public class UserDogView extends Fragment {
    private static final String ARG_PARAM1 = "userId";

    private Integer userId;
    // TODO: DB - create variables where user data gets stored

    public UserDogView() {
    }

    // TODO: DB - change params so the userID gets called
    public static UserDogView newInstance(Integer userId) {
        UserDogView fragment = new UserDogView();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_PARAM1, -1);
        }

        // TODO: DB - get User
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_dog_view, container, false);

        TextView user_name = view.findViewById(R.id.text_user_name);
        TextView user_age = view.findViewById(R.id.text_user_age);
        TextView user_distance = view.findViewById(R.id.text_user_distance);
        TextView user_description = view.findViewById(R.id.text_user_description);

        ShapeableImageView user_img1 = view.findViewById(R.id.image_user_1);
        ShapeableImageView user_img2 = view.findViewById(R.id.image_user_2);
        ShapeableImageView user_img3 = view.findViewById(R.id.image_user_3);

        DogView fragment_dog1 = DogView.newInstance(-1);
        getParentFragmentManager().beginTransaction().replace(R.id.frame_layout_dog1, fragment_dog1).commit();


        // TODO: DB - change pictures to actual users pictures
        //  if one Imageview is not needed --> hide it!
        //  max 3 user pictures are allowed
        // user_img3.setVisibility(View.GONE);

        return view;
    }
}