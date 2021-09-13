package com.projet.hungrybirds.actions;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginAction extends AppCompatActivity {
    public void callApi(View view, Context context)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/favoris/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    System.out.println(jsonArray);

                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String id_utilisateur = obj.getString("id_utilisateur");
                        String id_favori = obj.getString("id_favori");
                        System.out.println("id_favori : " + id_favori + " | id_utilisateur : " + id_utilisateur);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error : " + error);
            }
        });

        queue.add(stringRequest);
    }
}
