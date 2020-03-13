package br.android.ecommerce_marvel;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import br.android.ecommerce_marvel.br.android.ecommerce_marvel.Service.ComicService;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.models.Comics;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.models.ResponseDTO;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static br.android.ecommerce_marvel.br.android.ecommerce_marvel.Service.ComicService.BASE_URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_comic);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

        ComicService service = retrofit.create(ComicService.class);
        Call<ResponseDTO> requestComics = service.getComics();
    }
}
