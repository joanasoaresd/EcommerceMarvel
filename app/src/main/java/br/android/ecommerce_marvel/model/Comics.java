package br.android.ecommerce_marvel.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comics {

    private int id;
    private String title;
    private String description;
    private int pageCount;
    private ArrayList<Price> prices;
    private Thumbnail thumbnail;


    public Comics() {
        super();
    }

    public Comics(String title, int pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }

    public Comics(int id, String title, String description, int pageCount, ArrayList<Price> prices, Thumbnail thumbnail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.prices = prices;
        this.thumbnail = thumbnail;
    }

    public Comics(int id, String title, String description, int pageCount, ArrayList<Price> prices) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.prices = prices;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
    }
}

