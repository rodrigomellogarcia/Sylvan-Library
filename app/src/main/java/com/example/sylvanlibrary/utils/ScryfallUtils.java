package com.example.sylvanlibrary.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ScryfallUtils {

    final static String SCRYFALL_BASE_URL = "https://api.scryfall.com/";

    final static String PATH_CARD = "cards";
    final static String PATH_SEARCH = "search";

    final static String PARAM_NAME = "q";


    public static URL buildURLSearchByName(String cardName) {
        Uri uri = Uri.parse(SCRYFALL_BASE_URL).buildUpon()
                .appendPath(PATH_CARD)
                .appendPath(PATH_SEARCH)
                .appendQueryParameter(PARAM_NAME, cardName)
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
