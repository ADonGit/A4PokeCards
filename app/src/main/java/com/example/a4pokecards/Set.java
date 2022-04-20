package com.example.a4pokecards;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

public class Set {


    String setID;

    String setName;
    String setSeries;

    int printedTotal;
    int realTotal;

    String releaseDate;
    String updatedAt;

    String symbolImageLink;
    String logoImageLink;

    ArrayList<Card> cardList = new ArrayList<>();

    //Default Constructor
    Set() {
        setID = "";
        setName = "";
        setSeries = "";

        printedTotal = 0;
        realTotal = 0;
        releaseDate = "";
        updatedAt = "";

        symbolImageLink = "";
        logoImageLink = "";
    }

    //Add Card to the Set
    void addCardToSet(Card addCard) {
        cardList.add(addCard);
    }


    //Setters
    void setSetID(String sID) {
        setID = sID;
    }

    void setSetName(String sName) {
        setName = sName;
    }

    void setSetSeries(String sSeries) {
        setSeries = sSeries;
    }

    void setPrintedTotal(int pTotal) {
        printedTotal = pTotal;
    }

    void setRealTotal(int rTotal) {
        realTotal = rTotal;
    }

    void setReleaseDate(String rDate) {
        releaseDate = rDate;
    }

    void setUpdatedAt(String uAt) {
        updatedAt = uAt;
    }

    void setSymbol(String symLink) {
        symbolImageLink = symLink;
    }

    void setLogo(String logoLink) {
        logoImageLink = logoLink;
    }

    //Getters
    String getSetID() {
        return setID;
    }

    String getSetName() {
        return setName;
    }

    String getSetSeries() {
        return setSeries;
    }

    int getPrintedTotal() {
       return printedTotal;
    }

    int getRealTotal() {
        return realTotal;
    }

    String getReleaseDate() {
        return releaseDate;
    }

    String getUpdatedAt() {
        return updatedAt;
    }

    String getSymbolImageLink() {
        return symbolImageLink;
    }

    String getLogoImageLink() {
        return logoImageLink;
    }


}
