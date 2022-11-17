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

    private ArrayList<Drawable> mImageDrawable;

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
        mImageDrawable = new ArrayList<>();

        mAdapter = new RecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.fabian, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.yujin, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.tobi, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.david, null));
        mImageDrawable.add(ResourcesCompat.getDrawable(getResources(), R.drawable.alex, null));


        addItem(mImageDrawable.get(0), "Fabian",   "hey baby :)");
        addItem(mImageDrawable.get(1), "Yujin",  "Good morning!");
        addItem(mImageDrawable.get(2), "Tobi",  "genius");
        addItem(mImageDrawable.get(3), "David",  "I have a non-binary dog");
        addItem(mImageDrawable.get(4), "Alex",  "Still no answer from her");

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