package com.example.geoff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Weather extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_weather);

        webView = (WebView) findViewById(R.id.webview4);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com/search?q=whats+the+weather&oq=whats+the+we&aqs=chrome.0.0l2j69i57j0l3.3896j1j1&sourceid=chrome&ie=UTF-8");
    }
}
