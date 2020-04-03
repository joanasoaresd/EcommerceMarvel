package br.android.ecommerce_marvel.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.controller.ComicService;
import br.android.ecommerce_marvel.model.Comics;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    private ArrayList<Comics> comics;
    private Context context;
    private Comics comic;
    public RequestOptions options;


    public ComicAdapter(ArrayList<Comics> comics, Context context) {
        this.comics = comics;
        this.context = context;
    }

    public ComicAdapter(ArrayList<Comics> comicsList) {
        this.comics = comicsList;
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

            viewHolder.tituloComic.setText(comic.getTitle());
            viewHolder.paginas.setText(Integer.toString(comic.getPageCount()) +" páginas");

            Glide.with(viewHolder.imageComic.getContext())
                .load(comic.getThumbnail().getPortraitUncanny())
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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageComic = (ImageView) itemView.findViewById(R.id.ib_ImagemComic);
            this.tituloComic = (TextView) itemView.findViewById(R.id.tv_TitleComic);
            this.paginas = (TextView) itemView.findViewById(R.id.tv_NumPaginas);


        }


    }
}
