package com.jsmy.chongyin.activity;

import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.m_webview)
    WebView mWebview;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String url;
    private String bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#5DC080"));
        }
        url = getIntent().getStringExtra("url");
        bt = getIntent().getStringExtra("bt");
        tvTitle.setText(bt);
        mWebview.setVerticalScrollbarOverlay(true); //指定的垂直滚动条有叠加样式
        WebSettings settings = mWebview.getSettings();
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);//设定支持缩放
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebview.clearHistory();
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return super.shouldOverrideUrlLoading(view, request);
            }

//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                    handler.proceed();
////                super.onReceivedSslError(view, handler, error);
//            }

        });
        mWebview.loadUrl(url + "?yhid=" + MyApplication.getMyApplication().userInfo.getYhid());
//        mWebview.loadUrl(url);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void paresData(String result, String code) {
        if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        finish();
    }

}
