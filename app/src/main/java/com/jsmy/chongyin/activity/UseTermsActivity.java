package com.jsmy.chongyin.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jsmy.chongyin.R;

import butterknife.BindView;
import butterknife.OnClick;

public class UseTermsActivity extends BaseActivity {

    @BindView(R.id.m_webview)
    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_use_terms;
    }

    @Override
    protected void initView() {
        WebSettings settings = mWebview.getSettings();
        settings.setSupportZoom(false);

        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        mWebview.loadUrl("file:///android_asset/index.html");
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

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        finish();
    }
}
