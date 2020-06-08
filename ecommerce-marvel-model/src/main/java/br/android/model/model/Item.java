package br.android.model.model;

public class Item {

    private ComicsDTO comics;
    private int qty;

    public Item(ComicsDTO comics, int qty) {
        this.comics = comics;
        this.qty = qty;
    }

    public ComicsDTO getComics() {
        return comics;
    }

    public void setComics(ComicsDTO comics) {
        this.comics = comics;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
