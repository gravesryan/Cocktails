package com.squad.cocktails.ui.detail.viewcontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.model.database.DatabaseHandler;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailDetailActivity extends AppCompatActivity {
    public static final String COCKTAIL_EXTRA_KEY = "cocktailExtraKey";
    private TextView cocktailDetailName, cocktailInstructions, cocktailGlass,
            cocktailIngredients, cocktailAlcoholic, cocktailCategory,
            cocktailIBA, cocktailDateModified;
    private ImageView cocktailDetailImage;
    private Button favoriteButton;
    private boolean favorited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_detail);

        cocktailDetailName = (TextView)findViewById(R.id.cocktail_detail_name);
        cocktailInstructions = (TextView)findViewById(R.id.cocktail_instructions);
        cocktailDetailImage = (ImageView)findViewById(R.id.cocktail_detail_image);
        cocktailGlass = (TextView)findViewById(R.id.cocktail_glass);
        cocktailIngredients = (TextView)findViewById(R.id.cocktail_ingredients);
        cocktailAlcoholic = (TextView)findViewById(R.id.cocktail_alcoholic);
        cocktailCategory = (TextView)findViewById(R.id.cocktail_category);
        cocktailIBA = (TextView)findViewById(R.id.cocktail_iba);
        cocktailDateModified = (TextView)findViewById(R.id.cocktail_date_modified);
        favoriteButton = (Button)findViewById(R.id.cocktail_detail_favorite_button);

        final Cocktail cocktail = Parcels.unwrap(getIntent().getParcelableExtra(COCKTAIL_EXTRA_KEY));
        cocktailDetailName.setText(cocktail.getName());
        cocktailInstructions.setText(cocktail.getInstructions());
        cocktailGlass.setText(cocktail.getGlass());
        cocktailAlcoholic.setText(cocktail.getAlcoholic());
        cocktailCategory.setText(cocktail.getCategory());
        cocktailIBA.setText(cocktail.getIba());
        cocktailDateModified.setText("\nDate Modified: " + cocktail.getDateModified());

        String iba = cocktail.getIba();

        String ingredients = "Ingredients: \n";
        if (cocktail.getIngredient1().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient1().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure1()))
                ingredients += " ( " + cocktail.getMeasure1().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient2().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient2().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure2()))
                ingredients += " ( " + cocktail.getMeasure2().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient3().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient3().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure3()))
                ingredients += " ( " + cocktail.getMeasure3().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient4().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient4().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure4()))
                ingredients += " ( " + cocktail.getMeasure4().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient5().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient5().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure5()))
                ingredients += " ( " + cocktail.getMeasure5().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient6().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient6().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure6()))
                ingredients += " ( " + cocktail.getMeasure6().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient7().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient7().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure7()))
                ingredients += " ( " + cocktail.getMeasure7().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient8().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient8().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure8()))
                ingredients += " ( " + cocktail.getMeasure8().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient9().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient9().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure9()))
                ingredients += " ( " + cocktail.getMeasure9().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient10().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient10().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure10()))
                ingredients += " ( " + cocktail.getMeasure10().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient11().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient11().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure11()))
                ingredients += " ( " + cocktail.getMeasure11().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient12().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient12().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure12()))
                ingredients += " ( " + cocktail.getMeasure12().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient13().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient13().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure13()))
                ingredients += " ( " + cocktail.getMeasure13().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient14().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient14().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure14()))
                ingredients += " ( " + cocktail.getMeasure14().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }
        if (cocktail.getIngredient15().compareTo("") != 0) {
            ingredients += "     " + cocktail.getIngredient15().replace("\n", "");
            if (!isWhitespace(cocktail.getMeasure15()))
                ingredients += " ( " + cocktail.getMeasure15().replace("\n", "") + ")\n";
            else
                ingredients += "\n";
        }

        cocktailIngredients.setText(ingredients);

        String id = cocktail.getCocktailId();
        DatabaseHandler db = new DatabaseHandler(this);
        Cocktail favoriteCocktail = db.getFavorite(id);
        if (favoriteCocktail != null) {
            favorited = true;
            favoriteButton.setBackground(getResources().getDrawable(R.drawable.ic_star_filled));
        }

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(CocktailDetailActivity.this);
                if (favorited) {
                    favorited = false;
                    db.deleteFavorite(cocktail);
                    favoriteButton.setBackground(getResources().getDrawable(R.drawable.ic_star_unfilled));
                } else {
                    favorited = true;
                    db.addFavorite(cocktail);
                    favoriteButton.setBackground(getResources().getDrawable(R.drawable.ic_star_filled));
                }
            }
        });

        Glide.with(this)
                .load(cocktail.getThumbnail())
                .into(cocktailDetailImage);
    }

    public boolean isWhitespace(String string) {
        if (string.compareTo("") == 0) {
            return true;
        }
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i)))
                return false;
        }
        return true;
    }
}
