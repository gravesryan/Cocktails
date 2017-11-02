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
            + apiKey
            + "/filter.php?i=";

    private OnCocktailFetchResponse callbackListener;

    public void setCallbackListener(OnCocktailFetchResponse callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    protected  CocktailList doInBackground(String... strings) {
        String searchParams = strings[0];
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseApiUrl).newBuilder();
        //urlBuilder.addQueryParameter("your_search_parameters", searchParams);

        String url = urlBuilder.build().toString();
        String searchTerms = strings[0].substring(1, strings[0].length()-1);
        String[] strArray = searchTerms.split(", ");
        if (strArray.length == 1)
            url += strArray[0];
        else
            for (int i = 0; i < strArray.length; i++) {
                    url += "&" + strArray[i];
            }

        System.out.println(url);

        Request request = new Request.Builder().url(url).build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            if (response != null) {
                CocktailList list = CocktailParser.cocktailListFromJson(response.body().string());
                return list;
            }
        } catch (IOException e) {
            //do something
        }

        return null;
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
