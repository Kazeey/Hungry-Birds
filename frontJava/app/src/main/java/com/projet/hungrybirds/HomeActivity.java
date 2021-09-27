package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projet.hungrybirds.utils.Functions;

public class HomeActivity extends AppCompatActivity {

    Context mContext = this;

    Functions cFunctions = new Functions();

    Button mButtonHome, mButtonUser, mButtonStruc, mButtonDisconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mButtonUser = (Button) findViewById(R.id.buttonUserMenu);
        mButtonDisconnect = (Button) findViewById(R.id.buttonDisconnectMenu);
        mButtonStruc = (Button) findViewById(R.id.buttonStructure);
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
            mButtonStruc.setVisibility(View.GONE);
            mButtonUser.setVisibility(View.GONE);
        }
        else
        {
            mButtonUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ListAccountsActivity.class);
                    startActivity(intent);
                }
            });
        }

        mButtonStruc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ListStructuresActivity.class);
                startActivity(intent);
            }
        });

        mButtonDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}