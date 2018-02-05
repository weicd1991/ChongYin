package com.jsmy.chongyin.wxapi;

import android.content.Context;

import com.jsmy.chongyin.contents.Constants;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/5/10.
 */

public class WeixinPay {
    public static void weChatPay(Context context) {
        IWXAPI mApi = WXAPIFactory.createWXAPI(context, Constants.WEIXIN_APP_ID);
        mApi.registerApp(Constants.WEIXIN_APP_ID);
        PayReq request = new PayReq();
        request.appId = Constants.WEIXIN_APP_ID;
        request.partnerId = "1900000109";//商户号
        request.prepayId = "1101000000140415649af9fc314aa427";//微信返回的支付交易会话ID
        request.packageValue = "Sign=WXPay";//暂填写固定值Sign=WXPay
        request.nonceStr = "1101000000140429eb40476f8896f4c9";//随机字符串，不长于32位。推荐随机数生成算法
        request.timeStamp = "1398746574";//时间戳，请见接口规则-参数规定
        request.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";//签名，详见签名生成算法
        mApi.sendReq(request);
    }
}
