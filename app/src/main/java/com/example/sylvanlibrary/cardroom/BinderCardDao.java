package com.example.sylvanlibrary.cardroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BinderCardDao {

    @Query("SELECT * FROM t_binder_card")
    List<BinderCard> loadAllBinderCards();

    @Query("SELECT * FROM t_binder_card WHERE binder_id = :binderId")
    List<BinderCard> loadAllBinderCardsFromBinder(int binderId);

    @Insert
    void insertBinderCard(BinderCard binderCard);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBinderCard(BinderCard binderCard);

    @Delete
    void deleteBinderCard(BinderCard binderCard);


}
