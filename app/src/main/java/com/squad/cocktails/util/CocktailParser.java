package com.squad.cocktails.util;

import com.google.gson.Gson;
import com.squad.cocktails.model.CocktailList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailParser {
    public static final CocktailList cocktailListFromJson(String responseString) {
        Gson gson = new Gson();
        return gson.fromJson(responseString, CocktailList.class);
    }
}
