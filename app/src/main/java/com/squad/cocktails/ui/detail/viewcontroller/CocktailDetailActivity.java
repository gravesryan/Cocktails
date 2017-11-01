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
    private TextView cocktailDetailName, cocktailInstructions, cocktailGlass,
            cocktailIngredients, cocktailAlcoholic, cocktailCategory,
            cocktailIBA, cocktailDateModified;
    private ImageView cocktailDetailImage;

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


        Cocktail cocktail = Parcels.unwrap(getIntent().getParcelableExtra(COCKTAIL_EXTRA_KEY));
        cocktailDetailName.setText(cocktail.getName());
        cocktailInstructions.setText(cocktail.getInstructions());
        cocktailGlass.setText(cocktail.getGlass());
        cocktailAlcoholic.setText(cocktail.getAlcoholic());
        cocktailCategory.setText(cocktail.getCategory());
        cocktailIBA.setText(cocktail.getIba());
        cocktailDateModified.setText("\nDate Modified: " + cocktail.getDateModified());


        String ingredients = "Ingredients: \n";
//        if (cocktail.getIngredient1().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient1();
//            if (cocktail.getMeasure1().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure1() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient2().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient2();
//            if (cocktail.getMeasure2().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure2() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient3().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient3();
//            if (cocktail.getMeasure3().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure3() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient4().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient4();
//            if (cocktail.getMeasure4().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure4() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient5().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient5();
//            if (cocktail.getMeasure5().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure5() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient6().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient6();
//            if (cocktail.getMeasure6().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure6() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient7().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient7();
//            if (cocktail.getMeasure7().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure7() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient8().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient8();
//            if (cocktail.getMeasure8().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure8() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient9().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient9();
//            if (cocktail.getMeasure9().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure9() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient10().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient10();
//            if (cocktail.getMeasure10().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure10() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient11().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient11();
//            if (cocktail.getMeasure11().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure11() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient12().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient12();
//            if (cocktail.getMeasure12().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure12() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient13().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient13();
//            if (cocktail.getMeasure13().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure13() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient14().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient14();
//            if (cocktail.getMeasure14().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure14() + ")\n";
//            else
//                ingredients += "\n";
//        }
//        if (cocktail.getIngredient15().compareTo("") != 0) {
//            ingredients += "     " + cocktail.getIngredient15();
//            if (cocktail.getMeasure15().compareTo("") != 0)
//                ingredients += " (" + cocktail.getMeasure15() + ")\n";
//            else
//                ingredients += "\n";
//        }

        cocktailIngredients.setText(ingredients);

        Glide.with(this)
                .load(cocktail.getThumbnail())
                .into(cocktailDetailImage);
    }
}
