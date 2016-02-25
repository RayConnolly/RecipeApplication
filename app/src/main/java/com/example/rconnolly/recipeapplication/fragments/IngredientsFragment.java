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

    private String recipeIngredients;
    private String recipeDietLabels;
    private String recipeHealthLabels;

    public IngredientsFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        recipeIngredients = this.getArguments().getString("ingredients");
        recipeDietLabels = this.getArguments().getString("dietLbls");
        recipeHealthLabels = this.getArguments().getString("healthLbls");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ingredients_fragment, container, false);

        tvIngredients = (TextView) view.findViewById(R.id.txtRecipeIngredients);
        tvDietLabels = (TextView) view.findViewById(R.id.txtRecipeDietLabels);
        tvHealthLabels = (TextView) view.findViewById(R.id.txtRecipeHealthLabels);

        tvIngredients.setText(recipeIngredients);
        tvDietLabels.setText(recipeDietLabels);
        tvHealthLabels.setText(recipeHealthLabels);

        return view;
    }
}
