package com.projet.hungrybirds;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.projet.hungrybirds.actions.LoginAction;
import com.projet.hungrybirds.interfaces.VolleyCallback;
import com.projet.hungrybirds.utils.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    // Récupération des classes
    Functions cFunctionsClass = new Functions();
    LoginAction cLoginAction = new LoginAction();

    // Instanciation des variables liées aux composants
    EditText mEditMail, mEditPassword;
    Button mButtonConnexion, mButtonRegister, mButtonNoRegister;
    TextView mSetMessage;

    SharedPreferences sharedPreferences;

    private Context mContext;

    public String status;
    public int nbEssais;

    // Pour les durées seulement, Si 0 = 15min | Si 1 = 24h
    public int timeConnexion = 0;
    public int time;

    /**
     * Initialisation des variables au démarrage de l'activité
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onResume();
        setContentView(R.layout.activity_main);

        // Récupération du contexte
        mContext = this;

        // Récupération des boutons
        mButtonConnexion = (Button)findViewById(R.id.buttonConnexion);
        mButtonRegister = (Button)findViewById(R.id.buttonRegister);
        mButtonNoRegister = (Button)findViewById(R.id.buttonNoRegister);

        // Instanciation des onClicks
        mButtonConnexion.setOnClickListener(checkLogin);
        mButtonRegister.setOnClickListener(goToRegister);
        //mButtonNoRegister.setOnClickListener();

        // Récupération du texte servant à l'affichage du message
        mSetMessage = (TextView) findViewById(R.id.setMessage);

        // Instanciation du nombre d'essais pour une combinaison mail / password erronée
        nbEssais = 5;

        // Destruction des sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }

    /**
     * Vérification des inputs mail et password
     */
    private View.OnClickListener checkLogin = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            mEditMail = (EditText)findViewById(R.id.editMailConnexion);
            mEditPassword = (EditText)findViewById(R.id.editPasswordConnexion);

            // Récupération du texte des inputs
            String zMail = cFunctionsClass.getTextFromInput(mEditMail);
            String zPassword = cFunctionsClass.getTextFromInput(mEditPassword);

            // Vérification du format du mail
            boolean bMailConfirm = cFunctionsClass.checkMail(zMail);

            // Si le format du mail ne correspond pas au format attendu
            if (!bMailConfirm)
                cFunctionsClass.setMessage(mSetMessage, "Le format de l'email n'est pas correct, veuillez le modifier !", 0);

            // Si l'un des deux champs est vide
            if (zPassword.isEmpty())
                cFunctionsClass.setMessage(mSetMessage, "L'un des deux champs est vide, veuillez le renseigner !", 0);

            // Si le format du mail correspond au format normal d'une adresse, alors on vérifie si un utilisateur existe ou non
            if(bMailConfirm && !zPassword.isEmpty())
            {
                // On instancie le message à vide
                cFunctionsClass.setMessage(mSetMessage, getString(R.string.setMessage), 0);
                // On envoi les données à la fonction qui appelle l'API
                cLoginAction.sendLogin(mContext, zMail, zPassword, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) throws JSONException {
                        System.out.println(result);
                        // Si la réponse est un message d'erreur alors le JSON contient un champ "response", ce qui veut dire que la combinaison est incorrecte
                        if(result.has("response"))
                        {
                            // Si l'utilisateur a toujours ses 5 essais
                            if(nbEssais > 1)
                            {
                                // On décrément au fur et à mesure de ses erreurs, et on actualise le message
                                nbEssais = nbEssais - 1;
                                cFunctionsClass.setMessage(mSetMessage, "Nombre d'essais restants : ", nbEssais);
                            }
                            else // Sinon l'utilisateur n'a plus d'essais
                            {
                                String status = "0";
                                cLoginAction.changeStatusAccount(mContext, zMail, zPassword, status, new VolleyCallback() {
                                    @Override
                                    public void onSuccessResponse(JSONObject result) throws JSONException {
                                        System.out.println(result);
                                        // L'on appelle l'API pour bloquer son compte et on le lui signale
                                        cFunctionsClass.setMessage(mSetMessage, "Votre compte est bloqué, veuillez contacter un administrateur.", 0);
                                        return;
                                    }
                                });
                            }
                        }
                        else if (result.has("blocked")) // Si le JSON contient un champ "blocked" cela veut dire que le compte de l'utilisateur existe mais a été bloqué
                        {
                            nbEssais = 0;
                            cFunctionsClass.setMessage(mSetMessage, result.getString("blocked"), 0);
                            return;
                        }
                        else // Sinon l'utilisateur a toujours 5 essais
                        {
                            nbEssais = 5;

                            switch (result.getInt("role"))
                            {
                                case 0 :
                                    status = "Client";
                                    break;
                                case 1:
                                    status = "Association";
                                    break;
                                case 2:
                                    status = "Vendeur";
                                    break;
                            }

                            switch (timeConnexion)
                            {
                                case 0 :
                                    time = 15;
                                    break;
                                case 1 :
                                    time = 1440;
                                    break;
                            }

                            Calendar now = Calendar.getInstance();
                            now.add(Calendar.MINUTE, time);
                            Long timeDestruction = now.getTimeInMillis();

                            // Initialisation des SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putLong(getString(R.string.timeDestructionSavedKey), timeDestruction);
                            editor.putBoolean(getString(R.string.connectedSavedKey), true);
                            editor.putInt(getString(R.string.userIdSavedKey), result.getInt("id_utilisateur"));
                            editor.putString(getString(R.string.statusSavedKey), status);
                            editor.apply();

                            goToGestionUser();
                        }
                    }
                });
            }
        }
    };

    private View.OnClickListener goToRegister = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, RegisterActivity.class);
            startActivity(intent);
        }
    };

    public void goToGestionUser()
    {
        Intent intent = new Intent(this, GestionUserActivity.class);
        startActivity(intent);
    }

    // TODO : Décommenter la function
    /*
    private View.OnClickListener goToGestionUser = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, GestionUserActivity.class);
            startActivity(intent);
        }
    };
     */

}