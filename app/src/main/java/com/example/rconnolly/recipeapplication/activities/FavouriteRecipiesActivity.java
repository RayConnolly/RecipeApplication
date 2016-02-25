package com.example.rconnolly.recipeapplication.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.R;

/**
 * Created by Raymond on 24/02/2016.
 */
public class FavouriteRecipiesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.favourite_recipies_activity);

        final TextView myPrefs = (TextView)findViewById(R.id.txtVwMyPrefs);
        final Button savePrefs = (Button) findViewById(R.id.btnSavePrefs);
        final Button loadPrefs = (Button) findViewById(R.id.btnLoadPrefs);

        final SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);

        savePrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", "Ray Connolly");
                editor.commit();

            }
        });

        loadPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = prefs.getString("name", "Name not found!");
                myPrefs.setText(name);
            }
        });
    }
}
