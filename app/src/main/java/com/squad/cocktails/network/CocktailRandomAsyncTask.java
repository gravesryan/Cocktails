package com.squad.cocktails.network;

import android.os.AsyncTask;

import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.util.CocktailParser;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by ryanc on 11/4/2017.
 */

public class CocktailRandomAsyncTask extends AsyncTask<String, String, Cocktail> {
    private final String apiKey = "1";
    private final String baseApiUrl = "https://www.thecocktaildb.com/api/json/v1/"
            + apiKey
            + "/random.php";
    private CocktailRandomAsyncTask.OnCocktailFetchResponse callbackListener;

    public void setCallbackListener(CocktailRandomAsyncTask.OnCocktailFetchResponse callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    protected Cocktail doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(baseApiUrl).build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            if (response != null) {
                return CocktailParser.cocktailListFromJson(response.body()
                        .string())
                        .getCocktails()
                        .get(0);
            }
        } catch (IOException e) {
            //do something
        }
        return null;
    }

    @Override
    protected void onPostExecute(Cocktail cocktail) {
        super.onPostExecute(cocktail);
        callbackListener.onCallback(cocktail);
    }

    public interface OnCocktailFetchResponse {
        void onCallback(Cocktail cocktail);
    }
}
