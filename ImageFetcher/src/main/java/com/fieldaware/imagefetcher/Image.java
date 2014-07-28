package com.fieldaware.imagefetcher;

/**
 * Created by alberto on 27/07/14.
 */
public class Image {

    public String title;
    public SVGModel svg;

    public Image(String title, SVGModel svg){
        this.svg = svg;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String text) {
        this.title = title;
    }

    public SVGModel getSvg() {
        return svg;
    }

    public void setSvg(SVGModel svg) {
        this.svg = svg;
    }

}
