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
    private ArrayList<Item> listCheckout;
    private TextView totalSum;
    private Button btPurchaseCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        configuration();
        generateList();
        comeBackActionBar();

    }

    @Override
    public void onResume() {
        super.onResume();
        this.listCheckout = databaseComic.loadData();
    }

    private void generateList() {
        this.databaseComic = DbDatabaseComic.getInstance(getApplicationContext());
        listCheckout = new ArrayList<>();
        onResume();
        this.checkoutAdapter = new CheckoutAdapter(getApplicationContext(), this.listCheckout, this.databaseComic, this.totalSum);
        this.recyclerViewCheckout.setAdapter(checkoutAdapter);
        this.recyclerViewCheckout.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.totalSum.setText(String.format("$ %.2f ", checkoutAdapter.sumTotal(listCheckout)));

        btPurchaseCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckoutActivity.this, "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
                databaseComic.deleteAllRecords();
                generateList();
                checkoutAdapter.notifyDataSetChanged();
                Intent intent = new Intent(getApplicationContext(), CompletedPurchaseActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void configuration() {
        this.recyclerViewCheckout = findViewById(R.id.rv_checkout);
        this.btPurchaseCompleted = findViewById(R.id.bt_purchase_completed);
        this.totalSum = findViewById(R.id.tv_value_total);

    }

    private void comeBackActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setSubtitle("Checkout Comics");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
