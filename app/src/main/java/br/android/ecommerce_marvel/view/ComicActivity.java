package br.android.ecommerce_marvel.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Response;


import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.controller.ComicService;
import br.android.ecommerce_marvel.model.ComicDTO;
import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Data;
import retrofit2.Call;
import retrofit2.Callback;
import static br.android.ecommerce_marvel.view.RetrofitConfig.HASH;
import static br.android.ecommerce_marvel.view.RetrofitConfig.PUBLIC_KEY;
import static br.android.ecommerce_marvel.view.RetrofitConfig.TS;

public class ComicActivity extends AppCompatActivity {
    private static final String TAG = "COMICS";
    private ImageButton imageComic;
    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    ArrayList<Comics> comicsList = new ArrayList<>();
    //RetrofitConfig retrofitConfig = new RetrofitConfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_listacomics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


       // retrofitConfig.obterDadosJson();
        comicAdapter = new ComicAdapter(comicsList);
        comicAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(comicAdapter);

        obterDadosJson();

       // addListenerOnButton();
    } private void obterDadosJson(){
        ComicService comicService =  RetrofitConfig.getRetrofit().create(ComicService.class);

            final Call<ComicDTO> requestComics = comicService.getComics(TS,PUBLIC_KEY,HASH);
            requestComics.enqueue(new Callback<ComicDTO>() {
                /**
                 * Invoked for a received HTTP response.
                 * <p>
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call {@link Response#isSuccessful()} to determine if the response indicates success.
                 *
                 * @param call
                 * @param response
                 */
                @Override
                //OBTEVE UMA RESPOSTA
                public void onResponse(Call<ComicDTO> call, retrofit2.Response<ComicDTO> response) {
                    if (!response.isSuccessful()) {
                        Log.i(TAG, "Erro: " + response.code());

                    } else {
                        ArrayList<Comics> resposta = response.body().getData().getResults();
                        for (int i = 0; i < resposta.size(); i++) {
                            Comics c = resposta.get(i);
                           // Log.i(TAG, "ID: " + c.getId());
                            Log.i(TAG, "Title: " + c.getTitle());
                            //Log.i(TAG, "Descrição: " + c.getDescription());
                            Log.i(TAG, "Páginas: " + c.getPageCount());
                           // Log.i(TAG, String.format("Preço: %.2f", c.getPrices().get(0).getPrice()));
                            //Log.i(TAG, "Preço: " + c.getPrices().get(0).getPrice());
                            //Log.i(TAG, "Imagem: " + c.getThumbnail());
                            comicsList.add(c);
                            comicAdapter.notifyDataSetChanged();
                        }
                    }
                }


                /**
                 * Invoked when a network exception occurred talking to the server or when an unexpected
                 * exception occurred creating the request or processing the response.
                 *
                 * @param call
                 * @param t
                 */
                @Override
                public void onFailure(Call<ComicDTO> call, Throwable t) {
                    Log.e(TAG, "Erro: "+ t.getMessage());

                }
            });
            }
           // return comicsList;
}







  //    public void addListenerOnButton() {
  //      imageComic = (ImageButton) findViewById(R.id.ib_ImagemComic);
  //      imageComic.setOnClickListener(new View.OnClickListener() {
  //          @Override
  //          public void onClick(View v) {

   //             Toast.makeText(ComicActivity.this, "ImageComic is clicked!", Toast.LENGTH_SHORT).show();
   //         }
  //      });
  //  }







