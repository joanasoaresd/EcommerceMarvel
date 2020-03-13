package br.android.ecommerce_marvel.br.android.ecommerce_marvel.Service;

        import br.android.ecommerce_marvel.br.android.ecommerce_marvel.models.ResponseDTO;
        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;


public interface ComicService {

    public static final String BASE_URL="http://gateway.marvel.com:443/v1/public/";
    //DEFINE CONTRATO DA API
    @GET("comics")
    Call<ResponseDTO> getComics(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);
}
