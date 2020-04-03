package br.android.ecommerce_marvel.model;

public class Thumbnail {
    private static final String PORTRAIT_XLARGE = "portrait_xlarge";
    private static final String PORTRAIT_FANTASTIC = "portrait_fantastic";

    private String path;
    private String extension;

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

    public String getPortraitXlarge(){
        return getPath() +"/" + PORTRAIT_XLARGE + "." + getExtension();
    }

    public String getPortraitFantastic(){
        return getPath() +"/" + PORTRAIT_FANTASTIC + "." + getExtension();
    }

}

