package com.dogsy.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dogsy.R;


public class editAllDogs extends Fragment {


    private static final String LIST_ALL_DOGS = "param1";

    private String allDogs;

    public editAllDogs() {
        // Required empty public constructor
    }

    public static editAllDogs newInstance(String getAllDogs) {
        editAllDogs fragment = new editAllDogs();
        Bundle args = new Bundle();
        args.putString(LIST_ALL_DOGS, getAllDogs);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allDogs = getArguments().getString(LIST_ALL_DOGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_all_dogs, container, false);
    }
}