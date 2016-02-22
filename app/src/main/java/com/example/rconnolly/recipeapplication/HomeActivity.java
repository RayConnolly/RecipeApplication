package com.example.rconnolly.recipeapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by rconnolly on 2/19/2016.
 */
public class HomeActivity extends AppCompatActivity{

    GridView gridView;
    public static String[] imageNames = {"Chicken", "Beef", "Lamb", "Pork", "Seafood", "Vegetarian"};
    public static Integer[] images = {R.drawable.chicken, R.drawable.beef, R.drawable.lamb, R.drawable.pork, R.drawable.seafood, R.drawable.vegetarian};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView titleImg = (ImageView)findViewById(R.id.appTitleImg);
        titleImg.setImageResource(R.drawable.app_title_image);

         gridView = (GridView)findViewById(R.id.gridView);
         gridView.setAdapter(new ImageAdapter(this, imageNames, images));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

