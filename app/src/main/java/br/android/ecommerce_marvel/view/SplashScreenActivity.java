package br.android.ecommerce_marvel.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.android.ecommerce_marvel.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mostraMain();
            }
        }, 3000);

    }

    private void mostraMain() {

        Intent intent = new Intent(SplashScreenActivity.this, ComicActivity.class);
        startActivity(intent);
        finish();
    }
}


