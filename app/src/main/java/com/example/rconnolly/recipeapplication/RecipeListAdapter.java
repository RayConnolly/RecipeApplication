package com.example.rconnolly.recipeapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.models.RecipeModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by rconnolly on 2/19/2016.
 */
public class RecipeListAdapter extends ArrayAdapter {

    private List<RecipeModel> recipeModelList;
    private int resource;
    private LayoutInflater inflater;

    private TextView tvRecipeUri;
    private TextView tvRecipeLabel;
    private TextView tvRecipeSource;
    private ImageView ivRecipeSourceIcon;
    private TextView tvRecipeUrl;
    private ImageView ivRecipeImage;

    public RecipeListAdapter(Context context, int resource, List<RecipeModel> recipesList) {
        super(context, resource, recipesList);

        recipeModelList = recipesList;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO - Add ViewHolder here
        //RecyclerView.ViewHolder viewHolder = null;

        convertView = inflater.inflate(resource, null);

        tvRecipeLabel = (TextView) convertView.findViewById(R.id.tvRecipeLabel);
        tvRecipeUri = (TextView) convertView.findViewById(R.id.tvRecipeUri);
        tvRecipeSource = (TextView) convertView.findViewById(R.id.tvRecipeSource);
        ivRecipeSourceIcon = (ImageView) convertView.findViewById(R.id.ivRecipeSourceIcon);
        tvRecipeUrl = (TextView) convertView.findViewById(R.id.tvRecipeUrl);
        tvRecipeUrl.setMovementMethod(LinkMovementMethod.getInstance());
        ivRecipeImage = (ImageView) convertView.findViewById(R.id.ivRecipeImage);
        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

        ImageLoader.getInstance().displayImage(recipeModelList.get(position).getImage(), ivRecipeImage, new ImageLoadingListener() {
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

        // Recipe Label
        tvRecipeLabel.setText(recipeModelList.get(position).getLabel());

        // Recipe Source Specific fields
        ImageLoader.getInstance().displayImage(recipeModelList.get(position).getSourceIcon(), ivRecipeSourceIcon);
        tvRecipeSource.setText(recipeModelList.get(position).getSource());
        tvRecipeUri.setText("Uri: " + recipeModelList.get(position).getUri());

        // Recipe Url
        tvRecipeUrl.setText(recipeModelList.get(position).getUrl());

        return convertView;
    }
}
