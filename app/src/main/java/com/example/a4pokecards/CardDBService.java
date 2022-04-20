package com.example.a4pokecards;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CardDBService {

    interface dbListener {
        void onListReady(List<Card> faveList);

        void onAddDone();

        void CardDeleted();
    }

    dbListener listener;

    static CardDB db;
    ExecutorService dbExecutor = Executors.newFixedThreadPool(4);
    Handler mainThread_Handler = new Handler(Looper.getMainLooper());


    static void buildDBInstance(Context context) {
        db = Room.databaseBuilder(context, CardDB.class, "FavoriteDB").build();
    }

    public CardDB getDB(Context context) {
        if (db == null) {
            buildDBInstance(context);
        }
        return db;
    }

    public void saveNewFavorite(Card newFave) {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.cardDao().addNewFavoriteCard(newFave);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onAddDone();
                    }
                });
            }
        });
    }

    public void getAllCards() {
         //= null;

        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //faveList.add(db.cardDao().getAllFaves());
                List<Card> faveList = db.cardDao().getAllFaves();
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onListReady(faveList);
                    }
                });
            }
        });
    }

    public void deleteCard(Card del) {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.cardDao().removeFavoriteCard(del);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.CardDeleted();
                    }
                });
            }
        });
    }

}
