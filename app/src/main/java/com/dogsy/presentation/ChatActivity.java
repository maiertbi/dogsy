package com.dogsy.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dogsy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.dogsy.ChatItem;
import com.dogsy.RecyclerViewAdapter;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    RecyclerView mRecyclerView = null;
    RecyclerViewAdapter mAdapter = null;
    ArrayList<ChatItem> mList;

    private Drawable mImageDrawable;
    private String mMainText;
    private String mSubText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

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

        // recycler view
        mRecyclerView = findViewById(R.id.chatting_list_view);
        mList = new ArrayList<>();

        mAdapter = new RecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.person_plus, null);

        addItem(mImageDrawable, "Fabian",   "hey baby :)");
        addItem(mImageDrawable, "Yujin",  "Good morning!");
        addItem(mImageDrawable, "Tobi",  "genius");
        addItem(mImageDrawable, "David",  "I have a non-binary dog");
        addItem(mImageDrawable, "Alex",  "Still no answer from her");

        mAdapter.notifyDataSetChanged();
    }

    private void addItem(Drawable icon, String mainText, String subText) {
        ChatItem item = new ChatItem();

        item.setIcon(icon);
        item.setTitle(mainText);
        item.setDesc(subText);

        mList.add(item);
    }
}