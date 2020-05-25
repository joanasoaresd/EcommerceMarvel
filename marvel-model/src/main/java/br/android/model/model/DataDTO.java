package br.android.model.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Random;

import br.android.marvel_controller.utils.LoggerUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDTO {


    private int offset;
    private int limit;
    private int total;
    private int count;

    @JsonProperty(value = "results")
    private ArrayList<ComicsDTO> results;

    private static final int PERCENT_RARE = (12 * 20) / 100;
    private static final String TAG = "RANDOM";
    private int qtyRare;
    private ArrayList<Integer> listNumbersRandom;

    public DataDTO(int offset, int limit, int total, int count, ArrayList<ComicsDTO> results) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;

    }

    public int getRandomInt() {
        Random random = new Random();
        int max = results.size();
        int randomNumbers = random.nextInt(max);
        LoggerUtils.log(TAG, "NUMBERS RANDOM: " + randomNumbers);
        return randomNumbers;

    }


    public ArrayList<Integer> getRandomNoRepeatingInteger() {
        this.listNumbersRandom = new ArrayList<>();
        qtyRare = PERCENT_RARE;

        while (listNumbersRandom.size() < qtyRare) {
            int random = getRandomInt();

            if (!listNumbersRandom.contains(random)) {
                listNumbersRandom.add(random);
            }
        }
        return listNumbersRandom;
    }

    public void getListRandom() {
        listNumbersRandom = getRandomNoRepeatingInteger();
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < listNumbersRandom.size(); j++) {
                if (i == listNumbersRandom.get(j)) {
                    results.get(i).setRare(true);
                }
            }
        }
    }

    public int getNumeroDeQuadrinhosRaros() {
        return qtyRare;
    }

    public void setNumeroDeQuadrinhosRaros(int numeroDeQuadrinhosRaros) {
        this.qtyRare = numeroDeQuadrinhosRaros;
    }

    public DataDTO() {
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
    public ArrayList<ComicsDTO> getResults() {
        getListRandom();
        return results;
    }

    @JsonProperty(value = "results")
    public void setResults(ArrayList<ComicsDTO> results) {
        this.results = results;
    }


}
