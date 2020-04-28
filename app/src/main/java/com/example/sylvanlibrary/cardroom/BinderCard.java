package com.example.sylvanlibrary.cardroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.sylvanlibrary.Card;

@Entity(tableName = "t_binder_card")
public class BinderCard {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "binder_id")
    private int binderId;

    @ColumnInfo(name = "card_id")
    private int cardId;

    @ColumnInfo(name = "card_multiverse_id")
    private int cardMultiverseId;

    private int condition;

    public BinderCard(int id, int binderId, int cardId, int cardMultiverseId, int condition) {
        this.id = id;
        this.binderId = binderId;
        this.cardId = cardId;
        this.cardMultiverseId = cardMultiverseId;
        this.condition = condition;
    }

    @Ignore
    public BinderCard(int binderId, Card card) {
        this.binderId = binderId;
        this.cardId = card.id;
        this.cardMultiverseId = card.multiverseId;
        this.condition = 0;
    }
}
