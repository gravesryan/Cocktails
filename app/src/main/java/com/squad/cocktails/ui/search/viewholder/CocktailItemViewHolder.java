package com.squad.cocktails.ui.search.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView cocktailName, sourceDisplayName;
    private ImageView cocktailThumbnail;

    public CocktailItemViewHolder(View itemView) {
        super(itemView);

        cocktailName = (TextView)itemView.findViewById(R.id.cocktail_name);
        cocktailThumbnail = (ImageView)itemView.findViewById(R.id.cocktail_thumbnail);
        sourceDisplayName = (TextView)itemView.findViewById(R.id.source_display_name);

        cocktailName.setOnClickListener(this);
        cocktailThumbnail.setOnClickListener(this);
        sourceDisplayName.setOnClickListener(this);
    }

    public void setListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    private OnItemClickedListener listener;

    public void bindView(Cocktail item) {
        cocktailName.setText(item.getName());
        Glide.with(this.itemView)
                .load(item.getThumbnail())
                .into(cocktailThumbnail);

        sourceDisplayName.setText(item.getInstructions());
    }

    @Override
    public void onClick(View view) {
        listener.onItemClicked(CocktailItemViewHolder.this.getLayoutPosition());
    }

    public interface OnItemClickedListener {
        void onItemClicked(int position);
    }

}
