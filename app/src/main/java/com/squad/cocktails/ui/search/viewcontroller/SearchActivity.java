package com.squad.cocktails.ui.search.viewcontroller;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.model.database.DatabaseHandler;
import com.squad.cocktails.model.database.Favorite;
import com.squad.cocktails.network.CocktailRandomAsyncTask;
import com.squad.cocktails.ui.detail.viewcontroller.CocktailDetailActivity;

import org.parceler.Parcels;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.squad.cocktails.ui.search.viewcontroller.searchType.INGR;
import static com.squad.cocktails.ui.search.viewcontroller.searchType.NAME;

/**
 * Created by ryanc on 10/31/2017.
 */

enum searchType {
    INGR, NAME
}

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private EditText searchEditText;
    private Button searchButton, moreButton, lessButton, newSearchButton;
    private CocktailRandomAsyncTask randomTask;
    private LinearLayout layout, headerBarBackground;
    private EditText newSearch;
    private static final int NEW_SEARCH_ID = 1;
    private int searchId = NEW_SEARCH_ID;
    private searchType currentSearchType = INGR;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //SQLITE TEST
        DatabaseHandler db = new DatabaseHandler(this);
        Cocktail favorite = new Cocktail();
        favorite.setCocktailId("12345");
        favorite.setName("Test2");
        favorite.setThumbnail("google.com");
        //db.addFavorite(favorite);
        db.deleteFavorite(favorite);
        favorite = db.getFavorite("12345");



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        searchEditText = (EditText)findViewById(R.id.search_edit_text);
        searchButton = (Button)findViewById(R.id.my_search_button);
        moreButton = (Button)findViewById(R.id.more_terms_button);
        newSearchButton = (Button)findViewById(R.id.new_search_button);
        lessButton = (Button)findViewById(R.id.less_terms_button);
        lessButton.setEnabled(false);

        layout = (LinearLayout)findViewById(R.id.edit_text_layout);

        headerBarBackground = (LinearLayout)findViewById(R.id.nav_header_background);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input;
                ArrayList<String> searchStrings = new ArrayList<String>();
                searchStrings.add(searchEditText.getText().toString());
                for (int i = NEW_SEARCH_ID; i < searchId; i++) {
                    input = (EditText) layout.findViewWithTag(i);
                    searchStrings.add(input.getText().toString());
                }
                Intent resultIntent = new Intent(SearchActivity.this, ResultActivity.class);
                resultIntent.putExtra(ResultActivity.RESULT_EXTRA_KEY,
                        searchStrings);
                resultIntent.putExtra(ResultActivity.SEARCH_TYPE_KEY,
                        currentSearchType);
                startActivity(resultIntent);
            }
        });

        lessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!moreButton.isEnabled())
                    moreButton.setEnabled(true);
                layout.removeView(layout.findViewWithTag(--searchId));
                if (searchId == NEW_SEARCH_ID) {
                    lessButton.setAlpha(0);
                    lessButton.setEnabled(false);
                }
            }
        });

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lessButton.getAlpha() == 0)
                    lessButton.setAlpha(1);
                if (!lessButton.isEnabled())
                    lessButton.setEnabled(true);
                newSearch = new EditText(SearchActivity.this);
                newSearch.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
                newSearch.setHint("Additional ingredient");
                newSearch.setTag(searchId++);
                layout.addView(newSearch);
                if (searchId > 4) {
                    moreButton.setEnabled(false);
                }
            }
        });

        newSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = searchId-1; i >= NEW_SEARCH_ID; i--) {
                    layout.removeView(layout.findViewWithTag(i));
                }
                searchEditText.setText("");
                searchId = NEW_SEARCH_ID;
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
        //getMenuInflater().inflate(R.menu.options, menu);
//        randomTask = new CocktailRandomAsyncTask();
//        randomTask.setCallbackListener(new CocktailRandomAsyncTask.OnCocktailFetchResponse() {
//            @Override
//            public void onCallback(Cocktail cocktail) {
//                Bitmap randomDrinkImage = getBitmapFromURL(cocktail.getThumbnail());
//                Drawable dr = new BitmapDrawable(randomDrinkImage);
//                headerBarBackground.setBackground(dr);
//            }
//        });
//        randomTask.execute();
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_ingredients) {
            currentSearchType = INGR;
            moreButton.setAlpha(1);
            moreButton.setEnabled(true);
            for (int i = searchId-1; i >= NEW_SEARCH_ID; i--) {
                layout.removeView(layout.findViewWithTag(i));
            }
            searchEditText.setHint("Enter ingredients");
            searchId = NEW_SEARCH_ID;
        } else if (id == R.id.nav_name) {
            NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
            navigationView.getMenu().findItem(R.id.nav_ingredients).setChecked(false);
            currentSearchType = NAME;
            moreButton.setAlpha(0);
            moreButton.setEnabled(false);
            for (int i = searchId-1; i >= NEW_SEARCH_ID; i--) {
                layout.removeView(layout.findViewWithTag(i));
            }
            searchEditText.setHint("Enter cocktail name");
            searchId = NEW_SEARCH_ID;
        } else if (id == R.id.nav_favorites) {
            Intent favIntent = new Intent(SearchActivity.this, FavoritesActivity.class);
            startActivity(favIntent);
        } else if (id == R.id.nav_manage) {
            Intent aboutIntent = new Intent(SearchActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
        } else if (id == R.id.nav_random) {
            CocktailRandomAsyncTask randomTask = new CocktailRandomAsyncTask();
            randomTask.setCallbackListener(new CocktailRandomAsyncTask.OnCocktailFetchResponse() {
                @Override
                public void onCallback(Cocktail cocktail) {
                    Intent navIntent = new Intent(SearchActivity.this, CocktailDetailActivity.class);
                    navIntent.putExtra(CocktailDetailActivity.COCKTAIL_EXTRA_KEY, Parcels.wrap(cocktail));
                    startActivity(navIntent);
                }
            });
            randomTask.execute();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
