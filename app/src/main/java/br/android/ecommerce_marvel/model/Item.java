package br.android.ecommerce_marvel.model;

public class Item {

    private Comics comics;
    private int quantidade;

    public Item(Comics comics, int qtde) {
        this.comics = comics;
        this.quantidade = qtde;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
