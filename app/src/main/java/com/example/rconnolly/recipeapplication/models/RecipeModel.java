package com.example.rconnolly.recipeapplication.models;

/**
 * Created by rconnolly on 2/17/2016.
 */
public class RecipeModel {

    private String uri;
    private String label;
    private String image;
    private String source;
    private String sourceIcon;
    private String url;
    //private String[] ingredientLines;

    public RecipeModel(String uri, String label, String image, String source, String sourceIcon, String url){

        this.setUri(uri);
        this.setLabel(label);
        this.setImage(image);
        this.setSource(source);
        this.setSourceIcon(sourceIcon);
        this.setUrl(url);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceIcon() {
        return sourceIcon;
    }

    public void setSourceIcon(String sourceIcon) {
        this.sourceIcon = sourceIcon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public String[] getIngredientLines() {
//        return ingredientLines;
//    }
//
//    public void setIngredientLines(String[] ingredientLines) {
//        this.ingredientLines = ingredientLines;
//    }
}
