package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.projet.hungrybirds.actions.GestionAction;
import com.projet.hungrybirds.interfaces.VolleyCallback;
import com.projet.hungrybirds.utils.Functions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GestionUserActivity extends AppCompatActivity {

    Context mContext = this;

    Functions cFunctionsClass = new Functions();
    GestionAction cGestionAction = new GestionAction();

    String zStatus, zObj, zRole;

    Button mButtonReturn, mButtonSend;
    TextView mCreateStructure, mGestionError;
    EditText mEditName, mEditFirstname, mEditMail, mEditPassword, mEditPhone, mEditAddress, mEditTown, mEditPostalCode;
    Spinner mSpinnerRole;

    JSONObject jUsersData;

    boolean bChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_user);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("file_pref", mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        zObj = sharedPreferences.getString("UsersData", "");
        zStatus = sharedPreferences.getString(getString(R.string.statusSavedKey), "");

        try
        {
            jUsersData = new JSONObject(zObj);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        mButtonSend = (Button) findViewById(R.id.buttonGestionSend);
        mButtonSend.setOnClickListener(mCheckModif);
        mButtonSend.setEnabled(false);

        mButtonReturn = (Button) findViewById(R.id.buttonReturn);
        mButtonReturn.setOnClickListener(mReturn);

        mGestionError = (TextView) findViewById(R.id.textViewGestionError);

        mEditName = (EditText) findViewById(R.id.editTextGestionNom);
        mEditFirstname = (EditText) findViewById(R.id.editTextGestionPrenom);
        mEditMail = (EditText) findViewById(R.id.editTextGestionMail);
        mEditPassword = (EditText) findViewById(R.id.editTextGestionPassword);
        mEditPhone = (EditText) findViewById(R.id.editTextGestionPhone);
        mEditAddress = (EditText) findViewById(R.id.editTextGestionAdresse);
        mEditTown = (EditText) findViewById(R.id.editTextGestionVille);
        mEditPostalCode = (EditText) findViewById(R.id.editTextGestionPostalCode);

        mSpinnerRole = (Spinner) findViewById(R.id.spinnerGestionRole);
        mSpinnerRole.setOnItemSelectedListener(mCheckSpinner);

        try {
            mEditName.setText(jUsersData.getString("nom"));
            mEditFirstname.setText(jUsersData.getString("prenom"));
            mEditMail.setText(jUsersData.getString("mail"));
            mEditPassword.setText(jUsersData.getString("password"));
            mEditPhone.setText(jUsersData.getString("telephone"));
            mEditAddress.setText(jUsersData.getString("adresse"));
            mEditTown.setText(jUsersData.getString("ville"));
            mEditPostalCode.setText(jUsersData.getString("code_postal"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Instanciation du watcher pour le bouton de modification
        mEditName.addTextChangedListener(sendDataWatcher);
        mEditFirstname.addTextChangedListener(sendDataWatcher);
        mEditMail.addTextChangedListener(sendDataWatcher);
        mEditPassword.addTextChangedListener(sendDataWatcher);
        mEditPhone.addTextChangedListener(sendDataWatcher);
        mEditAddress.addTextChangedListener(sendDataWatcher);
        mEditTown.addTextChangedListener(sendDataWatcher);
        mEditPostalCode.addTextChangedListener(sendDataWatcher);
    }

    private AdapterView.OnItemSelectedListener mCheckSpinner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            bChanged = true;

            String zNameInput, zFirstnameInput, zMailInput, zPasswordInput, zPhoneNumberInput, zAddressInput, zTownInput, zPostalCodeInput;

            zNameInput = mEditName.getText().toString().trim();
            zFirstnameInput = mEditFirstname.getText().toString().trim();
            zMailInput = mEditMail.getText().toString().trim();
            zPasswordInput = mEditPassword.getText().toString().trim();
            zPhoneNumberInput = mEditPhone.getText().toString().trim();
            zAddressInput = mEditAddress.getText().toString().trim();
            zTownInput = mEditTown.getText().toString().trim();
            zPostalCodeInput = mEditPostalCode.getText().toString().trim();

            mButtonSend.setEnabled(!zNameInput.isEmpty() && !zFirstnameInput.isEmpty() && !zMailInput.isEmpty() && !zPasswordInput.isEmpty() &&
                    !zPhoneNumberInput.isEmpty() && !zAddressInput.isEmpty() && !zTownInput.isEmpty() &&
                    !zPostalCodeInput.isEmpty() && bChanged);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final TextWatcher sendDataWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String zNameInput, zFirstnameInput, zMailInput, zPasswordInput, zPhoneNumberInput, zAddressInput, zTownInput, zPostalCodeInput;

            zNameInput = mEditName.getText().toString().trim();
            zFirstnameInput = mEditFirstname.getText().toString().trim();
            zMailInput = mEditMail.getText().toString().trim();
            zPasswordInput = mEditPassword.getText().toString().trim();
            zPhoneNumberInput = mEditPhone.getText().toString().trim();
            zAddressInput = mEditAddress.getText().toString().trim();
            zTownInput = mEditTown.getText().toString().trim();
            zPostalCodeInput = mEditPostalCode.getText().toString().trim();

            boolean bIsMail = cFunctionsClass.checkMail(zMailInput);
            if (!bIsMail)
                cFunctionsClass.setMessage(mGestionError, getString(R.string.incorrectMailFormat), 0);
            else
                cFunctionsClass.setMessage(mGestionError, "", 0);


            mButtonSend.setEnabled(!zNameInput.isEmpty() && !zFirstnameInput.isEmpty() && !zMailInput.isEmpty() && !zPasswordInput.isEmpty() &&
                    !zPhoneNumberInput.isEmpty() && !zAddressInput.isEmpty() && !zTownInput.isEmpty() &&
                    !zPostalCodeInput.isEmpty() && bIsMail && bChanged);
        }
    };

    private View.OnClickListener mCheckModif = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try
            {
                switch (mSpinnerRole.getSelectedItem().toString())
                {
                    case "Client":
                        zRole = "0";
                        break;
                    case "Association":
                        zRole = "1";
                        break;
                    case "Vendeur":
                        zRole = "2";
                        break;
                }

                HashMap<String, String> objectUser = new HashMap<>();
                objectUser.put("id_utilisateur", jUsersData.getString("id_utilisateur"));
                objectUser.put("nom", mEditName.getText().toString());
                objectUser.put("prenom", mEditFirstname.getText().toString());
                objectUser.put("mail", mEditMail.getText().toString());
                objectUser.put("password", mEditPassword.getText().toString());
                objectUser.put("telephone", mEditPhone.getText().toString());
                objectUser.put("adresse", mEditAddress.getText().toString());
                objectUser.put("ville", mEditTown.getText().toString());
                objectUser.put("code_postal", mEditPostalCode.getText().toString());
                objectUser.put("role", zRole);
                objectUser.put("statut", "1");

                cGestionAction.updateUser(mContext, objectUser, new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) throws JSONException {
                        cFunctionsClass.setMessage(mGestionError, getString(R.string.userAltered), 0);

                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("file_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.remove(getString(R.string.statusSavedKey));
                        editor.apply();
                        editor.putString(getString(R.string.statusSavedKey), objectUser.get("statut"));
                        editor.apply();
                    }

                    @Override
                    public void onSuccessResponse(JSONArray result) throws JSONException {}

                    @Override
                    public void onSuccessResponse(String result) {}
                });
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener mReturn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;

            if(zStatus == "Admin")
                intent = new Intent(mContext, ListAccountsActivity.class);
            else
                intent = new Intent(mContext, HomeActivity.class);

            startActivity(intent);
        }
    };
}