package br.android.ecommerce_marvel;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder().baseUrl("http://gateway.marvel.com:443/v1/public/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

}
