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
import br.android.ecommerce_marvel.model.ComicsDTO;

public class DetailsComicsActivity extends AppCompatActivity {

    private ComicsDTO comics;
    private Button bt_buy, bt_cart;
    private ImageButton bt_add_qty, bt_decrement_qty;
    DbDatabaseComic dbDatabaseComic;
    private TextView tv_qty_count;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dbDatabaseComic = DbDatabaseComic.getInstance(getApplicationContext());

        configuration();
        rescue();
        comeBackActionBar();

    }

    private void rescue() {
        Intent i = getIntent();
        comics = i.getParcelableExtra("selectedComic");

        loadingImage(comics.getThumbnail().getPortraitUncanny());
        loadingID(comics.getId());
        loadingTitle(comics.getTitle());
        loadingPage(comics.getPageCount());
        loadingDescr(comics.getDescription());
        loadingPrice(comics.getPrices().get(0).getPrice());

        bt_add_qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tv_qty_count.setText("" + count);
            }
        });

        bt_decrement_qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if (count <= 0) {
                    count = 1;
                    tv_qty_count.setText("" + count);
                } else
                    tv_qty_count.setText("" + count);
            }
        });

        bt_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(new ComicsDTO(comics.getId(), comics.getTitle(), comics.getDescription(), comics.getPageCount(), comics.getPrices().get(0).getPrice(), comics.getThumbnail().getPortraitFantastic(), comics.getRare()), count);
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
                finish();

            }
        });

        bt_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(new ComicsDTO(comics.getId(), comics.getTitle(), comics.getDescription(), comics.getPageCount(), comics.getPrices().get(0).getPrice(), comics.getThumbnail().getPortraitFantastic(), comics.getRare()), count);
                Toast.makeText(getApplicationContext(), "Quadrinho adicionado ao carrinho!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addToCart(ComicsDTO c, int quant) {
        dbDatabaseComic.updateQty(new ComicsDTO(c.getId(), c.getTitle(), c.getDescription(), c.getPageCount(), c.getPrice(), c.getThumb(), c.getRare()), quant);

    }

    private void configuration() {
        bt_buy = findViewById(R.id.bt_buy);
        bt_cart = findViewById(R.id.bt_cart);
        bt_add_qty = findViewById(R.id.bt_qtyDetails);
        tv_qty_count = findViewById(R.id.tv_qtyDetails);
        bt_decrement_qty = findViewById(R.id.ib_qtyDetailsMinus);
    }

    private void comeBackActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setSubtitle("Details Comics");

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        TextView tvPreco = findViewById(R.id.tv_Price);
        String preco = "Preço: $ " + String.format("%.2f", price);
        tvPreco.setText(preco);
    }

    private void loadingPage(int page) {
        TextView tvPage = findViewById(R.id.tv_NumPgeDetails);
        String pagina = page + " páginas";
        tvPage.setText(pagina);
    }

    private void loadingID(int id) {
        TextView tvId = findViewById(R.id.tv_Id);
        String idComic = "ID: " + id;
        tvId.setText(idComic);
    }

    private void loadingDescr(String description) {
        TextView tvDesc = findViewById(R.id.tv_Desc);
        if (description == null) {
            description = "Indisponível.";
            tvDesc.setText(description);
        }
        tvDesc.setText(description);
    }

    private void loadingImage(String imageUrl) {
        ImageView imageComic = findViewById(R.id.ImagemView);
        Glide.with(this)
                .load(comics.getThumbnail().getPortraitUncanny())
                .error(R.drawable.not_found)
                .into(imageComic);
    }
}

