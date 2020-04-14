package br.android.ecommerce_marvel.view;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;

import br.android.ecommerce_marvel.R;

import br.android.ecommerce_marvel.model.Comics;


public class DetailsComicsActivity extends AppCompatActivity {

    private Comics comics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhamento);


        configuracao();
        Intent i = getIntent();
        //   Toast.makeText(DetailsComicsActivity.this, i.getIntExtra("position", 0) + "", Toast.LENGTH_SHORT).show();
       //int position = i.getIntExtra("position", 0);
         voltarActionBar();

    }

    private void voltarActionBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //mostrar botão
        getSupportActionBar().setHomeButtonEnabled(true); //ativar botão
        getSupportActionBar().setSubtitle("Detalhamento Comics");
    }

    private void configuracao() {
        ImageView imageComic = findViewById(R.id.fotoImagemView);
        TextView tvId = findViewById(R.id.tv_Id);
        TextView tvTitle = findViewById(R.id.tv_TitleComic);
        TextView tvDesc = findViewById(R.id.tv_Desc);
        TextView tvPage = findViewById(R.id.tv_NumPaginas);
        TextView tvPreco = findViewById(R.id.tv_Price);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}

