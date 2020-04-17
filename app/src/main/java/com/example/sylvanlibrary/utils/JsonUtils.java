package com.example.sylvanlibrary.utils;

import android.util.Log;

import com.example.sylvanlibrary.Card;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Card[] JsonToCards (String jsonResult) {

        JSONObject cardsJsonObject = null;
        try {
            cardsJsonObject = new JSONObject(jsonResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray cardsJsonArray = null;
        try {
            cardsJsonArray = cardsJsonObject.getJSONArray("cards");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Card[] cards = new Card[cardsJsonArray.length()];


        for (int i = 0; i < cards.length; i++) {
            String newCardName = null;
            String newCardImageUrl = null;
            int newCardMultiverseId = 0;

            try {
                newCardName = cardsJsonArray.getJSONObject(i).getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
                //Log.w("JsonUtils", "Card[" + Integer.toString(i) + "] doesn't have name field");
            }

            try {
                newCardImageUrl = cardsJsonArray.getJSONObject(i).getString("imageUrl");
            } catch (JSONException e) {
                e.printStackTrace();
                //Log.w("JsonUtils", "Card[" + Integer.toString(i) + "] doesn't have imageUrl field");
            }

            try {
                newCardMultiverseId = cardsJsonArray.getJSONObject(i).getInt("multiverseid");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            cards[i] = new Card(newCardName, newCardImageUrl, newCardMultiverseId);
        }

        return cards;
    }

}
