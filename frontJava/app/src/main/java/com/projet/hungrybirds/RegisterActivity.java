package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.projet.hungrybirds.utils.Functions;

public class RegisterActivity extends AppCompatActivity {

    Functions cFunctions = new Functions();

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
    }

    public void test()
    {
    // TODO : Selection entre les view pour l'alert box
    }
}