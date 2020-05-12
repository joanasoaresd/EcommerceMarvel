package br.android.ecommerce_marvel.controller;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static br.android.ecommerce_marvel.controller.IComicService.BASE_URL;

public class RetrofitConfig {
    public static final String PUBLIC_KEY = "e41cecfc987a8e484709341de94276f4";
    public static final String HASH = "825bf5ade6cbd6a4d33c9e9b3f8c6304";
    public static final String TS = "1584109605";

    public static Retrofit getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit;
    }
}
