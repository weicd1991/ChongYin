package com.jsmy.chongyin.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.ToastUtil;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Advertisement3Activity extends Activity implements RewardedVideoAdListener {

    @BindView(R.id.img_load2)
    ImageView imgLoad2;
    @BindView(R.id.rela_load)
    RelativeLayout relaLoad;
    private RewardedVideoAd mRewardedVideoAd;
    private boolean islLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement3);
        ButterKnife.bind(this);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, getString(R.string.ad_app_id));

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();


    }

    @Override
    public void onPause() {
        super.onPause();
        MyLog.showLog("Advertisement3Activity", "-- onPause()");
        mRewardedVideoAd.pause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewRotate();
        MyLog.showLog("Advertisement3Activity", "-- onResume()");
        mRewardedVideoAd.resume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.showLog("Advertisement3Activity", "-- onStop()");
    }

    private void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(getString(R.string.ad_vi_unit_id), new AdRequest.Builder().build());
        }
    }


    private void showRewardedVideo() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        MyLog.showLog("Advertisement3Activity", "onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        MyLog.showLog("Advertisement3Activity", "onRewardedVideoAdClosed");
        setBackData(101);

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        MyLog.showLog("Advertisement3Activity", "onRewardedVideoAdFailedToLoad + " + errorCode);
        ToastUtil.showShort(this, "广告获取失败 - " + errorCode + " - 请稍后重试");
        relaLoad.setVisibility(View.VISIBLE);
        islLoad = false;
        setBackData(0);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        MyLog.showLog("Advertisement3Activity", "onRewardedVideoAdLoaded");
        relaLoad.setVisibility(View.GONE);
        islLoad = true;
        showRewardedVideo();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        MyLog.showLog("Advertisement3Activity", "onRewardedVideoAdOpened");
    }

    @Override
    public void onRewarded(RewardItem reward) {
        MyLog.showLog("Advertisement3Activity", String.format(" onRewarded! currency: %s amount: %d", reward.getType(),
                reward.getAmount()));
    }

    @Override
    public void onRewardedVideoStarted() {
        MyLog.showLog("Advertisement3Activity", "onRewardedVideoStarted");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLog.showLog("Advertisement3Activity", " - " + requestCode + " - " + resultCode);
    }

    //loading旋转动画
    private void viewRotate() {
        relaLoad.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(imgLoad2, "rotation", 0F, 1080F).setDuration(10000);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                relaLoad.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setBackData(int resultCode){
        setResult(resultCode);
        this.finish();
    }



}
