package com.example.a4pokecards;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//Api Key
//2ed062a8-e3a1-4059-abff-7690fcad1ee1

//A card ID will look like
//hgss1-25

public class NetworkingService {

    private String apiKey = "2ed062a8-e3a1-4059-abff-7690fcad1ee1";
    private String urlPre = "https://api.pokemontcg.io/v2/cards/";

    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler = new Handler(Looper.getMainLooper());

    interface NetworkingListener {
        void dataListener(String JSONString);
    }

    public void getCardData(String set, String card){
        connect(set + "-" + card);
    }


    public NetworkingListener listener;

    public void connect(String urlCode) {
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;

                try {
                    String JSONString = "";

                    URL urlObject = new URL((urlPre + urlCode));

                    httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("x-api-key", apiKey);  //Adding our API key via the x-api-key header
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");

                    InputStream in = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);

                    int inputStreamData = 0;
                    while ((inputStreamData = reader.read()) != -1) {
                        char current = (char)inputStreamData;
                        JSONString += current;
                    } //When we exit, our JSON is done and ready

                    //Now we send it to be decoded in the JSONService
                    final String finalJSONString = JSONString;
                    networkingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.dataListener(finalJSONString);
                        }
                    });

                    } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    httpURLConnection.disconnect();
                }
            }
        });
    }

}
