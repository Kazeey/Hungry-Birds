package com.example.hungrybirds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Bonjour";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.connexionButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                System.out.println("test");
            }
        });
    }
/*
    public void changeActivity(View view)
    {
        Intent intent = new Intent(this, GestionUser.class);
        EditText editTextMail = (EditText) findViewById(R.id.editConnectionMailAddress);
        EditText editTextPassword = (EditText) findViewById(R.id.editConnectionPassword);
        String message = editTextMail.getText().toString() + " " + editTextPassword.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
*/
}