package br.android.ecommerce_marvel.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    private ImageButton bt_add_qtde, bt_diminuir_qtd;
    DbDatabaseComic dbDatabaseComic;
    private TextView tv_qtdecontador;
    private int contador = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhamento);

        dbDatabaseComic = new DbDatabaseComic(getApplicationContext());

        configuracao();
        resgatar();
        voltarActionBar();

    }
    //Referente ao quadrinho selecionado, carrega os dados desse item.
    private void resgatar(){
        Intent i = getIntent();
        comics = i.getParcelableExtra("comicSelecionado");

        //Carregar elementos do quadrinho selecionado
            loadingImage(comics.getThumbnail().getPortraitUncanny());
            loadingID(comics.getId());
            loadingTitle(comics.getTitle());
            loadingPage(comics.getPageCount());
            loadingDescr(comics.getDescription());
            loadingPrice(comics.getPrices().get(0).getPrice());

           //Adicionar (+1) na qtde
            bt_add_qtde.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contador ++;
                    tv_qtdecontador.setText(""+contador);
                }
            });

            //Diminuir (-1) na qtde
            bt_diminuir_qtd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contador --;
                    if(contador <= 0) {
                        contador = 1;
                        tv_qtdecontador.setText(""+contador);
                    }else
                        tv_qtdecontador.setText(""+contador);
                }
            });

            //Comprar adicionando ao carrinho e encaminhando para checkout
            btComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCarrinho(new Comics(comics.getId(), comics.getTitle(), comics.getDescription(), comics.getPageCount(), comics.getPrices().get(0).getPrice(), comics.getThumbnail().getPortraitFantastic(), comics.getRaro()), contador);
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                    startActivity(intent);
                    finish();

                }
            });

            //Apenas adicionando ao carrinho
            btcarrinho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCarrinho(new Comics(comics.getId(), comics.getTitle(), comics.getDescription(), comics.getPageCount(), comics.getPrices().get(0).getPrice(), comics.getThumbnail().getPortraitFantastic(), comics.getRaro()), contador);
                    Toast.makeText(getApplicationContext(),"Quadrinho adicionado ao carrinho!" ,Toast.LENGTH_SHORT).show();

                }
            });
    }

    //inserindo dados para adicionar ao carrinho seja em add ou buy
    private void addCarrinho(Comics c, int quant){
        dbDatabaseComic.atualizarQTDE(new Comics(c.getId(), c.getTitle(), c.getDescription(), c.getPageCount(), c.getPrice(), c.getThumb(),c.getRaro()), quant);

    }

    //inicializando butões e textos
    private void configuracao(){
        btComprar = findViewById(R.id.bt_buy);
        btcarrinho = findViewById(R.id.bt_carrinho);
        bt_add_qtde = findViewById(R.id.bt_qtdeDetails);
        tv_qtdecontador = findViewById(R.id.tv_qtdDetails);
        bt_diminuir_qtd = findViewById(R.id.ib_qtdDetailsMenos);
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

