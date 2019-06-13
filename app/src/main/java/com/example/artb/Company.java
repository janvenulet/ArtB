package com.example.artb;

import androidx.annotation.Nullable;

public class Company {


    public String name;
    public String country;
    public String industry;

    public int stockValue;
    public int ownedShares;

    public float lastChange = 0;


    public Company(String name, @Nullable String country, @Nullable String industry, int value){
        this.name = name;
        this.country = country;
        this.industry = industry;
        this.stockValue = value;
        ownedShares = 0;
    }


    public Company(String name, @Nullable String country, @Nullable String industry, String value){
        this.name = name;
        this.country = country;
        this.industry = industry;
        this.stockValue = Integer.parseInt(value.replace("$", ""));
        ownedShares = 0;
    }

}
