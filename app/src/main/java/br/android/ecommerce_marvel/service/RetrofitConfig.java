package br.android.ecommerce_marvel.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.model.ComicService;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.model.ComicResposta;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.model.Price;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static br.android.ecommerce_marvel.br.android.ecommerce_marvel.model.ComicService.BASE_URL;

public class RetrofitConfig extends AppCompatActivity {
    private static final String TAG = "COMICS";
    public static final String PUBLIC_KEY = "e41cecfc987a8e484709341de94276f4";
    public static final String HASH = "825bf5ade6cbd6a4d33c9e9b3f8c6304";
    public static final String TS = "1584109605";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

        ComicService service = retrofit.create(ComicService.class);
        final Call<ComicResposta> requestComics = service.getComics(TS,PUBLIC_KEY,HASH);
        requestComics.enqueue(new Callback<ComicResposta>() {

            @Override
            //OBTEVE UMA RESPOSTA
            public void onResponse(Call<ComicResposta> call, Response<ComicResposta> response) {
                if(!response.isSuccessful()){
                    Log.i("TAG", "Erro:  "+ response.code());
                }else{
                    ArrayList<Comics> resposta = response.body().getData().getResults();

                    //CHAMADA PARA RETORNAR DADOS DO JSON
                  for (int i = 0; i < resposta.size(); i++ ){

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
                              String.format("Preço: %.2f" , price));
                    }
                    }
               }

            @Override
            public void onFailure(Call<ComicResposta> call, Throwable t) {
                Log.e(TAG, "Erro: "+t.getMessage());
            }
        });
    }}
