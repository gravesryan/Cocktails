package com.squad.cocktails.network;

import android.os.AsyncTask;

import com.squad.cocktails.model.CocktailList;
import com.squad.cocktails.util.CocktailParser;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailSearchAsyncTask extends AsyncTask<String, String, CocktailList> {
    private final String apiKey = "1";
    private final String baseApiUrl = "https://www.thecocktaildb.com/api/json/v1/"
            + apiKey
            + "/lookup.php?i=13060";

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

        Request request = new Request.Builder().url(url).build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            if (response != null) {
                return CocktailParser.cocktailListFromJson(response.body().string());
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

}
