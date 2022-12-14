package com.dogsy.presentation.fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dogsy.R;
import com.dogsy.presentation.start.RegisterDog;
import com.dogsy.presentation.start.StartScreen;


public class EditAllDogs extends Fragment implements View.OnClickListener {


    private static final String LIST_ALL_DOGS = "allDogs";

    private Integer allDogs;
    // TODO: DB - create variables
    View view;

    public EditAllDogs() {
    }

    // TODO: DB - change param to a List of all dogs
    public static EditAllDogs newInstance(Integer getAllDogs) {
        EditAllDogs fragment = new EditAllDogs();
        Bundle args = new Bundle();
        args.putInt(LIST_ALL_DOGS, getAllDogs);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allDogs = getArguments().getInt(LIST_ALL_DOGS);
        }

        // TODO: DB - get all Dogs and save it to variables
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_all_dogs, container, false);

        // TODO: DB and FABIAN - make a list of all dogs

        ImageButton btn_addDog = view.findViewById(R.id.ib_adddog);
        btn_addDog.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_adddog:
                Intent newDog = new Intent(getActivity(), RegisterDog.class);
                startActivity(newDog);
        }
    }
}