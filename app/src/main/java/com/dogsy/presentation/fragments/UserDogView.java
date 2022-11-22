package com.dogsy.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dogsy.R;

public class UserDogView extends Fragment {
    private static final String ARG_PARAM1 = "userId";

    private Integer userId;

    public UserDogView() {
    }

    // TODO: DB - create params so the userID gets called
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

        // TODO: DB - get all textviews, imageviews etc from .xml and change them to the ones from the actual user
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_dog_view, container, false);
    }
}