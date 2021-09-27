package com.projet.hungrybirds.interfaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccessResponse(JSONObject result) throws JSONException;
    void onSuccessResponse(JSONArray result) throws JSONException;
    void onSuccessResponseGet(String result);
}
