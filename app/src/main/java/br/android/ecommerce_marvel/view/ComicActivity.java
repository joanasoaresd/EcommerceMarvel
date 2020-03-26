package br.android.ecommerce_marvel.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import br.android.ecommerce_marvel.R;
import br.android.ecommerce_marvel.model.Comics;

public class ComicActivity extends AppCompatActivity {

   // private Comics c;
    private TextView textViewTitle;
    private TextView textViewPage;
    private ImageButton imageComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitle = (TextView) findViewById(R.id.tv_TitleComic);
        textViewPage = (TextView) findViewById(R.id.tv_NumPaginas);

        addListenerOnButton();
    }

    public void addListenerOnButton(){

        imageComic = (ImageButton) findViewById(R.id.ib_ImagemComic);
        imageComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ComicActivity.this,"ImageComic is clicked!", Toast.LENGTH_SHORT).show();
            }
        });


        }

    }





}
