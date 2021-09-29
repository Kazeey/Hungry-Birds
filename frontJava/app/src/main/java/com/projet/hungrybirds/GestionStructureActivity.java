package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projet.hungrybirds.actions.CommonAction;
import com.projet.hungrybirds.actions.GestionAction;
import com.projet.hungrybirds.interfaces.VolleyCallback;
import com.projet.hungrybirds.utils.Functions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;

public class GestionStructureActivity extends AppCompatActivity {

    Context mContext = this;

    Functions cFunctions = new Functions();
    GestionAction cGestionAction = new GestionAction();
    CommonAction cCommonAction = new CommonAction();

    Button mButtonReturn, mButtonSend;
    TextView mErrorZone;
    EditText mEditDescription, mEditTimeBegin, mEditTimeEnd, mEditSiret;

    Boolean bCase = false;

    JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_structure);

        Intent intent = getIntent();
        int nPreviousAct = intent.getIntExtra("from", 0);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mEditDescription = (EditText) findViewById(R.id.editTextDescription);
        mEditTimeBegin = (EditText) findViewById(R.id.editTextTimeBegin);
        mEditTimeEnd = (EditText) findViewById(R.id.editTextTimeEnd);
        mEditSiret = (EditText) findViewById(R.id.editTextSiret);

        mErrorZone = (TextView) findViewById(R.id.textViewErrorMessage);

        mButtonSend = (Button) findViewById(R.id.buttonSend);
        mButtonReturn = (Button) findViewById(R.id.buttonReturn);

        mButtonSend.setOnClickListener(mCheckModif);

        mButtonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(nPreviousAct == 0 || nPreviousAct == 2)
                    intent = new Intent(mContext, ListStructuresActivity.class);
                else
                    intent = new Intent(mContext, HomeActivity.class);

                startActivity(intent);
            }
        });

        mEditDescription.addTextChangedListener(structureDataWatcher);
        mEditTimeBegin.addTextChangedListener(structureDataWatcher);
        mEditTimeEnd.addTextChangedListener(structureDataWatcher);
        mEditSiret.addTextChangedListener(structureDataWatcher);

        if (nPreviousAct == 0)
        {
            try
            {
                object = new JSONObject(sharedPreferences.getString("StructData", ""));

                mEditDescription.setText(object.getString("description"));
                mEditTimeBegin.setText(object.getString("heure_debut"));
                mEditTimeEnd.setText(object.getString("heure_fin"));
                mEditSiret.setText(object.getString("siret"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            mEditDescription.setHint(R.string.placeholderDescription);
            mEditTimeBegin.setText("18:00:00");
            mEditTimeEnd.setText("19:00:00");
            mEditSiret.setHint(R.string.placeholderSiret);
        }
    }

    private TextWatcher structureDataWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) {
            String zDescriptionInput, zTimeBeginInput, zTimeEndInput, zSiretInput;
            final boolean[] bIsSiret = {false};

            zDescriptionInput = mEditDescription.getText().toString().trim();
            zTimeBeginInput = mEditTimeBegin.getText().toString().trim();
            zTimeEndInput = mEditTimeEnd.getText().toString().trim();
            zSiretInput = mEditSiret.getText().toString().trim();

            cCommonAction.checkSiret(mContext, zSiretInput, new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) throws JSONException {}

                @Override
                public void onSuccessResponse(JSONArray result) throws JSONException {}

                @Override
                public void onSuccessResponse(String result) {
                    if(!result.equals(""))
                    {
                        cFunctions.setMessage(mErrorZone, "", 0);
                        bIsSiret[0] = true;
                        mButtonSend.setEnabled(!zDescriptionInput.isEmpty() && !zTimeBeginInput.isEmpty() && !zTimeEndInput.isEmpty() && !zSiretInput.isEmpty() && bIsSiret[0]);
                    }
                    else
                    {
                        cFunctions.setMessage(mErrorZone, getString(R.string.incorrectSiretNumber), 0);
                        mButtonSend.setEnabled(false);
                    }
                }
            });

        }
    };

    private View.OnClickListener mCheckModif = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            SharedPreferences sharedPreferences = mContext.getSharedPreferences("file_pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            HashMap<String, String> objectStructure = new HashMap<>();
            objectStructure.put("mail", sharedPreferences.getString("mail", ""));
            objectStructure.put("description", mEditDescription.getText().toString());
            objectStructure.put("heure_debut", mEditTimeBegin.getText().toString());
            objectStructure.put("heure_fin", mEditTimeEnd.getText().toString());
            objectStructure.put("siret", mEditSiret.getText().toString());

            if(sharedPreferences.getBoolean("modify", false))
                bCase = true;

            cGestionAction.createStructure(mContext, objectStructure, bCase, new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) throws JSONException {
                    System.out.println(result);
                }

                @Override
                public void onSuccessResponse(JSONArray result) throws JSONException {}

                @Override
                public void onSuccessResponse(String result) {}
            });
        }
    };
}