package br.android.ecommerce_marvel.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
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
    private ArrayList<Item> listaItem;
    private Context context;
    DbDatabaseComic databaseComic;



    public CheckoutAdapter(Context context, ArrayList<Item> itens, DbDatabaseComic database) {
        this.context = context;
        this.listaItem = itens;
        this.databaseComic = database;


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

        item = this.listaItem.get(i);

        viewHolder.qtde.setText(""+item.getQuantidade());
        viewHolder.tituloComic.setText(item.getComics().getTitle());
        viewHolder.preco.setText(String.format("$ %.2f " , item.getComics().getPrice()));
        Glide.with(viewHolder.imageComic.getContext()).load(item.getComics().getThumb()).error(R.drawable.not_found).into(viewHolder.imageComic);
    }

    @Override
    public int getItemCount() {
        return this.listaItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageComic;
        private TextView tituloComic, preco;
        private EditText qtde;
        private ImageButton bt_del;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageComic = (ImageView) itemView.findViewById(R.id.iv_thumb_checkout);
            this.tituloComic = (TextView) itemView.findViewById(R.id.tv_titlecheckout);
            this.preco = (TextView) itemView.findViewById(R.id.tv_pricecheckout);
            this.qtde = (EditText) itemView.findViewById(R.id.et_qtdeCheckout);
            bt_del = (ImageButton) itemView.findViewById(R.id.bt_apagar);

            bt_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseComic.deletarRegistros(listaItem.get(getAdapterPosition()).getComics().getId());
                   // databaseComic.atualizarLista(listaItem.get(getAdapterPosition()));
                    listaItem = databaseComic.carregarDados();
                    notifyDataSetChanged();
                     if(listaItem.size() == 0) {

                         //total = 0 set total...

                     }
                     }
            });

            qtde.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    int numero = Integer.parseInt(qtde.getText().toString());
                    if(numero <= 0){
                        Toast.makeText(context, "Não é possível inserir esta quantidade.", Toast.LENGTH_LONG).show();
                        listaItem = databaseComic.carregarDados();
                        notifyDataSetChanged();

                        //atualizar valor da quantidade

                    } else {
                        for(int i = 0; i < listaItem.size(); i++) {
                            databaseComic.atualizarQTDE(listaItem.get(i).getComics(), numero);
                            listaItem = databaseComic.carregarDados();
                            notifyDataSetChanged();
                        }
                    }
                }
            });

        }
    }
}
