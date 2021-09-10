package com.projet.hungrybirds.actions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoginAction {

    public void getUserFromLogin(String zMail, String zPassword) throws IOException, JSONException {
        URL api = new URL("https://127.0.0.1:3001/login/connect");

        HttpsURLConnection myConnection = (HttpsURLConnection) api.openConnection();
        myConnection.setRequestMethod("POST");

        String body = "{'name': " + zMail + ", 'password': " + zPassword + "}";

        myConnection.setDoOutput(true);

        try (OutputStream os = myConnection.getOutputStream())
        {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(myConnection.getInputStream(), "utf-8")))
        {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null)
            {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
