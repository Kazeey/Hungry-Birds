package com.projet.hungrybirds.actions;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.projet.hungrybirds.interfaces.VolleyCallback;

import java.util.HashMap;
import java.util.Map;

public class CommonAction {
    public void checkSiret(Context context, String Siret, VolleyCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.insee.fr/entreprises/sirene/V3" + "/siren/" + Siret;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccessResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse.statusCode != 200)
                    callback.onSuccessResponse("");
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer cad0f335-61f9-3683-a583-c40e8c046457");
                return headers;
            }
        };

        queue.add(stringRequest);
    }
}
