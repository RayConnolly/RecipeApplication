package com.example.rconnolly.recipeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rconnolly on 2/16/2016.
 */
public class CustomListAdapter extends ArrayAdapter<String> {

    private static View customView;
    private static TextView recipeTitleText;
    private static TextView recipeDescText;

    private final String[] recipeName;
    private final String[] recipeDesc;
    private final Integer[] recipeImageId;

    private static ImageView recipeImage;

    CustomListAdapter(Context context, Integer[] recipeImageId, String[] recipeName, String[] recipeDesc) {
        super(context, R.layout.list_row, recipeName);

        this.recipeName = recipeName;
        this.recipeDesc = recipeDesc;
        this.recipeImageId = recipeImageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        customView = inflater.inflate(R.layout.list_row, parent, false);

        initViews();

        recipeImage.setImageResource(recipeImageId[position]);
        recipeTitleText.setText(recipeName[position]);
        recipeDescText.setText("This is the " + recipeDesc[position] + " description");

        return customView;
    }

    private void initViews() {

        recipeImage = (ImageView) customView.findViewById(R.id.recipe_image);
        recipeTitleText = (TextView) customView.findViewById(R.id.recipe_title_text);
        recipeDescText = (TextView) customView.findViewById(R.id.recipe_desc_text);
    }

}
