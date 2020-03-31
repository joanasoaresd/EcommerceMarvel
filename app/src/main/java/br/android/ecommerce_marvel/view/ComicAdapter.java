package br.android.ecommerce_marvel.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.model.Comics;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    private ArrayList<Comics> comics;
    private Context context;
    private Comics comic;


    public ComicAdapter(ArrayList<Comics> comics, Context context) {
        this.comics = comics;
        this.context = context;
    }

    //public ComicAdapter(Context context) {
    //    this.context = context;
   // }


    public void setComics(ArrayList<Comics> comics) {
        this.comics = comics;
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

            //TextView textView = viewHolder.tituloComic;
            //textView.setText(comic.getTitle());

            viewHolder.tituloComic.setText(comic.getTitle());
            viewHolder.paginas.setText(comic.getPageCount());



    }

    @Override
    public int getItemCount() {
        return this.comics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton imageComic;
        private TextView tituloComic, paginas;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageComic = (ImageButton) itemView.findViewById(R.id.ib_ImagemComic);
            this.tituloComic = (TextView) itemView.findViewById(R.id.tv_TitleComic);
            this.paginas = (TextView) itemView.findViewById(R.id.tv_NumPaginas);

        }


    }
}
