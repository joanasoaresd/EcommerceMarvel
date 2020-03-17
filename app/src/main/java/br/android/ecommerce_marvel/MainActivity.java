package br.android.ecommerce_marvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.android.ecommerce_marvel.br.android.ecommerce_marvel.Service.ComicService;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.models.Comics;
//import br.android.ecommerce_marvel.br.android.ecommerce_marvel.models.ResponseDTO;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.models.ResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static br.android.ecommerce_marvel.br.android.ecommerce_marvel.Service.ComicService.BASE_URL;

public class MainActivity extends AppCompatActivity {
    private static final  String TAG = "suemar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_comic_fragment);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

        ComicService service = retrofit.create(ComicService.class);
        //FALTA OS PARAMETROS DO GETCOMICS
        Call<ResponseDTO> requestComics = service.getComics();

        requestComics.enqueue(new Callback<ResponseDTO>() {
            @Override
            //OBTEVE UMA RESPOSTA
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(!response.isSuccessful()){
                    Log.i("TAG", "Erro:  "+ response.code());
                }else{
                    //REQUISIÇÃO RETORNOU COM SUCESSO

                    ResponseDTO resp = response.body();

                    //FALTA CONSERTAR O COMIC PARA RETORNAR ESSES DADOS
                    for(Comics c: resp.comic){
                        Log.i(TAG, String.format("%d: %s: %s:" + c.getId(), c.getTitle(), c.getDescription()));

                        //for(Price p: c.prices){
                          // Log.i(TAG,"%s: %d " +  p.getType(), p.getPrice());
                        }
                    }

                    Log.i(TAG, "-----------------------");
               // }
            }
            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                Log.e(TAG, "Erro: "+t.getMessage());
            }
        });
    }
}
