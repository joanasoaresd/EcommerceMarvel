package br.android.ecommerce_marvel.db;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.ComicsDTO;
import br.android.ecommerce_marvel.model.Item;


public interface IDAO {
    void insertData(ComicsDTO c, int quantidade);

    public ArrayList<Item> loadData();
}
