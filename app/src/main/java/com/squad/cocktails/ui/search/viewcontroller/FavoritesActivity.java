package com.squad.cocktails.ui.search.viewcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.model.database.DatabaseHandler;
import com.squad.cocktails.network.CocktailLookupAsyncTask;
import com.squad.cocktails.ui.detail.viewcontroller.CocktailDetailActivity;
import com.squad.cocktails.ui.search.adapter.CocktailSearchAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryanc on 2/28/2018.
 */

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView favoritesList;
    private LinearLayoutManager linearLayoutManager;
    private CocktailSearchAdapter adapter;
    private CocktailLookupAsyncTask lookupTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesList = (RecyclerView)findViewById(R.id.cocktail_favorites_list);
        linearLayoutManager = new LinearLayoutManager(this);
        favoritesList.setLayoutManager(linearLayoutManager);

        DatabaseHandler db = new DatabaseHandler(this);
        List<Cocktail> favorites = db.getAllFavorites();
        adapter = new CocktailSearchAdapter(new ArrayList<Cocktail>(favorites));
        adapter.setCocktailItemClickListener(new CocktailSearchAdapter.CocktailItemClickListener() {
            @Override
            public void onCocktailItemClicked(Cocktail selectedItem) {
                lookupTask = new CocktailLookupAsyncTask();
                lookupTask.setCallbackListener(new CocktailLookupAsyncTask.OnCocktailFetchResponse() {
                    @Override
                    public void onCallback(Cocktail cocktail) {
                        Intent navIntent = new Intent(FavoritesActivity.this, CocktailDetailActivity.class);
                        navIntent.putExtra(CocktailDetailActivity.COCKTAIL_EXTRA_KEY, Parcels.wrap(cocktail));
                        startActivity(navIntent);
                    }
                });
                lookupTask.execute(selectedItem.getCocktailId());
            }
        });
        favoritesList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHandler db = new DatabaseHandler(this);
        List<Cocktail> favorites = db.getAllFavorites();
        adapter = new CocktailSearchAdapter(new ArrayList<Cocktail>(favorites));
        adapter.setCocktailItemClickListener(new CocktailSearchAdapter.CocktailItemClickListener() {
            @Override
            public void onCocktailItemClicked(Cocktail selectedItem) {
                lookupTask = new CocktailLookupAsyncTask();
                lookupTask.setCallbackListener(new CocktailLookupAsyncTask.OnCocktailFetchResponse() {
                    @Override
                    public void onCallback(Cocktail cocktail) {
                        Intent navIntent = new Intent(FavoritesActivity.this, CocktailDetailActivity.class);
                        navIntent.putExtra(CocktailDetailActivity.COCKTAIL_EXTRA_KEY, Parcels.wrap(cocktail));
                        startActivity(navIntent);
                    }
                });
                lookupTask.execute(selectedItem.getCocktailId());
            }
        });
        favoritesList.setAdapter(adapter);
    }
}
