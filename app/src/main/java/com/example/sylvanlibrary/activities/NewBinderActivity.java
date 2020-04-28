package com.example.sylvanlibrary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sylvanlibrary.AppExecutors;
import com.example.sylvanlibrary.R;
import com.example.sylvanlibrary.cardroom.Binder;
import com.example.sylvanlibrary.cardroom.BinderDatabase;

public class NewBinderActivity extends AppCompatActivity {


    EditText mBinderNameEditText;

    private BinderDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_binder);

        mBinderNameEditText = findViewById(R.id.et_newbinder);


        mDatabase = BinderDatabase.getInstance(getApplicationContext());
    }

    public void onSaveBinderClicked(View view) {
        String newBinderName = mBinderNameEditText.getText().toString();

        if (newBinderName == null || newBinderName == "")
            newBinderName = "Nova pasta";

        final Binder newBinder = new Binder(newBinderName);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.binderDao().insertBinder(newBinder);
                finish();
            }
        });
    }

}
