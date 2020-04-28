package com.example.sylvanlibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{
    public Card[] cards;

    ListCardClickListener mListCardClickListener;

    public CardAdapter(ListCardClickListener clickListener) {
        mListCardClickListener = clickListener;
    }

    public interface ListCardClickListener {
        void onListCardClick(int clickedCardIndex);
    }




    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.card_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        CardViewHolder viewHolder = new CardViewHolder(view, mListCardClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(cards[position]);
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (cards == null)
            return 0;
        return cards.length;
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mNameTextView;
        TextView mCardType;
        ImageView mCardPreviewImageView;
        ListCardClickListener mListCardClickListener;


        public CardViewHolder(View itemView, ListCardClickListener clickListener) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.tv_name);
            mCardPreviewImageView = (ImageView) itemView.findViewById(R.id.iv_card_preview);
            mCardType = (TextView) itemView.findViewById(R.id.tv_card_type);
            mListCardClickListener = clickListener;

            itemView.setOnClickListener(this);
        }

        void bind(Card card) {
            if (card == null) {
                return;
            }

            if (card.name != null) mNameTextView.setText(card.name);
            if (card.type != null) mCardType.setText(card.type);
            if(card.imageUrl != null) {
                Picasso.get().load(card.imageUrl).into(mCardPreviewImageView);
            } else {
                Picasso.get().load(Card.getCardBackUrl()).into(mCardPreviewImageView);
            }
        }

        @Override
        public void onClick(View v) {
            mListCardClickListener.onListCardClick(getAdapterPosition());
        }
    }
}
