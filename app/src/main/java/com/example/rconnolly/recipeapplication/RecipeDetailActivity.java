package com.example.rconnolly.recipeapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class RecipeDetailActivity extends AppCompatActivity {

    private String recipeImgUrl = null;
    private String recipeLbl = null;
    private String recipeDesc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // Add Ingredients fragment
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_title, new IngredientsFragment())
//                    .add(R.id.fragment_image, new IngredientsFragment())
//                    .add(R.id.fragment_text_block, new IngredientsFragment())
//                    .commit();
//        }

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
        }

        ImageView ivRecipeImg = (ImageView) findViewById(R.id.recipe_detail_image);
        TextView tvRecipeLbl = (TextView) findViewById(R.id.recipe_detail_name_text);
        TextView tvRecipeDesc = (TextView) findViewById(R.id.recipe_detail_desc_text);

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
        tvRecipeDesc.setText(recipeDesc);






//        Bundle bundle = getIntent().getExtras();
//
//        if (bundle != null){
//            url = bundle.getString("uriValue");
//        }
//
//        WebView browser = (WebView)findViewById(R.id.webView);
//        browser.getSettings().setLoadsImagesAutomatically(true);
//        browser.getSettings().setJavaScriptEnabled(true);
//        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        browser.loadUrl(url);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


//        button = (Button) findViewById(R.id.second_activity_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(view.getContext(), MainActivity.class);
//
//                startActivity(intent);
//            }
//        });
    }
}
