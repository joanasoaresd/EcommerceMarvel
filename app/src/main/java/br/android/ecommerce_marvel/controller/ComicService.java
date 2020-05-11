package br.android.ecommerce_marvel.controller;

        import br.android.ecommerce_marvel.model.ComicDTO;
        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;


public interface ComicService {

    public static final String BASE_URL="https://gateway.marvel.com:443/v1/public/";
    //DEFINE CONTRATO DA API
    @GET("comics")
    Call<ComicDTO> getComics(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);
}
