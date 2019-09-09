package com.example.webtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.loadButton);
        editText = findViewById(R.id.urlInput);
        webView = findViewById(R.id.webView);

        // webView setting -> 자바스크립트 엔진켜기
        webView.getSettings().setJavaScriptEnabled(true);
        // webView 안에서 외부 url 페이지 보여주기
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("file:///android_asset/www/index.html");
        // 자바와 자바스크립트 함수 호출
        webView.addJavascriptInterface(new MyJavascript(), "clickOnFace");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(editText.getText().toString());
            }
        });
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    class MyJavascript {
        @JavascriptInterface
        public void clickOnFace(){

        }
    }
}
