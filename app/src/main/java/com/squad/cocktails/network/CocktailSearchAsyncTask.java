package com.squad.cocktails.network;

import android.os.AsyncTask;

import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.model.CocktailList;
import com.squad.cocktails.util.CocktailParser;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailSearchAsyncTask extends AsyncTask<String, String, CocktailList> {
    private final String apiKey = "1";
    private final String baseApiUrl = "https://www.thecocktaildb.com/api/json/v1/"
            + apiKey;

    private OnCocktailFetchResponse callbackListener;
    private String searchURL = baseApiUrl;

    public void setCallbackListener(OnCocktailFetchResponse callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    protected  CocktailList doInBackground(String... strings) {
        String searchParams = strings[0];
        String searchType = strings[1];
        OkHttpClient client = new OkHttpClient();
        String searchTerms = strings[0].substring(1, strings[0].length()-1);
        String[] terms = searchTerms.split(", ");
        String[] urls = new String[terms.length];

        if (searchType.compareTo("INGR") == 0) {
            //searchURL += "/filter.php?i=";
            searchURL += "/filter.php?i=";
        } else if (searchType.compareTo("NAME") == 0) {
            searchURL += "/search.php?s=";
        }


        for (int i = 0; i < terms.length; i++) {
            terms[i].replaceAll(" ", "_");
            urls[i] = searchURL + terms[i];
        }

        Request[] requests = new Request[urls.length];
        for (int i = 0; i < requests.length; i++) {
            requests[i] = new Request.Builder().url(urls[i]).build();
        }

        Response[] responses = new Response[requests.length];

        CocktailList[] cocktailLists = new CocktailList[requests.length];

        for (int i = 0; i < requests.length; i++) {
            try {
                responses[i] = client.newCall(requests[i]).execute();
                if (responses[i] != null) {
                    cocktailLists[i] = CocktailParser.cocktailListFromJson(responses[i].body().string());
                    //return list;
                }
            } catch (IOException e) {
                //do something
            }
        }
        CocktailList finalList = null;
        if (cocktailLists[0] == null) {
            ArrayList<Cocktail> tempList = new ArrayList<Cocktail>();
            tempList.add(new Cocktail());
            finalList = new CocktailList(tempList);
        } else {
            finalList = cocktailLists[0].cocktailListIntersections(cocktailLists);
        }
        return finalList;
    }

    @Override
    protected void onPostExecute(CocktailList cocktailList) {
        super.onPostExecute(cocktailList);
        callbackListener.onCallback(cocktailList);
    }

    public interface OnCocktailFetchResponse {
        void onCallback(CocktailList cocktailList);
    }

//    public interface OnCocktailFetchCocktail {
//        void onCallback(Cocktail cocktail);
//    }

}
