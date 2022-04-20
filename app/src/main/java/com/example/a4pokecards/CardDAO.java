package com.example.a4pokecards;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDAO {

    @Insert
    void addNewFavoriteCard(Card add);

    @Delete
    void removeFavoriteCard(Card del);

    @Query("SELECT * FROM Card")
    List<Card> getAllFaves();

}
