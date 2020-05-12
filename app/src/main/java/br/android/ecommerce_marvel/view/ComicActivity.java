package br.android.ecommerce_marvel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.controller.IComicService;
import br.android.ecommerce_marvel.controller.RetrofitConfig;
import br.android.ecommerce_marvel.db.DbDatabaseComic;
import br.android.ecommerce_marvel.model.ResponseDTO;
import br.android.ecommerce_marvel.model.ComicsDTO;
import br.android.ecommerce_marvel.model.Item;
import br.android.ecommerce_marvel.utils.LoggerUtils;
import retrofit2.Call;
import retrofit2.Callback;

import static br.android.ecommerce_marvel.controller.RetrofitConfig.HASH;
import static br.android.ecommerce_marvel.controller.RetrofitConfig.PUBLIC_KEY;
import static br.android.ecommerce_marvel.controller.RetrofitConfig.TS;

public class ComicActivity extends AppCompatActivity {

    private static final String TAG = "COMICS";
    private static final String TAAG = "CONTADOR";
    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    ArrayList<ComicsDTO> comicsList;
    FloatingActionButton fab;
    ArrayList<Item> listCheckout;
    DbDatabaseComic databaseComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        logoActionBar();
        initialize();
        getDataJson();

    }

    @Override
    public void onResume() {
        super.onResume();
        listCheckout = databaseComic.loadData();
    }

    private void logoActionBar() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_marvel_simbolo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private void getDataJson() {
        IComicService comicService = RetrofitConfig.getRetrofit().create(IComicService.class);
        final Call<ResponseDTO> requestComics = comicService.getComics(TS, PUBLIC_KEY, HASH);
        requestComics.enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, retrofit2.Response<ResponseDTO> response) {
                if (!response.isSuccessful()) {
                    LoggerUtils.log(TAG, "Erro: " + response.code());

                } else {
                    ArrayList<ComicsDTO> answers = response.body().getData().getResults();
                    comicAdapter = new ComicAdapter(comicsList);
                    recyclerView.setAdapter(comicAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(ComicActivity.this, 2));
                    for (int i = 0; i < answers.size(); i++) {
                        ComicsDTO c = answers.get(i);
                        LoggerUtils.log(TAG, "ID: " + c.getId());
                        LoggerUtils.log(TAG, "Title: " + c.getTitle());
                        LoggerUtils.log(TAG, "Description: " + c.getDescription());
                        LoggerUtils.log(TAG, "Page: " + c.getPageCount());
                        LoggerUtils.log(TAG, String.format("Price: $ %.2f", c.getPrices().get(0).getPrice()));
                        LoggerUtils.log(TAG, "Image: " + c.getThumbnail().getPortraitFantastic());
                        LoggerUtils.log(TAG, "Is Rare? " + c.getRare());
                        comicsList.add(c);
                        comicAdapter.notifyDataSetChanged();
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (qtyItems() == 0) {
                                    Intent i = new Intent(getApplicationContext(), CartIsEmptyActivity.class);
                                    startActivity(i);

                                } else if (qtyItems() > 0) {
                                    Intent i = new Intent(getApplicationContext(), CheckoutActivity.class);
                                    startActivity(i);


                                }
                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                LoggerUtils.log(TAG, "Erro: " + t.getMessage());

            }
        });
    }

    private int qtyItems() {
        int count = 0;
        for (int i = 0; i < listCheckout.size(); i++) {
            count += 1;

        }
        LoggerUtils.log(TAAG, "count " + count);
        return count;
    }

    public void initialize() {
        this.databaseComic = DbDatabaseComic.getInstance(getApplicationContext());
        this.recyclerView = findViewById(R.id.rv_listacomics);
        this.fab = findViewById(R.id.fab);
        listCheckout = new ArrayList<>();
        comicsList = new ArrayList<>();
    }
}