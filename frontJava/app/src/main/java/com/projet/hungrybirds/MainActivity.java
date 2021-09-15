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


public class MainActivity extends AppCompatActivity {

    // Récupération des classes
    Functions cFunctionsClass = new Functions();
    LoginAction cLoginAction = new LoginAction();

    // Instanciation des variables liées aux composants
    EditText mEditMail, mEditPassword;
    Button mButtonConnexion;
    TextView mSetMessage;

    SharedPreferences sharedPreferences;

    private Context mContext;

    public String statut;
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
        mButtonConnexion.setOnClickListener(mCheckLogin);

        // Récupération du texte servant à l'affichage du message
        mSetMessage = (TextView) findViewById(R.id.setMessage);

        // Instanciation du nombre d'essais pour une combinaison mail / password erronée
        nbEssais = 5;
    }

    /**
     * Vérification des inputs mail et password
     */
    private View.OnClickListener mCheckLogin = new View.OnClickListener()
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
                cFunctionsClass.setMessage(mSetMessage, "Le mot de passe est vide, veuillez le renseigner !", 0);

            // Si le format du mail correspond au format normal d'une adresse, alors on vérifie si un utilisateur existe ou non
            if(bMailConfirm && !zPassword.isEmpty())
            {
                // On instancie le message à vide
                cFunctionsClass.setMessage(mSetMessage, "", 0);
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
                                String statut = "0";
                                cLoginAction.changeStatutAccount(mContext, zMail, zPassword, statut, new VolleyCallback() {
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
                                    statut = "Client";
                                    break;
                                case 1:
                                    statut = "Association";
                                    break;
                                case 2:
                                    statut = "Vendeur";
                                    break;
                            }

                            switch (timeConnexion)
                            {
                                case 0 :
                                    time = 900000;
                                    break;
                                case 1 :
                                    time = ((900000 * 4) * 24);
                                    break;
                            }

                            System.out.println("Statut : " + statut + " | timeConnexion : " + time);
                            //goToGestionUser(view);
                        }
                    }
                });
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