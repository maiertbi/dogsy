package com.dogsy.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.dogsy.presentation.classes.ChatItem;
import com.dogsy.ChatRecyclerViewAdaptor;
import com.dogsy.presentation.classes.PhotoItem;
import com.dogsy.PhotoRecyclerViewAdaptor;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    RecyclerView cRecyclerView = null;
    ChatRecyclerViewAdaptor cAdapter = null;
    ArrayList<ChatItem> cList;
    private ArrayList<Drawable> cImageDrawable;

    RecyclerView mRecyclerView = null;
    PhotoRecyclerViewAdaptor mAdapter = null;
    ArrayList<PhotoItem> mList;
    private ArrayList<Drawable> mImageDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        cRecyclerView = findViewById(R.id.chatting_list_view);
        mRecyclerView = findViewById(R.id.matching_list_view);


        // ========================chatting_list_view=================================
        /*
            As far as I understood it the chatting function will not be implemented!
            So therefore the chatting_list_view can remain with the sample data!

            - Tobi
         */

        cList = new ArrayList<>();
        cImageDrawable = new ArrayList<>();

        cAdapter = new ChatRecyclerViewAdaptor(cList);
        cRecyclerView.setAdapter(cAdapter);
        cRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        cImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.fabian, null));
        cImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.yujin, null));
        cImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.tobi, null));
        cImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.david, null));
        cImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.alex, null));

        addItem(cImageDrawable.get(0), "Fabian",   "hey baby :)");
        addItem(cImageDrawable.get(1), "Yujin",  "Good morning!");
        addItem(cImageDrawable.get(2), "Tobi",  "genius");
        addItem(cImageDrawable.get(3), "David",  "I have a non-binary dog");
        addItem(cImageDrawable.get(4), "Alex",  "Still no answer from her");

        cAdapter.notifyDataSetChanged();

        //==========================matching_list_view============================
        mList = new ArrayList<>();
        mImageDrawable = new ArrayList<>();

        mAdapter = new PhotoRecyclerViewAdaptor(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        // TODO: DB - change it for backend usage
        /*
            I don't know if we are going to implement this. This should be the last thing to do.
            If we don't have enough time we can leave this with the sample data.

            - Tobi
         */
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.fabian, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.yujin, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.tobi, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.david, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.alex, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.like, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.dislike, null));

        addPhoto(mImageDrawable.get(0));
        addPhoto(mImageDrawable.get(1));
        addPhoto(mImageDrawable.get(2));
        addPhoto(mImageDrawable.get(3));
        addPhoto(mImageDrawable.get(4));
        addPhoto(mImageDrawable.get(5));
        addPhoto(mImageDrawable.get(6));




        // bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.chatting);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.matching:
                    startActivity(new Intent(getApplicationContext(), MatchingActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.chatting:
                    return true;
            }
            return false;
        });
    }

    private void addItem(Drawable icon, String mainText, String subText) {
        ChatItem item = new ChatItem();

        item.setIcon(icon);
        item.setTitle(mainText);
        item.setDesc(subText);

        cList.add(item);
    }

    private void addPhoto(Drawable icon) {
        PhotoItem item = new PhotoItem();

        item.setIcon(icon);

        mList.add(item);
    }
}