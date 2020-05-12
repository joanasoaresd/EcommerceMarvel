package br.android.ecommerce_marvel.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ThumbnailDTO implements Parcelable {

    private static final String PORTRAIT_FANTASTIC = "portrait_fantastic";
    private static final String PORTRAIT_UNCANNY = "portrait_uncanny";
    private static final String STARDART_FANTASTIC = "standard_fantastic";
    private static final String LANDSCAPE_INCREDIBLE = "landscape_incredible";

    private String path;
    private String extension;

    public ThumbnailDTO(@JsonProperty("path") String path, @JsonProperty("extension") String extension) {
        this.path = path;
        this.extension = extension;
    }


    protected ThumbnailDTO(Parcel in) {
        path = in.readString();
        extension = in.readString();
    }


    public static final Creator<ThumbnailDTO> CREATOR = new Creator<ThumbnailDTO>() {
        @Override
        public ThumbnailDTO createFromParcel(Parcel in) {
            return new ThumbnailDTO(in);
        }

        @Override
        public ThumbnailDTO[] newArray(int size) {
            return new ThumbnailDTO[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPortraitFantastic() {
        return getPath() + "/" + PORTRAIT_FANTASTIC + "." + getExtension();
    }

    public String getPortraitUncanny() {
        return getPath() + "/" + PORTRAIT_UNCANNY + "." + getExtension();
    }

    public String getStardartFantastic() {
        return getPath() + "/" + STARDART_FANTASTIC + "." + getExtension();
    }

    public String getLandscapeIncredible() {
        return getPath() + "/" + LANDSCAPE_INCREDIBLE + "." + getExtension();
    }


    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(extension);
    }
}