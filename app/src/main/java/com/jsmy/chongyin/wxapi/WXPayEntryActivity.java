package com.jsmy.chongyin.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.utils.MD5;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.ToastUtil;
import com.qq.e.comm.util.Md5Util;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPay";

    private IWXAPI api;

    private String partnerId;
    private String prepayId;
    private String nonceStr;
    private String timeStamp;
    private String sign;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.WEIXIN_APP_ID, false);
        api.handleIntent(getIntent(), this);
        api.registerApp(Constants.WEIXIN_APP_ID);
        partnerId = getIntent().getStringExtra("partnerId");
        prepayId = getIntent().getStringExtra("prepayId");
        nonceStr = getIntent().getStringExtra("nonceStr");
        timeStamp = getIntent().getStringExtra("timeStamp");
        sign = getIntent().getStringExtra("sign");
        try {
            weChatPay(this);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        MyLog.showLog(TAG, "onWXPay");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        MyLog.showLog(TAG, "onPayFinish, errCode = " + req.openId);
    }

    @Override
    public void onResp(BaseResp resp) {
        MyLog.showLog(TAG, "onPayFinish, errCode = " + resp.errCode);
        MyLog.showLog(TAG, "onPayFinish, errStr = " + resp.errStr);
        MyLog.showLog(TAG, "onPayFinish, transaction = " + resp.transaction);
        MyLog.showLog(TAG, "onPayFinish, openId = " + resp.openId);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
//                Toast.makeText(MyApplication.getMyApplication(), "支付成功", Toast.LENGTH_SHORT).show();
                ToastUtil.showShort(MyApplication.getMyApplication(),"支付成功!");
                EventBus.getDefault().post(new DowloadEvent("支付成功","wx"));
            } else {
//                Toast.makeText(MyApplication.getMyApplication(), "支付失败", Toast.LENGTH_SHORT).show();
                ToastUtil.showShort(MyApplication.getMyApplication(),"支付失败!");
                EventBus.getDefault().post(new DowloadEvent("支付失败","wxfail"));
            }
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        MyLog.showLog(TAG, "onDestroy");
        super.onDestroy();
    }

    //支付
    public void weChatPay(Context context) throws NoSuchAlgorithmException {
        PayReq request = new PayReq();

        request.appId = Constants.WEIXIN_APP_ID;

        request.packageValue = "Sign=WXPay";//暂填写固定值Sign=WXPay

        request.partnerId = this.partnerId;//商户号
        MyLog.showLog(TAG, "partnerId " + this.partnerId);

        request.prepayId = this.prepayId;//微信返回的支付交易会话ID
        MyLog.showLog(TAG, "prepayId " + this.prepayId);

        request.nonceStr = this.nonceStr;//随机字符串，不长于32位。推荐随机数生成算法
        MyLog.showLog(TAG, "nonceStr " + this.nonceStr);

        request.timeStamp = String.valueOf(genTimeStamp());//时间戳，请见接口规则-参数规定
        MyLog.showLog(TAG, "timeStamp " + this.timeStamp);

        List<WXRequset> params = new LinkedList<>();
        params.add(new WXRequset("appid", request.appId));
        params.add(new WXRequset("noncestr", request.nonceStr));
        params.add(new WXRequset("package", request.packageValue));
        params.add(new WXRequset("partnerid", request.partnerId));
        params.add(new WXRequset("prepayid", request.prepayId));
        params.add(new WXRequset("timestamp", request.timeStamp));

        request.sign = genAppSign(params);//签名，详见签名生成算法
        MyLog.showLog(TAG, "sign " + request.sign);

        api.sendReq(request);
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String genAppSign(List<WXRequset> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getKey());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append("RArtiLGHbkqJB6Hwqwiiwd6KM3VW7Dan");

        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e(TAG, appSign);
        return appSign;
    }



}
