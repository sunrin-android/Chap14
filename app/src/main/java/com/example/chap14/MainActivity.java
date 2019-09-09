package com.example.chap14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        // 자바스크립트 엔진 켜기
        webView.getSettings().setJavaScriptEnabled(true);
        // 자바스크립트 자바 메소드 호출
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "myScript");
        // 웹뷰에 보여줄 url 로드
        webView.loadUrl("file:///android_asset/index.html");
//        webView.loadUrl("https://m.naver.com");
        // url을 통한 웹페이지를 웹뷰안에서 보여주기
        webView.setWebViewClient(new MyWebViewClient());
    }

    class MyJavaScriptInterface {
        @JavascriptInterface
        public void showToast(String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

//        @TargetApi(Build.VERSION_CODES.N); // api 21 이상부터
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
//            return super.shouldOverrideUrlLoading(view, request);
//        }
    }
}
