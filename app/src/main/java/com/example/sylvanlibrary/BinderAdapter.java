package com.example.sylvanlibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sylvanlibrary.cardroom.Binder;

import java.util.ArrayList;
import java.util.List;

public class BinderAdapter extends RecyclerView.Adapter<BinderAdapter.BinderViewHolder>{
    private List<Binder> binders = new ArrayList<>();
    BinderListClickListener mBinderListClickListener;

    public interface BinderListClickListener {
        void onBinderListClick(int binderIndex);
    }

    public BinderAdapter (BinderListClickListener clickListener) {
        mBinderListClickListener = clickListener;
    }

    public List<Binder> getBinders() {
        return binders;
    }

    @NonNull
    @Override
    public BinderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.binder_list_item, parent, false);

        return new BinderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BinderViewHolder holder, int position) {
        Binder currBinder = binders.get(position);
        holder.mNameTextView.setText(currBinder.getName());
    }

    @Override
    public int getItemCount() {
        return binders.size();
    }

    public void setBinders(List<Binder> binders) {
        this.binders = binders;
        notifyDataSetChanged();
    }

    class BinderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mNameTextView;

        public BinderViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tv_binderitem_name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBinderListClickListener.onBinderListClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            mBinderListClickListener.onBinderListClick(getAdapterPosition());
        }
    }
}
