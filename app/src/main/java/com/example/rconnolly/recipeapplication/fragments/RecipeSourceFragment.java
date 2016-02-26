package com.example.rconnolly.recipeapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.rconnolly.recipeapplication.R;

/**
 * Created by rconnolly on 2/25/2016.
 */
public class RecipeSourceFragment extends Fragment {

    private String sourceUrl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        sourceUrl = this.getArguments().getString("sourceUrl");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recipe_source_fragment, container, false);

        WebView browser = (WebView) view.findViewById(R.id.webView);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.loadUrl(sourceUrl);

        return view;
    }
}
