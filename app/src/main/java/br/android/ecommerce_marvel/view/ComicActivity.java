package br.android.ecommerce_marvel.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.controller.ComicService;
import br.android.ecommerce_marvel.model.ComicDTO;
import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Data;
import retrofit2.Call;
import retrofit2.Callback;

import static br.android.ecommerce_marvel.controller.ComicService.BASE_URL;
import static br.android.ecommerce_marvel.view.RetrofitConfig.HASH;
import static br.android.ecommerce_marvel.view.RetrofitConfig.PUBLIC_KEY;
import static br.android.ecommerce_marvel.view.RetrofitConfig.TS;

public class ComicActivity extends AppCompatActivity {

    private ImageButton imageComic;
    private RecyclerView recyclerView;
    private ComicAdapter comicAdapter;
    ArrayList<Comics> comicsList;
    RetrofitConfig retrofitConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_listacomics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        retrofitConfig.obterDadosJson();
        comicAdapter = new ComicAdapter(new ArrayList<Comics>(), ComicActivity.this);
        recyclerView.setAdapter(comicAdapter);

        addListenerOnButton();
    }


      public void addListenerOnButton() {
        imageComic = (ImageButton) findViewById(R.id.ib_ImagemComic);
        imageComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ComicActivity.this, "ImageComic is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }


       }



