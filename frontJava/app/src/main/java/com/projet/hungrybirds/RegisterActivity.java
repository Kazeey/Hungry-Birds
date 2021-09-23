package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.projet.hungrybirds.actions.RegisterAction;
import com.projet.hungrybirds.interfaces.VolleyCallback;
import com.projet.hungrybirds.utils.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity
{

    // Récupération du context
    private Context mContext = this;

    Functions cFunctionsClass = new Functions();
    RegisterAction cRegisterAction = new RegisterAction();

    Button mButtonReturn, mButtonRegister;
    ScrollView mStructureScrollView;
    EditText mEditName, mEditFirstname, mEditMail, mEditPassword, mEditPhoneNumber, mEditAddress, mEditTown, mEditPostalCode, mEditStructureName, mEditSiretNumber;
    TextView mRegisterError;

    String zStatus, zRole;

    boolean bStructure, bIsSiret;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        Bundle extras = getIntent().getExtras();
        zStatus = extras.getString("activityDesign");
        switch (zStatus)
        {
            case "Client" :
                bStructure = false;
                zRole = "0";
                ((TextView)findViewById(R.id.textViewRegisterPage)).setText(getString(R.string.createClientAccount));
                break;
            case "Association" :
                bStructure = true;
                zRole = "1";
                ((TextView)findViewById(R.id.textViewRegisterPage)).setText(getString(R.string.createAssocAccount));
                break;
            case "Vendeur" :
                bStructure = true;
                zRole = "2";
                ((TextView)findViewById(R.id.textViewRegisterPage)).setText(getString(R.string.createVendeurAccount));
                break;
        }

        mStructureScrollView = findViewById(R.id.scrollViewRegisterPageStructure);

        if (!bStructure)
            mStructureScrollView.setVisibility(View.GONE);

        // Récupération des champs
        mEditName = findViewById(R.id.editTextName);
        mEditFirstname = findViewById(R.id.editTextFirstname);
        mEditMail = findViewById(R.id.editTextMail);
        mEditPassword = findViewById(R.id.editTextPassword);
        mEditPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        mEditAddress = findViewById(R.id.editTextAddress);
        mEditTown = findViewById(R.id.editTextTown);
        mEditPostalCode = findViewById(R.id.editTestPostalCode);

        if (bStructure)
        {
            mEditStructureName = findViewById(R.id.editTextStructureName);
            mEditSiretNumber = findViewById(R.id.editTextSiret);
        }

        // Instanciation du watcher pour le bouton d'inscription
        mEditName.addTextChangedListener(registerLoginWatcher);
        mEditFirstname.addTextChangedListener(registerLoginWatcher);
        mEditMail.addTextChangedListener(registerLoginWatcher);
        mEditPassword.addTextChangedListener(registerLoginWatcher);
        mEditPhoneNumber.addTextChangedListener(registerLoginWatcher);
        mEditAddress.addTextChangedListener(registerLoginWatcher);
        mEditTown.addTextChangedListener(registerLoginWatcher);
        mEditPostalCode.addTextChangedListener(registerLoginWatcher);

        if (bStructure)
        {
            mEditStructureName.addTextChangedListener(registerLoginWatcher);
            mEditSiretNumber.addTextChangedListener(registerLoginWatcher);
        }

        // Récupération des boutons
        mButtonReturn = (Button)findViewById(R.id.buttonReturn);
        mButtonRegister = (Button)findViewById(R.id.buttonRegisterActivity);

        // Instanciation des boutons
        mButtonReturn.setOnClickListener(returnToLogin);
        mButtonRegister.setOnClickListener(checkRegister);

        // Récupération des TextViews
        mRegisterError = findViewById(R.id.textViewRegisterError);

        bIsSiret = false;
    }

    private TextWatcher registerLoginWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {
            String zNameInput, zFirstnameInput, zMailInput, zPasswordInput, zPhoneNumberInput, zAddressInput, zTownInput, zPostalCodeInput, zStructureNameInput, zSiretNumberInput;

            zNameInput = mEditName.getText().toString().trim();
            zFirstnameInput = mEditFirstname.getText().toString().trim();
            zMailInput = mEditMail.getText().toString().trim();
            zPasswordInput = mEditPassword.getText().toString().trim();
            zPhoneNumberInput = mEditPhoneNumber.getText().toString().trim();
            zAddressInput = mEditAddress.getText().toString().trim();
            zTownInput = mEditTown.getText().toString().trim();
            zPostalCodeInput = mEditPostalCode.getText().toString().trim();

            if(bStructure)
            {
                zStructureNameInput = mEditStructureName.getText().toString().trim();
                zSiretNumberInput = mEditSiretNumber.getText().toString().trim();

                mButtonRegister.setEnabled(!zNameInput.isEmpty() && !zFirstnameInput.isEmpty() && !zMailInput.isEmpty() && !zPasswordInput.isEmpty() &&
                        !zPhoneNumberInput.isEmpty() && !zAddressInput.isEmpty() && !zTownInput.isEmpty() &&
                        !zPostalCodeInput.isEmpty() && !zStructureNameInput.isEmpty() && !zSiretNumberInput.isEmpty());
            }
            else
            {
                mButtonRegister.setEnabled(!zNameInput.isEmpty() && !zFirstnameInput.isEmpty() && !zMailInput.isEmpty() && !zPasswordInput.isEmpty() &&
                        !zPhoneNumberInput.isEmpty() && !zAddressInput.isEmpty() && !zTownInput.isEmpty() &&
                        !zPostalCodeInput.isEmpty());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    private View.OnClickListener checkRegister = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            // Insertion des valeurs dans un Objet JSON
            HashMap<String, String> objectUser = new HashMap<>();
            objectUser.put("nom",cFunctionsClass.getTextFromInput(mEditName));
            objectUser.put("prenom",cFunctionsClass.getTextFromInput(mEditFirstname));
            objectUser.put("mail",cFunctionsClass.getTextFromInput(mEditMail));
            objectUser.put("password",cFunctionsClass.getTextFromInput(mEditPassword));
            objectUser.put("telephone",cFunctionsClass.getTextFromInput(mEditPhoneNumber));
            objectUser.put("adresse",cFunctionsClass.getTextFromInput(mEditAddress));
            objectUser.put("ville",cFunctionsClass.getTextFromInput(mEditTown));
            objectUser.put("code_postal",cFunctionsClass.getTextFromInput(mEditPostalCode));
            objectUser.put("role", zRole);
            objectUser.put("statut", "1");

            HashMap<String, HashMap> objectBody = new HashMap<>();
            objectBody.put("objectUser", objectUser);

            if (bStructure) {
                HashMap<String, String> objectStructure = new HashMap<>();
                objectStructure.put("mail", cFunctionsClass.getTextFromInput(mEditMail));
                objectStructure.put("description", cFunctionsClass.getTextFromInput(mEditStructureName));
                objectStructure.put("siret", cFunctionsClass.getTextFromInput(mEditSiretNumber));
                objectBody.put("objectStructure", objectStructure);

                cRegisterAction.checkSiret(mContext, objectStructure.get("siret").toString(), new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) throws JSONException {}

                    @Override
                    public void onSuccessResponseGet(String result) {
                        if(!result.equals(""))
                        {
                            cFunctionsClass.setMessage(mRegisterError, "", 0);
                            cRegisterAction.createUserAndStructure(mContext, objectBody, new VolleyCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) throws JSONException {
                                    if (result.has("response"))
                                    {
                                        cFunctionsClass.setMessage(mRegisterError, result.getString("response"), 0);
                                    }
                                    else
                                    {
                                        System.out.println("Created at first : " + result);
                                        cFunctionsClass.setMessage(mRegisterError, getString(R.string.accountCreated), 0);
                                        cFunctionsClass.redirectAfterTime(mContext, 3000);
                                    }
                                }

                                @Override
                                public void onSuccessResponseGet(String result) {}
                            });
                        }
                        else
                        {
                            cFunctionsClass.setMessage(mRegisterError, getString(R.string.incorrectSiretNumber), 0);
                        }
                    }
                });
            }
            else
            {
                cRegisterAction.createAccount(mContext, objectUser, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) throws JSONException {
                        if (result.has("response"))
                        {
                            cFunctionsClass.setMessage(mRegisterError, result.getString("response"), 0);
                        }
                        else
                        {
                            cFunctionsClass.setMessage(mRegisterError, getString(R.string.accountCreated), 0);
                            cFunctionsClass.redirectAfterTime(mContext, 3000);
                        }
                    }

                    @Override
                    public void onSuccessResponseGet(String result) {}
                });
            }
        }
    };

    private View.OnClickListener returnToLogin = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
        }
    };


}