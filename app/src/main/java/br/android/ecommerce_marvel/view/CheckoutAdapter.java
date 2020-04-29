package br.android.ecommerce_marvel.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;

import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Thumbnail;


public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    private Comics comic;
    private ArrayList<Comics> comics;
    private Context context;
    private Intent intent;



    public CheckoutAdapter(Context context, ArrayList<Comics> comics, Intent i) {
        this.context = context;
        this.comics = comics;
        this.intent = new Intent();
        this.intent = i;

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

        comic = this.comics.get(i);

        int quant = intent.getExtras().getInt("quantidade");
        viewHolder.qtde.setText(""+quant);

        String title = intent.getStringExtra("title");
        viewHolder.tituloComic.setText(title);

        double price = intent.getExtras().getDouble("price");
        viewHolder.preco.setText(String.format("$ %.2f " , price));

        String image = intent.getStringExtra("image");
        Glide.with(context).load(image).error(R.drawable.not_found).into(viewHolder.imageComic);

    }

    @Override
    public int getItemCount() {
        return this.comics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageComic;
        private TextView tituloComic, preco, qtde;
        //private EditText qtde;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageComic = (ImageView) itemView.findViewById(R.id.iv_thumb_checkout);
            this.tituloComic = (TextView) itemView.findViewById(R.id.tv_titlecheckout);
            this.preco = (TextView) itemView.findViewById(R.id.tv_pricecheckout);
            this.qtde = (TextView) itemView.findViewById(R.id.tv_qtdeCheckout);

        }


    }
}
