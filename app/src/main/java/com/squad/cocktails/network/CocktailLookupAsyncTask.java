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
 * Created by ryanc on 10/31/2017.
 */

public class CocktailLookupAsyncTask extends AsyncTask<String, String, Cocktail> {
    private final String apiKey = "1";
    private final String baseApiUrl = "https://www.thecocktaildb.com/api/json/v1/"
            + apiKey
            + "/lookup.php?i=";
    private CocktailLookupAsyncTask.OnCocktailFetchResponse callbackListener;

    public void setCallbackListener(CocktailLookupAsyncTask.OnCocktailFetchResponse callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    protected Cocktail doInBackground(String... strings) {
        String searchParams = strings[0];
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseApiUrl).newBuilder();

        String url = urlBuilder.build().toString();

        url += strings[0];

        Request request = new Request.Builder().url(url).build();
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
