package br.android.ecommerce_marvel.view;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import br.android.ecommerce_marvel.R;

import br.android.ecommerce_marvel.db.DbDatabaseComic;
import br.android.ecommerce_marvel.model.Comics;


public class DetailsComicsActivity extends AppCompatActivity {

    private Comics comics;
    private Button btComprar, btcarrinho;
    DbDatabaseComic dbDatabaseComic;
  //  private EditText qtd;
  //  DbDatabaseComic dbDatabaseComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhamento);

        btComprar = findViewById(R.id.bt_buy);
        btcarrinho = findViewById(R.id.bt_carrinho);
        dbDatabaseComic = new DbDatabaseComic(getApplicationContext());
        //qtd = findViewById(R.id.et_qtd);

        resgatar();
        voltarActionBar();

    }
    //Referente ao quadrinho selecionado, carrega os dados desse item.
    private void resgatar(){
        Intent i = getIntent();
        comics = i.getParcelableExtra("comicSelecionado");

            loadingImage(comics.getThumbnail().getPortraitUncanny());
            loadingID(comics.getId());
            loadingTitle(comics.getTitle());
            loadingPage(comics.getPageCount());
            loadingDescr(comics.getDescription());
            loadingPrice(comics.getPrices().get(0).getPrice());


            btComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   addCarrinho(comics.getId(), comics.getTitle(), comics.getDescription(), comics.getPageCount(), comics.getPrices().get(0).getPrice());
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                    //intent.putExtra("quantidade", qtd.getText().toString());
                    startActivity(intent);
                    finish();
                }
            });


            btcarrinho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  addCarrinho(comics.getId(), comics.getTitle(), comics.getDescription(), comics.getPageCount(), comics.getPrices().get(0).getPrice());
                    Toast.makeText(getApplicationContext(),"Quadrinho adicionado ao carrinho!" ,Toast.LENGTH_SHORT).show();

                }
            });
    }
    //adicionar ao carrinho seja em add ou buy
    private void addCarrinho(int id, String title, String descr, int page, double price){
        DbDatabaseComic dbDatabaseComic = new DbDatabaseComic(getApplicationContext());
        dbDatabaseComic.inserirDados(comics.getId(), comics.getTitle(), comics.getDescription(), comics.getPageCount(), comics.getPrices().get(0).getPrice());

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
    //carrega titulo do item selecionado
    private void loadingTitle(String title) {
        TextView tvTitle = findViewById(R.id.tv_TitleComic);
        tvTitle.setText(title);
    }
    //carrega preço do item selecionado
    private void loadingPrice(Double price) {
        TextView  tvPreco = findViewById(R.id.tv_Price);
        String preco = "Preço: $ " + String.format("%.2f", price);
        tvPreco.setText(preco);
    }
    //carrega numero de paginas do item selecionado
    private void loadingPage(int page) {
        TextView tvPage =  findViewById(R.id.tv_NumPaginas);
        String pagina = page + " páginas";
        tvPage.setText(pagina);
    }
    //carrega id do item selecionado
    private void loadingID(int id) {
        TextView tvId =  findViewById(R.id.tv_Id);
        String idComic = "ID: " + id;
        tvId.setText(idComic);
    }
    //carrega descrição do item selecionado
    private void loadingDescr(String descricao) {
        TextView tvDesc = findViewById(R.id.tv_Desc);
        if(descricao == null){
            descricao = "Indisponível.";
            tvDesc.setText(descricao);
        }
        tvDesc.setText(descricao);
    }
    //carrega imagem do item selecionado
    private void loadingImage(String imageUrl) {
        ImageView imageComic = findViewById(R.id.fotoImagemView);
            Glide.with(this)
                    .load(comics.getThumbnail().getPortraitUncanny())
                    .error(R.drawable.not_found)
                    .into(imageComic);
    }
}

