package br.android.ecommerce_marvel.db;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.Comics;


public interface DAO {

    //DAO: contém os métodos utilizados para acessar o banco de dados.
    //interface criada para adicionar elemento no carrinho ou comprar, so muda o destino.
    void inserirDados(int id, String title, String descr, int page, double price);
    public ArrayList<Comics> carregarDados();
}