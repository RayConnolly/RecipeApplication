package com.example.rconnolly.recipeapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.R;

/**
 * Created by rconnolly on 2/16/2016.
 */
public class IngredientsFragment extends Fragment {

    private TextView tvIngredients;
    private TextView tvDietLabels;
    private TextView tvHealthLabels;

    public IngredientsFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        tvIngredients = (TextView) container.findViewById(R.id.tvRecipeIngredients);
        tvDietLabels = (TextView) container.findViewById(R.id.tvRecipeDietLabels);
        tvHealthLabels = (TextView) container.findViewById(R.id.tvRecipeHealthLabels);

        String recipeIngredients = this.getArguments().getString("ingredients");
        String recipeDietLabels = this.getArguments().getString("dietLbls");
        String recipeHealthLabels = this.getArguments().getString("healthLbls");

//        final String recipeIngredients = tvIngredientLines.getText().toString();
//        final String recipeDietLabels = tvDietLabels.getText().toString();
//        final String recipeHealthLabels = tvHealthLabels.getText().toString();

        tvIngredients.setText(recipeIngredients);
        tvDietLabels.setText(recipeDietLabels);
        tvHealthLabels.setText(recipeHealthLabels);


//        tvIngredientLines.setText(Arrays.toString(recipeModelList.get(position).getIngredientLines()).replaceAll("\\[|\\]", ""));
//        tvDietLabels.setText(Arrays.toString(recipeModelList.get(position).getDietLabels()).replaceAll("\\[|\\]", ""));
//        tvHealthLabels.setText(Arrays.toString(recipeModelList.get(position).getHealthLabels()).replaceAll("\\[|\\]", ""));

        return inflater.inflate(R.layout.ingredients_fragment, container, false);


    }
}
