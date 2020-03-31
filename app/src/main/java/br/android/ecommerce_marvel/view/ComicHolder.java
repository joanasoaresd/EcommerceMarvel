package br.android.ecommerce_marvel.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.android.ecommerce_marvel.R;

public class ComicHolder extends RecyclerView.ViewHolder {

    private TextView textTitle, textPage;
    private ImageButton imageComics;

    public ComicHolder(@NonNull View itemView) {
        super(itemView);

        this.textTitle = itemView.findViewById(R.id.tv_TitleComic);
        this.textPage = itemView.findViewById(R.id.tv_NumPaginas);
    }
}
