package com.example.rconnolly.recipeapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.R;

/**
 * Created by rconnolly on 2/22/2016.
 */
public class FoodCategoryGridViewAdapter extends BaseAdapter {

    private String test;
    Context context;
    String[] result;
    Integer[] images;
    private static LayoutInflater inflater = null;

    public FoodCategoryGridViewAdapter(Context context, String[] imageNames, Integer[] images){
        this.context = context;
        this.result = imageNames;
        this.images = images;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) { return position; }

    class ViewHolder {

        private ImageView imageVw;
        private TextView textVw;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.food_categories_row, parent, false);

            viewHolder.imageVw = (ImageView) convertView.findViewById(R.id.imgView);
            viewHolder.textVw = (TextView) convertView.findViewById(R.id.txtView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageVw.setImageResource(images[position]);
        viewHolder.textVw.setText(result[position]);

//        viewHolder.imgBtn.setTag(result[position].toString());
//
//        final String imgBtnVal = viewHolder.imgBtn.getTag().toString().toLowerCase();
//
//        viewHolder.imgBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(v.getContext(), RecipesListActivity.class);
//                intent.putExtra("searchVal", imgBtnVal);
//                context.startActivity(intent);
//            }
//        });

        return convertView;
    }
}

