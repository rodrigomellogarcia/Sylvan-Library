package com.example.sylvanlibrary;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sylvanlibrary.cardroom.Binder;
import com.example.sylvanlibrary.cardroom.BinderDatabase;

import java.util.List;

public class BinderListViewModel extends AndroidViewModel {

    private LiveData<List<Binder>> binders;

    public BinderListViewModel(@NonNull Application application) {
        super(application);
        BinderDatabase database = BinderDatabase.getInstance(this.getApplication());

        Log.d(BinderListViewModel.class.getSimpleName(), "Actively retrieving binders from Database");

        binders = database.binderDao().loadAllBinders();
    }

    public LiveData<List<Binder>> getBinders() {return binders;}
}
