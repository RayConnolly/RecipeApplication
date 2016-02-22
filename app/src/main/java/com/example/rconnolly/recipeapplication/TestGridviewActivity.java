package com.example.rconnolly.recipeapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.GridView;

/**
 * Created by rconnolly on 2/19/2016.
 */
public class TestGridviewActivity extends AppCompatActivity{

    GridView gridView;
    public static String[] imageNames = {"Chicken", "Beef", "Lamb", "Pork", "Seafood", "Vegitarian"};
    public static Integer[] images = {R.drawable.chicken_carbonarra, R.drawable.chicken_tikka, R.drawable.chicken_korma, R.drawable.lasagne, R.drawable.fillet_steak, R.drawable.lasagne};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_gridview_activity);

         gridView = (GridView)findViewById(R.id.gridView);
         gridView.setAdapter(new ImageAdapter(this, imageNames, images));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

