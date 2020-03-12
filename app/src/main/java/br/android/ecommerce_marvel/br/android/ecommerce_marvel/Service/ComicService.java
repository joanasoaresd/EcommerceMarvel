package br.android.ecommerce_marvel.br.android.ecommerce_marvel.Service;

        import br.android.ecommerce_marvel.br.android.ecommerce_marvel.model.Comics;
        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;


public interface ComicService {

    @GET("v1/public/comics")
    Call<Comics> getComics(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);
}
