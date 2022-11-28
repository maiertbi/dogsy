package com.dogsy.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogsy.R;
import com.dogsy.application.service.DogService;
import com.dogsy.application.service.MatchingService;
import com.dogsy.domain.model.Dog;
import com.dogsy.domain.model.User;
import com.google.android.material.imageview.ShapeableImageView;

public class UserDogView extends Fragment {
    private static final String ARG_PARAM1 = "user";

    // TODO: DB - create variables where user data gets stored
    private User user;

    public UserDogView() {
    }

    // TODO: DB - change params so the userID gets called
    public static UserDogView newInstance(User user) {
        UserDogView fragment = new UserDogView();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_PARAM1);
        }

        // TODO: DB - get User and save it to variables
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

        FrameLayout dog1 = view.findViewById(R.id.frame_layout_dog1);
        FrameLayout dog2 = view.findViewById(R.id.frame_layout_dog2);
        FrameLayout dog3 = view.findViewById(R.id.frame_layout_dog3);
        FrameLayout dog4 = view.findViewById(R.id.frame_layout_dog4);
        FrameLayout dog5 = view.findViewById(R.id.frame_layout_dog5);


        // TODO: DB - change data to actual users data
        //  max 3 user_pictures are allowed! if one Imageview is not needed --> hide it!
        // user_name.setText();
        // user_img3.setVisibility(View.GONE);

        // TODO: DB - get Dogs
        //  5 FrameLayout have been created --> so min 1 dog and max 5 dogs per user!
        //  if a Framelayout is not needed --> hide it!
        /*
            The best way to implement the hiding of the FrameLayouts/user pictures/... which are not needed would be like this example:
                int userDogCount = 3;
                int delFrameCount = 5 - userDogCount;

                if (--delFrameCount >= 0) dog5.setVisibility(View.GONE);
                if (--delFrameCount >= 0) dog4.setVisibility(View.GONE);
                if (--delFrameCount >= 0) dog3.setVisibility(View.GONE);
                if (--delFrameCount >= 0) dog2.setVisibility(View.GONE);
         */

        // create first Dog
        DogView fragment_dog1 = DogView.newInstance(DogService.instance.getDogById(user.getId(),user.getDogIds().get(0)).get()); // TODO: 28/11/2022 add the dog of the next user
        getParentFragmentManager().beginTransaction().replace(R.id.frame_layout_dog1, fragment_dog1).commit();

        return view;
    }
}