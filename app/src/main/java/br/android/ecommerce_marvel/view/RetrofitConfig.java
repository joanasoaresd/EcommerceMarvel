package br.android.ecommerce_marvel.view;

import android.util.Log;

import java.util.ArrayList;

import br.android.ecommerce_marvel.controller.ComicService;
import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.ComicDTO;
import br.android.ecommerce_marvel.model.Price;
import br.android.ecommerce_marvel.model.Thumbnail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static br.android.ecommerce_marvel.controller.ComicService.BASE_URL;

public class RetrofitConfig {
    private static final String TAG = "COMICS";
    public static final String PUBLIC_KEY = "e41cecfc987a8e484709341de94276f4";
    public static final String HASH = "825bf5ade6cbd6a4d33c9e9b3f8c6304";
    public static final String TS = "1584109605";
    ArrayList<Comics> aux = new ArrayList<>();


//retrofit
  public static Retrofit getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit;
    }


 //  public static ComicService comicService(){
  //   return getRetrofit().create(ComicService.class);
 //  }
  //  1 - retornar um arraylist de comics, que você vai colocar no adapter depois, ou
//2 - receber como parâmetro um arraylist de comics, que você vai preencher dentro do método, e colocar no adapter depois

}
