package com.jsmy.chongyin.xinlangweibo;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/5.
 */

public class WeiboLogin {
    private AuthInfo mAuthInfo;
    public SsoHandler mSsoHandler;
    private Activity mActivity;
    private Oauth2AccessToken mAccessToken;

    private static WeiboLogin instance = new WeiboLogin();
    private static String TAG = "weibo";

    private String openid;

    /**
     * 注销回调
     */
    private WeiboLogin() {
    }

    public static WeiboLogin getInstance() {
        return WeiboLogin.instance;
    }

    public void Weibologin(Activity activity) {
        this.mActivity = activity;
        if (mAuthInfo == null) {
            mAuthInfo = new AuthInfo(MyApplication.getMyApplication(), Constants.WEIBO_APP_KEY, Constants.WEIBO_REDIRECT_URL, Constants.WEIBO_SCOPE);
        }
        if (mSsoHandler == null) {
            mSsoHandler = new SsoHandler(mActivity);
        }
        mAccessToken = AccessTokenKeeper.readAccessToken(mActivity);
//        try {
//            if (mAccessToken.isSessionValid()) {
//                String uid = mAccessToken.getUid();
//                //这个地方的UsersAPI ,是官方的demo的依赖库module里面的，
//                UsersAPI usersAPI = new UsersAPI(mActivity, Constants.WEIBO_APP_KEY, mAccessToken);
//                usersAPI.show(Long.parseLong(uid), new SinaRequestListener(mActivity));
//                // 保存 Token 到 SharedPreferences
//                AccessTokenKeeper.writeAccessToken(mActivity, mAccessToken);
//            } else {
//                mSsoHandler.authorize(new SelfWbAuthListener());
//            }
//        } catch (Exception e) {
        mSsoHandler.authorize(new SelfWbAuthListener());
//        }
    }

    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link "onActivityResult"} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     * 该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    //从这里获取用户输入的 电话号码信息
                    String uid = mAccessToken.getUid();
                    if (mAccessToken.isSessionValid()) {
                        //这个地方的UsersAPI ,是官方的demo的依赖库module里面的，
                        UsersAPI usersAPI = new UsersAPI(mActivity, Constants.WEIBO_APP_KEY, mAccessToken);
                        usersAPI.show(Long.parseLong(uid), new SinaRequestListener(mActivity));
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(mActivity, mAccessToken);
//                        Toast.makeText(mActivity, "授权成功", Toast.LENGTH_SHORT).show();
                        ToastUtil.showShort(mActivity, "授权成功!");
                    } else {
                        // 以下几种情况，您会收到 Code：
                        // 1. 当您未在平台上注册的应用程序的包名与签名时；
                        // 2. 当您注册的应用程序包名与签名不正确时；
                        // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                        String code = token.getBundle().getString("code");
                        String message = "授权失败";
                        if (!TextUtils.isEmpty(code)) {
                            message = message + "\nObtained the code: " + code;
                        }
//                        Toast.makeText(MyApplication.getMyApplication(), message, Toast.LENGTH_LONG).show();
                        ToastUtil.showShort(MyApplication.getMyApplication(), message);
                    }

                }
            });
        }

        @Override
        public void cancel() {
//            Toast.makeText(mActivity,
//                    "取消授权", Toast.LENGTH_LONG).show();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showShort(mActivity.getApplicationContext(), "取消授权!");
                }
            });
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
//            Toast.makeText(mActivity,
//                    errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
            ToastUtil.showShort(mActivity, errorMessage.getErrorMessage());
        }
    }

    public class SinaRequestListener implements RequestListener {

        private Activity mActivity;

        public SinaRequestListener(Activity activity) {
            this.mActivity = activity;
        }

        @Override
        public void onComplete(String response) {
            MyLog.showLog(TAG, "" + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                openid = jsonObject.getString("idstr");// 唯一标识符(uid)
                SharePrefUtil.saveString(mActivity, "openid", openid);
                SharePrefUtil.saveString(mActivity, "dl", "WB");
                String nc = jsonObject.getString("name");// 姓名
                String xb = jsonObject.getString("gender");
                if ("m".equals(xb)) {
                    xb = "男";
                } else {
                    xb = "女";
                }
                String tx = jsonObject.getString("avatar_hd");//头像
                //通过获取到的用户数据，你可以进行自己的业务逻辑操作
                //do something Gson解析数据
                if (null != MyApplication.getMyApplication().userInfo) {
                    EventBus.getDefault().post(new DowloadEvent("WB" + "_" + openid, "openid"));
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("isAND", "Y");
                    map.put("dl", "WB");
                    map.put("openid", openid);
                    map.put("nc", nc);
                    map.put("tx", tx);
                    map.put("xb", xb);
                    NetWork.getNetVolue(ServiceCode.GET_REGISTER, map, ServiceCode.NETWORK_GET, null);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            MyLog.showLog(TAG, e.toString());
        }
    }

    /**
     * 调用 {@link LogoutAPI#logout(RequestListener)} 来注销。
     */
    public void logout(Activity activity) {
        this.mActivity = activity;
        new LogoutAPI(mActivity, Constants.WEIBO_APP_KEY, AccessTokenKeeper.readAccessToken(mActivity)).logout(new RequestListener() {
            @Override
            public void onComplete(String response) {
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        if (obj.isNull("error")) {
                            String value = obj.getString("result");

                            // 注销成功
                            if ("true".equalsIgnoreCase(value)) {
                                // XXX: 考虑是否需要将 AccessTokenKeeper 放到 SDK 中？？
                                //AccessTokenKeeper.clear(getContext());
                                // 清空当前 Token
                                MyLog.showLog(TAG, "注销成功");
                                mAccessToken = null;

                            }
                        } else {
                            String error_code = obj.getString("error_code");
                            if (error_code.equals("21317")) {
                                mAccessToken = null;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onWeiboException(WeiboException e) {
                MyLog.showLog(TAG, "WeiboException： " + e.getMessage());
                // 注销失败
            }
        });
    }

}
