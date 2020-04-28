package br.android.ecommerce_marvel.view;

import android.content.Context;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;


import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.model.Comics;


public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    private ArrayList<Comics> comics;
    private Comics comic;

    public Comics getComicItem(int position){
        return comics.get(position);

    }

    public ComicAdapter(ArrayList<Comics> comicsList) {
        this.comics = comicsList;
    }


    @NonNull
    @Override
    public ComicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        //comic é um elemento da lista comics
        comic = this.comics.get(i);

        viewHolder.tituloComic.setText(comic.getTitle());
        viewHolder.paginas.setText(Integer.toString(comic.getPageCount()) + " páginas");

        Glide.with(viewHolder.imageComic.getContext())
                .load(comic.getThumbnail().getLandscapeIncredible())
                .error(R.drawable.not_found)
                .into(viewHolder.imageComic);
    }

    @Override
    public int getItemCount() {
        return this.comics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageComic;
        private TextView tituloComic, paginas;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.imageComic = (ImageView) itemView.findViewById(R.id.ib_ImagemComic);
            this.tituloComic = (TextView) itemView.findViewById(R.id.tv_TitleComic);
            this.paginas = (TextView) itemView.findViewById(R.id.tv_NumPaginas);


            imageComic.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Context context = imageComic.getContext();
                    Intent i = new Intent(context, DetailsComicsActivity.class);
                    //comic selecionado na posição
                    i.putExtra("comicSelecionado", getComicItem(getAdapterPosition()));
                    context.startActivity(i);
                }


            });

        }

    }
}
