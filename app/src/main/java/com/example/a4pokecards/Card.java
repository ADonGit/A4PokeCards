package com.example.a4pokecards;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Card {


    String cardID;
    @PrimaryKey(autoGenerate = true)
    int primID;

    String cardName;

    String superType;
    String superSubTypeList;

    //String level;
    String hp;
    String typeList;

    String hostSetID;
    String hostSetSeries;
    String hostSetName;

    String cardNumber;
    String cardRarity;
    String flavorText;

    String smallImageLink;
    String largeImageLink;

    int printedTotal;

    //Constructor
    Card() {
        cardID = "";
        cardName = "";
        superType = "";
        superSubTypeList = "";

        //level = "";
        hp = "";
        typeList = "";
        hostSetID = "";
        hostSetSeries = "";
        hostSetName = "";

        cardNumber = "0";
        //primID = Integer.valueOf(cardNumber);
        //primID = 0;
        cardRarity = "";
        flavorText = "";

        smallImageLink = "";
        largeImageLink = "";

        printedTotal = 0;

    }


    //Setters
    void setCardID(String cID) {
        cardID = cID;
    }

    void setCardName(String cName) {
        cardName = cName;
    }

    void setSuperType(String sType) {
        superType = sType;
    }

    void setSuperSubTypeList(String ssList) {
        superSubTypeList = ssList;
    }

    //void setLevel(String lvl) { level = lvl; }

    void setHP(String sHP) {
        hp = sHP;
    }

    void setType(String tList) {
        typeList = tList;
    }

    void setHostSet(String hSet) {
        hostSetID = hSet;
    }

    void setCardNumber(String cNum) {
        cardNumber = cNum;
    }

    void setCardRarity(String cRarity) {
        cardRarity = cRarity;
    }

    void setFlavorText(String fText) {
        flavorText = fText;
    }

    void setSmallImageLink(String sLink) {
        smallImageLink = sLink;
    }

    void setLargeImageLink(String lLink) {
        largeImageLink = lLink;
    }

    void setPrintedTotal(int tTotal) { printedTotal = tTotal; }

    void setHostSetSeries(String hSet) { hostSetSeries = hSet; }

    void setHostSetName(String nSet) { hostSetName = nSet; }


    //Getters
    String getCardID() {
        return cardID;
    }

    String getCardName() {
        return cardName;
    }

    String getSuperType() {
        return superType;
    }

    String getSuperSubTypeList() {
        return superSubTypeList;
    }

    //String getLevel() { return level; }

    String getHP() {
        return hp;
    }

    String getType() {
        return typeList;
    }

    String getSetID() {
        return hostSetID;
    }

    String getCardNumber() {
        return cardNumber;
    }

    String getCardRarity() {
        return cardRarity;
    }

    String getFlavorText() {
        return flavorText;
    }

    String getSmallImageLink() {
        return smallImageLink;
    }

    String getLargeImageLink() {
        return largeImageLink;
    }

    int getPrintedTotal() { return printedTotal; }

    String getHostSetSeries() { return hostSetSeries; }

    String getHostSetName() { return hostSetName; }





}
