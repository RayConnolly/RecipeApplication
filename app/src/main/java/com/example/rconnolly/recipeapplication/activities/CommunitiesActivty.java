package com.example.rconnolly.recipeapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rconnolly.recipeapplication.R;
import com.example.rconnolly.recipeapplication.adapters.CommunitiesAdapter;
import com.example.rconnolly.recipeapplication.models.Community;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond on 29/02/2016.
 */
public class CommunitiesActivty extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardView cardView;
    private CommunitiesAdapter communitiesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.communities_activity);

        cardView = (CardView)findViewById(R.id.cvCommunities);

        recyclerView = (RecyclerView) findViewById(R.id.rvCommunities);
        communitiesAdapter = new CommunitiesAdapter(getApplicationContext(), getData());
        recyclerView.setAdapter(communitiesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private static List<Community> getData(){

        List<Community> communities = new ArrayList<>();
        int [] images = { R.drawable.man, R.drawable.woman, R.drawable.man_and_woman, R.drawable.home_decor}; // TODO images to be added to array
        String [] names = { "Men", "Women", "Men & Women", "Home Decor"}; // TODO names to be added to array

        for ( int i = 0; i < images.length && i < names.length; i++){

            Community community = new Community();

            community.setImageId(images[i]);
            community.setName(names[i]);

            communities.add(community);
        }

        return communities;
    }
}
