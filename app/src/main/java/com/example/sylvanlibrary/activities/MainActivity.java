package com.example.sylvanlibrary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;

import com.example.sylvanlibrary.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSearch(View view) {
        Intent searchIntent = new Intent(this, CardSearchActivity.class);
        startActivity(searchIntent);
    }

    public void goToBinder(View view) {
        Intent binderIntent = new Intent(this, BinderListActivity.class);
        startActivity(binderIntent);
    }


}
