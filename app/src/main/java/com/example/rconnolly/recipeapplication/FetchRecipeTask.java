package com.example.rconnolly.recipeapplication;

/**
 * Created by rconnolly on 2/16/2016.
 */
//public class FetchRecipeTask extends AsyncTask<String, Void, String[]> {
//
//    private final String LOG_TAG = FetchRecipeTask.class.getSimpleName();
//
//    public FetchRecipeTask(){
//    }
//
//    @Override
//    protected String[] doInBackground(String... params) {
//        if (params.length == 0){
//            return null;
//        }
//
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//
//        String recipeStr = null;
//
//        String format = "json";
//        int from = 0;
//        int to = 3;
//
//        try{
//            //String tempUrl = "https://www.edamam.com/search?q=beef&from=0&to=10&app_key=${f0a3e23184a690b536f959d16568b22ae578bb6f}";
//            final String RECIPE_BASE_URL = "https://www.edamam.com/search?";
//            final String QUERY_PARAM = "q";
//            //final String RECIPES_PARAM = "count";
//            final String RECIPES_FROM = "from";
//            final String RECIPES_TO = "to";
//            //final String APPID_ID = "app_id";
//            final String APP_KEY = "app_key";
//
//            Uri builtUri = Uri.parse(RECIPE_BASE_URL).buildUpon()
//                    .appendQueryParameter(QUERY_PARAM, params[0])
//                    .appendQueryParameter(RECIPES_FROM, Integer.toString(from))
//                    .appendQueryParameter(RECIPES_TO, Integer.toString(to))
//                    .appendQueryParameter(APP_KEY, BuildConfig.RECIPE_API_KEY)
//                    .build();
//
//            URL url = new URL(builtUri.toString());
//
//            Log.v(LOG_TAG, "Built URI: " + builtUri.toString());
//
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null){
//                return null;
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String line = "";
//            while((line = reader.readLine()) != null){
//
//                buffer.append(line + "\n");
//            }
//
//            if (buffer.length() == 0){
//                return null;
//            }
//            recipeStr = buffer.toString();
//
//            Log.v(LOG_TAG, "RecipeModel JSON String: " + recipeStr);
//
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "Error", e);
//            return null;
//        } finally {
//            if (urlConnection != null){
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                    Log.e(LOG_TAG, "Error closing stream", e);
//                }
//            }
//        }
//
//        try{
//            return getRecipeDataFromJson(recipeStr);
//        } catch (JSONException e) {
//            Log.e(LOG_TAG, e.getMessage(), e);
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    private String getRecipeDataFromJson(String recipeStr) throws JSONException{
//
//        final String LIST = "label";
//        final String IMAGE = "image";
//        final String SOURCE = "source";
//        final String SOURCE_ICON = "sourceIcon";
//        final String DIET_LABELS = "dietLabels";
//        final List<RecipeModel.Ingredients> INGREDIENTS;
//
//        JSONObject recipeJson = new JSONObject(recipeStr);
//        JSONArray recipeArray = recipeJson.getJSONArray(LIST);
//
//        JSONObject finalObject = recipeArray.getJSONObject(0);
//
//        RecipeModel recipeModel = new RecipeModel();
//        recipeModel.setLabel(finalObject.getString(LIST));
//
//
//        return recipeModel.toString();
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//
//        if (result != null){
//            mForecastAdapter.clear();
//            for (String dayForecastStr : result){
//                mForecastAdapter.add(dayForecastStr);
//            }
//        }
//    }
//}
