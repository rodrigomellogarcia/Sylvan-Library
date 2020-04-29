package com.example.sylvanlibrary.cardsearch;

import androidx.lifecycle.MutableLiveData;

import com.example.sylvanlibrary.Card;

import java.util.ArrayList;
import java.util.List;

public class CardSearchRepository {

    private static CardSearchRepository mInstance;
    private ArrayList<Card> dataSet = new ArrayList<>();

    public static CardSearchRepository getInstance() {
        if(mInstance == null) {
            mInstance = new CardSearchRepository();
        }
        return mInstance;
    }

    public MutableLiveData<List<Card>> getSearchedCards() {
        //TODO: Adicionar dados da API no dataSet
        MutableLiveData<List<Card>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setSearchedCards() {

    }


}
