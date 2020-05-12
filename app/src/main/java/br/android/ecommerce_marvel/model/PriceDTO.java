package br.android.ecommerce_marvel.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDTO implements Parcelable {

    @JsonProperty(value = "type")
    private String type;
    @JsonProperty(value = "price")
    private double price;

    public PriceDTO(@JsonProperty("price") double price, @JsonProperty("type") String type) {
        this.type = type;
        this.price = price;
    }

    protected PriceDTO(Parcel in) {
        type = in.readString();
        price = in.readDouble();
    }

    public static final Creator<PriceDTO> CREATOR = new Creator<PriceDTO>() {
        @Override
        public PriceDTO createFromParcel(Parcel in) {
            return new PriceDTO(in);
        }

        @Override
        public PriceDTO[] newArray(int size) {
            return new PriceDTO[size];
        }
    };


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeDouble(price);
    }
}
