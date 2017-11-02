package com.squad.cocktails.ui.search.viewcontroller;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.model.CocktailList;
import com.squad.cocktails.network.CocktailLookupAsyncTask;
import com.squad.cocktails.network.CocktailSearchAsyncTask;
import com.squad.cocktails.ui.detail.viewcontroller.CocktailDetailActivity;
import com.squad.cocktails.ui.search.adapter.CocktailSearchAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class SearchActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton, moreButton;
    private CocktailSearchAsyncTask task;
    private CocktailLookupAsyncTask lookupTask;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayout layout;
    private CocktailSearchAdapter adapter;
    private EditText newSearch;
    private int searchId = 0;
    private static final int NEW_SEARCH_ID = 123;
    //private RecyclerView cocktailResultList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = (EditText)findViewById(R.id.search_edit_text);
        searchButton = (Button)findViewById(R.id.my_search_button);
        moreButton = (Button)findViewById(R.id.more_terms_button);
        layout = (LinearLayout)findViewById(R.id.main_layout);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input;
                ArrayList<String> searchStrings = new ArrayList<String>();
                searchStrings.add(searchEditText.getText().toString());
                for (int i = 0; i < searchId; i++) {
                    input = (EditText)layout.findViewWithTag(i);
                    searchStrings.add(input.getText().toString());
                }
                Intent resultIntent = new Intent(SearchActivity.this, ResultActivity.class);
                resultIntent.putExtra(ResultActivity.RESULT_EXTRA_KEY,
                                        searchStrings);
                startActivity(resultIntent);
            }
        });

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeView(moreButton);
                layout.removeView(searchButton);
                newSearch = new EditText(SearchActivity.this);
                newSearch.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
                newSearch.setHint("More Search Terms");
                newSearch.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
                newSearch.setTag(searchId++);
                layout.addView(newSearch);
                layout.addView(moreButton);
                layout.addView(searchButton);

                //newSearch.setId(SearchActivity.NEW_SEARCH_ID);
            }
        });
    }
}
