package br.android.ecommerce_marvel.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.br.android.ecommerce_marvel.model.Comics;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Comics> comics;
    private Context context;

    public MyAdapter(ArrayList<Comics> comics, Context context) {
        this.comics = comics;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_comic, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Comics comic = comics.get(i);

        viewHolder.textViewTitle.setText(comic.getTitle());
        viewHolder.textViewId.setText(comic.getId());
        viewHolder.textViewDesc.setText(comic.getDescription());
        viewHolder.textViewCountPage.setText(comic.getPageCount());
        viewHolder.textViewPrice.setText(comic.getPrices());
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewId;
        public TextView textViewPrice;
        public TextView textViewCountPage;
        public TextView textViewDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.TextViewTitle);
            textViewDesc = (TextView) itemView.findViewById(R.id.TextViewDesc);
            textViewPrice = (TextView) itemView.findViewById(R.id.TextViewPrice);
            textViewCountPage = (TextView) itemView.findViewById(R.id.TextViewCountPage);
            textViewId = (TextView) itemView.findViewById(R.id.TextViewId);
        }
    }
}
