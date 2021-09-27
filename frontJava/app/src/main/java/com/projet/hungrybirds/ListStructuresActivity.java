package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;

public class ListStructuresActivity extends AppCompatActivity {

    Functions cFunctions = new Functions();
    GestionAction cGestionAction = new GestionAction();

    Context mContext = this;

    String zStatus;
    int nUserId;

    LinearLayout linearLayoutContainer;
    Button mButtonReturn, mButtonAddStructure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean bVerification = cFunctions.verification(this);
        if(bVerification == false)
            cFunctions.redirect(mContext);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("file_pref", mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("modify", false);

        nUserId = sharedPreferences.getInt(getString(R.string.userIdSavedKey), 0);

        cGestionAction.getStructures(mContext, nUserId, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject result) throws JSONException {
                if (result.has("response"))
                {
                    setContentView(R.layout.activity_gestion_structure);
                    mButtonReturn = (Button) findViewById(R.id.buttonReturn);
                    mButtonReturn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, HomeActivity.class);
                            startActivity(intent);
                        }
                    });
                    transferToGesStructure();
                }
            }

            @Override
            public void onSuccessResponse(JSONArray result) throws JSONException {
                JSONObject obj;
                for(int i = 0; i < result.length(); i++)
                {
                    obj = result.getJSONObject(i);
                    setContentView(R.layout.activity_list_structures);
                    linearLayoutContainer = (LinearLayout) findViewById(R.id.linearLayoutGestionContainer);
                    generateElements(obj.getString("description"), obj.getString("siret"), obj);
                    mButtonAddStructure = (Button) findViewById(R.id.buttonAddNewStructure);
                    mButtonAddStructure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, GestionStructureActivity.class);
                            intent.putExtra("from", 2);
                            startActivity(intent);
                        }
                    });
                    mButtonReturn = (Button) findViewById(R.id.buttonReturn);
                    mButtonReturn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, HomeActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onSuccessResponseGet(String result) {}
        });
    }

    private void generateElements(String zName, String zSiret, JSONObject obj)
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
        textViewMain.setText(zName);

        LinearLayout.LayoutParams layoutTextSecondaryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutTextSecondaryParams.setMargins(0,50,0,0);
        textViewSecondary.setLayoutParams(layoutTextSecondaryParams);
        textViewSecondary.setTextSize(16);
        textViewSecondary.setText(zSiret);

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

                editor.putString("StructData", obj.toString());
                editor.putBoolean("modify", true);
                editor.apply();

                Intent intent = new Intent(mContext, GestionStructureActivity.class);
                startActivity(intent);
            }
        });
    }

    private void transferToGesStructure()
    {
        Intent intent = new Intent(mContext, GestionStructureActivity.class);
        intent.putExtra("from", 1);
        startActivity(intent);
    }
}