package com.projet.hungrybirds;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    // Récupération des classes
    Functions cFunctionsClass = new Functions();
    LoginAction cLoginAction = new LoginAction();

    // Instanciation des variables liées aux composants
    EditText mEditMail, mEditPassword;
    Button mButtonConnexion, mButtonRegister, mButtonNoRegister;
    TextView mSetMessage, mForgottenPassword;

    // Récupération du context
    private Context mContext = this;

    public String zStatus;
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

        // Récupération des boutons
        mButtonConnexion = (Button)findViewById(R.id.buttonConnexion);
        mButtonRegister = (Button)findViewById(R.id.buttonRegister);
        mButtonNoRegister = (Button)findViewById(R.id.buttonNoRegister);

        // Récupération du texte servant à l'affichage du message
        mSetMessage = (TextView) findViewById(R.id.setMessage);
        mForgottenPassword = (TextView) findViewById(R.id.textViewForgottenPassword);

        // Instanciation des onClicks
        mButtonConnexion.setOnClickListener(checkLogin);
        mButtonRegister.setOnClickListener(goToRegister);
        mForgottenPassword.setOnClickListener(forgotPassword);
        mButtonNoRegister.setOnClickListener(goToHome);

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
                cFunctionsClass.setMessage(mSetMessage, getString(R.string.incorrectMailFormat), 0);

            // Si l'un des deux champs est vide
            if (zPassword.isEmpty())
                cFunctionsClass.setMessage(mSetMessage, getString(R.string.emptyField), 0);

            // Si le format du mail correspond au format normal d'une adresse, alors on vérifie si un utilisateur existe ou non
            if(bMailConfirm && !zPassword.isEmpty())
            {
                // On instancie le message à vide
                cFunctionsClass.setMessage(mSetMessage, getString(R.string.setMessage), 0);
                // On envoi les données à la fonction qui appelle l'API

                // Creation of the JSON body
                HashMap<String, String> object = new HashMap<String, String>();
                object.put("mail", zMail);
                object.put("password", zPassword);

                cLoginAction.sendLogin(mContext, object, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) throws JSONException {
                        // Si la réponse est un message d'erreur alors le JSON contient un champ "response", ce qui veut dire que la combinaison est incorrecte
                        if(result.has("response"))
                        {
                            if(nbEssais > 1)
                            {
                                nbEssais = nbEssais - 1;
                                cFunctionsClass.setMessage(mSetMessage, getString(R.string.numberTries), nbEssais);
                            }
                            else {
                                String status = "0";

                                // Creation of the JSON body
                                HashMap<String, String> object = new HashMap<String, String>();
                                object.put("mail", zMail);
                                object.put("password", zPassword);
                                object.put("statut", status);

                                cLoginAction.changeStatusAccount(mContext, object, new VolleyCallback() {
                                    @Override
                                    public void onSuccessResponse(JSONObject result) throws JSONException {
                                        cFunctionsClass.setMessage(mSetMessage, getString(R.string.accountBlocked), 0);
                                        return;
                                    }

                                    @Override
                                    public void onSuccessResponse(JSONArray result) throws JSONException {}

                                    @Override
                                    public void onSuccessResponse(String result) { }
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
                                    zStatus = "Client";
                                    break;
                                case 1:
                                    zStatus = "Association";
                                    break;
                                case 2:
                                    zStatus = "Vendeur";
                                    break;
                                case 3:
                                    zStatus = "Admin";
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
                            editor.putString(getString(R.string.statusSavedKey), zStatus);
                            editor.putString("mail", result.getString("mail"));
                            editor.apply();

                            Intent intent = new Intent(mContext, HomeActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onSuccessResponse(JSONArray result) throws JSONException {}

                    @Override
                    public void onSuccessResponse(String result) { }
                });
            }
        }
    };

    private View.OnClickListener goToRegister = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setTitle(R.string.dialogTypeActivity);
            Intent intent = new Intent(mContext, RegisterActivity.class);

            alert.setNeutralButton(R.string.client, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.putExtra("activityDesign", getString(R.string.client));
                    startActivity(intent);
                }
            });

            alert.setPositiveButton(R.string.association, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.putExtra("activityDesign", getString(R.string.association));
                    startActivity(intent);
                }
            });

            alert.setNegativeButton(R.string.vendeur, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.putExtra("activityDesign", getString(R.string.vendeur));
                    startActivity(intent);
                }
            });

            alert.show();
        }
    };

    public void goToAdmin(View view) {
        Intent intent = new Intent(mContext, AdminActivity.class);
        startActivity(intent);
    }

    public void goToGestionUser()
    {
        Intent intent = new Intent(mContext, ListAccountsActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener forgotPassword = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, ForgotPasswordActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener goToHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, HomeActivity.class);
            startActivity(intent);
        }
    };


}