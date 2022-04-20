package com.example.a4pokecards;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//This class just parses the JSON string that we've received

//Api Key
//2ed062a8-e3a1-4059-abff-7690fcad1ee1

//A card ID will look like
//swsh4-25
public class JsonService {

    public Card getCardData(String cardJSON) {

        Card returnCard = new Card();

        try {
            JSONObject cardObject = new JSONObject(cardJSON);
            JSONObject dataObject = cardObject.getJSONObject("data");
            JSONObject imageObject = dataObject.getJSONObject("images");
            JSONObject setObject = dataObject.getJSONObject("set");

            if (dataObject.has("id")) {
                returnCard.setCardID(dataObject.getString("id"));
            }

            if (dataObject.has("name")) {
                returnCard.setCardName(dataObject.getString("name"));
            }

            if (dataObject.has("number")) {
                returnCard.setCardNumber(dataObject.getString("number"));
            }

            if (dataObject.has("supertype")) {
                returnCard.setSuperType(dataObject.getString("supertype"));
            }

            if (dataObject.has("hp")) {
                returnCard.setHP(dataObject.getString("hp"));
            }

            if (dataObject.has("types")) {
                returnCard.setType(dataObject.getString("types"));
            }

            if (dataObject.has("flavorText")) {
                returnCard.setFlavorText(dataObject.getString("flavorText"));
            }

            if (setObject.has("id")) {
                returnCard.setHostSet(setObject.getString("id"));
            }

            if (dataObject.has("rarity")) {
                returnCard.setCardRarity(dataObject.getString("rarity"));
            }

            if (imageObject.has("small")) {
                returnCard.setLargeImageLink(imageObject.getString("small"));
            }

            if (imageObject.has("large")) {
                returnCard.setSmallImageLink(imageObject.getString("large"));
            }

            if (setObject.has("printedTotal")) {
                returnCard.setPrintedTotal(setObject.getInt("printedTotal"));
            }

            if (setObject.has("name")) {
                returnCard.setHostSetName(setObject.getString("name"));
            }

            if (setObject.has("series")) {
                returnCard.setHostSetSeries(setObject.getString("series"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return returnCard;
    }

}
