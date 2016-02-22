package com.example.rconnolly.recipeapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rconnolly on 2/22/2016.
 */
public class ImageAdapter extends BaseAdapter {

    Context context;
    String[] result;
    Integer[] images;
    private static LayoutInflater inflater = null;

    public ImageAdapter(Context context, String[] imageNames, Integer[] images){
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

        private ImageButton imgBtn;
        private TextView textVw;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.recipe_list, parent, false);

            viewHolder.imgBtn = (ImageButton) convertView.findViewById(R.id.imgBtn);
            viewHolder.textVw = (TextView) convertView.findViewById(R.id.textVw);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgBtn.setImageResource(images[position]);
        viewHolder.textVw.setText(result[position]);

        viewHolder.imgBtn.setTag(result[position].toString());

        final String imgBtnVal = viewHolder.imgBtn.getTag().toString().toLowerCase();

        viewHolder.imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("searchVal", imgBtnVal);
                context.startActivity(intent);

//                Toast.makeText(context, "You clicked on: " + imgBtn.getTag().toString().toLowerCase(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}

