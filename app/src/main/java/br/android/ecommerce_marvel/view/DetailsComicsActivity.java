package br.android.ecommerce_marvel.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;

import br.android.ecommerce_marvel.R;

import br.android.ecommerce_marvel.model.Comics;


public class DetailsComicsActivity extends AppCompatActivity {

    TextView tvTitle, tvDescr, tvPage, tvPreco, tvId;
    ImageView imageComic;
    private Comics comics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhamento);

       //Intent i = getIntent();
      //  comics = i.getParcelableExtra("comicSelecionado");  if(i.hasExtra("comicSelecionado")){

        configuracao();
        resgatar();
      voltarActionBar();

    }

    private void resgatar(){
        Intent i = getIntent();
        comics = i.getParcelableExtra("comicSelecionado");

        String titulo = comics.getTitle();
        String numPag = String.valueOf(comics.getPageCount());
        tvTitle.setText(titulo);
        tvPage.setText(numPag);


    }

  //  protected void onResume() {
  ///      super.onResume();
  //      voltarActionBar();
   // }
    private void voltarActionBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //mostrar botão
        getSupportActionBar().setHomeButtonEnabled(true); //ativar botão
        getSupportActionBar().setSubtitle("Detalhamento Comics");
        Intent i = new Intent(this, ComicActivity.class);
        startActivity(i);
        finish();
    }

    private void configuracao() {
        ImageView imageComic = findViewById(R.id.fotoImagemView);
        TextView tvTitle = findViewById(R.id.tv_TitleComic);
        TextView tvId =  findViewById(R.id.tv_Id);
        TextView  tvPreco = findViewById(R.id.tv_Price);
        TextView tvPage =  findViewById(R.id.tv_NumPaginas);
        TextView tvDesc = findViewById(R.id.tv_Desc);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadingImage(String url) {
        ImageView imageComic = findViewById(R.id.fotoImagemView);
        //CRIAR CONDIÇÃO PRA IMAGENS: http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available
        Glide.with(getApplicationContext())
                .load(comics.getThumbnail().getPortraitFantastic())
                .error(R.drawable.not_found)
                .into(imageComic);
    }


}

