package com.fieldaware.imagefetcher;

/**
 * Created by alberto on 28/07/14.
 */
public class SVGModel {

    public String png_full_lossy;
    public String png_thumb;

    public SVGModel (String png_thumb, String png_full_lossy) {
        this.png_thumb = png_thumb;
        this.png_full_lossy = png_full_lossy;
    }

    public String getPng_full_lossy() {
        return png_full_lossy;
    }

    public void setPng_full_lossy(String png_full_lossy) {
        this.png_full_lossy = png_full_lossy;
    }

    public String getPng_thumb() {
        return png_thumb;
    }

    public void setPng_thumb(String thumbUrl) {
        this.png_thumb = thumbUrl;
    }
}
