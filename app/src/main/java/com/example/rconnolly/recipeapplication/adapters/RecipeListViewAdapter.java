package com.example.rconnolly.recipeapplication.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.R;
import com.example.rconnolly.recipeapplication.models.RecipeModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rconnolly on 2/19/2016.
 */
public class RecipeListViewAdapter extends ArrayAdapter {

    private final String LOG_TAG = RecipeListViewAdapter.class.getSimpleName();

    private Context context;
    private List<RecipeModel> recipeModelList;
    private int resource;
    private LayoutInflater inflater;

    public RecipeListViewAdapter(Context context, int resource, List<RecipeModel> recipesList) {
        super(context, resource, recipesList);

        this.context = context;
        recipeModelList = recipesList;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(resource, null);

            viewHolder.tvRecipeLabel = (TextView) convertView.findViewById(R.id.tvRecipeLabel);
            viewHolder.tvRecipeUri = (TextView) convertView.findViewById(R.id.tvRecipeUri);
            viewHolder.tvRecipeSource = (TextView) convertView.findViewById(R.id.tvRecipeSource);
            viewHolder.ivRecipeSourceIcon = (ImageView) convertView.findViewById(R.id.ivRecipeSourceIcon);
            viewHolder.tvRecipeUrl = (TextView) convertView.findViewById(R.id.tvRecipeUrl);
            viewHolder.ivRecipeImage = (ImageView) convertView.findViewById(R.id.ivRecipeImage);

            viewHolder.tvIngredientLines = (TextView) convertView.findViewById(R.id.tvRecipeIngredients);
            viewHolder.tvDietLabels = (TextView) convertView.findViewById(R.id.tvRecipeDietLabels);
            viewHolder.tvHealthLabels = (TextView) convertView.findViewById(R.id.tvRecipeHealthLabels);
            //viewHolder.ratingBar = (RatingBar)convertView.findViewById(R.id.rbRecipeRating);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

        ImageLoader.getInstance().displayImage(recipeModelList.get(position).getImage(), viewHolder.ivRecipeImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);
            }
        });

        viewHolder.ivRecipeImage.setTag(recipeModelList.get(position).getImage());

        // Recipe Label
        viewHolder.tvRecipeLabel.setText(recipeModelList.get(position).getLabel());

        // Recipe Source Specific fields
        ImageLoader.getInstance().displayImage(recipeModelList.get(position).getSourceIcon(), viewHolder.ivRecipeSourceIcon);
        viewHolder.tvRecipeSource.setText(recipeModelList.get(position).getSource());
        viewHolder.tvRecipeUri.setText(recipeModelList.get(position).getUri());

        // Recipe Url
        viewHolder.tvRecipeUrl.setText(recipeModelList.get(position).getUrl());

        viewHolder.tvIngredientLines.setText(Arrays.toString(recipeModelList.get(position).getIngredientLines()).replaceAll("\\[|\\]", ""));
        viewHolder.tvDietLabels.setText(Arrays.toString(recipeModelList.get(position).getDietLabels()).replaceAll("\\[|\\]", ""));
        viewHolder.tvHealthLabels.setText(Arrays.toString(recipeModelList.get(position).getHealthLabels()).replaceAll("\\[|\\]", ""));

        //viewHolder.ratingBar.setRating(2);

        return convertView;
    }

    class ViewHolder {

        private TextView tvRecipeUri;
        private TextView tvRecipeLabel;
        private TextView tvRecipeSource;
        private ImageView ivRecipeSourceIcon;
        private TextView tvRecipeUrl;
        private ImageView ivRecipeImage;
        private TextView tvIngredientLines;
        private TextView tvDietLabels;
        private TextView tvHealthLabels;
        //private RatingBar ratingBar;
    }

}
