package br.android.ecommerce_marvel.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.controller.ComicService;
import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.ComicDTO;
import br.android.ecommerce_marvel.view.ComicAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static br.android.ecommerce_marvel.controller.ComicService.BASE_URL;

public class RetrofitConfig extends AppCompatActivity {
    private static final String TAG = "COMICS";
    public static final String PUBLIC_KEY = "e41cecfc987a8e484709341de94276f4";
    public static final String HASH = "825bf5ade6cbd6a4d33c9e9b3f8c6304";
    public static final String TS = "1584109605";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ComicAdapter comicAdapter;
   // private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit);

       // recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // GETAPPLICATIONCONTEXT?
       // comicAdapter = new ComicAdapter();
       // recyclerView.setAdapter(comicAdapter);
       // recyclerView.setHasFixedSize(true);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        //recyclerView.setLayoutManager(layoutManager);

         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        obterDadosJson();
    }
    private void obterDadosJson(){
        ComicService service = retrofit.create(ComicService.class);
        final Call<ComicDTO> requestComics = service.getComics(TS,PUBLIC_KEY,HASH);
        requestComics.enqueue(new Callback<ComicDTO>() {

            @Override
            //OBTEVE UMA RESPOSTA
            public void onResponse(Call<ComicDTO> call, Response<ComicDTO> response) {
                if(!response.isSuccessful()){
                    Log.i("TAG", "Erro:  "+ response.code());


                }else{
                    ArrayList<Comics> resposta = response.body().getData().getResults();
                    //comicAdapter.adicionarListaComic(resposta);
                    for (int i = 0; i < resposta.size(); i++ ) {

                        String title = resposta.get(i).getTitle();
                        String description = resposta.get(i).getDescription();
                        int pageCount = resposta.get(i).getPageCount();
                        int id = resposta.get(i).getId();
                        //Thumbnail thumbnail = resposta.get(i).getThumbnail();
                        Double price = resposta.get(i).getPrices().get(0).getPrice();


                        Log.d(TAG, "onResponse: \n" +
                                "Título:" + title + "\n" +
                                "Descrição: " + description + "\n" +
                                "Número de Páginas: " + pageCount + "\n" +
                                "ID: " + id + "\n" +
                                String.format("Preço: %.2f", price));

                    }
               }
            }

            @Override
            public void onFailure(Call<ComicDTO> call, Throwable t) {
                Log.e(TAG, "Erro: "+t.getMessage());
            }
        });
    }}
