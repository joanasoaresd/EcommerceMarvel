package br.android.ecommerce_marvel.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.android.ecommerce_marvel.R;

public class Carrinho_vazio extends AppCompatActivity {

    Button bt_pagina_inicial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_vazio2);

        voltarActionBar();

        bt_pagina_inicial = findViewById(R.id.bt_voltar_inicial);
        bt_pagina_inicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ComicActivity.class);
                startActivity(i);

            }
        });

    }

    private void voltarActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //mostrar botão
        getSupportActionBar().setHomeButtonEnabled(true); //ativar botão
        // getSupportActionBar().setSubtitle("Detalhamento Comics");

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
