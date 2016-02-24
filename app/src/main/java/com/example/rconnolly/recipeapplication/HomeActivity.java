package com.example.rconnolly.recipeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by rconnolly on 2/19/2016.
 */
public class HomeActivity extends AppCompatActivity{

    GridView gridView;
    public static String[] imageNames = {"Chicken", "Beef", "Lamb", "Pork", "Seafood", "Vegetarian", "Thai", "Indian", "Chinese", "Italian"};
    public static Integer[] images = {R.drawable.chicken,
                                      R.drawable.beef,
                                      R.drawable.lamb,
                                      R.drawable.pork,
                                      R.drawable.seafood,
                                      R.drawable.vegetarian,
                                      R.drawable.lamb,
                                      R.drawable.pork,
                                      R.drawable.seafood,
                                      R.drawable.vegetarian};
    // TODO - Add new images to match 'imageNames' array values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView titleImg = (ImageView)findViewById(R.id.appTitleImg);
        titleImg.setImageResource(R.drawable.app_title_image_3);

        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(new ImageGridviewAdapter(this, imageNames, images));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                gridView.setTag(imageNames[position].toString());

                final String imgBtnVal = gridView.getTag().toString().toLowerCase();

                Intent intent = new Intent(view.getContext(), MainActivity.class);

                intent.putExtra("searchVal", imgBtnVal);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

