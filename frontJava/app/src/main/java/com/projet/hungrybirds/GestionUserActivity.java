package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projet.hungrybirds.utils.Functions;

public class GestionUserActivity extends AppCompatActivity {

    Functions cFunctions = new Functions();

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_user);

        boolean bVerification = cFunctions.verification(this);
        if(bVerification == false)
            cFunctions.redirect(mContext);

    }

    private View.OnClickListener mCheckLogin = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {

        }

    };

}