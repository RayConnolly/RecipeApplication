package com.example.rconnolly.recipeapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.R;

/**
 * Created by rconnolly on 2/25/2016.
 */
public class RecipeSourceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recipe_source_fragment, container, false);

        TextView textView = (TextView) view.findViewById(R.id.recipeSourceFragment);

        textView.setText("This is FRAGMENT 2");

        return view;
    }
}
