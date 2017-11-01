package com.squad.cocktails.ui.search.viewcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.model.CocktailList;
import com.squad.cocktails.network.CocktailLookupAsyncTask;
import com.squad.cocktails.network.CocktailSearchAsyncTask;
import com.squad.cocktails.ui.detail.viewcontroller.CocktailDetailActivity;
import com.squad.cocktails.ui.search.adapter.CocktailSearchAdapter;

import org.parceler.Parcels;

/**
 * Created by ryanc on 11/1/2017.
 */

public class ResultActivity extends AppCompatActivity {
    public static final String RESULT_EXTRA_KEY = "resultExtraKey";
    private CocktailSearchAsyncTask searchTask;
    private CocktailLookupAsyncTask lookupTask;
    private RecyclerView cocktailResultList;
    private LinearLayoutManager linearLayoutManager;
    private CocktailSearchAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        cocktailResultList = (RecyclerView)findViewById(R.id.cocktail_result_list);
        cocktailResultList.setLayoutManager(linearLayoutManager);

        searchTask = new CocktailSearchAsyncTask();
        searchTask.setCallbackListener(new CocktailSearchAsyncTask.OnCocktailFetchResponse() {
            @Override
            public void onCallback(CocktailList cocktailList) {
                adapter = new CocktailSearchAdapter(cocktailList.getCocktails());
                adapter.setCocktailItemClickListener(new CocktailSearchAdapter.CocktailItemClickListener() {
                    @Override
                    public void onCocktailItemClicked(Cocktail selectedItem) {
                        lookupTask = new CocktailLookupAsyncTask();
                        lookupTask.setCallbackListener(new CocktailLookupAsyncTask.OnCocktailFetchResponse() {
                            @Override
                            public void onCallback(Cocktail cocktail) {
                                Intent navIntent = new Intent(ResultActivity.this, CocktailDetailActivity.class);
                                navIntent.putExtra(CocktailDetailActivity.COCKTAIL_EXTRA_KEY, Parcels.wrap(cocktail));
                                startActivity(navIntent);
                            }
                        });
                        lookupTask.execute(selectedItem.getCocktailId());
                    }
                });
                cocktailResultList.setAdapter(adapter);
            }
        });
        searchTask.execute(getIntent().getStringExtra(RESULT_EXTRA_KEY));
    }
}
