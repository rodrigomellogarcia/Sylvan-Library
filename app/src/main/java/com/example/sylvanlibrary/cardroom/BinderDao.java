package com.example.sylvanlibrary.cardroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BinderDao {

    @Query("SELECT * FROM t_binder")
    LiveData<List<Binder>> loadAllBinders();

    @Query("SELECT * FROM t_binder WHERE id = :id")
    LiveData<Binder> loadBinder(int id);

    @Insert
    void insertBinder(Binder binder);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBinder(Binder binder);

    @Delete
    void deleteBinder(Binder binder);
}
