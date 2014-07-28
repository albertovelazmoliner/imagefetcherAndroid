package com.fieldaware.imagefetcher;

import java.util.ArrayList;

/**
 * Created by alberto on 28/07/14.
 */
public class PageDataModel {
    ArrayList<Image> payload = new ArrayList<Image>();

    public PageDataModel(ArrayList<Image> payload){
        this.payload = payload;
    }

    public ArrayList<Image> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<Image> payload) {
        this.payload = payload;
    }

}
