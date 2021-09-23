package com.example.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WwaActivity extends AppCompatActivity {

    int etp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wwa);

        fin();
        modiftxt();
        modiftxt1();
        modiftxt2();
        FlecheD();
        FlecheG();

    }


    private void fin() {
        findViewById(R.id.buttonClose).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }

    private void modiftxt(){


        if(etp == 0){
            ( (TextView) findViewById(R.id.textView_texte)).setText(R.string.Prealable);
        }else if(etp == 1){
            ( (TextView) findViewById(R.id.textView_texte)).setText(R.string.Empatage);
        }else if(etp == 2){
            ( (TextView) findViewById(R.id.textView_texte)).setText(R.string.Brassage);
        }else if(etp == 3){
            ( (TextView) findViewById(R.id.textView_texte)).setText(R.string.Filtrer);
        }

    }

    private void modiftxt1(){
        findViewById(R.id.imageButton_flecheD).setOnClickListener(new TextView.OnClickListener(){
            public void onClick(View v){
                if(etp < 3){
                    etp++;
                    modiftxt();
                }

            }
        });
    }

    private void modiftxt2(){
        findViewById(R.id.imageButton_flecheG).setOnClickListener(new TextView.OnClickListener(){
            public void onClick(View v){
                if (etp >0){
                    etp--;
                    modiftxt();
                }

            }
        });
    }

    private void FlecheD(){
        findViewById(R.id.imageButton_flecheD).setVisibility(View.VISIBLE);
    }

    private void FlecheG(){
        findViewById(R.id.imageButton_flecheG).setVisibility(View.VISIBLE);
    }

}
