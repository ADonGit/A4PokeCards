package com.example.a4pokecards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder> {

    ArrayList<Card> faveList = new ArrayList<>();
    Context context;


    public CardRecyclerAdapter(ArrayList<Card> recCardList, Context context) {
        this.faveList = recCardList;
        this.context = context;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.card_row, parent, false);
       return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardRecyclerAdapter.CardViewHolder holder, int position) {
        holder.cardName.setText(faveList.get(position).getCardName());
        holder.cardHP.setText(faveList.get(position).getHP());
        holder.cardSeries.setText(faveList.get(position).getHostSetSeries());
        holder.cardSet.setText(faveList.get(position).getHostSetName());
        holder.flavorText.setText(faveList.get(position).getFlavorText());

        Picasso.get().load(faveList.get(position).getSmallImageLink()).into(holder.cardImage);

    }

    @Override
    public int getItemCount() {
        return faveList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView cardName;
        TextView cardHP;
        TextView cardSeries;
        TextView cardSet;
        TextView flavorText;

        ImageView cardImage;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardName = itemView.findViewById(R.id.recCardData);
            cardHP = itemView.findViewById(R.id.recHPData);
            cardSeries = itemView.findViewById(R.id.recSeriesData);
            cardSet = itemView.findViewById(R.id.recSetData);
            flavorText = itemView.findViewById(R.id.recFlavorData);

            cardImage = itemView.findViewById(R.id.recCardImage);
        }
    }
}
