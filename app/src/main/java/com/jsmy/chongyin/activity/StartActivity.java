package com.jsmy.chongyin.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.LoginBean;
import com.jsmy.chongyin.bean.RegisterBean;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.listener.IPermission;
import com.jsmy.chongyin.service.LocationService;
import com.jsmy.chongyin.service.NetWorkService;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends BaseActivity {

    @BindView(R.id.img_start)
    ImageView imgStart;

    private String yhid;
    private String openid;
    private String dh;
    private String mm;

    private ConnectivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        getNetIp();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(NetworkReceiver, intentFilter);
        startNetService();
        Glide.with(this)
                .load(R.drawable.start2)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgStart);
        getLocationDate();
//        Glide.with(this)
//                .load(R.drawable.start2)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .listener(new RequestListener<Integer, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        GifDrawable drawable = (GifDrawable) resource;
//                        GifDecoder decoder = drawable.getDecoder();
//                        int duration = 0;
//                        for (int i = 0; i < drawable.getFrameCount(); i++) {
//                            duration += decoder.getDelay(i);
//                        }
//                        //发送延时消息，通知动画结束
//                        handler.sendEmptyMessageDelayed(0, duration);
//                        return false;
//                    }
//                })
//                .into(new GlideDrawableImageViewTarget(imgStart, 1));
    }

    private void startNetService() {
        Intent intent = new Intent(this, NetWorkService.class);
        startService(intent);
    }

    private BroadcastReceiver NetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkAvailable()) {
                setAnimo(imgStart);
            } else {
                ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
            }
        }
    };

    /**
     * 检测网络是否连接
     */
    private boolean NetworkAvailable() {
        try {
            //得到网络连接信息
            manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                // 获取NetworkInfo对象
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();

                //去进行判断网络是否连接
                if (networkInfo != null || networkInfo.isConnected()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(NetworkReceiver);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            switch (DecodeData.getCodeRoMsg(result, DecodeData.CHECK)) {
                case ServiceCode.GET_LOGIN_NUM:
                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
                        showCloseWindow();
                        MyLog.showLog("NNN", "您的账号已被禁用，解封入口www.urmer.com");
                    } else {
                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
                        finish();
                    }
                    break;
                case ServiceCode.UP_DATE_WZ_NUM:

                    break;
                case ServiceCode.GET_DH_LOGIN_NUM:
                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
                        showCloseWindow();
                    } else {
                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
                        finish();
                    }
                    break;
                default:

                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
            switch (DecodeData.getCodeRoMsg(result, DecodeData.CHECK)) {
                case ServiceCode.GET_LOGIN_NUM:
                    ToastUtil.showShort(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
                    gotoSomeActivity(this, AtyTag.ATY_Login, false);
                    finish();
//                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
//                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
//                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
//                        showCloseWindow();
//                    } else {
//                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
//                        finish();
//                    }
                    break;
                case ServiceCode.UP_DATE_WZ_NUM:

                    break;
                case ServiceCode.GET_DH_LOGIN_NUM:
                    ToastUtil.showShort(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
                    gotoSomeActivity(this, AtyTag.ATY_Login, false);
                    finish();
//                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
//                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
//                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
//                        showCloseWindow();
//                    } else {
//                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
//                        finish();
//                    }
                    break;
                default:

                    break;
            }
        }
    }

    //获取本地地数据自动登录
    private void getLocationDate() {
        yhid = SharePrefUtil.getString(this, "yhid", "");
        MyLog.showLog("SSS", "yhid = " + yhid);
        openid = SharePrefUtil.getString(this, "openid", "");
        MyLog.showLog("SSS", "openid = " + openid);
        dh = SharePrefUtil.getString(this, "dh", "");
        MyLog.showLog("SSS", "dh = " + dh);
        mm = SharePrefUtil.getString(this, "mm", "");
        MyLog.showLog("SSS", "mm = " + mm);
        loginData();
    }

    //访问登录接口
    private void loginData() {
        if (!"".equals(yhid) && !"".equals(openid)) {
            map.clear();
            map.put("yhid", yhid);
            map.put("openid", openid);
            NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
        } else if (!"".equals(dh) && !"".equals(mm)) {
            map.clear();
            map.put("dh", dh);
            map.put("mm", mm);
            NetWork.getNetVolue(ServiceCode.GET_DH_LOGIN, map, ServiceCode.NETWORK_GET, null);
        } else {
            gotoSomeActivity(this, AtyTag.ATY_Login, false);
            finish();
        }
    }

    private void setAnimo(ImageView img) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(img, "alpha", 1f, 1f, 1f);
//        animator.setDuration(1500);
//        animator.start();
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                getLocationDate();
//            }
//        });
//        handler.sendEmptyMessageDelayed(0,3500);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_aplha_in, R.anim.activity_aplha_out);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getLocationDate();
        }
    };


}
