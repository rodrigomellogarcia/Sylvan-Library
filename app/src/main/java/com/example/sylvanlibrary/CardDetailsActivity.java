package com.example.sylvanlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CardDetailsActivity extends AppCompatActivity {

    Card card;

    ImageView mCardImageView;
    TextView mCardNameTextView;
    TextView mCardTextTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        mCardImageView = findViewById(R.id.iv_details_card_image);
        mCardNameTextView = findViewById(R.id.tv_details_card_name);

        Intent intent = getIntent();

        if (!intent.hasExtra("card")) { return; }

        card = (Card) intent.getExtras().getSerializable("card");

        if(card != null) {
            Picasso.get().load(card.imageUrl).into(mCardImageView);
            mCardNameTextView.setText(card.name);
        } else {
            Log.e("CardDetailsActivity", "onCreate: card is null");
        }

    }

    public void seeOnGatherer(View view) {
        String gathererUrl = "https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=" + Integer.toString(card.multiverseId);
        Uri gathererUri = Uri.parse(gathererUrl);
        Intent gathererIntent = new Intent(Intent.ACTION_VIEW, gathererUri);
        if (gathererIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(gathererIntent);
        }
    }

}
