package com.squad.cocktails.ui.search.viewcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.model.CocktailList;
import com.squad.cocktails.network.CocktailSearchAsyncTask;
import com.squad.cocktails.ui.detail.viewcontroller.CocktailDetailActivity;
import com.squad.cocktails.ui.search.adapter.CocktailSearchAdapter;

import org.parceler.Parcels;

/**
 * Created by ryanc on 10/31/2017.
 */

public class SearchActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private CocktailSearchAsyncTask task;
    private LinearLayoutManager linearLayoutManager;
    private CocktailSearchAdapter adapter;
    private RecyclerView cocktailResultList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = (EditText)findViewById(R.id.search_edit_text);
        searchButton = (Button)findViewById(R.id.my_search_button);
        cocktailResultList = (RecyclerView)findViewById(R.id.cocktail_result_list);

        linearLayoutManager = new LinearLayoutManager(this);

        cocktailResultList.setLayoutManager(linearLayoutManager);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new CocktailSearchAsyncTask();
                task.setCallbackListener(new CocktailSearchAsyncTask.OnCocktailFetchResponse() {
                    @Override
                    public void onCallback(CocktailList cocktailList) {
                        adapter = new CocktailSearchAdapter(cocktailList.getCocktails());
                        adapter.setCocktailItemClickListener(new CocktailSearchAdapter.CocktailItemClickListener() {
                            @Override
                            public void onCocktailItemClicked(Cocktail selectedItem) {

                                Intent navIntent = new Intent(SearchActivity.this, CocktailDetailActivity.class);
                                navIntent.putExtra(CocktailDetailActivity.COCKTAIL_EXTRA_KEY, Parcels.wrap(selectedItem));
                                startActivity(navIntent);
                            }
                        });
                        cocktailResultList.setAdapter(adapter);
                    }
                });
                String searchTerms = searchEditText.getText().toString();
                task.execute(searchTerms);
            }
        });
    }
}
