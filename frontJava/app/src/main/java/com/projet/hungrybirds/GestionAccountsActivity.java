package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projet.hungrybirds.actions.GestionAction;
import com.projet.hungrybirds.interfaces.VolleyCallback;
import com.projet.hungrybirds.utils.Functions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GestionAccountsActivity extends AppCompatActivity {

    Functions cFunctions = new Functions();
    GestionAction cGestionAction = new GestionAction();

    Context mContext = this;

    String zStatus;

    LinearLayout linearLayoutContainer;
    Button mButtonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean bVerification = cFunctions.verification(this);
        if(bVerification == false)
            cFunctions.redirect(mContext);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("file_pref", mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        zStatus = sharedPreferences.getString(getString(R.string.statusSavedKey), "");

        if (zStatus == "Admin")
        {
            setContentView(R.layout.activity_list_accounts);

            linearLayoutContainer = (LinearLayout) findViewById(R.id.linearLayoutGestionContainer);
        }
        else
        {
            setContentView(R.layout.activity_gestion_user);
        }

        generateUserData();

        mButtonReturn = (Button)findViewById(R.id.buttonReturn);
        mButtonReturn.setOnClickListener(mReturnToHome);
    }

    private void generateUserData ()
    {
        boolean bCase = false;
        int nIdUser = 0;

        if (zStatus != "Admin")
        {
            SharedPreferences sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            nIdUser = sharedPreferences.getInt(getString(R.string.userIdSavedKey), 0);
            bCase = true;
        }

        cGestionAction.getAllAccounts(mContext, bCase, nIdUser, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject result) throws JSONException {}

            @Override
            public void onSuccessResponse(JSONArray result) throws JSONException {
                JSONObject obj;

                for(int i = 0; i < result.length(); i++)
                {
                    obj = result.getJSONObject(i);

                    if (zStatus == "Admin")
                        generateElements(obj.getString("nom") + " " + obj.getString("prenom"), obj.getString("mail"), obj);
                    else
                        transferToGesAccount(obj);
                }
            }

            @Override
            public void onSuccessResponseGet(String result) {

            }
        });
    }

    private void generateElements(String zUser, String zMail, JSONObject obj)
    {
        LinearLayout linearLayoutCase = new LinearLayout(mContext);
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        ImageView imageView = new ImageView(mContext);
        TextView textViewMain = new TextView(mContext);
        TextView textViewSecondary = new TextView(mContext);

        LinearLayout.LayoutParams layoutCaseParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutCaseParams.setMargins(12,12,12,12);
        layoutCaseParams.height = 150;
        linearLayoutCase.setLayoutParams(layoutCaseParams);
        linearLayoutCase.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutCase.setGravity(View.TEXT_ALIGNMENT_CENTER);
        linearLayoutCase.setBackgroundResource(R.drawable.bg_item);
        linearLayoutCase.setClickable(true);

        LinearLayout.LayoutParams layoutRelativelayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(layoutRelativelayoutParams);

        LinearLayout.LayoutParams layoutImageParams = new LinearLayout.LayoutParams(160, 160);
        layoutImageParams.setMargins(0,0,12,0);
        imageView.setLayoutParams(layoutImageParams);
        imageView.setImageResource(R.drawable.gear);

        LinearLayout.LayoutParams layoutTextMainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutTextMainParams.setMargins(0,10,0,0);
        textViewMain.setLayoutParams(layoutTextMainParams);
        textViewMain.setTextSize(18);
        textViewMain.setText(zUser);

        LinearLayout.LayoutParams layoutTextSecondaryPasrams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutTextSecondaryPasrams.setMargins(0,50,0,0);
        textViewSecondary.setLayoutParams(layoutTextSecondaryPasrams);
        textViewSecondary.setTextSize(16);
        textViewSecondary.setText(zMail);

        linearLayoutCase.addView(imageView);
        relativeLayout.addView(textViewMain);
        relativeLayout.addView(textViewSecondary);
        linearLayoutCase.addView(relativeLayout);
        linearLayoutContainer.addView(linearLayoutCase);

        linearLayoutCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("UsersData", obj.toString());

                editor.apply();

                Intent intent = new Intent(mContext, GestionUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void transferToGesAccount(JSONObject obj)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("UsersData", obj.toString());

        editor.apply();

        Intent intent = new Intent(mContext, GestionUserActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener mReturnToHome = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, HomeActivity.class);
            startActivity(intent);
        }
    };

}