package com.projet.hungrybirds.actions;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.projet.hungrybirds.interfaces.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GestionAction {
    public void getAllAccounts(Context context, boolean bCase, int nIdUser, VolleyCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/utilisateurs/";

        if (bCase)
            url += nIdUser;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                    callback.onSuccessResponse(response);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error : " + error);
            }
        });

        queue.add(jsonArrayRequest);
    }
}
