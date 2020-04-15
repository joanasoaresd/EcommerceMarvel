package br.android.ecommerce_marvel.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.ImageButton;


import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.controller.ComicService;
import br.android.ecommerce_marvel.model.ComicDTO;
import br.android.ecommerce_marvel.model.Comics;
import retrofit2.Call;
import retrofit2.Callback;
import static br.android.ecommerce_marvel.view.RetrofitConfig.HASH;
import static br.android.ecommerce_marvel.view.RetrofitConfig.PUBLIC_KEY;
import static br.android.ecommerce_marvel.view.RetrofitConfig.TS;

public class ComicActivity extends AppCompatActivity {

    private static final String TAG = "COMICS";
    private ImageButton imageComic;
    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    ArrayList<Comics> comicsList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_activity);
        logoActionBar();

        this.recyclerView = findViewById(R.id.rv_listacomics);

        obterDadosJson();


    }

    private void logoActionBar(){
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private void obterDadosJson() {
        ComicService comicService = RetrofitConfig.getRetrofit().create(ComicService.class);
        final Call<ComicDTO> requestComics = comicService.getComics(TS, PUBLIC_KEY, HASH);
        requestComics.enqueue(new Callback<ComicDTO>() {

            @Override
            //OBTEVE UMA RESPOSTA
            public void onResponse(Call<ComicDTO> call, retrofit2.Response<ComicDTO> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "Erro: " + response.code());

                } else {
                    ArrayList<Comics> resposta = response.body().getData().getResults();
                    comicAdapter = new ComicAdapter(comicsList);
                    recyclerView.setAdapter(comicAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(ComicActivity.this, 3));
                    Comics c = new Comics();
                    for (int i = 0; i < resposta.size(); i++) {
                        c = resposta.get(i);
                        Log.i(TAG, "ID: " + c.getId());
                        Log.i(TAG, "Title: " + c.getTitle());
                        Log.i(TAG, "Descrição: " + c.getDescription());
                        Log.i(TAG, "Páginas: " + c.getPageCount());
                        Log.i(TAG, String.format("Preço: %.2f", c.getPrices().get(0).getPrice()));
                        Log.i(TAG, "Imagem: " + c.getThumbnail().getPortraitFantastic());
                        Log.i(TAG, "URL: " + c.getUrl());
                        comicsList.add(c);
                        comicAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ComicDTO> call, Throwable t) {
                Log.e(TAG, "Erro: " + t.getMessage());

            }
        });
    }
}









