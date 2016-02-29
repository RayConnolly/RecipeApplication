package com.example.rconnolly.recipeapplication.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rconnolly.recipeapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond on 24/02/2016.
 */
public class FavouriteRecipiesActivity extends AppCompatActivity {

    private Button loadPrefs;
    private SharedPreferences sharedPreferences;
    private static final String NO_PREFS_DEFAULT = "Unable to display favourites recipes!";
    private String recipeImage;
    private String recipeLabel;
    private Float recipeRating;
    private String[] prefsArray;
    private List<String> prefsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.favourite_recipies_activity);

//        sharedPreferences = getSharedPreferences("FavoutiteRecipesPreferences", MODE_PRIVATE);

        //final TextView myPrefs = (TextView)findViewById(R.id.txtVwMyPrefs);
//        final Button savePrefs = (Button) findViewById(R.id.btnSavePrefs);

        final TextView favRecipeImage = (TextView)findViewById(R.id.tvFavRecipeImg);
        final TextView favRecipeLabel = (TextView)findViewById(R.id.tvFavRecipeLabel);
        final TextView favRecipeRating = (TextView)findViewById(R.id.tvFavRecipeRating);

        loadPrefs = (Button) findViewById(R.id.btnLoadPrefs);

        //sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);

//        savePrefs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("name", "Ray Connolly");
//                editor.commit();
//
//            }
//        });

        loadPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("FAVOURITES", MODE_PRIVATE);

                Map<String, ?> allEntries = sharedPreferences.getAll();

                prefsList = new ArrayList<String>();

                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

                    Toast.makeText(getApplicationContext(),  entry.getKey() + "/" + entry.getValue(), Toast.LENGTH_LONG).show();

//                    prefsList.add(entry.getValue().toString());

                    //if (entry.getKey().equals(recipeRating)) {

                        //prefsList.add(recipeRating.floatValue());


                    //}
                }
            }
        });


    }

    private List<?> getTopRatedRecipes(){

        return null;
    }
}

