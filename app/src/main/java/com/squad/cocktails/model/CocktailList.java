package com.squad.cocktails.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailList {

    @SerializedName("drinks")
    private ArrayList<Cocktail> cocktails;

    public ArrayList<Cocktail> getCocktails() {
        return cocktails;
    }

}
