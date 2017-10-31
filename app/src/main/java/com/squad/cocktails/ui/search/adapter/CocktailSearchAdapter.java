package com.squad.cocktails.ui.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squad.cocktails.R;
import com.squad.cocktails.model.Cocktail;
import com.squad.cocktails.ui.search.viewholder.CocktailItemViewHolder;

import java.util.ArrayList;

/**
 * Created by ryanc on 10/31/2017.
 */

public class CocktailSearchAdapter extends RecyclerView.Adapter<CocktailItemViewHolder> {
    private ArrayList<Cocktail> bindableCollection;
    private CocktailItemClickListener cocktailItemClickListener;

    public void setCocktailItemClickListener(CocktailItemClickListener cocktailItemClickListener) {
        this.cocktailItemClickListener = cocktailItemClickListener;
    }

    public CocktailSearchAdapter(ArrayList<Cocktail> collection) {
        this.bindableCollection = collection;
    }

    @Override
    public CocktailItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cocktail_search_item, parent, false);

        return new CocktailItemViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(CocktailItemViewHolder holder, int position) {
        Cocktail item = bindableCollection.get(position);
        holder.setListener(new CocktailItemViewHolder.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                if (cocktailItemClickListener != null) {
                    cocktailItemClickListener.onCocktailItemClicked(bindableCollection.get(position));
                }
            }
        });
        holder.bindView(item);
    }

    @Override
    public int getItemCount() { return this.bindableCollection.size(); }

    public interface CocktailItemClickListener {
        void onCocktailItemClicked(Cocktail selectedItem);
    }

}
