package com.example.androidtutorial.api;

/**
 * Created by vpenemetsa on 8/12/13.
 */
public class Recipe {

    private String image;

    private String recipeName;

    private String sourceUrl;

    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Recipe(String image, String recipeName, String sourceUrl, String source) {
        this.image = image;
        this.recipeName = recipeName;
        this.sourceUrl = sourceUrl;
        this.source = source;
    }
}