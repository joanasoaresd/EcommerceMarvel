package br.android.ecommerce_marvel.model;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comics implements Parcelable {


    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "pageCount")
    private int pageCount;

    @JsonProperty(value = "prices")
    private ArrayList<Price> prices;

    @JsonProperty(value = "thumbnail")
    private Thumbnail thumbnail;

    private double price;

    private String thumb;

    private boolean raro;


    public Comics() {
        super();
    }

    public Comics(int id, String title, String description, int pageCount, double price, String thumbnail, boolean raro) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.price = price;
        this.thumb = thumbnail;
        this.raro = raro;
    }

    public Comics(int id, String title, String description, int pageCount, double price, boolean raro) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.price = price;
        this.raro = raro;
    }

    public boolean getRaro() {
        return raro;
    }

    public void setRaro(boolean raro) {
        this.raro = raro;
    }

    public String getThumb() {
        return thumb;
    }

    public String mostrarRaro() {
        String text = " Comum";
        if (getRaro() == true) {
            text = " Raro";
            return text;
        }
        return text;
    }


    protected Comics(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        pageCount = in.readInt();
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        prices = new ArrayList<>();
        in.readList(prices, Price.class.getClassLoader());

    }


    public static final Creator<Comics> CREATOR = new Creator<Comics>() {
        @Override
        public Comics createFromParcel(Parcel in) {
            return new Comics(in);
        }

        @Override
        public Comics[] newArray(int size) {
            return new Comics[size];
        }
    };

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(pageCount);
        dest.writeParcelable(thumbnail, flags);
        dest.writeList(prices);

    }
}


