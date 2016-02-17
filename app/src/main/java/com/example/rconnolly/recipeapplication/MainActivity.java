package com.example.rconnolly.recipeapplication;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rconnolly.recipeapplication.models.Recipe;

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

    private static TextView firstTextView;
    private static TextView secondTextView;
    private Button button;

    private List<Recipe> recipes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        final String[] recipes = {"Chicken Carbonara", "Chicken Tikka Masala", "Chicken Korma", "Lasagne", "Pizza", "Beef Tortillas", "Fillet Steak"};
        final String[] recipeDescriptions = {"Chicken Carbonara", "Chicken Tikka Masala", "Chicken Korma", "Lasagne", "Pizza", "Beef Tortilla", "Fillet Steak"};
        final Integer[] recipesImages = {R.drawable.chicken_carbonarra, R.drawable.chicken_tikka, R.drawable.chicken_korma, R.drawable.lasagne, R.drawable.pizza, R.drawable.tortillas, R.drawable.fillet_steak};

//        ListAdapter mAdapter = new CustomListAdapter(this, recipesImages, recipes, recipeDescriptions);
//        ListView mList = (ListView) findViewById(R.id.main_activity_list);
//
//        mList.setAdapter(mAdapter);

        ListAdapter mAdapter = new RecipeAdapter(getApplicationContext(), R.layout.list_row_temp, recipes2);
        ListView mList = (ListView) findViewById(R.id.main_activity_list);

        mList.setAdapter(mAdapter);
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

    public class RecipeAdapter extends ArrayAdapter {

        private List<Recipe> recipeModelList;
        private int resource;
        private LayoutInflater inflater;
        private View customView;

        //private ImageView ivRecipeImage;
        private TextView tvRecipeLabel;
        private TextView tvRecipeSource;
        private TextView tvRecipeSourceIcon;
        private TextView tvRecipeDietLabel;
        private RatingBar rbRecipeRating;
        private TextView tvRecipeIngredients;

        //private String[] recipeLabels;

        public RecipeAdapter(Context context, int resource, List<Recipe> recipes) {
            super(context, resource, recipes);

            recipeModelList = recipes;
            this.resource = resource;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            inflater = LayoutInflater.from(getContext());
            customView = inflater.inflate(R.layout.list_row_temp, parent, false);

            initViews();

            //tvRecipeLabel.setText(tvRecipeLabel[position]);

            return null;
        }

        private void initViews() {

            //ivRecipeImage = (ImageView) customView.findViewById(R.id.recipe_image);
            tvRecipeLabel = (TextView) customView.findViewById(R.id.recipe_title_text);
            tvRecipeSource = (TextView) customView.findViewById(R.id.tvRecipeSource);
            tvRecipeSourceIcon = (TextView) customView.findViewById(R.id.tvRecipeSourceIcon);
            tvRecipeDietLabel = (TextView) customView.findViewById(R.id.tvRecipeDietLabel);
            rbRecipeRating =(RatingBar) customView.findViewById(R.id.rbRecipeRating);
            tvRecipeIngredients = (TextView) customView.findViewById(R.id.tvRecipeIngredients);
        }


    }

    public class FetchRecipeTask extends AsyncTask<String, Void, List<Recipe>> {

        private final String LOG_TAG = FetchRecipeTask.class.getSimpleName();

        public FetchRecipeTask() {
        }

        @Override
        protected List<Recipe> doInBackground(String... params) {
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
                final String RECIPE_BASE_URL = "https://www.edamam.com/search?";
                final String QUERY_PARAM = "q";
                //final String RECIPES_PARAM = "count";
                final String RECIPES_FROM = "from";
                final String RECIPES_TO = "to";
                //final String APPID_ID = "app_id";
                final String APP_KEY = "app_key";

                Uri builtUri = Uri.parse(RECIPE_BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, params[0])
                        .appendQueryParameter(RECIPES_FROM, Integer.toString(from))
                        .appendQueryParameter(RECIPES_TO, Integer.toString(to))
                        .appendQueryParameter(APP_KEY, BuildConfig.RECIPE_API_KEY)
                        .build();

                URL url = new URL(builtUri.toString());

                Log.v(LOG_TAG, "Built URI: " + builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                recipeStr = buffer.toString();

                Log.v(LOG_TAG, "Recipe JSON String: " + recipeStr);

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

        private List<Recipe> getRecipeDataFromJson(String recipeStr) throws JSONException {

            final String LIST = "label";
            final String IMAGE = "image";
            final String SOURCE = "source";
            final String SOURCE_ICON = "sourceIcon";
            final String DIET_LABELS = "dietLabels";
            final List<Recipe.Ingredients> INGREDIENTS;

            JSONObject recipeJson = new JSONObject(recipeStr);
            JSONArray recipeArray = recipeJson.getJSONArray(LIST);

            String recipeLabel = null;
            Recipe recipeModel = new Recipe();
            List<Recipe> recipeList = new ArrayList<>();

            //Gson gson = new Gson();
            for (int i = 0; i < recipeArray.length(); i++) {

                JSONObject finalObject = recipeArray.getJSONObject(i);
                //recipeModel = gson.fromJson(finalObject.toString(), Recipe.class);

                recipeLabel = finalObject.getString("source");
            }

            recipeList.add(recipeModel);
            return recipeList;
        }

        @Override
        protected void onPostExecute(List<Recipe> result) {

            if (result != null) {
//                mForecastAdapter.clear();
//                for (String dayForecastStr : result) {
//                    mForecastAdapter.add(dayForecastStr);
//                }
//                ListAdapter mAdapter = new RecipeAdapter(getApplicationContext(), R.layout.list_row_temp, result);
//                ListView mList = (ListView) findViewById(R.id.main_activity_list);
//
//                mList.setAdapter(mAdapter);
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
        return super.onOptionsItemSelected(item);
    }

}
