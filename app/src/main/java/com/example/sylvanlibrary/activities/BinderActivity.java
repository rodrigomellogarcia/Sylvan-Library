package com.example.sylvanlibrary.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sylvanlibrary.CardAdapter;
import com.example.sylvanlibrary.R;

public class BinderActivity extends AppCompatActivity implements CardAdapter.ListCardClickListener {

    RecyclerView mRecyclerView;
    CardAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);


        mRecyclerView = findViewById(R.id.rv_binder_cards);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CardAdapter(this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onListCardClick(int clickedCardIndex) {

    }
}
