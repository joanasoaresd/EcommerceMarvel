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
import br.android.ecommerce_marvel.model.ComicsDTO;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    private ArrayList<ComicsDTO> comics;
    private ComicsDTO comic;


    public ComicsDTO getComicItem(int position) {
        return comics.get(position);

    }

    public ComicAdapter(ArrayList<ComicsDTO> comicsList) {
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

        comic = this.comics.get(i);

        viewHolder.titleComic.setText(comic.getTitle());
        viewHolder.price.setText(String.format("$ %.2f ", comic.getPrices().get(0).getPrice()));
        viewHolder.rare.setText(comic.showRare());

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
        private TextView titleComic, price, rare;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.imageComic = (ImageView) itemView.findViewById(R.id.ib_ImagemComic);
            this.titleComic = (TextView) itemView.findViewById(R.id.tv_TitleComic);
            this.price = (TextView) itemView.findViewById(R.id.tv_price_comic);
            this.rare = (TextView) itemView.findViewById(R.id.tv_rare);


            imageComic.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Context context = imageComic.getContext();
                    Intent i = new Intent(context, DetailsComicsActivity.class);
                    i.putExtra("selectedComic", getComicItem(getAdapterPosition()));
                    context.startActivity(i);
                }


            });

        }

    }
}