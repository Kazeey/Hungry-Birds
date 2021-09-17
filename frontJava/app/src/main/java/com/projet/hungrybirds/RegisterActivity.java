package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projet.hungrybirds.utils.Functions;

public class RegisterActivity extends AppCompatActivity {

    Functions cFunctions = new Functions();

    Context mContext = this;

    Button mButtonReturn, mButtonRegister;

    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        status = extras.getString("activityDesign");
        switch (status)
        {
            case "Client" :
                setContentView(R.layout.activity_register_client);
                ((TextView)findViewById(R.id.textViewRegisterPageClient)).setText("Création d'un compte client :");
                break;
            case "Association" :
                setContentView(R.layout.activity_register_structure);
                ((TextView)findViewById(R.id.textViewRegisterPageStructure)).setText("Création d'un compte d'association :");
                break;
            case "Vendeur" :
                setContentView(R.layout.activity_register_structure);
                ((TextView)findViewById(R.id.textViewRegisterPageStructure)).setText("Création d'un compte vendeur :");
                break;
        }

        // Récupération des boutons
        mButtonReturn = (Button)findViewById(R.id.buttonReturn);
        mButtonRegister = (Button)findViewById(R.id.buttonRegisterActivity);

        // Instanciation des boutons
        mButtonReturn.setOnClickListener(returnToLogin);
        mButtonRegister.setOnClickListener(checkRegister);
    }

    private View.OnClickListener checkRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            
        }
    };

    private View.OnClickListener returnToLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
        }
    };


}