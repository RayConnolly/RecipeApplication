package com.example.rconnolly.recipeapplication.models;

import java.util.List;

/**
 * Created by rconnolly on 2/17/2016.
 */
public class Recipe {

    private String label;
    private String image;
    private String source;
    private String sourceIcon;
    private String dietLabels;
    private List<Ingredients> ingredients;

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

    public String getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(String dietLabels) {
        this.dietLabels = dietLabels;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public static class Ingredients{

        private String text;
        private double quantity;
        private String measure;
        private String food;
        private double weight;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }
}
