package com.squad.cocktails.ui.search.viewcontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.squad.cocktails.R;

/**
 * Created by ryanc on 2/28/2018.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView credit1 = (TextView)findViewById(R.id.credit1_text);
        credit1.setText(Html.fromHtml(getString(R.string.freepik_credit)));
        TextView credit2 = (TextView)findViewById(R.id.credit2_text);
        credit2.setText(Html.fromHtml(getString(R.string.Gregor_Cresnar_credit)));
    }
}
