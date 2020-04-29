package com.example.sylvanlibrary.cardsearch;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sylvanlibrary.Card;

import java.util.List;

public class CardSearchViewModel extends AndroidViewModel {

    private MutableLiveData<List<Card>> mSearchedCards;

    public CardSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Card>> getSearchedCards() {
        return mSearchedCards;
    }
}
