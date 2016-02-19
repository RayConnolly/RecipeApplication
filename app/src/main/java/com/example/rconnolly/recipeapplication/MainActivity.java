package com.example.rconnolly.recipeapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.rconnolly.recipeapplication.models.RecipeModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

    private RecipeListAdapter recipeAdapter;
    private List<RecipeModel> recipes;
    private ListView lvRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateRecipes();
    }

    private void updateRecipes() {
        FetchRecipeTask recipeTask = new FetchRecipeTask();
        recipeTask.execute("https://www.edamam.com/search?q=beef&from=0&to=10&app_key=${f0a3e23184a690b536f959d16568b22ae578bb6f}");
    }

    public class FetchRecipeTask extends AsyncTask<String, String, List<RecipeModel>> {

        private final String LOG_TAG = FetchRecipeTask.class.getSimpleName();

        public FetchRecipeTask() {
        }

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
                URL url = new URL(params[0]);

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

                recipeModel = new RecipeModel(uri, label, image, source, sourceIcon, url);
                recipeModelList.add(recipeModel);
            }
            return recipeModelList;
        }

        @Override
        protected void onPostExecute (List < RecipeModel > result) {

            recipeAdapter = new RecipeListAdapter(getApplicationContext(), R.layout.list_recipe_category_row, result);
            lvRecipes = (ListView) findViewById(R.id.main_activity_list);

            lvRecipes.setAdapter(recipeAdapter);
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
