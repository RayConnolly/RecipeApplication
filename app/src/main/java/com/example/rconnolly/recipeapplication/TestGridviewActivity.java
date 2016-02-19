package com.example.rconnolly.recipeapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by rconnolly on 2/19/2016.
 */
public class TestGridviewActivity extends AppCompatActivity{

    GridView gridView;
    String[] imageNames = {"Chicken", "Beef", "Lamb", "Pork"};
    int[] images = {R.drawable.chicken_carbonarra, R.drawable.chicken_tikka, R.drawable.chicken_korma, R.drawable.lasagne};

    private ImageView imgOne;
    private ImageView imgTwo;
    private ImageView imgThree;
    private ImageView imgFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_gridview_activity);

        gridView = (GridView)findViewById(R.id.gridView);

        ImageAdapter imgAdapter = new ImageAdapter(this, imageNames, images);

        gridView.setAdapter(imgAdapter);

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Toast.makeText(getApplicationContext(), "Yo", Toast.LENGTH_SHORT).show();
//            }
//        });

//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .displayer(new FadeInBitmapDisplayer(500))
//                .build();
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//                .defaultDisplayImageOptions(defaultOptions)
//                .build();
//        ImageLoader.getInstance().init(config);
//
//        imgOne = (ImageView)findViewById(R.id.imgBtnOne);
//        imgTwo = (ImageView)findViewById(R.id.imgBtnTwo);
//        imgThree = (ImageView)findViewById(R.id.imgBtnThree);
//        imgFour = (ImageView)findViewById(R.id.imgBtnFour);


//        ImageLoader.getInstance().displayImage("http://coolabah-hotel.com/wp-content/uploads/2013/06/roast_beef_dinner.jpg", imgOne);
//        ImageLoader.getInstance().displayImage("https://d1hekt5vpuuw9b.cloudfront.net/assets/article/97c0de5e5a907833ca1106afef3238dc_rubber-chicken-lede2-580x326_featuredImage.jpg", imgTwo);
//        ImageLoader.getInstance().displayImage("http://cdn.sheknows.com/articles/2011/04/Tonights_Dinners/Grilled_Leg_of_Lamb.jpg", imgThree);
//        ImageLoader.getInstance().displayImage("http://images.edge-generalmills.com/1ab0c0c5-96df-4d21-8eff-f88e83661c98.jpg", imgFour);
    }

    public class ImageAdapter extends BaseAdapter {

        private Context context;
        String[] imageNames;
        int[] images;

        public ImageAdapter(Context context, String[] imageNames, int[] images){
            this.context = context;
            this.imageNames = imageNames;
            this.images = images;
        }

        @Override
        public int getCount() {
            return imageNames.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if (convertView == null){

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView= inflater.inflate(R.layout.test_gridview_activity, null);
                //view = getLayoutInflater().inflate(R.layout.test_gridview_activity, parent, false);
            }

            ImageView imageViewOne = (ImageView)findViewById(R.id.imgViewOne);
            ImageView imageViewTwo = (ImageView)findViewById(R.id.imgViewTwo);
            ImageView imageViewThree = (ImageView)findViewById(R.id.imgViewThree);
            ImageView imageViewFour = (ImageView)findViewById(R.id.imgViewFour);

            imageViewOne.setImageResource(images[position]);
            imageViewTwo.setImageResource(images[position]);
            imageViewThree.setImageResource(images[position]);
            imageViewFour.setImageResource(images[position]);

            return convertView;
        }
    }
}
