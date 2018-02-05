package com.jsmy.chongyin.TencentQQ;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.activity.LoginActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/5.
 */

public class TencentLogin {
    private Activity mActivity;
    private static TencentLogin instance = new TencentLogin();
    private static String TAG = "tencent";
    //需要腾讯提供的一个Tencent类
    private Tencent mTencent;
    //还需要一个IUiListener 的实现类（LogInListener implements IUiListener）
    public LogInListener mListener;


    private String openid;

    private TencentLogin() {
    }

    public static TencentLogin getInstance() {
        return TencentLogin.instance;
    }

    public void Tencentlogin(Activity activity) {
        this.mActivity = activity;
        //首先需要用APP ID 获取到一个Tencent实例
        mTencent = Tencent.createInstance(Constants.TENCENT_APP_ID, mActivity.getApplicationContext());
        //初始化一个IUiListener对象，在IUiListener接口的回调方法中获取到有关授权的某些信息
        // （千万别忘了覆写onActivityResult方法，否则接收不到回调）
        mListener = new LogInListener();

        //登录
        if (!mTencent.isSessionValid()) {
            mTencent.login(mActivity, "all", mListener);
        }
    }

    //登出
    public void tencentLogout() {
        mTencent = Tencent.createInstance(Constants.TENCENT_APP_ID, MyApplication.getMyApplication());
        mTencent.logout(MyApplication.getMyApplication());
    }

    private class LogInListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            JSONObject jsonObject = (JSONObject) o;
            initOpenidAndToken(jsonObject);
        }

        @Override
        public void onError(UiError uiError) {
//            Toast.makeText(mActivity, "QQ授权出错！", Toast.LENGTH_LONG).show();
            ToastUtil.showShort(mActivity,"QQ授权出错！");
        }

        @Override
        public void onCancel() {
//            Toast.makeText(mActivity, "QQ授权取消！", Toast.LENGTH_LONG).show();
            ToastUtil.showShort(mActivity,"QQ授权取消!");
        }
    }

    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            openid = jsonObject.getString("openid");

            SharePrefUtil.saveString(mActivity, "openid", openid);
            SharePrefUtil.saveString(mActivity, "dl", "QQ");
            String token = jsonObject.getString("access_token");
            String expires = jsonObject.getString("expires_in");
            mTencent.setAccessToken(token, expires);
            mTencent.setOpenId(openid);
            getUserInfo();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {
        //sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
        final QQToken mQQToken = mTencent.getQQToken();
        UserInfo userInfo = new UserInfo(mActivity, mQQToken);
        userInfo.getUserInfo(new IUiListener() {
                                 @Override
                                 public void onComplete(final Object o) {
                                     //通过获取到的用户数据，你可以进行自己的业务逻辑操作
                                     //do something Gson解析数据
                                     Gson gson = new Gson();
                                     TencentBean bean = gson.fromJson(o.toString(), TencentBean.class);

                                     if (null != MyApplication.getMyApplication().userInfo) {
                                         EventBus.getDefault().post(new DowloadEvent("QQ" + "_" + openid, "openid"));
                                     } else {
                                         Map<String, String> map = new HashMap<String, String>();
                                         map.put("isAND", "Y");
                                         map.put("dl", "QQ");
                                         map.put("openid", openid);
                                         map.put("nc", bean.getNickname());
                                         map.put("tx", bean.getFigureurl_qq_2());
                                         map.put("xb", bean.getGender());
                                         NetWork.getNetVolue(ServiceCode.GET_REGISTER, map, ServiceCode.NETWORK_GET, null);
                                     }

                                 }

                                 @Override
                                 public void onError(UiError uiError) {
                                     MyLog.showLog(TAG, "获取qq用户信息错误");
//                                     Toast.makeText(mActivity, "获取qq用户信息错误", Toast.LENGTH_SHORT).show();
                                     ToastUtil.showShort(mActivity,"获取qq用户信息错误!");
                                 }

                                 @Override
                                 public void onCancel() {
                                     MyLog.showLog(TAG, "获取qq用户信息取消");
//                                     Toast.makeText(mActivity, "获取qq用户信息取消", Toast.LENGTH_SHORT).show();
                                     ToastUtil.showShort(mActivity,"获取qq用户信息取消!");
                                 }

                             }
        );
    }


}
