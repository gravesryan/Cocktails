package com.squad.cocktails.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailList {

    @SerializedName("drinks")
    private ArrayList<Cocktail> cocktails;

    public CocktailList(ArrayList<Cocktail> list) {
        cocktails = list;
    }

    public ArrayList<Cocktail> getCocktails() {
        return cocktails;
    }

    public CocktailList cocktailListIntersections(CocktailList[] allLists) {
        if (allLists.length == 1)
            return allLists[0];
        int listIdx = 0;

        ArrayList<Cocktail> list1, list2;
        list1 = allLists[listIdx++].getCocktails();

        ArrayList<Cocktail> newList = new ArrayList<>();

        int allLength = allLists.length;

        while (listIdx < allLists.length) {
            list2 = allLists[listIdx++].getCocktails();
//            int i = 0, j = 0;
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (Integer.parseInt(list1.get(i).getCocktailId()) == Integer.parseInt(list2.get(j).getCocktailId()))
                        if (!newList.contains(list2.get(j)))
                            newList.add(list2.get(j));
                }
            }
            list1 = newList;
        }
        CocktailList list = new CocktailList(newList);
        return new CocktailList(newList);
    }

}
