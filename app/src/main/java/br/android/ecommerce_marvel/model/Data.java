package br.android.ecommerce_marvel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    private int offset;
    private int limit;
    private int total;
    private int count;

    @JsonProperty(value = "results")
    private ArrayList<Comics> results;

    public Data(int offset, int limit, int total, int count, ArrayList<Comics> results) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;
    }

    public String typeResultsComics(){
        String types = " ";
        for (int i = 0; i <= results.size(); i++){
            if(i>0)
                types = ", ";
            types += results.get(i).getTitle();
        } return types;
    }

    public Data() {
        super();
    }

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

    @JsonProperty(value = "results")
    public ArrayList<Comics> getResults() {
        return results;
    }
    @JsonProperty(value = "results")
    public void setResults(ArrayList<Comics> results) {
        this.results = results;
    }


}
