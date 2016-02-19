package com.example.rconnolly.recipeapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.models.RecipeModel;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FetchRecipeTask.RecipeAdapter recipeAdapter;
    private List<RecipeModel> recipes;
    private ListView lvRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        lvRecipes = (ListView) findViewById(R.id.main_activity_list);
        updateRecipes();

//        final String[] recipes = {"Chicken Carbonara", "Chicken Tikka Masala", "Chicken Korma", "Lasagne", "Pizza", "Beef Tortillas", "Fillet Steak"};
//        final String[] recipeDescriptions = {"Chicken Carbonara", "Chicken Tikka Masala", "Chicken Korma", "Lasagne", "Pizza", "Beef Tortilla", "Fillet Steak"};
//        final Integer[] recipesImages = {R.drawable.chicken_carbonarra, R.drawable.chicken_tikka, R.drawable.chicken_korma, R.drawable.lasagne, R.drawable.pizza, R.drawable.tortillas, R.drawable.fillet_steak};

//        ListAdapter mAdapter = new CustomListAdapter(this, recipesImages, recipes, recipeDescriptions);
//        ListView mList = (ListView) findViewById(R.id.main_activity_list);
//
//        mList.setAdapter(mAdapter);

//        ListAdapter mAdapter = new RecipeAdapter(this, recipes2);
//        ListView mList = (ListView) findViewById(R.id.main_activity_list);
//
//        mList.setAdapter(mAdapter);
//
//        mList.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        String selectedRecipe = recipes[+position];
//                        String selectedRecipeDesc = recipeDescriptions[+position];
//                        //Integer selectedRecipeImage = recipesImages[+ position];
//
//                        // BitmapDrawable selectedImage = (BitmapDrawable) recipesImages[+ position];
//                        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), selectedRecipeImage);
//
//                        Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
//                        intent.putExtra("name", selectedRecipe);
//                        intent.putExtra("desc", selectedRecipeDesc);
//                        //intent.putExtra("bitmap", bitmap);
//
//                        startActivity(intent);
//                    }
//                }
//        );
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void updateRecipes() {
        FetchRecipeTask recipeTask = new FetchRecipeTask();
        recipeTask.execute("https://www.edamam.com/search?q=beef&from=0&to=5&app_key=${f0a3e23184a690b536f959d16568b22ae578bb6f}");
    }

    public class FetchRecipeTask extends AsyncTask<String, String, List<RecipeModel>> {

        private final String LOG_TAG = FetchRecipeTask.class.getSimpleName();

//        public FetchRecipeTask() {
//        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<RecipeModel> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String recipeStr = null;

            String format = "json";
            int from = 0;
            int to = 3;

            try {
                //String tempUrl = "https://www.edamam.com/search?q=beef&from=0&to=10&app_key=${f0a3e23184a690b536f959d16568b22ae578bb6f}";
//                final String RECIPE_BASE_URL = "https://www.edamam.com/search?";
                final String RECIPE_BASE_URL = "https://www.edamam.com/search?q=beef&from=0&to=2&app_key=${f0a3e23184a690b536f959d16568b22ae578bb6f}";

                final String QUERY_PARAM = "q";
                //final String RECIPES_PARAM = "count";
                final String RECIPES_FROM = "from";
                final String RECIPES_TO = "to";
                //final String APPID_ID = "app_id";
                final String APP_KEY = "app_key";

//                 Uri builtUri = Uri.parse(RECIPE_BASE_URL).buildUpon()
//                        .appendQueryParameter(QUERY_PARAM, params[0])
//                        .appendQueryParameter(RECIPES_FROM, Integer.toString(from))
//                        .appendQueryParameter(RECIPES_TO, Integer.toString(to))
//                        .appendQueryParameter(APP_KEY, BuildConfig.RECIPE_API_KEY)
//                        .build();
//
//                URL url = new URL(builtUri.toString());

//                Log.v(LOG_TAG, "Built URI: " + builtUri.toString());

                URL url = new URL(params[0]);

                //URL url = new URL(RECIPE_BASE_URL);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                recipeStr = buffer.toString();

                Log.v(LOG_TAG, "RecipeModel JSON String: " + recipeStr);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getRecipeDataFromJson(recipeStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            return null;
        }

        private List<RecipeModel> getRecipeDataFromJson(String recipeStr) throws JSONException {

            JSONObject parentObject = new JSONObject(recipeStr);
            JSONArray hitsArray = parentObject.getJSONArray("hits");

            RecipeModel recipeModel = null;
            List<RecipeModel> recipeModelList = new ArrayList<>();

            StringBuffer finalBufferedData = new StringBuffer();

            for (int i = 0; i < hitsArray.length(); i++) {

                JSONObject object = hitsArray.getJSONObject(i);

                JSONObject recipeObject = null;

                if(object.has("recipe")) {
                    if (!object.isNull("recipe")) {
                        recipeObject = object.getJSONObject("recipe");
                    }
                }

                String uri = null;
                String label = null;
                String image = null;
                String source = null;
                String sourceIcon = null;
                String url = null;
                //String[] ingredientLines = null;

                if(recipeObject.has("uri")) {
                    if (!recipeObject.isNull("uri")) {
                        uri = recipeObject.getString("uri");
                    }
                }
                if(recipeObject.has("label")) {
                    if (!recipeObject.isNull("label")) {
                        label = recipeObject.getString("label");
                    }
                }
                if(recipeObject.has("image")) {
                    if (!recipeObject.isNull("image")) {
                        image = recipeObject.getString("image");
                    }
                }
                if(recipeObject.has("source")) {
                    if (!recipeObject.isNull("source")) {
                        source = recipeObject.getString("source");
                    }
                }
                if(recipeObject.has("sourceIcon")) {
                    if (!recipeObject.isNull("sourceIcon")) {
                        sourceIcon = recipeObject.getString("sourceIcon");
                    }
                }
                if(recipeObject.has("url")) {
                    if (!recipeObject.isNull("url")) {
                        url = recipeObject.getString("url");
                    }
                }
//                if(recipeObject.has("ingredientLines")) {
//                    if (!recipeObject.isNull("ingredientLines")) {
//                        ingredientLines = recipeObject.getString("ingredientLines");
//                    }
//                }

                recipeModel = new RecipeModel(uri, label, image, source, sourceIcon, url);
                recipeModelList.add(recipeModel);
            }
            return recipeModelList;
        }

        @Override
        protected void onPostExecute (List < RecipeModel > result) {

            recipeAdapter = new RecipeAdapter(getApplicationContext(), R.layout.list_row_temp, result);
            lvRecipes = (ListView) findViewById(R.id.main_activity_list);

            lvRecipes.setAdapter(recipeAdapter);
        }


        public class RecipeAdapter extends ArrayAdapter {

            private List<RecipeModel> recipeModelList;
            private int resource;
            private LayoutInflater inflater;

            private TextView tvRecipeUri;
            private TextView tvRecipeLabel;
            private TextView tvRecipeSource;
            private ImageView ivRecipeSourceIcon;
            private TextView tvRecipeUrl;
            private ImageView ivRecipeImage;

            // private TextView tvRecipeDietLabel;
            // private RatingBar rbRecipeRating;
            // private TextView tvRecipeIngredients;

            public RecipeAdapter(Context context, int resource, List<RecipeModel> recipesList) {
                super(context, resource, recipesList);

                recipeModelList = recipesList;
                this.resource = resource;
                inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                RecyclerView.ViewHolder viewHolder = null;

                convertView = inflater.inflate(resource, null);

                tvRecipeLabel = (TextView) convertView.findViewById(R.id.tvRecipeLabel);
                tvRecipeUri = (TextView) convertView.findViewById(R.id.tvRecipeUri);
                tvRecipeSource = (TextView) convertView.findViewById(R.id.tvRecipeSource);
                ivRecipeSourceIcon = (ImageView) convertView.findViewById(R.id.ivRecipeSourceIcon);
                tvRecipeUrl = (TextView) convertView.findViewById(R.id.tvRecipeUrl);
                ivRecipeImage = (ImageView) convertView.findViewById(R.id.ivRecipeImage);
                final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
                // tvRecipeDietLabel = (TextView) convertView.findViewById(R.id.tvRecipeDietLabel);
                // rbRecipeRating =(RatingBar) convertView.findViewById(R.id.rbRecipeRating);
                // tvRecipeIngredients = (TextView) convertView.findViewById(R.id.tvRecipeIngredients);

                ImageLoader.getInstance().displayImage(recipeModelList.get(position).getImage(), ivRecipeImage, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        progressBar.setVisibility(View.GONE);
                    }
                });


                tvRecipeLabel.setText(recipeModelList.get(position).getLabel());

                ImageLoader.getInstance().displayImage(recipeModelList.get(position).getSourceIcon(), ivRecipeSourceIcon);
                tvRecipeSource.setText(recipeModelList.get(position).getSource());
                tvRecipeUri.setText("Uri: " + recipeModelList.get(position).getUri());

                tvRecipeUrl.setText(recipeModelList.get(position).getUrl());

                return convertView;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_refresh) {

            updateRecipes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
