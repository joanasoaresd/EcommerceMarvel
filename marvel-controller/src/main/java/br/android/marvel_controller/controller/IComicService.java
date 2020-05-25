package br.android.marvel_controller.controller;

import br.android.model.model.ResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IComicService {

    public static final String BASE_URL = "https://gateway.marvel.com:443/v1/public/";

    @GET("comics")
    Call<ResponseDTO> getComics(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);
}
