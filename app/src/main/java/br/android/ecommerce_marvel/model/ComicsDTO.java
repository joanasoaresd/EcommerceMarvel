package br.android.ecommerce_marvel.model;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicsDTO implements Parcelable {


    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "pageCount")
    private int pageCount;

    @JsonProperty(value = "prices")
    private ArrayList<PriceDTO> prices;

    @JsonProperty(value = "thumbnail")
    private ThumbnailDTO thumbnail;

    private double price;

    private String thumb;

    private boolean rare;


    public ComicsDTO() {
        super();
    }

    public ComicsDTO(int id, String title, String description, int pageCount, double price, String thumbnail, boolean rare) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.price = price;
        this.thumb = thumbnail;
        this.rare = rare;
    }

    public ComicsDTO(int id, String title, String description, int pageCount, double price, boolean rare) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.price = price;
        this.rare = rare;
    }

    public boolean getRare() {
        return rare;
    }

    public void setRare(boolean rare) {
        this.rare = rare;
    }

    public String getThumb() {
        return thumb;
    }

    public String showRare(boolean isRare) {
        String text = " Comum";
        if (isRare) {
            text = " Raro";
            return text;
        }
        return text;
    }


    protected ComicsDTO(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        pageCount = in.readInt();
        thumbnail = in.readParcelable(ThumbnailDTO.class.getClassLoader());
        prices = new ArrayList<>();
        in.readList(prices, PriceDTO.class.getClassLoader());

    }


    public static final Creator<ComicsDTO> CREATOR = new Creator<ComicsDTO>() {
        @Override
        public ComicsDTO createFromParcel(Parcel in) {
            return new ComicsDTO(in);
        }

        @Override
        public ComicsDTO[] newArray(int size) {
            return new ComicsDTO[size];
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

    public ThumbnailDTO getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailDTO thumbnail) {
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

    public ArrayList<PriceDTO> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<PriceDTO> prices) {
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


