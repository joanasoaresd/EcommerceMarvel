package br.android.ecommerce_marvel.br.android.ecommerce_marvel.models;

import java.util.ArrayList;

public class Data {

    private int offset;
    private int limit;
    private int total;
    private int count;
    private ArrayList<Comics> results;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Comics> getResults() {
        return results;
    }

    public void setResults(ArrayList<Comics> results) {
        this.results = results;
    }
}
