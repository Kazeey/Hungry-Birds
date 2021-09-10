package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.projet.hungrybirds.utils.Functions;

public class MainActivity extends AppCompatActivity {

    // Instanciation des inputs
    EditText mEditMail, mEditPassword;
    // Récupération de la classe Functions
    Functions oFunctionsClass = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToGestionUser(View view)
    {
        //Intent intent = new Intent(this, GestionUserActivity.class);
        //startActivity(intent);

        mEditMail = (EditText)findViewById(R.id.editMailConnexion);
        mEditPassword = (EditText)findViewById(R.id.editPasswordConnexion);

        String zMail = oFunctionsClass.getTextFromInput(mEditMail);
        String zPasword = oFunctionsClass.getTextFromInput(mEditPassword);
        boolean bMailConfirm = oFunctionsClass.checkMail(zMail);

        System.out.println(bMailConfirm);
    }

    // Méthode pour aller sur le noRegister
}