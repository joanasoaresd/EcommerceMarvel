package br.android.ecommerce_marvel.view;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.db.DbDatabaseComic;
import br.android.ecommerce_marvel.model.Item;


public class CheckoutActivity extends AppCompatActivity {

    private DbDatabaseComic databaseComic;
    private RecyclerView recyclerViewCheckout;
    private CheckoutAdapter checkoutAdapter;
    private ArrayList<Item> listaCheckout = new ArrayList<>();
    private TextView valorTotal;
    private Button bt_finalizar_compra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);

        configurar();
        gerarLista();
        atualizarValorTotal(this.valorTotal);
        voltarActionBar();

    }

    private void gerarLista() {
        this.databaseComic = new DbDatabaseComic(getApplicationContext());
        this.listaCheckout = databaseComic.carregarDados();

        this.checkoutAdapter = new CheckoutAdapter(getApplicationContext(), this.listaCheckout, databaseComic, valorTotal);
        this.recyclerViewCheckout.setAdapter(checkoutAdapter);
        this.recyclerViewCheckout.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        bt_finalizar_compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckoutActivity.this, "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
                databaseComic.deletarTodosRegistros();
                gerarLista();
                valorTotal.setText(String.format("$ %.2f " , somaTotal()));
                checkoutAdapter.notifyDataSetChanged();
            }
        });
    }

    private void atualizarValorTotal(TextView valorTotal){
        valorTotal.setText(String.format("$ %.2f " , somaTotal()));
    }

    //valor total será qtde de itens * valor do item
    private double somaTotal(){
        double soma = 0;
        for (int i = 0; i < this.listaCheckout.size(); i++) {
            double valor = this.listaCheckout.get(i).getComics().getPrice() * this.listaCheckout.get(i).getQuantidade();
            soma = valor+soma;
           // this.valorTotal.setText(String.format("$ %.2f " , soma));

        } return soma;
    }

    private void configurar(){
        this.recyclerViewCheckout = findViewById(R.id.rv_checkout);
        this.bt_finalizar_compra = findViewById(R.id.bt_finalizar_compra);
        this.valorTotal = findViewById(R.id.tv_valor_total);
    }

    private void voltarActionBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //mostrar botão
        getSupportActionBar().setHomeButtonEnabled(true); //ativar botão
        getSupportActionBar().setSubtitle("Checkout Comics");
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
