package com.example.sylvanlibrary.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.sylvanlibrary.AppExecutors;
import com.example.sylvanlibrary.BinderAdapter;
import com.example.sylvanlibrary.BinderListViewModel;
import com.example.sylvanlibrary.R;
import com.example.sylvanlibrary.cardroom.Binder;
import com.example.sylvanlibrary.cardroom.BinderDatabase;

import java.util.List;

public class BinderListActivity extends AppCompatActivity implements BinderAdapter.BinderListClickListener{

    RecyclerView recyclerView;

    BinderAdapter mAdapter;

    BinderDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_list);

        recyclerView = findViewById(R.id.rv_binderlist_binders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        mAdapter = new BinderAdapter(this);
        recyclerView.setAdapter(mAdapter);

        mDatabase = BinderDatabase.getInstance(getApplicationContext());
        retrieveBinders();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // retrieveBinders();
    }

    private void retrieveBinders() {
        final LiveData<List<Binder>> binders = mDatabase.binderDao().loadAllBinders();
        //BinderListViewModel viewModel = ViewModelProviders.of(this).get(BinderListViewModel.class);
        binders.observe(this, new Observer<List<Binder>>() {
            @Override
            public void onChanged(List<Binder> binders) {
                mAdapter.setBinders(binders);
            }
        });
    }

    public void onAddBinderClicked(View view) {
        Intent newBinderIntent = new Intent(this, NewBinderActivity.class);
        Log.d("BinderListActivity","Entrando em NewBinderActivity");
        startActivity(newBinderIntent);
    }

    @Override
    public void onBinderListClick(final int binderIndex) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.binderDao().deleteBinder(mAdapter.getBinders().get(binderIndex));
                //retrieveBinders();
            }
        });
    }

}
