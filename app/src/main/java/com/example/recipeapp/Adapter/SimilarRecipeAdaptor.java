package com.example.recipeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Listeners.RecipeClickListener;
import com.example.recipeapp.Models.SimilarRecipeResponse;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdaptor extends RecyclerView.Adapter<SimilarRecipeViewHolder>{

    Context context;
    List<SimilarRecipeResponse> list;
    RecipeClickListener recipeClickListener;

    public SimilarRecipeAdaptor(Context context, List<SimilarRecipeResponse> list, RecipeClickListener recipeClickListener) {
        this.context = context;
        this.list = list;
        this.recipeClickListener = recipeClickListener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.similar_name.setText(list.get(position).title);
        holder.similar_name.setSelected(true);
        holder.similar_serving.setText(list.get(position).servings+" persons");
        Picasso.get().load("https://spoonacular.com/recipeImages/" + list.get(position).id+"-480x360."+list.get(position).imageType ).into(holder.imageView_simlar);

        holder.similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeClickListener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class SimilarRecipeViewHolder extends RecyclerView.ViewHolder{

    CardView similar_recipe_holder;
    TextView similar_name, similar_serving;
    ImageView imageView_simlar;

    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        similar_recipe_holder = itemView.findViewById(R.id.similar_recipe_holder);
        similar_serving = itemView.findViewById(R.id.similar_serving);
        similar_name = itemView.findViewById(R.id.similar_name);
        imageView_simlar = itemView.findViewById(R.id.imageView_simlar);
    }
}
