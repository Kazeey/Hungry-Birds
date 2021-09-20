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
import com.projet.hungrybirds.utils.Functions;

public class RegisterActivity extends AppCompatActivity {

    // Récupération du context
    private Context mContext = this;

    Functions cFunctionsClass = new Functions();
    RegisterAction cRegisterAction = new RegisterAction();

    Button mButtonReturn, mButtonRegister;
    ScrollView mStructureScrollView;
    EditText mEditName, mEditFirstname, mEditMail, mEditPassword, mEditPhoneNumber, mEditAddress, mEditTown, mEditPostalCode, mEditStructureName, mEditSiretNumber;

    String zStatus;

    boolean bStructure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        Bundle extras = getIntent().getExtras();
        zStatus = extras.getString("activityDesign");
        switch (zStatus)
        {
            case "Client" :
                bStructure = false;
                ((TextView)findViewById(R.id.textViewRegisterPage)).setText(getString(R.string.createClientAccount));
                break;
            case "Association" :
                bStructure = true;
                ((TextView)findViewById(R.id.textViewRegisterPage)).setText(getString(R.string.createAssocAccount));
                break;
            case "Vendeur" :
                bStructure = true;
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
        mEditPassword = findViewById(R.id.editTextPhoneNumber);
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
    }

    private TextWatcher registerLoginWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
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
        public void afterTextChanged(Editable editable) {

        }
    };

    private View.OnClickListener checkRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener returnToLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
        }
    };


}