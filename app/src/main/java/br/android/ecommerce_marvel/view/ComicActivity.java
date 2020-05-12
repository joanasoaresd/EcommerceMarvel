package br.android.ecommerce_marvel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.controller.ComicService;
import br.android.ecommerce_marvel.controller.RetrofitConfig;
import br.android.ecommerce_marvel.db.DbDatabaseComic;
import br.android.ecommerce_marvel.model.ComicDTO;
import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Item;
import retrofit2.Call;
import retrofit2.Callback;

import static br.android.ecommerce_marvel.controller.RetrofitConfig.HASH;
import static br.android.ecommerce_marvel.controller.RetrofitConfig.PUBLIC_KEY;
import static br.android.ecommerce_marvel.controller.RetrofitConfig.TS;

public class ComicActivity extends AppCompatActivity {

    private static final String TAG = "COMICS";
    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    ArrayList<Comics> comicsList;
    FloatingActionButton fab;
    ArrayList<Item> listaCheckout;
    DbDatabaseComic databaseComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_activity);

        logoActionBar();
        inicializar();
        obterDadosJson();

    }

    @Override
    public void onResume() {
        super.onResume();
        listaCheckout = databaseComic.carregarDados();
    }

    private void logoActionBar() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_marvel_simbolo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private void obterDadosJson() {
        ComicService comicService = RetrofitConfig.getRetrofit().create(ComicService.class);
        final Call<ComicDTO> requestComics = comicService.getComics(TS, PUBLIC_KEY, HASH);
        requestComics.enqueue(new Callback<ComicDTO>() {

            @Override
            public void onResponse(Call<ComicDTO> call, retrofit2.Response<ComicDTO> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "Erro: " + response.code());

                } else {
                    ArrayList<Comics> resposta = response.body().getData().getResults();
                    comicAdapter = new ComicAdapter(comicsList);
                    recyclerView.setAdapter(comicAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(ComicActivity.this, 2));
                    for (int i = 0; i < resposta.size(); i++) {
                        Comics c = resposta.get(i);
                        Log.i(TAG, "ID: " + c.getId());
                        Log.i(TAG, "Title: " + c.getTitle());
                        Log.i(TAG, "Descrição: " + c.getDescription());
                        Log.i(TAG, "Páginas: " + c.getPageCount());
                        Log.i(TAG, String.format("Preço: $ %.2f", c.getPrices().get(0).getPrice()));
                        Log.i(TAG, "Imagem: " + c.getThumbnail().getPortraitFantastic());
                        Log.i(TAG, "É Raro? " + c.getRaro());
                        comicsList.add(c);
                        comicAdapter.notifyDataSetChanged();
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (quantidadeItens() == 0) {
                                    Intent i = new Intent(getApplicationContext(), Carrinho_vazio.class);
                                    startActivity(i);

                                } else if (quantidadeItens() > 0) {
                                    Intent i = new Intent(getApplicationContext(), CheckoutActivity.class);
                                    startActivity(i);


                                }
                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<ComicDTO> call, Throwable t) {
                Log.e(TAG, "Erro: " + t.getMessage());

            }
        });
    }

    private int quantidadeItens() {
        int contador = 0;
        for (int i = 0; i < listaCheckout.size(); i++) {
            contador += 1;

        }
        System.out.println("cont " + contador);
        return contador;
    }

    public void inicializar() {
        this.databaseComic = DbDatabaseComic.getInstance(getApplicationContext());
        this.recyclerView = findViewById(R.id.rv_listacomics);
        this.fab = findViewById(R.id.fab);
        listaCheckout = new ArrayList<>();
        comicsList = new ArrayList<>();
    }
}









