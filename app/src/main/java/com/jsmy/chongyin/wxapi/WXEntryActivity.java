package com.jsmy.chongyin.wxapi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jsmy.chongyin.NetWork.Gitapi;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.LoginActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.LoginBean;
import com.jsmy.chongyin.bean.RegisterBean;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.ActivityTack;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    /**
     * 微信登录页面
     *
     * @author kevin_chen 2016-12-10 下午19:03:45
     * @version v1.0
     */
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ServiceCode.SERVICE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private Gitapi gitapi;
    private Call<String> call;
    private String result;
    private String openid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mWeixinAPI = WXAPIFactory.createWXAPI(this, Constants.WEIXIN_APP_ID, false);
        MyApplication.mWxApi.handleIntent(getIntent(), this);
        MyLog.showLog("WXEntryActivity", "onCreate");
        if (MyApplication.getMyApplication().isFirst) {
//            loginToWeiXin(this);
            MyApplication.getMyApplication().isFirst = false;
        } else {
            WXEntryActivity.this.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLog.showLog("WXEntryActivity", "结束微信登录");
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        MyLog.showLog("WXEntryActivity", "onNewIntent");
//        setIntent(intent);
//        mWeixinAPI.handleIntent(intent, this);//必须调用此句话
//    }

//    public void loginToWeiXin(Context context) {
//        mWeixinAPI.registerApp(Constants.WEIXIN_APP_ID);
//        if (mWeixinAPI != null && mWeixinAPI.isWXAppInstalled()) {
//            SendAuth.Req req = new SendAuth.Req();
//            req.scope = "snsapi_userinfo";
//            req.state = "wechat_sdk_demo_test_neng";
//            mWeixinAPI.sendReq(req);
//        } else {
////            Toast.makeText(context, "没有安装微信,请选择其他方式登录！", Toast.LENGTH_SHORT).show();
//            ToastUtil.showShort(context, "没有安装微信,请选择其他方式登录！");
//        }
//    }

    //微信发送的请求将回调到onReq方法
    @Override
    public void onReq(BaseReq req) {
        MyLog.showLog("WXEntryActivity", "onReq");
    }

    //发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
        MyLog.showLog("WXEntryActivity", "onResp");
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                MyLog.showLog("WXEntryActivity", "ERR_OK");
                //发送成功
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                if (sendResp != null) {
                    String code = sendResp.code;
                    getAccess_token(code);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                MyLog.showLog("WXEntryActivity", "ERR_USER_CANCEL");
                WXEntryActivity.this.finish();
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                MyLog.showLog("WXEntryActivity", "ERR_AUTH_DENIED");
                WXEntryActivity.this.finish();
                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }

    }

    /**
     * 获取openid accessToken值用于后期操作
     *
     * @param code 请求码
     */
    private void getAccess_token(final String code) {
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + Constants.WEIXIN_APP_ID
                + "&secret="
                + Constants.WEIXIN_APP_SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
        //网络请求，根据自己的请求方式
        gitapi = retrofit.create(Gitapi.class);
        Map<String, String> map = new HashMap<>();
        call = gitapi.getNetWork(path, map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                result = response.body();
                JSONObject jsonObject = null;
                try {
                    MyLog.showLog("WXEntryActivity", result);
                    jsonObject = new JSONObject(result);
                    openid = jsonObject.getString("openid").toString().trim();

                    SharePrefUtil.saveString(WXEntryActivity.this, "openid", openid);
                    SharePrefUtil.saveString(WXEntryActivity.this, "dl", "WX");
                    String access_token = jsonObject.getString("access_token").toString().trim();
                    getUserMesg(access_token, openid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("WXEntryActivity", t.toString());
            }
        });

    }


    /**
     * 获取微信的个人信息
     *
     * @param access_token
     * @param openid
     */
    private void getUserMesg(final String access_token, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
        MyLog.showLog("WXEntryActivity", "getUserMesg：" + path);
        //网络请求，根据自己的请求方式
        gitapi = retrofit.create(Gitapi.class);
        Map<String, String> map = new HashMap<>();
        call = gitapi.getNetWork(path, map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                JSONObject jsonObject = null;
                try {
                    result = response.body();
                    jsonObject = new JSONObject(result);
                    MyLog.showLog("WXEntryActivity", result);
                    String nc = jsonObject.getString("nickname");
                    int sex = Integer.parseInt(jsonObject.get("sex").toString());
                    String tx = jsonObject.getString("headimgurl");
                    String xb = "男";
                    if (1 == sex) {
                        xb = "男";
                    } else if (2 == sex) {
                        xb = "女";
                    }
                    if (null != MyApplication.getMyApplication().userInfo) {
                        EventBus.getDefault().post(new DowloadEvent("WX" + "_" + openid, "openid"));
                        endWXDL();
                    } else {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("isAND", "Y");
                        map.put("dl", "WX");
                        map.put("openid", openid);
                        map.put("nc", nc);
                        map.put("tx", tx);
                        map.put("xb", xb);
                        NetWork.getNetVolue(ServiceCode.GET_REGISTER, map, ServiceCode.NETWORK_GET, null);
                        WXEntryActivity.this.finish();
//                        endWXDL();
//                        registerWX(map);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("WXEntryActivity", t.toString());
            }
        });
    }

    private void endWXDL() {
        WXEntryActivity.this.finish();
    }

    private void registerWX(Map<String, String> map) {
        gitapi = retrofit.create(Gitapi.class);
        Call<String> callLog = gitapi.getNetWork(ServiceCode.SERVICE_URL + ServiceCode.GET_REGISTER, map);
        callLog.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String data = response.body();
                MyLog.showLog("WXEntryActivity", data);
                Gson gson = new Gson();
                RegisterBean.DataBean bean = gson.fromJson(data, RegisterBean.class).getData();
                String yhid = "" + bean.getYhid();
                SharePrefUtil.saveString(WXEntryActivity.this, "yhid", yhid);
                String openid = SharePrefUtil.getString(WXEntryActivity.this, "openid", "");
                loginWX(yhid, openid);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.showLog("WXEntryActivity", t.toString());
            }
        });
    }

    private void loginWX(final String yhid, final String openid) {
        if (!"".equals(yhid) && !"".equals(openid)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("yhid", yhid);
            map.put("openid", openid);
            NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
            gitapi = retrofit.create(Gitapi.class);
            Call<String> callLog = gitapi.getNetWork(ServiceCode.SERVICE_URL + ServiceCode.GET_REGISTER, map);
            callLog.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    MyLog.showLog("WXEntryActivity", response.body());
                    Gson gson = new Gson();
                    SharePrefUtil.saveString(getApplicationContext(), "yhid", yhid);
                    SharePrefUtil.saveString(getApplicationContext(), "openid", openid);
                    MyApplication.getMyApplication().userInfo = gson.fromJson(response.body(), LoginBean.class).getData();
                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
                        showCloseWindow();
                    } else {
                        Intent intent = new Intent(WXEntryActivity.this, LoginActivity.class);
                        intent.putExtra("iswx", "iswx");
                        intent.putExtra("yhid", yhid);
                        intent.putExtra("openid", openid);
                        overridePendingTransition(R.anim.activity_aplha_in, R.anim.activity_aplha_out);
                        WXEntryActivity.this.startActivity(intent);
                        WXEntryActivity.this.finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    MyLog.showLog("WXEntryActivity", t.toString());
                }
            });
        }
    }

    public void setAliasByYhid(final String yhid) {

        MyLog.showLog("JJJ", " --- " + yhid + " ---");
        //这是别名
        JPushInterface.setAlias(this, yhid, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                MyLog.showLog("JJJ", " --- " + yhid + " ---" + i);
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
    }

    //禁用提示框
    public void showCloseWindow() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        tvData.setVisibility(View.GONE);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tvCancel.setVisibility(View.GONE);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("您的账号已被禁用，解封入口www.urmer.com");
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ActivityTack.getInstanse().exit();
            }
        });
        dialog.show();
    }

}
