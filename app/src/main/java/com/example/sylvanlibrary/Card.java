package com.example.sylvanlibrary;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Card  implements Serializable {
    public String name;
    public String imageUrl;
    public int multiverseId;

    public Card(String name, @Nullable String imageUrl, int multiverseId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.multiverseId = multiverseId;
    }

    public static String getCardBackUrl() {
        return "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=0&type=card";
    }
}
