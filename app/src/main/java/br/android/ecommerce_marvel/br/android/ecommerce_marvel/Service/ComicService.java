package br.android.ecommerce_marvel.br.android.ecommerce_marvel.Service;

import br.android.ecommerce_marvel.br.android.ecommerce_marvel.model.ComicsDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ComicService {

    @GET("v1/public/comics")
    Call<ComicsDTO> getComics(@Query() String comics);
}
