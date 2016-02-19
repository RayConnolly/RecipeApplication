package com.example.rconnolly.recipeapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by rconnolly on 2/16/2016.
 */
public class FoodCategoryActivity extends AppCompatActivity {

    private static final int NUM_ROWS = 2;
    private static final int NUM_COLS = 2;

    ImageButton imgButtons[][] = new ImageButton[NUM_ROWS][NUM_COLS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_food);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        populateButtons();

//        String[] categories = {"Beef", "Chicken", "Pork", "Lamb", "Vegitarian"};
//
//        ListAdapter mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
//        ListView mList = (ListView) findViewById(R.id.category_list_view);
//
//        mList.setAdapter(mAdapter);
//
//        mList.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        String category = String.valueOf(parent.getItemAtPosition(position));
//
//                        Intent intent = new Intent(FoodCategoryActivity.this, MainActivity.class);
//                        startActivity(intent);
//
//
//                    }
//                }
//        );

    }

    private void populateButtons(){
        TableLayout table = (TableLayout) findViewById(R.id.foodTypeImagesTable);
        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams( new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

        for (int col = 0; col < NUM_COLS ; col++) {
            final int FINAL_COL = col;
            final int FINAL_ROW = row;
            ImageButton imgButton = new ImageButton(this);
            imgButton.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f));
            tableRow.addView(imgButton);

            //button.setText("" + row + "" + col);
            imgButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tableButtonClicked(FINAL_COL, FINAL_ROW);
                }
            });

            imgButtons[row][col] = imgButton;
        }
        }
    }

    private void tableButtonClicked(int col, int row) {
        //Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        ImageButton imgButton = imgButtons[col][row];
        //button.setBackgroundResource(R.drawable.chicken_carbonarra);
        //ImageLoader.getInstance().displayImage(R.drawable.chicken_carbonarra, imgbutton);

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(
                        R.drawable.chicken_carbonarra)
                .showImageForEmptyUri(
                        R.drawable.chicken_carbonarra)
                .showImageOnFail(R.drawable.chicken_carbonarra)
                .displayer(new FadeInBitmapDisplayer(500))
                .cacheInMemory(true).cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        //imageLoader.displayImage(R.drawable.chicken_carbonarra, imageButton, options);
    }
}
