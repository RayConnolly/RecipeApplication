package com.example.rconnolly.recipeapplication.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.R;
import com.example.rconnolly.recipeapplication.fragments.IngredientsFragment;
import com.example.rconnolly.recipeapplication.fragments.RecipeSourceFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class RecipeDetailsActivity extends FragmentActivity {

    private String recipeImgUrl = null;
    private String recipeLbl = null;
    private String recipeDesc = null;
    private String recipeUrl = null;
    private String recipeIngredients = null;
    private String recipeDietLabels = null;
    private String recipeHealthLabels = null;
    private IngredientsFragment ingredientsFragment;
    private RecipeSourceFragment recipeSourceFragment;
    private Button btnLoadDetails;
    private Button btnLoadSource;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details_activity);

        // Set up UIL options
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        // Retrieve values from Recipes activity
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            recipeImgUrl = bundle.getString("recipeImg");
            recipeLbl = bundle.getString("recipeLbl");
            recipeDesc = bundle.getString("recipeDesc");
            recipeUrl = bundle.getString("recipeUrl");
            recipeIngredients = bundle.getString("recipeIngredients");
            recipeDietLabels = bundle.getString("recipeDietLabels");
            recipeHealthLabels = bundle.getString("recipeHealthLabels");
        }

        ImageView ivRecipeImg = (ImageView) findViewById(R.id.recipe_detail_image);
        TextView tvRecipeLbl = (TextView) findViewById(R.id.recipe_detail_name_text);
        TextView tvRecipeDesc = (TextView) findViewById(R.id.recipe_detail_desc_text);
        btnLoadDetails = (Button) findViewById(R.id.button_load_details);
        btnLoadSource = (Button) findViewById(R.id.button_load_source);

        // TODO Use this imageSize
        ImageSize targetImageSize = new ImageSize(120, 120);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ImageLoader.getInstance().displayImage(recipeImgUrl, ivRecipeImg, new ImageLoadingListener() {
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

        tvRecipeLbl.setText(recipeLbl);
        //tvRecipeDesc.setText(recipeDesc);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;
                if(v == findViewById(R.id.button_load_details)){
                    fragment = new IngredientsFragment();
                    setRecipeDetails(fragment);
                } else {
                    fragment = new RecipeSourceFragment();
                    setSourceDetails(fragment);
                }
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack("fragment");
                transaction.commit();
            }
        };
        btnLoadDetails = (Button)findViewById(R.id.button_load_details);
        btnLoadDetails.setOnClickListener(listener);
        btnLoadSource = (Button)findViewById(R.id.button_load_source);
        btnLoadSource.setOnClickListener(listener);
    }

    private void setRecipeDetails(Fragment fragment){

        Bundle bundle = new Bundle();
        String ingredients = recipeIngredients;
        String dietLbls = recipeDietLabels;
        String healthLbls = recipeHealthLabels;

        bundle.putString("ingredients", ingredients);
        bundle.putString("dietLbls", dietLbls);
        bundle.putString("healthLbls", healthLbls);

        fragment.setArguments(bundle);
    }

    private void setSourceDetails(Fragment fragment){

        Bundle bundle = new Bundle();
        String sourceUrl = recipeUrl;

        bundle.putString("sourceUrl", sourceUrl);

        fragment.setArguments(bundle);
    }

}

