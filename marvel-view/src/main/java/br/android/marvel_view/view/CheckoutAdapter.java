package br.android.marvel_view.view;

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

import br.android.marvel_view.R;
import br.android.model.db.DbDatabaseComic;
import br.android.model.model.Item;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    private ArrayList<Item> listItem;
    private Context context;
    DbDatabaseComic databaseComic;
    TextView total;

    public CheckoutAdapter() {
        super();
    }

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

        Item item = this.listItem.get(i);

        viewHolder.qty.setText("" + item.getQty());
        viewHolder.titleComic.setText(item.getComics().getTitle());
        viewHolder.price.setText(String.format("$ %.2f ", item.getComics().getPrice()));
        Glide.with(viewHolder.imageComic.getContext()).load(item.getComics().getThumb()).error(R.drawable.not_found).into(viewHolder.imageComic);
    }

    @Override
    public int getItemCount() {
        return this.listItem.size();
    }

    public double sumTotal(ArrayList<Item> items) {
        double sum = 0;
        for (int i = 0; i < items.size(); i++) {
            double value = items.get(i).getComics().getPrice() * items.get(i).getQty();
            sum = value + sum;

        }
        return sum;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageComic;
        private TextView titleComic;
        private TextView price;
        private EditText qty;
        private ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageComic = (ImageView) itemView.findViewById(R.id.iv_thumb_checkout);
            this.titleComic = (TextView) itemView.findViewById(R.id.tv_titlecheckout);
            this.price = (TextView) itemView.findViewById(R.id.tv_pricecheckout);
            this.qty = (EditText) itemView.findViewById(R.id.et_qtdeCheckout);
            this.delete = (ImageButton) itemView.findViewById(R.id.bt_delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseComic.deleteRecords(listItem.get(getAdapterPosition()).getComics().getId());
                    listItem = databaseComic.loadData();
                    total.setText(String.format("$ %.2f ", sumTotal(listItem)));
                    notifyDataSetChanged();

                    if (listItem.isEmpty()) {
                        databaseComic.loadData();
                        Context c = delete.getContext();
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
                        total.setText(String.format("$ %.2f ", sumTotal(listItem)));
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
