package com.dogsy.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dogsy.R;


public class EditAllDogs extends Fragment {


    private static final String LIST_ALL_DOGS = "param1";

    private Integer allDogs;

    public EditAllDogs() {
        // Required empty public constructor
    }

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_all_dogs, container, false);
    }
}