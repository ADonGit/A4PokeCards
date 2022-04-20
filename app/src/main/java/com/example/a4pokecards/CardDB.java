package com.example.a4pokecards;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Card.class}, version = 1)
public abstract class CardDB extends RoomDatabase {
    public abstract CardDAO cardDao();
}


