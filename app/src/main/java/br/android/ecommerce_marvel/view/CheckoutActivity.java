package br.android.ecommerce_marvel.view;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.db.DbDatabaseComic;
import br.android.ecommerce_marvel.db.DbOpenHelper;
import br.android.ecommerce_marvel.model.Comics;


public class CheckoutActivity extends AppCompatActivity {

    DbDatabaseComic databaseComic;
    RecyclerView recyclerViewCheckout;
    CheckoutAdapter checkoutAdapter;
    ArrayList<Comics> listaCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);

        voltarActionBar();

        databaseComic = new DbDatabaseComic(getApplicationContext());
        //carregar dados = getAllComics
        Cursor cursor = (Cursor) databaseComic.carregarDados();
        String[] nomeCampos = new String[] {DbOpenHelper.ID, DbOpenHelper.TITLE, DbOpenHelper.PRICE};
        int [] idViews = new int[]{R.id.tv_Id, R.id.tv_titlecheckout, R.id.tv_Price};

        this.checkoutAdapter = new CheckoutAdapter(getApplicationContext(), this.listaCheckout);
        this.recyclerViewCheckout = (RecyclerView) findViewById(R.id.rv_checkout);
        this.recyclerViewCheckout.setAdapter(checkoutAdapter);
        this.recyclerViewCheckout.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


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
}
