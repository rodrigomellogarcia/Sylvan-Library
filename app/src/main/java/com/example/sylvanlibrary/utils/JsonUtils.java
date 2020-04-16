package com.example.sylvanlibrary.utils;

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
            try {
                cards[i] = new Card(cardsJsonArray.getJSONObject(i).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return cards;
    }

}
