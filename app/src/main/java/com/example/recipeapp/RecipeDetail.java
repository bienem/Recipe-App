package com.example.recipeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Adapter.IngredientsAdapter;
import com.example.recipeapp.Adapter.InstructionAdaptors;
import com.example.recipeapp.Adapter.SimilarRecipeAdaptor;
import com.example.recipeapp.Listeners.InstructionListener;
import com.example.recipeapp.Listeners.RecipeClickListener;
import com.example.recipeapp.Listeners.RecipeDetailsListener;
import com.example.recipeapp.Listeners.SimilarRecipeListener;
import com.example.recipeapp.Models.InstructionResponse;
import com.example.recipeapp.Models.RecipeDetailsResponse;
import com.example.recipeapp.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetail extends AppCompatActivity {

    int id;

    TextView meal_name,meal_source, meal_summary;
    ImageView meal_image;
    RecyclerView meal_ingredients, meal_instruction, similar_meal;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdaptor similarRecipeAdaptor;
    InstructionAdaptors instructionAdaptors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        manager.getSimilarRecipe(similarRecipeListener, id);
        manager.getInstruction(instructionListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading details...");
        dialog.show();
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            meal_name.setText(response.title);
            meal_source.setText(response.sourceName);
            meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(meal_image);

            meal_ingredients.setHasFixedSize(true);
            meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetail.this,LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetail.this, response.extendedIngredients);
            meal_ingredients.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetail.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private  final SimilarRecipeListener similarRecipeListener =new SimilarRecipeListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
            similar_meal.setHasFixedSize(true);
            similar_meal.setLayoutManager(new LinearLayoutManager(RecipeDetail.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdaptor = new SimilarRecipeAdaptor(RecipeDetail.this, response, recipeClickListener);
            similar_meal.setAdapter(similarRecipeAdaptor);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetail.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private  final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetail.this, RecipeDetail.class)
            .putExtra("id", id));
        }
    };

    private final InstructionListener instructionListener= new InstructionListener() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
            meal_instruction.setHasFixedSize(true);
            meal_instruction.setLayoutManager(new LinearLayoutManager(RecipeDetail.this,LinearLayoutManager.VERTICAL, false));
            instructionAdaptors = new InstructionAdaptors(RecipeDetail.this,response);
            meal_instruction.setAdapter(instructionAdaptors);
        }

        @Override
        public void didError(String message) {

        }
    };

    private void findViews() {
        meal_name = findViewById(R.id.meal_name);
        meal_source = findViewById(R.id.meal_source);
        meal_summary = findViewById(R.id.meal_summary);
        meal_image = findViewById(R.id.meal_image);
        meal_ingredients = findViewById(R.id.meal_ingredients);
        similar_meal = findViewById(R.id.similar_meal);
        meal_instruction = findViewById(R.id.meal_instruction);

    }
}