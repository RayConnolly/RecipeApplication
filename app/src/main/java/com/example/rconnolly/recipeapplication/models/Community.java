package com.example.rconnolly.recipeapplication.models;

/**
 * Created by Raymond on 29/02/2016.
 */
public class Community {

    int imageId;
    String name;

//    public Community(String imageId, String name) {
//        this.imageId = imageId;
//        this.name = name;
//    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
