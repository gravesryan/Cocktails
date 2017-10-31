package com.squad.cocktails.ui.detail.viewcontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailDetailActivity extends AppCompatActivity {
    public static final String COCKTAIL_EXTRA_KEY = "cocktailExtraKey";
    private TextView cocktailDetailName, cocktailDescription;
    private ImageView cocktailDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_detail);

        cocktailDetailName = (TextView)findViewById(R.id.cocktail_detail_name);
        cocktailDescription = (TextView)findViewById(R.id.cocktail_description);
        cocktailDetailImage = (ImageView)findViewById(R.id.cocktail_detail_image);

        Cocktail cocktail = Parcels.unwrap(getIntent().getParcelableExtra(COCKTAIL_EXTRA_KEY));
        cocktailDetailName.setText(cocktail.getName());

        String ingredients = "Ingredients: \n";
        if (cocktail.getIngredient1().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient1() + " (" + cocktail.getMeasure1() + ")\n";
        if (cocktail.getIngredient2().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient2() + " (" + cocktail.getMeasure2() + ")\n";
        if (cocktail.getIngredient3().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient3() + " (" + cocktail.getMeasure3() + ")\n";
        if (cocktail.getIngredient4().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient4() + " (" + cocktail.getMeasure4() + ")\n";
        if (cocktail.getIngredient5().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient5() + " (" + cocktail.getMeasure5() + ")\n";
        if (cocktail.getIngredient6().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient6() + " (" + cocktail.getMeasure6() + ")\n";
        if (cocktail.getIngredient7().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient7() + " (" + cocktail.getMeasure7() + ")\n";
        if (cocktail.getIngredient8().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient8() + " (" + cocktail.getMeasure8() + ")\n";
        if (cocktail.getIngredient9().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient9() + " (" + cocktail.getMeasure9() + ")\n";
        if (cocktail.getIngredient10().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient10() + " (" + cocktail.getMeasure10() + ")\n";
        if (cocktail.getIngredient11().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient11() + " (" + cocktail.getMeasure11() + ")\n";
        if (cocktail.getIngredient12().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient12() + " (" + cocktail.getMeasure12() + ")\n";
        if (cocktail.getIngredient13().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient13() + " (" + cocktail.getMeasure13() + ")\n";
        if (cocktail.getIngredient14().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient14() + " (" + cocktail.getMeasure14() + ")\n";
        if (cocktail.getIngredient15().compareTo("") != 0)
            ingredients += "     " + cocktail.getIngredient15() + " (" + cocktail.getMeasure15() + ")\n";

        cocktailDescription.setText(ingredients);

        Glide.with(this)
                .load(cocktail.getThumbnail())
                .into(cocktailDetailImage);
    }
}
