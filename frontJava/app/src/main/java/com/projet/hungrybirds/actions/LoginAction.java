package com.projet.hungrybirds.actions;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.projet.hungrybirds.interfaces.VolleyCallback;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginAction extends AppCompatActivity {

    /**
     * This function sends the logins to the back and get an answer
     * Either the result contains the user data
     * Either the result contains the response field, to signal that no user was found
     * Either the result contains the blocked field, to signal that the account is blocked
     *
     * @param context   Context of the current activity
     * @param object    JSON Object of the user's data
     * @param callback  Returns the response in a callbak
     */
    public void sendLogin(Context context, HashMap<String, String> object, VolleyCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/login/connect";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(object), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    callback.onSuccessResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error : ", error.toString());
            }
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
    };

    /**
     * This function sends the statut to the back.
     *
     * @param context   Context of the current activity
     * @param mail      Mail of the user
     * @param password  Password of the user
     * @param statut    1 or 0 / 1 => Activated | 0 => Desactivated
     * @param callback  Returns the response in a callback
     */
    public void changeStatusAccount(Context context, String mail, String password, String statut, VolleyCallback callback)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/login/changeStatutAccount";

        // Creation of the JSON body
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("mail", mail);
        params.put("password", password);
        params.put("statut", statut);
        System.out.println(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    callback.onSuccessResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error : ", error.toString());
            }
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
    };

    /**
     * This function is an exemple of a GET method.
     *
     * @param context   Contect from the targeted Activity
     * @return          Return the response as JSONObject
     */
    public void exempleGetFromApi(Context context)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:3001/favoris/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject obj;
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        obj = jsonArray.getJSONObject(i);
                        String id_utilisateur = obj.getString("id_utilisateur");
                        String id_favori = obj.getString("id_favori");
                    }
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

        queue.add(stringRequest);
    }
}
