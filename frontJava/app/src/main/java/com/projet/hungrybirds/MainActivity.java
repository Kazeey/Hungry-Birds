package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projet.hungrybirds.interfaces.VolleyCallback;
import com.projet.hungrybirds.utils.Functions;
import com.projet.hungrybirds.actions.LoginAction;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    // Récupération des classes
    Functions cFunctionsClass = new Functions();
    LoginAction cLoginAction = new LoginAction();

    // Instanciation des variables liées aux composants
    EditText mEditMail, mEditPassword;
    Button mButtonConnexion;
    TextView mSetMessage;

    private Context mContext;

    public int nbEssais = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onResume();
        setContentView(R.layout.activity_main);

        // Récupération du contexte
        mContext = this;

        // Récupération des boutons
        mButtonConnexion = (Button)findViewById(R.id.buttonConnexion);
        mButtonConnexion.setOnClickListener(mCheckLogin);

        // Récupération du texte servant à l'affichage du message
        mSetMessage = (TextView) findViewById(R.id.textConnexionPage);
    }

    private View.OnClickListener mCheckLogin = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            mEditMail = (EditText)findViewById(R.id.editMailConnexion);
            mEditPassword = (EditText)findViewById(R.id.editPasswordConnexion);

            // Récupération du texte des inputs
            String zMail = cFunctionsClass.getTextFromInput(mEditMail);
            String zPasword = cFunctionsClass.getTextFromInput(mEditPassword);

            // Vérification du format du mail
            boolean bMailConfirm = cFunctionsClass.checkMail(zMail);


            // Si le format du mail correspond au format normal d'une adresse, alors on check dans le back si l'utilisateur existe
            if(bMailConfirm)
            {
                cLoginAction.sendLogin(mContext, zMail, zPasword, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {
                        if(result.has("response"))
                        {

                        }

                        if(nbEssais > 1)
                        {
                            nbEssais = nbEssais - 1;
                            cFunctionsClass.setMessage(mSetMessage, "Nombre d'essais restants : ", nbEssais);
                        }
                        else
                        {
                            //cLoginAction.blockAccount(mContext, zMail);
                            cFunctionsClass.setMessage(mSetMessage, "Votre compte est bloqué, veuillez contacter un administrateur.", 0);
                            return;
                        }
                    }
                });

                // Si l'utilisateur existe, alors il se connecte, sinon un message d'erreur s'affichera
            }
        }
    };

    public void goToGestionUser(View view)
    {
        Intent intent = new Intent(this, GestionUserActivity.class);
        startActivity(intent);
    }

    // Méthode pour aller sur le noRegister
}