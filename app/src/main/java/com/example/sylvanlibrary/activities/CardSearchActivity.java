package com.example.sylvanlibrary.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sylvanlibrary.Card;
import com.example.sylvanlibrary.CardAdapter;
import com.example.sylvanlibrary.cardsearch.CardSearchViewModel;
import com.example.sylvanlibrary.utils.NetworkUtils;
import com.example.sylvanlibrary.R;
import com.example.sylvanlibrary.utils.JsonUtils;
import com.example.sylvanlibrary.utils.ScryfallUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class CardSearchActivity extends AppCompatActivity implements CardAdapter.ListCardClickListener {

    EditText mSearchEditText;
    Button mSearchButton;
    TextView mSearchStatusTextView;
    RecyclerView mRecyclerView;
    CardAdapter mCardAdapter;

    private CardSearchViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_seach);

        mSearchEditText = findViewById(R.id.et_search);
        mSearchButton = findViewById(R.id.button_search);
        mSearchStatusTextView = findViewById(R.id.tv_search_status);
        mRecyclerView = findViewById(R.id.rv_cards);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);

        mCardAdapter = new CardAdapter(this);

        mRecyclerView.setAdapter(mCardAdapter);

        mViewModel = new ViewModelProvider(this).get(CardSearchViewModel.class);
        mViewModel.getSearchedCards().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> cards) {
                mCardAdapter.setCards(mViewModel.getSearchedCards());
            }
        });
    }

    public void searchByName(View view) {

        String searchedName = mSearchEditText.getText().toString();

        //URL url = NetworkUtils.buildURLSearchByName(searchedName);
        //new SearchByNameTask().execute(url);

        URL url = ScryfallUtils.buildURLSearchByName(searchedName);
        Log.d("URL", url.toString());

        new ScryByNameTask().execute(url);
    }

    @Override
    public void onListCardClick(int clickedCardIndex) {
        Intent intent = new Intent(this, CardDetailsActivity.class);
        intent.putExtra("card", (Serializable) mCardAdapter.cards[clickedCardIndex]);
        startActivity(intent);
    }

    public class ScryByNameTask extends AsyncTask<URL, Void, String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mSearchStatusTextView.setText("Pesquisando...");
            mCardAdapter.setCards(null);
        }


        @Override
        protected String doInBackground(URL... urls) {
            String jsonResults = null;
            try {
                jsonResults = ScryfallUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResults;
        }

        @Override
        protected void onPostExecute(String s) {
            Card[] cards = new Card[0];

            cards = JsonUtils.scryfallToCards(s);

            mSearchStatusTextView.setText("Foram encontrados " + Integer.toString(cards.length)  + " resultados.");
            mCardAdapter.setCards(cards);
        }
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
            Card[] cards = JsonUtils.JsonToCards(s);
            mSearchStatusTextView.setText("Foram encontrados " + Integer.toString(cards.length)  + " resultados.");
            mCardAdapter.setCards(cards);
        }
    }

}


