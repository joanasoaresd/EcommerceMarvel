package br.android.ecommerce_marvel.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.android.ecommerce_marvel.R;

import br.android.ecommerce_marvel.model.Comics;


public class DetailsComicsActivity extends AppCompatActivity {

    private Comics comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhamento);

        resgatar();
        voltarActionBar();

    }
    private void resgatar(){
        Intent i = getIntent();
            comics = i.getParcelableExtra("comicSelecionado");

            loadingImage(comics.getThumbnail().getPortraitUncanny());
            loadingID(comics.getId());
            loadingTitle(comics.getTitle());
            loadingPage(comics.getPageCount());
            loadingDescr(comics.getDescription());
            loadingPrice(comics.getPrices().get(0).getPrice());

    }

    private void voltarActionBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //mostrar botão
        getSupportActionBar().setHomeButtonEnabled(true); //ativar botão
        getSupportActionBar().setSubtitle("Detalhamento Comics");

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
        String preco = "Preço: $ " + String.format("%.2f", price);
        tvPreco.setText(preco);
    }

    private void loadingPage(int page) {
        TextView tvPage =  findViewById(R.id.tv_NumPaginas);
        String pagina = page + " páginas";
        tvPage.setText(pagina);
    }

    private void loadingID(int id) {
        TextView tvId =  findViewById(R.id.tv_Id);
        String idComic = "ID: " + id;
        tvId.setText(idComic);
    }

    private void loadingDescr(String descricao) {
        TextView tvDesc = findViewById(R.id.tv_Desc);
        if(descricao == null){
            descricao = "Indisponível.";
            tvDesc.setText(descricao);
        }
        tvDesc.setText(descricao);
    }

    private void loadingImage(String imageUrl) {
        ImageView imageComic = findViewById(R.id.fotoImagemView);
            Glide.with(this)
                    .load(comics.getThumbnail().getPortraitUncanny())
                    .error(R.drawable.not_found)
                    .into(imageComic);



    }

}

