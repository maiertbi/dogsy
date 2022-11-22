package com.dogsy.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dogsy.R;

public class DogView extends Fragment {
    private static final String ARG_PARAM1 = "dogId";

    private Integer mParam1;

    public DogView() {
    }

    public static DogView newInstance(Integer dogId) {
        DogView fragment = new DogView();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, dogId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dog_view, container, false);



        return view;
    }
}