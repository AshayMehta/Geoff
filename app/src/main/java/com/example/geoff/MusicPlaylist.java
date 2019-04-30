package com.example.geoff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MusicPlaylist extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_musicplaylist);

        webView = (WebView) findViewById(R.id.webview3);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com/playlist?list=PLi9J8D4T_-pqdHPdZRW_1aEpKdHH4hy4O");
    }
}
