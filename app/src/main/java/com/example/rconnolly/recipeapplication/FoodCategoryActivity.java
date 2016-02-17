package com.example.rconnolly.recipeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by rconnolly on 2/16/2016.
 */
public class FoodCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_food);

        String[] categories = {"Beef", "Chicken", "Pork", "Lamb", "Vegitarian"};

        ListAdapter mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        ListView mList = (ListView) findViewById(R.id.category_list_view);

        mList.setAdapter(mAdapter);

        mList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String category = String.valueOf(parent.getItemAtPosition(position));

                        Intent intent = new Intent(FoodCategoryActivity.this, MainActivity.class);
                        startActivity(intent);


                    }
                }
        );


    }
}
