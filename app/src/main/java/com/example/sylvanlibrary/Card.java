package com.example.sylvanlibrary;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Card  implements Serializable {
    public int id;
    public int multiverseId;
    public String name;
    public String imageUrl;
    public String type;

    public Card(String name, @Nullable String imageUrl, String type, int multiverseId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.type = type;
        this.multiverseId = multiverseId;
    }

    public static String getCardBackUrl() {
        return "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=0&type=card";
    }
}
