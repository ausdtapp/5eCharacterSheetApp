package com.example.a5echaractersheetapp;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView characterItemView;

    public OnCharacterListener listener;


    private CharacterViewHolder(View itemView, OnCharacterListener listener) {
        super(itemView);
        characterItemView = itemView.findViewById(R.id.textView);

        this.listener = listener;
        characterItemView.setOnClickListener(this);

    }

    //Sets the text that shows for each entry in recyclerview
    public void bind(String text) {
        characterItemView.setText(text);
    }

    //Creates CharacterViewHolder object with onclicklistener from overriden in main
    static CharacterViewHolder create(ViewGroup parent, OnCharacterListener listener) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CharacterViewHolder(view, listener);
    }

    public interface OnCharacterListener {
        void onCharacterClick(int position);
    }

    @Override
    public void onClick(View v) {
        Log.d("On Click: ", "Adapter position is " + getAdapterPosition());

        listener.onCharacterClick(getAdapterPosition());

        Log.d("On Click", "Ran onCharacterClick");
    }





}

public class CharacterListAdapter extends ListAdapter<com.example.a5echaractersheetapp.Character, CharacterViewHolder>  {
    private CharacterViewHolder.OnCharacterListener listener;


    public CharacterListAdapter(@NonNull DiffUtil.ItemCallback<com.example.a5echaractersheetapp.Character> diffCallback, CharacterViewHolder.OnCharacterListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    //Passes listener to new characterviewholder objects
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CharacterViewHolder.create(parent, this.listener);
    }

    //Assigns character item names to viewholder objects
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        com.example.a5echaractersheetapp.Character current = getItem(position);
        holder.bind(current.getCharName());
    }


    //Allows comparison of character items to see if items have changed
    static class CharacterDiff extends DiffUtil.ItemCallback<com.example.a5echaractersheetapp.Character> {

        @Override
        public boolean areItemsTheSame(@NonNull com.example.a5echaractersheetapp.Character oldItem, @NonNull com.example.a5echaractersheetapp.Character newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull com.example.a5echaractersheetapp.Character oldItem, @NonNull com.example.a5echaractersheetapp.Character newItem) {
            return oldItem.getCharName().equals(newItem.getCharName());
        }
    }



}