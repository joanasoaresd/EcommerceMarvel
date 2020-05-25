package br.android.marvel_view.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import br.android.marvel_controller.controller.IComicService;
import br.android.marvel_controller.controller.RetrofitConfig;
import br.android.marvel_controller.utils.LoggerUtils;
import br.android.marvel_view.R;
import br.android.model.db.DbDatabaseComic;
import br.android.model.model.ComicsDTO;
import br.android.model.model.Item;
import br.android.model.model.ResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;

import static br.android.marvel_controller.controller.RetrofitConfig.HASH;
import static br.android.marvel_controller.controller.RetrofitConfig.PUBLIC_KEY;
import static br.android.marvel_controller.controller.RetrofitConfig.TS;


public class ComicActivity extends AppCompatActivity {

    private static final String TAG = "COMICS";
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
                                if (listCheckout.isEmpty()) {
                                    Intent i = new Intent(getApplicationContext(), CartIsEmptyActivity.class);
                                    startActivity(i);

                                } else {
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

    public void initialize() {
        this.databaseComic = DbDatabaseComic.getInstance(getApplicationContext());
        this.recyclerView = findViewById(R.id.rv_listacomics);
        this.fab = findViewById(R.id.fab);
        listCheckout = new ArrayList<>();
        comicsList = new ArrayList<>();
    }
}