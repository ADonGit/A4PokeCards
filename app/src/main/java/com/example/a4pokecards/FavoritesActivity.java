package com.example.a4pokecards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity
        implements CardDBService.dbListener
{

    Favorites favCards = new Favorites();

    RecyclerView.LayoutManager layoutManager;
    RecyclerView cardTable;
    CardRecyclerAdapter cardAdapter;
    ImageView cardImageView;

    Button delButton;

    CardDBService dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);


        dbManager = ((MyApp)getApplication()).dbManager;
        dbManager.getDB(this);
        dbManager.listener = this;

        layoutManager = new LinearLayoutManager(this);
        cardImageView = (ImageView) findViewById(R.id.recCardImage);
        delButton = (Button) findViewById(R.id.delButton);

        dbManager.getAllCards();

        cardTable = findViewById(R.id.cardRecView);
        cardAdapter = new CardRecyclerAdapter(favCards.cardList, this);
        cardTable.setAdapter(cardAdapter);
        cardTable.setLayoutManager(new LinearLayoutManager(this));
        cardTable.setNestedScrollingEnabled(false);


        /*
        delButton.setOnClickListener(new View.OnClickListener() {

           // int position = viewHolder.getAdapterPosition();
            //dbService.deleteCar(adapter.carList.get(position));

            @Override
            public void onClick(View view) {
                dbManager.listener = FavoritesActivity.this;
                //dbManager.deleteCard();
            }
        });*/


    }


    @Override
    public void onListReady(List<Card> faveList) {
        favCards.cardList = (ArrayList<Card>) faveList;
        //cardAdapter.notifyDataSetChanged();

        cardTable = findViewById(R.id.cardRecView);
        cardAdapter = new CardRecyclerAdapter(favCards.cardList, this);
        cardTable.setAdapter(cardAdapter);
        cardTable.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onAddDone() {
        dbManager.getAllCards();
    }

    @Override
    public void CardDeleted() {
        dbManager.getAllCards();
    }

}