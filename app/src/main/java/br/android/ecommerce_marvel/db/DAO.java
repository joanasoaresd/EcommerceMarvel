package br.android.ecommerce_marvel.db;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Item;


public interface DAO {

    //DAO: contém os métodos utilizados para acessar o banco de dados.
    //interface criada para adicionar elemento no carrinho ou comprar, so muda o destino.
    void inserirDados(Comics c, int quantidade);

    public ArrayList<Item> carregarDados();
}
