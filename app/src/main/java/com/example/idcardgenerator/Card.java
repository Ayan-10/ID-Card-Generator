package com.example.idcardgenerator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Card {
    String name;
    String id;
    String branch;
    String father;
    String cn;
    String adrs;
    Bitmap image;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }

    public String getFather() {
        return father;
    }

    public String getCn() {
        return cn;
    }

    public String getAdrs() {
        return adrs;
    }

    public Bitmap getImage() {
        return image;
    }

    public Card(String name, String id, String branch, String father, String cn, String adrs, Bitmap image) {
        this.name =name;
        this.id = id;
        this.branch = branch;
        this.father = father;
        this.cn = cn;
        this.adrs = adrs;
        this.image = image;
    }

}
