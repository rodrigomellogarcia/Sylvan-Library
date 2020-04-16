package com.example.sylvanlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sylvanlibrary.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.resource.Card;

public class MainActivity extends AppCompatActivity {

    EditText mSearchEditText;
    Button mSearchButton;
    TextView mSearchStatusTextView;
    RecyclerView mRecyclerView;
    CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchEditText = findViewById(R.id.et_search);
        mSearchButton = findViewById(R.id.button_search);
        mSearchStatusTextView = findViewById(R.id.tv_search_status);
        mRecyclerView = findViewById(R.id.rv_cards);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);

        mCardAdapter = new CardAdapter();

        mRecyclerView.setAdapter(mCardAdapter);
    }

    public void searchByName(View view) {

        String searchedName = mSearchEditText.getText().toString();

        URL url = NetworkUtils.buildURLSearchByName(searchedName);

        new SearchByNameTask().execute(url);
    }

    public class SearchByNameTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mSearchStatusTextView.setText("Pesquisando...");
            mCardAdapter.setCards(null);
        }

        @Override
        protected String doInBackground(URL... urls) {

            String jsonResults = null;
            try {
                jsonResults = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonResults;
        }

        @Override
        protected void onPostExecute(String s) {
            mSearchStatusTextView.setText("Foram encontrados " + Integer.toString(JsonUtils.JsonToCards(s).length)  + " resultados.");
            mCardAdapter.setCards(JsonUtils.JsonToCards(s));
        }
    }

}


