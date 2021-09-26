package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projet.hungrybirds.utils.Functions;

import java.sql.Array;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Context mContext = this;

    Functions cFunctions = new Functions();

    Button mButtonHome, mButtonUser, mButtonDisconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mButtonUser = (Button) findViewById(R.id.buttonUserMenu);
        mButtonDisconnect = (Button) findViewById(R.id.buttonDisconnectMenu);
        mButtonHome = (Button) findViewById(R.id.buttonHomeMenu);

        mButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HomeActivity.class);
                startActivity(intent);
            }
        });

        boolean bVerification = cFunctions.verification(this);
        if(!bVerification)
        {
            mButtonUser.setVisibility(View.GONE);
        }
        else
        {
            mButtonUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, GestionAccountsActivity.class);
                    startActivity(intent);
                }
            });
        }

        mButtonDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}