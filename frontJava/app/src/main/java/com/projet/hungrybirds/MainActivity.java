package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeActivity(View view)
    {
        //((TextView)findViewById(R.id.textConnexionPage)).setText("Teub");

        Intent intent = new Intent(this, GestionUserActivity.class);
        startActivity(intent);
    }
}