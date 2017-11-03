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
            int i = 0, j = 0;
            while(i < list1.size() && j < list2.size()) {
                if (list1.get(i).getName().compareTo(list2.get(j).getName()) < 0)
                    i++;
                else if (list1.get(i).getName().compareTo(list2.get(j).getName()) > 0)
                    j++;
                else {
                    newList.add(list2.get(j++));
                    i++;
                }
            }
            list1 = newList;
            if (listIdx < allLists.length)
                newList = new ArrayList<>();
        }
        return new CocktailList(newList);
    }

    public void sortAlphabetically() {
        quickSort(0, cocktails.size());
    }

    private void quickSort(int a, int b) {
        int i = a, j = b;
        String pivot = cocktails.get(a + (b-a)/2).getName();
        while (i <= j) {
            while (cocktails.get(i).getName().compareTo(pivot) < 0) {
                i++;
            }
            while (cocktails.get(j).getName().compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                Cocktail temp = cocktails.get(i);
                cocktails.set(i, cocktails.get(j));
                cocktails.set(j, temp);
                i++;
                j--;
            }
        }
        if (a < j)
            quickSort(a, j);
        if (i < b)
            quickSort(i, b);
    }

}
