package br.android.model.db;

import java.util.ArrayList;

import br.android.model.model.ComicsDTO;
import br.android.model.model.Item;


public interface IDAO {
    void insertData(ComicsDTO c, int quantidade);

    public ArrayList<Item> loadData();
}
