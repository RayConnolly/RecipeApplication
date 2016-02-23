package com.example.rconnolly.recipeapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class RecipeDetailActivity extends AppCompatActivity {

    private Button button;
    private WebView browser;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            url = bundle.getString("uriValue");
        }

        WebView browser = (WebView)findViewById(R.id.webView);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.loadUrl(url);







//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //ImageView recipeImageView = (ImageView) findViewById(R.id.recipe_detail_image);
//        TextView recipeNameTextView = (TextView) findViewById(R.id.recipe_detail_name_text);
//        TextView recipeDescTextView = (TextView) findViewById(R.id.recipe_detail_desc_text);


        //Bundle extras = getIntent().getExtras();



//        if (extras != null) {
//            String recipeName = extras.getString("name");
//            String recipeDesc = extras.getString("desc");

            //Bitmap recipeImage = extras.getParcelable("bitmap");


//            Bitmap recipeImage = BitmapFactory.decodeByteArray(
//                    getIntent().getByteArrayExtra("bitmap"), 0, getIntent().getByteArrayExtra("bitmap").length);
            //recipeImageView.setImageBitmap(recipeImage);

        //Bitmap recipeImage = (Bitmap)this.getIntent().getParcelableExtra("bitmap");
        String recipeName = (String)this.getIntent().getStringExtra("name");
        String recipeDesc = (String)this.getIntent().getStringExtra("desc");

//            recipeNameTextView.setText(recipeName);
//            recipeDescTextView.setText(recipeDesc);
        //}

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
