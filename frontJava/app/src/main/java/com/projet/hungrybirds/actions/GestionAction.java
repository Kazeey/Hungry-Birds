package com.projet.hungrybirds.actions;

import android.content.Context;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class GestionAction {
    public void getAccounts(Context context, boolean bCase, int nIdUser, VolleyCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/utilisateurs/";

        if (bCase && nIdUser != 0)
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

    public void getStructures(Context context, int nIdUser, VolleyCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/structures/";

        if (nIdUser != 0)
            url += "user/" + nIdUser;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    callback.onSuccessResponse(response);
                }
                catch (JSONException e)
                {
                    System.out.println("Error :" );
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error : " + error);
            }
        });

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                    callback.onSuccessResponse(response);
                }
                catch (JSONException e)
                {
                    System.out.println("Error :" );
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error : " + error);
            }
        });

        queue.add(jsonObjectRequest);
        queue.add(jsonArrayRequest);
    }

    public void updateUser(Context context, HashMap<String, String> object, VolleyCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/utilisateurs/" + object.get("id_utilisateur");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, url, new JSONObject(object), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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
            public void onErrorResponse(VolleyError error) {}
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }

    public void createStructure(Context context, HashMap<String, String> object, boolean bCase, VolleyCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/structures";

        int method = 1;

        if(bCase)
            method = 7;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(object), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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
            public void onErrorResponse(VolleyError error) {}
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }

}
