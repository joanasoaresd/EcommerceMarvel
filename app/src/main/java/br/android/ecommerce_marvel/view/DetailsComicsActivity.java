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

    private Comics comics;
    private  ImageView imageComic;
    private TextView tvTitle, tvId, tvPreco, tvPage, tvDesc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhamento);

        configuracao();
        resgatar();
        voltarActionBar();

    }
    private void resgatar(){
        Intent i = getIntent();
        comics =  i.getParcelableExtra("comicSelecionado");
       loadingID(comics.getId());

       // Glide.with(getApplicationContext()).load(comics.getThumbnail().getPortraitFantastic()).into(imageComic);

        loadingTitle(comics.getTitle());
        loadingPage(comics.getPageCount());
       loadingDescr(comics.getDescription());
      // loadingPrice(comics.getPrices().get(0).getPrice());
      //  loadingImage(comics.getThumbnail().getPortraitFantastic());



    }

    private void voltarActionBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //mostrar botão
        getSupportActionBar().setHomeButtonEnabled(true); //ativar botão
        getSupportActionBar().setSubtitle("Detalhamento Comics");

    }

    private void configuracao() {
         imageComic = findViewById(R.id.fotoImagemView);
         tvTitle = findViewById(R.id.tv_TitleComic);
         tvId =  findViewById(R.id.tv_Id);
         tvPreco = findViewById(R.id.tv_Price);
         tvPage =  findViewById(R.id.tv_NumPaginas);
         tvDesc = findViewById(R.id.tv_Desc);


    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadingTitle(String title) {
        TextView tvTitle = findViewById(R.id.tv_TitleComic);
        tvTitle.setText(title);
    }

    private void loadingPrice(Double price) {
        TextView  tvPreco = findViewById(R.id.tv_Price);
        String preco = "Preço: R$"+String.format("%.2f", price);
        tvPreco.setText(preco);
    }

    private void loadingPage(int page) {
        TextView tvPage =  findViewById(R.id.tv_NumPaginas);
        String pagina = page + "páginas";
        tvPage.setText(pagina);
    }

    private void loadingID(int id) {
        TextView tvId =  findViewById(R.id.tv_Id);
        String idComic = "ID: "+ id;
        tvId.setText(idComic);
    }

    private void loadingDescr(String descricao) {
        TextView tvDesc = findViewById(R.id.tv_Desc);

        if(descricao == null){
            descricao = "Descrição Indisponivel.";
        }

        tvDesc.setText(descricao);
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

