package com.droppages.pedrohenrique.afazeres.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.droppages.pedrohenrique.afazeres.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void goToMain(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
