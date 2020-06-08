package br.android.marvel_app.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.android.ecommerce_marvel.view.SplashScreenActivity;

public class main  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(getApplicationContext(), SplashScreenActivity.class);
        startActivity(i);



    }
}
