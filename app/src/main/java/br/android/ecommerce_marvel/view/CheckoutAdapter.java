package br.android.ecommerce_marvel.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.db.DbDatabaseComic;
import br.android.ecommerce_marvel.model.Item;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    private Item item;
    private ArrayList<Item> listItem;
    private Context context;
    DbDatabaseComic databaseComic;
    TextView total;


    public CheckoutAdapter(Context context, ArrayList<Item> itens, DbDatabaseComic database, TextView total) {
        this.context = context;
        this.listItem = itens;
        this.databaseComic = database;
        this.total = total;

    }

    @NonNull
    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_checkout, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutAdapter.ViewHolder viewHolder, int i) {

        item = this.listItem.get(i);

        viewHolder.qty.setText("" + item.getQty());
        viewHolder.titleComic.setText(item.getComics().getTitle());
        viewHolder.price.setText(String.format("$ %.2f ", item.getComics().getPrice()));
        Glide.with(viewHolder.imageComic.getContext()).load(item.getComics().getThumb()).error(R.drawable.not_found).into(viewHolder.imageComic);
    }

    @Override
    public int getItemCount() {
        return this.listItem.size();
    }

    public void sumTotal(TextView total) {
        double sum = 0;
        for (int i = 0; i < this.listItem.size(); i++) {
            double value = this.listItem.get(i).getComics().getPrice() * this.listItem.get(i).getQty();
            sum = value + sum;

        }
        total.setText(String.format("$ %.2f ", sum));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageComic;
        private TextView titleComic, price;
        private EditText qty;
        private ImageButton bt_del;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageComic = (ImageView) itemView.findViewById(R.id.iv_thumb_checkout);
            this.titleComic = (TextView) itemView.findViewById(R.id.tv_titlecheckout);
            this.price = (TextView) itemView.findViewById(R.id.tv_pricecheckout);
            this.qty = (EditText) itemView.findViewById(R.id.et_qtdeCheckout);
            bt_del = (ImageButton) itemView.findViewById(R.id.bt_delete);

            bt_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseComic.deleteRecords(listItem.get(getAdapterPosition()).getComics().getId());
                    listItem = databaseComic.loadData();
                    sumTotal(total);
                    notifyDataSetChanged();

                    if (listItem.size() == 0) {
                        databaseComic.loadData();
                        Context c = bt_del.getContext();
                        Intent i = new Intent(c, CartIsEmptyActivity.class);
                        c.startActivity(i);
                        ((Activity) c).finish();

                    }
                }
            });

            qty.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int numero = Integer.parseInt(qty.getText().toString());
                    if (numero <= 0) {
                        Toast.makeText(context, "Não é possível inserir esta quantidade.", Toast.LENGTH_LONG).show();
                        listItem = databaseComic.loadData();
                        notifyDataSetChanged();

                    } else {
                        databaseComic.updateList(listItem.get(getAdapterPosition()), numero);
                        listItem = databaseComic.loadData();
                        sumTotal(total);
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
