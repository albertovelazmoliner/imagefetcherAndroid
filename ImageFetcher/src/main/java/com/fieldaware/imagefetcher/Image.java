package com.fieldaware.imagefetcher;

/**
 * Created by alberto on 27/07/14.
 */
public class Image {
    public String imageUrl;
    public String text;

    public Image(String url, String name){
        this.imageUrl = url;
        this.text = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
