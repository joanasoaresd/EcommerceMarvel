package br.android.ecommerce_marvel.model;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {


    private int offset;
    private int limit;
    private int total;
    private int count;

    @JsonProperty(value = "results")
    private ArrayList<Comics> results;

    private static final int PORCENTAGEM_DOS_RAROS = (12 * 20) / 100;
    private int numeroDeQuadrinhosRaros;
    private ArrayList<Integer> listaNumerosRandom;

    public Data(int offset, int limit, int total, int count, ArrayList<Comics> results) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;

    }

    //fazer o random de 20 números(tamanho da lista) e os 12% sorteados serão indices dos raros, remover o que for saindo para n ir para o mesmo indice
    public int getRandomInt() {
        Random random = new Random();
        int max = results.size();
        //nextInt(20-0)+0
        int randomNumbers = random.nextInt(max);
        System.out.println("Random " + randomNumbers);
        return randomNumbers;

    }


    public ArrayList<Integer> getRandomNoRepeatingInteger() {
        this.listaNumerosRandom = new ArrayList<>();
        numeroDeQuadrinhosRaros = PORCENTAGEM_DOS_RAROS;

        while (listaNumerosRandom.size() < numeroDeQuadrinhosRaros) {
            int random = getRandomInt();

            if (!listaNumerosRandom.contains(random)) {
                listaNumerosRandom.add(random);
            }
        }
        System.out.println("LISTA DE NUMEROS: " + listaNumerosRandom);
        return listaNumerosRandom;
    }

    public void getListRandom() {
        listaNumerosRandom = getRandomNoRepeatingInteger();
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < listaNumerosRandom.size(); j++) {
                if (i == listaNumerosRandom.get(j)) {
                    results.get(i).setRaro(true);
                }
            }
        }
    }

    public int getNumeroDeQuadrinhosRaros() {
        return numeroDeQuadrinhosRaros;
    }

    public void setNumeroDeQuadrinhosRaros(int numeroDeQuadrinhosRaros) {
        this.numeroDeQuadrinhosRaros = numeroDeQuadrinhosRaros;
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
        getListRandom();
        return results;
    }

    @JsonProperty(value = "results")
    public void setResults(ArrayList<Comics> results) {
        this.results = results;
    }


}
