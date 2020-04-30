package br.android.ecommerce_marvel.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.db.DbDatabaseComic;
import br.android.ecommerce_marvel.db.DbOpenHelper;
import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Item;


public class CheckoutActivity extends AppCompatActivity {

    private DbDatabaseComic databaseComic;
    private RecyclerView recyclerViewCheckout;
    private CheckoutAdapter checkoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);

        this.recyclerViewCheckout = findViewById(R.id.rv_checkout);

        voltarActionBar();
        databaseComic = new DbDatabaseComic(getApplicationContext());
        ArrayList<Item> listaCheckout = databaseComic.carregarDados();

        this.checkoutAdapter = new CheckoutAdapter(getApplicationContext(), listaCheckout, getIntent());
        this.recyclerViewCheckout.setAdapter(checkoutAdapter);
        this.recyclerViewCheckout.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
