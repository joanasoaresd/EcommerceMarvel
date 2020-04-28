package br.android.ecommerce_marvel.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.db.DbOpenHelper;
import br.android.ecommerce_marvel.model.Comics;
import android.content.Intent;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    private Comics comic;
    private ArrayList<Comics> comics;
    private Context context;
    private Intent intent;
    private String quant, price, title;


    public CheckoutAdapter(Context context, ArrayList<Comics> comics) {
        this.context = context;
        this.comics = comics;
    }

    public CheckoutAdapter(ArrayList<Comics> comics) {
        this.comics = comics;
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

       // this.quant = this.intent.getStringExtra("quantidade");
      //  viewHolder.qtde.setText(quant);

       viewHolder.preco.setText(String.format("PREÇO: $ %.2f " , comic.getPrice()));
       viewHolder.tituloComic.setText(comic.getTitle());

      //Glide.with(viewHolder.imageComic.getContext())
      //          .load(comic.getThumbnail().getPortraitFantastic())
      //          .error(R.drawable.not_found)
      //          .into(viewHolder.imageComic);

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
