package com.example.fragmenttest;
/**
 * 新闻详情页，直接使用webview显示新闻
 */

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.loopj.android.image.SmartImageView;

import java.util.zip.Inflater;

import static android.view.KeyEvent.KEYCODE_BACK;

public class Detail_WebView extends AppCompatActivity {
    private WebView webview;
    private String URL;
    private String Title;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String imgurl;
    private SmartImageView smtiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_page);
        Intent intent = getIntent();
        URL = intent.getStringExtra("address");
        Title = intent.getStringExtra("title");
        imgurl = intent.getStringExtra("imgurl");
        webview = (WebView) findViewById(R.id.wbv);
        smtiv = (SmartImageView) findViewById(R.id.bigsiv);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapseBar);
        WebSettings settings = webview.getSettings();
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webview.getSettings().setAppCachePath(appCachePath);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);//允许使用js
        // settings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        // settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setBlockNetworkImage(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView webView, SslErrorHandler handler, SslError error) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                }
            }
        });
        webview.loadUrl(URL);
        ActionBar actionBar = getSupportActionBar();
        collapsingToolbarLayout.setTitle(Title);
        smtiv.setImageUrl(imgurl);
        if (actionBar != null) {
            actionBar.hide();
            ImageButton imageButton = (ImageButton) findViewById(R.id.back);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}