package com.example.rconnolly.recipeapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.rconnolly.recipeapplication.R;

/**
 * Created by rconnolly on 2/23/2016.
 */
public class SplashActivity extends AppCompatActivity {

    private Button foodTypeBtn;
    private Button favouritesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen_activity);

        foodTypeBtn = (Button) findViewById(R.id.splashScreenButton);
        favouritesBtn = (Button) findViewById(R.id.splashScreenFavouritesButton);

        foodTypeBtn.setBackgroundResource(R.drawable.button);
        favouritesBtn.setBackgroundResource(R.drawable.button);

        foodTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FoodCategoriesActivity.class);
                startActivity(intent);

            }
        });

        favouritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FavouriteRecipiesActivity.class);
                startActivity(intent);

            }
        });
    }
}
