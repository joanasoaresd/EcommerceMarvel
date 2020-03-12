package br.android.ecommerce_marvel.br.android.ecommerce_marvel.model;


import android.media.Image;

import java.util.ArrayList;
public class Comics {

    private int id;
    private Image thumbnail;
    private String Title;
    private String description;
    private int pageCount;
    private ArrayList<Price> prices;

    public int getId() {
        return id;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Comics{" +
                "id=" + id +
                ", thumbnail=" + thumbnail +
                ", Title='" + Title + '\'' +
                ", description='" + description + '\'' +
                ", pageCount=" + pageCount +
                ", prices=" + prices +
                '}';
    }
}
