package com.example.a4pokecards;

import androidx.room.Entity;

import java.util.ArrayList;

@Entity
public class Favorites {

    ArrayList<Card> cardList = new ArrayList<Card>();

    void setFavorite(Card entry) {cardList.add(entry);}

    ArrayList<Card> getCardList() { return cardList; }

}
