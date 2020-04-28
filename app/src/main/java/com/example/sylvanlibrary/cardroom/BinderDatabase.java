package com.example.sylvanlibrary.cardroom;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Binder.class}, version = 1, exportSchema = false)
public abstract class BinderDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "binders";
    private static BinderDatabase sInstance;

    public static BinderDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(BinderDatabase.class.getSimpleName(), "Creating new database");
                sInstance = Room.databaseBuilder(context.getApplicationContext(), BinderDatabase.class, BinderDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(BinderDatabase.class.getSimpleName(),"Getting the database instance");
        return sInstance;
    }

    public abstract BinderDao binderDao();

}
