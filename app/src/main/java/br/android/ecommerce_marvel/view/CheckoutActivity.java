package br.android.ecommerce_marvel.view;
import android.content.Intent;
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
    private ArrayList<Item> listaCheckout;
    private TextView valorTotal;
    private Button bt_finalizar_compra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);

        configurar();
        gerarLista();
        voltarActionBar();

    }

    @Override
    public void onResume(){
        super.onResume();
        this.listaCheckout = databaseComic.carregarDados();
    }

    private void gerarLista() {
        this.databaseComic =  DbDatabaseComic.getInstance(getApplicationContext());
        listaCheckout = new ArrayList<>();
        onResume();
        this.checkoutAdapter = new CheckoutAdapter(getApplicationContext(), this.listaCheckout, this.databaseComic, this.valorTotal);
        this.recyclerViewCheckout.setAdapter(checkoutAdapter);
        this.recyclerViewCheckout.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.checkoutAdapter.somaTotal(this.valorTotal);

        bt_finalizar_compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckoutActivity.this, "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
                databaseComic.deletarTodosRegistros();
                gerarLista();
                checkoutAdapter.notifyDataSetChanged();
                Intent intent = new Intent(getApplicationContext(), CompraFinalizada.class);
                startActivity(intent);
                finish();
            }
        });
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
