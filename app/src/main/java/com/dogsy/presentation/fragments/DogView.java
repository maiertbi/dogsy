package com.dogsy.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogsy.R;
import com.google.android.material.imageview.ShapeableImageView;

public class DogView extends Fragment {
    private static final String ARG_PARAM1 = "dogId";

    private Integer dogId;
    // TODO: DB - create variables where dog data gets stored


    public DogView() {
    }

    // TODO: DB - change params so the dogId gets called
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
            dogId = getArguments().getInt(ARG_PARAM1);
        }

        // TODO: DB - get Dog and save it to variables
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dog_view, container, false);

        TextView dog_name = view.findViewById(R.id.text_dog_name);
        TextView dog_age = view.findViewById(R.id.text_dog_age);
        TextView dog_description = view.findViewById(R.id.text_dog_description);
        TextView dog_breed = view.findViewById(R.id.text_dog_breed);
        TextView dog_personality = view.findViewById(R.id.text_dog_personality);

        ShapeableImageView dog_img1 = view.findViewById(R.id.image_dog_1);
        ShapeableImageView dog_img2 = view.findViewById(R.id.image_dog_2);
        ShapeableImageView dog_img3 = view.findViewById(R.id.image_dog_3);

        // TODO: DB - change data to actual dogs data
        //  max 3 dog_pictures are allowed! if one Imageview is not needed --> hide it!

        return view;
    }
}