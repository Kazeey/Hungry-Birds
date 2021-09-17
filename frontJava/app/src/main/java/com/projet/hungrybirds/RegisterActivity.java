package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.projet.hungrybirds.utils.Functions;

public class RegisterActivity extends AppCompatActivity {

    Functions cFunctions = new Functions();

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        switch (extras.getString("activityDesign"))
        {
            case "Client" :
                setContentView(R.layout.activity_register_client);
                break;
            case "Association" :
            case "Vendeur" :
                setContentView(R.layout.activity_register_structure);
                break;
        }
    }

    public void test()
    {

    }
}