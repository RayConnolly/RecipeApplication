package com.example.rconnolly.recipeapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.R;
import com.example.rconnolly.recipeapplication.models.Community;

import java.util.Collections;
import java.util.List;

/**
 * Created by Raymond on 29/02/2016.
 */
public class CommunitiesAdapter extends RecyclerView.Adapter<CommunitiesAdapter.CommunitiesViewHolder> {

    private LayoutInflater inflater;
    private List<Community> data = Collections.emptyList();

    public CommunitiesAdapter(Context context, List<Community> data){

        this.data = data;
        this.inflater = inflater.from(context);
    }

    @Override
    public CommunitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.communities_activity_card, parent, false);

        CommunitiesViewHolder holder = new CommunitiesViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CommunitiesViewHolder holder, int position) {

        Community community = data.get(position);

        holder.communityImage.setImageResource(community.getImageId());
        holder.communityTitle.setText(community.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommunitiesViewHolder extends RecyclerView.ViewHolder{

        ImageView communityImage;
        TextView communityTitle;

        public CommunitiesViewHolder(View itemView) {
            super(itemView);

            communityImage = (ImageView) itemView.findViewById(R.id.ivCommunities);
            communityTitle = (TextView) itemView.findViewById(R.id.tvCommunities);
        }
    }
}
