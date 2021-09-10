package com.projet.hungrybirds.actions;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class LoginAction extends AsyncTask<String, Void, Boolean> {

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... url)
    {
        try
        {
            URL urlLogin = new URL("127.0.0.1/login/connect");

            HttpsURLConnection urlConnection = (HttpsURLConnection) urlLogin.openConnection();
            InputStream is = urlConnection.getInputStream();

            return true;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

}
