package br.android.ecommerce_marvel.db;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Item;


public interface DAO {
    void inserirDados(Comics c, int quantidade);

    public ArrayList<Item> carregarDados();
}
