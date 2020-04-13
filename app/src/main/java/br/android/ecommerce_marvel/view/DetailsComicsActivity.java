package br.android.ecommerce_marvel.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




import br.android.ecommerce_marvel.R;

import br.android.ecommerce_marvel.model.Comics;


public class DetailsComicsActivity extends AppCompatActivity {

    private Comics comics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhamento);

        configuracao();
        Intent i = getIntent();
        //   Toast.makeText(DetailsComicsActivity.this, i.getIntExtra("position", 0) + "", Toast.LENGTH_SHORT).show();
        int position = i.getIntExtra("position", 0);


    }

    private void configuracao() {
        ImageView imageComic = findViewById(R.id.fotoImagemView);
        TextView tvId = findViewById(R.id.tv_Id);
        TextView tvTitle = findViewById(R.id.tv_TitleComic);
        TextView tvDesc = findViewById(R.id.tv_Desc);
        TextView tvPage = findViewById(R.id.tv_NumPaginas);
        TextView tvPreco = findViewById(R.id.tv_Price);
    }
}

