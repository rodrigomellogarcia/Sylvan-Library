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
            String newCardType = null;
            int newCardMultiverseId = 0;
            String newCardImageCropUrl = null;

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
                newCardType = cardsJsonArray.getJSONObject(i).getString("type");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                newCardMultiverseId = cardsJsonArray.getJSONObject(i).getInt("multiverseid");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            cards[i] = new Card(newCardMultiverseId, newCardName, newCardType, newCardImageUrl, newCardImageCropUrl);
        }

        return cards;
    }

    public static Card[] scryfallToCards(String jsonResult){

        JSONObject jsonListObject = null;
        try {
            jsonListObject = new JSONObject(jsonResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonData = null;
        try {
            jsonData = jsonListObject.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Card[] cards = new Card[jsonData.length()];

        for (int i = 0; i < cards.length; i++) {
            int newCardMultiverseId = 0;
            String newCardName = null;
            String newCardType = null;
            String newCardImageUrl = null;
            String newCardImageCropUrl = null;


            try {
                newCardName = jsonData.getJSONObject(i).getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                newCardType = jsonData.getJSONObject(i).getString("type_line");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                newCardMultiverseId = jsonData.getJSONObject(i).getJSONArray("multiverse_ids").getInt(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                newCardImageUrl = jsonData.getJSONObject(i).getJSONObject("image_uris").getString("normal");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                newCardImageCropUrl = jsonData.getJSONObject(i).getJSONObject("image_uris").getString("art_crop");
            } catch (JSONException e) {
                e.printStackTrace();

            }

            if (newCardImageCropUrl != null)
                Log.w("JSON", newCardImageCropUrl);

            cards[i] = new Card(newCardMultiverseId, newCardName, newCardType, newCardImageUrl, newCardImageCropUrl);

        }

        return  cards;
    }
}
