package com.jsmy.chongyin.wxapi;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/5/10.
 */

public class WeixinLogin {
    public static void loginToWeiXin(Context context) {
//        if (mWeixinAPI != null && mWeixinAPI.isWXAppInstalled()) {
//            SendAuth.Req req = new SendAuth.Req();
//            req.scope = "snsapi_userinfo";
//            req.state = "wechat_sdk_demo_test_neng";
//            mWeixinAPI.sendReq(req);
//        } else {
////            Toast.makeText(context, "没有安装微信,请选择其他方式登录！", Toast.LENGTH_SHORT).show();
//            ToastUtil.showShort(context, "没有安装微信,请选择其他方式登录！");
//        }
        context.startActivity(new Intent(context, WXEntryActivity.class));
    }
}
