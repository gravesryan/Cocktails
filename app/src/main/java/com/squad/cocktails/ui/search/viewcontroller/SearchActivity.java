package com.squad.cocktails.ui.search.viewcontroller;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.squad.cocktails.R;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class SearchActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton, moreButton, lessButton, newSearchButton;
    private LinearLayout layout;
    private EditText newSearch;
    private static final int NEW_SEARCH_ID = 1;
    private int searchId = NEW_SEARCH_ID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        Intent testIntent = new Intent(SearchActivity.this, OptionsActivity.class);
//        startActivity(testIntent);

        searchEditText = (EditText)findViewById(R.id.search_edit_text);
        searchButton = (Button)findViewById(R.id.my_search_button);
        moreButton = (Button)findViewById(R.id.more_terms_button);
        newSearchButton = (Button)findViewById(R.id.new_search_button);
        lessButton = (Button)findViewById(R.id.less_terms_button);
        lessButton.setEnabled(false);

        layout = (LinearLayout)findViewById(R.id.edit_text_layout);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input;
                ArrayList<String> searchStrings = new ArrayList<String>();
                searchStrings.add(searchEditText.getText().toString());
                for (int i = NEW_SEARCH_ID; i < searchId; i++) {
                    input = (EditText)layout.findViewWithTag(i);
                    searchStrings.add(input.getText().toString());
                }
                Intent resultIntent = new Intent(SearchActivity.this, ResultActivity.class);
                resultIntent.putExtra(ResultActivity.RESULT_EXTRA_KEY,
                                        searchStrings);
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
                newSearch.setHint("More Search Terms");
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
}
