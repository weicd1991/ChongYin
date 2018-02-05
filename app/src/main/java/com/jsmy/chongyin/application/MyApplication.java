package com.jsmy.chongyin.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;

import com.jsmy.chongyin.TencentQQ.TencentLogin;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.bean.LoginBean;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.wxapi.WXEntryActivity;
import com.qq.e.ads.cfg.MultiProcessFlag;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2017/4/5.
 */

public class MyApplication extends Application {
    public static boolean isDebug = false;
    public static String Tag_Now = "";
    private static MyApplication myApplication;
    public LoginBean.DataBean userInfo;
    public static int time;
    public int pm;
    public boolean isFirst = true;
    public String isFriend = "N";
    public String haoyouID = "";

    public static String latitude = "";//纬度
    public static String longitude = "";//经度
    public static String addressLine = "";//位置
    public static String countryName = "";//国家
    public static String locality = "";//城市
    public static String admin = "";//省份
    public static String ip = "";
    public static boolean isShowNet = false;

    public String isxs = "N";

    public Drawable drawable;

    public static int msgTime1 = 0;
    public static int msgTime2 = 0;
    public static int msgTime3 = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        //初始化sdk
        JPushInterface.setDebugMode(false);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
        Set<String> set = new HashSet<>();
        set.add("chongyin0");//名字任意，可多添加几个
        set.add("chongyin1");//名字任意，可多添加几个
        set.add("chongyin2");//名字任意，可多添加几个
        set.add("chongyin3");//名字任意，可多添加几个
        JPushInterface.setTags(this, set, null);//设置标签
        //这是别名
        JPushInterface.setAlias(this, "chongyin", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                switch (i) {
                    case 0:

                        break;
                    case 6002:

                        break;
                    default:

                        break;
                }
            }
        });
        /** 友盟 设置是否对日志信息进行加密, 默认false(不加密). */
        MobclickAgent.enableEncrypt(true);//6.0.0版本及以后

        //新浪微博
        WbSdk.install(this, new AuthInfo(this, Constants.WEIBO_APP_KEY, Constants.WEIBO_REDIRECT_URL, Constants.WEIBO_SCOPE));

        //腾讯广告
        MultiProcessFlag.setMultiProcess(true);

        MultiDex.install(this);

        registerToWX();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        TencentLogin.getInstance().tencentLogout();
    }
    public static IWXAPI mWxApi;

    private void registerToWX() {
        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, Constants.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(Constants.WEIXIN_APP_ID);
    }

    public static void loginToWeiXin() {
        if (mWxApi != null && mWxApi.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test_neng";
            mWxApi.sendReq(req);
        } else {
//            Toast.makeText(context, "没有安装微信,请选择其他方式登录！", Toast.LENGTH_SHORT).show();
            ToastUtil.showShort(getMyApplication().getApplicationContext(), "没有安装微信,请选择其他方式登录！");
        }
//        context.startActivity(new Intent(context, WXEntryActivity.class));
    }

}
