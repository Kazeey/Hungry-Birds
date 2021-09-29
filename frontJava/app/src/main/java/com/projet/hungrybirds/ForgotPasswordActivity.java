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
import android.widget.TextView;

import com.projet.hungrybirds.actions.LoginAction;
import com.projet.hungrybirds.actions.RegisterAction;
import com.projet.hungrybirds.interfaces.VolleyCallback;
import com.projet.hungrybirds.utils.Functions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ForgotPasswordActivity extends AppCompatActivity
{

    // Récupération du context
    private final Context mContext = this;

    Functions cFunctionsClass = new Functions();
    LoginAction cLoginAction = new LoginAction();

    Button mButtonReturn, mButtonSend;
    TextView mErrorMessage;
    EditText mEditMail;

    boolean bIsMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mButtonReturn = findViewById(R.id.buttonReturnForgottenPassword);
        mButtonSend = findViewById(R.id.buttonSendForgottenPassword);
        mErrorMessage = findViewById(R.id.textViewErrorForgottenPassword);
        mEditMail = findViewById(R.id.editTextEmailForgottenPassword);

        mEditMail.addTextChangedListener(forgotPasswordWatcher);

        mButtonSend.setOnClickListener(sendMail);
        mButtonReturn.setOnClickListener(returnToLogin);
    }

    private final TextWatcher forgotPasswordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String zMailInput;

            zMailInput = mEditMail.getText().toString().trim();
            bIsMail = cFunctionsClass.checkMail(zMailInput);

            if (!bIsMail)
                cFunctionsClass.setMessage(mErrorMessage, getString(R.string.incorrectMailFormat), 0);
            else
                cFunctionsClass.setMessage(mErrorMessage, "", 0);

            mButtonSend.setEnabled(!zMailInput.isEmpty() && bIsMail);
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    private final View.OnClickListener sendMail = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cFunctionsClass.setMessage(mErrorMessage, "", 0);

            HashMap<String, String> object = new HashMap<>();
            object.put("mail", cFunctionsClass.getTextFromInput(mEditMail));

            cLoginAction.forgotPassword(mContext, object, new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) throws JSONException {
                    if (result.has("result"))
                    {
                        cFunctionsClass.setMessage(mErrorMessage, result.getString("result"), 0);
                    }
                }

                @Override
                public void onSuccessResponse(JSONArray result) throws JSONException {}

                @Override
                public void onSuccessResponse(String result) {}
            });
        }
    };

    private final View.OnClickListener returnToLogin = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            cFunctionsClass.redirect(mContext);
        }
    };
}