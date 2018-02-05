package com.jsmy.chongyin.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.utils.GetUUID;
import com.jsmy.chongyin.utils.MyLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Advertisement2Activity extends Activity {

    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.img_load2)
    ImageView imgLoad2;
    @BindView(R.id.rela_load)
    RelativeLayout relaLoad;

    private InterstitialAd mInterstitialAd;

    private String gd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
        setContentView(R.layout.activity_advertisement2);
        ButterKnife.bind(this);
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, getString(R.string.ad_app_id));

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.ad_in_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                finish();
            }
        });
        startGame();
        gd = getIntent().getStringExtra("gd");
        MyLog.showLog("Ads"," - " + gd);
        MyLog.showLog("Ads","DeviceId - " + GetUUID.getDeviceId(this));
        MyLog.showLog("Ads","Identity - " + GetUUID.getIdentity());
    }

    @Override
    public void onResume() {
        super.onResume();
        if ("Y".equals(gd)){
            viewRotate();
        }else {
            finish();
//            imgBg.setVisibility(View.GONE);
//            imgClose.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    //loading旋转动画
    private void viewRotate() {
        relaLoad.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(imgLoad2, "rotation", 0F, 1080F).setDuration(3000);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                relaLoad.setVisibility(View.GONE);
                showInterstitial();
            }
        });
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            imgBg.setVisibility(View.GONE);
            imgClose.setVisibility(View.GONE);
            mInterstitialAd.show();
        } else {
//            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            finish();
//            imgBg.setVisibility(View.VISIBLE);
//            imgClose.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        finish();
    }

    private void startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }

}
