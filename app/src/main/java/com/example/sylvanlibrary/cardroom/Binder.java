package com.example.sylvanlibrary.cardroom;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "t_binder")
public class Binder {
    @PrimaryKey(autoGenerate = true)
    int id;

    String name;

    public Binder(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Binder(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
