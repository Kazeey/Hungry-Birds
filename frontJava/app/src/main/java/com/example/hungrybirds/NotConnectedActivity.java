package com.example.hungrybirds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NotConnectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_connected);

        init();
    }

    private void init() {
        ecouteBtn();
        ecouteBtn1();
    }

    private void ecouteBtn() {
        findViewById(R.id.button_wwa).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(NotConnectedActivity.this,WwaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ecouteBtn1() {
        findViewById(R.id.button_form).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(NotConnectedActivity.this, ContactFormActivity.class);
                startActivity(intent);
            }
        });
    }


}