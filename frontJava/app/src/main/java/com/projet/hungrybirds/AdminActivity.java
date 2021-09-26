package com.projet.hungrybirds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        WebView myWebView = (WebView) findViewById(R.id.admin);
        if(myWebView.getParent() != null) {
            ((ViewGroup)myWebView.getParent()).removeView(myWebView);
        }
        myWebView.setWebViewClient(new WebViewClient());
        setContentView(myWebView);
        myWebView.loadUrl("http://localhost:8100/");
    }
}