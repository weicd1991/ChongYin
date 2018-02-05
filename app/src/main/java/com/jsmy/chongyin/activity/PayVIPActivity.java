package com.jsmy.chongyin.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.alibaba.AliPay;
import com.jsmy.chongyin.alibaba.PayResult;
import com.jsmy.chongyin.alibaba.SignUtils;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.WXPayBean;
import com.jsmy.chongyin.bean.ZFBBean;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.wxapi.WXPayEntryActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class PayVIPActivity extends BaseActivity {

    @BindView(R.id.tv_moon)
    TextView tvMoon;
    @BindView(R.id.tv_je)
    TextView tvJe;
    @BindView(R.id.check_weixin)
    CheckBox checkWeixin;
    @BindView(R.id.check_zfb)
    CheckBox checkZfb;
    @BindView(R.id.tv_huiyuan)
    TextView tvHuiyuan;

    String detail = "";
    String body = "";
    Double totalFee;
    private String zflx = "微信";

    private String number = "";

    private boolean isWXPay = true;

    private WXPayBean.DataBean wxPay;
    private WXPayBean wx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_pay_vip;
    }

    @Override
    protected void initView() {
        if ("huiyuan".equals(getIntent().getStringExtra("payType"))) {
            tvHuiyuan.setText("会员（月）");
            tvMoon.setText("x" + getIntent().getStringExtra("moon"));
            detail = "会员";
            body = "开通会员";
        } else {
            tvHuiyuan.setText("元宝 ");
            tvMoon.setText("x" + getIntent().getStringExtra("moon"));
            detail = "元宝";
            body = "购买元宝";
        }
        tvJe.setText("" + getIntent().getStringExtra("price"));
        totalFee = Double.parseDouble(getIntent().getStringExtra("price"));
        number = getIntent().getStringExtra("moon");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.WX_PAY_NUM:
                    wx = gson.fromJson(result, WXPayBean.class);
                    wxPay = gson.fromJson(result, WXPayBean.class).getData();
                    startWXPay();
                    break;
                case ServiceCode.ALI_PAY_NUM:
                    ZFBBean zfbBean = gson.fromJson(result, ZFBBean.class);
                    startAliPay(zfbBean.getDate());
                    break;
                case ServiceCode.UP_DATE_YB_SHOP_NUM:
                    getJCSJ();
                    finish();
                    break;
                case ServiceCode.UP_DATE_GM_VIP_NUM:
                    EventBus.getDefault().post(new DowloadEvent("close", "close"));
                    finish();
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
            if ("wx".equals(code)) {
                zflx = "微信";
                EventBus.getDefault().post(new DowloadEvent("close", "close"));
//                if ("huiyuan".equals(getIntent().getStringExtra("payType"))) {
//                    setPayHuiYuan();
//                } else {
//                    setPayBack();
//                }
                ToastUtil.showShort(getApplicationContext(), result);
                finish();
            } else if ("zfb".equals(code)) {
                zflx = "支付宝";
                aliPay = null;
                EventBus.getDefault().post(new DowloadEvent("close", "close"));
//                if ("huiyuan".equals(getIntent().getStringExtra("payType"))) {
//                    setPayHuiYuan();
//                } else {
//                    setPayBack();
//                }
                ToastUtil.showShort(getApplicationContext(), result);
                finish();
            } else if ("zfbfail".equals(code)) {
                aliPay = null;
                ToastUtil.showShort(getApplicationContext(), result);
            } else if ("wxfail".equals(code)) {
                ToastUtil.showShort(getApplicationContext(), result);
            }
        }

    }

    //访问基础数据
    private void getJCSJ() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SY_JCSJ, map, ServiceCode.NETWORK_GET, null);
    }

    //调微信支付
    private void startWXPay() {
        Intent intent = new Intent(this, WXPayEntryActivity.class);
        //商户号
        intent.putExtra("partnerId", wx.getPartnerid() + "");
        //微信返回的支付交易会话ID
        intent.putExtra("prepayId", wx.getPrepay_id() + "");
        //随机字符串，不长于32位。推荐随机数生成算法
        intent.putExtra("nonceStr", wx.getNoncestr() + "");
        //时间戳，请见接口规则-参数规定
        intent.putExtra("timeStamp", wx.getTimeStamp() + "");
        //签名，详见签名生成算法
        intent.putExtra("sign", wxPay.getPaySign() + "");
        startActivity(intent);
    }

    //微信服务器下单
    private void getWXPay(String detail, String body, Double totalFee) {
        map.clear();
        map.put("isAND", "Y");
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        if ("huiyuan".equals(getIntent().getStringExtra("payType"))) {
            map.put("detail", "1");//商品名称
            map.put("body", "1");//开通会员
        } else {
            map.put("detail", "2");//商品名称
            map.put("body", "2");//购买元宝
        }
//        map.put("detail", detail);//商品名称
//        map.put("body", body);//购买元宝
        map.put("totalFee", (int) (totalFee * 100) + "");//总金额
//        map.put("totalFee", (int) (0.01 * 100) + "");//总金额 测试专用
        map.put("cns", number);//数量
        map.put("isxs", MyApplication.getMyApplication().isxs);
        NetWork.getNetVolue(ServiceCode.WX_PAY, map, ServiceCode.NETWORK_GET, null);
    }

    //支付宝服务器下单
    private void getAlipay(String detail, String body, String totalFee) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        if ("huiyuan".equals(getIntent().getStringExtra("payType"))) {
            map.put("detail", "1");//商品名称
            map.put("body", "1");//开通会员
        } else {
            map.put("detail", "2");//商品名称
            map.put("body", "2");//购买元宝
        }
        map.put("totalFee", totalFee + "");//总金额
//        map.put("totalFee", 0.01 + "");//总金额 测试专用
        map.put("cns", number);//数量
        map.put("isxs", MyApplication.getMyApplication().isxs);
        NetWork.getNetVolue(ServiceCode.ALI_PAY, map, ServiceCode.NETWORK_GET, null);
    }

    //调支付宝支付
    AliPay aliPay;

    private void startAliPay(String info) {

        Intent intent = new Intent(this, AlipayActivity.class);
        intent.putExtra("info", info);
        startActivity(intent);

    }

    //购买元宝支付回调
    private void setPayBack() {
        MyLog.showLog("BBB", "元宝：" + "moon - " + moon + "price - " + price + " - ");
        map.clear();
        map.put("isAND", "Y");
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("ybcns", number);
        map.put("je", totalFee + "");
        map.put("zflx", zflx);
        map.put("isxs", MyApplication.getMyApplication().isxs);
        NetWork.getNetVolue(ServiceCode.UP_DATE_YB_SHOP, map, ServiceCode.NETWORK_GET, null);
    }

    //购买会员支付回调
    private void setPayHuiYuan() {
        MyLog.showLog("BBB", "会员：" + "moon - " + moon + "price - " + price + " - ");
        map.clear();
        map.put("isAND", "Y");
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("vipsj", number);
        map.put("je", totalFee + "");
        map.put("zflx", zflx);
        NetWork.getNetVolue(ServiceCode.UP_DATE_GM_VIP, map, ServiceCode.NETWORK_GET, null);
    }

    @OnClick({R.id.check_weixin, R.id.check_zfb, R.id.btn_check, R.id.img_close, R.id.rela_weixin, R.id.rela_zfb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_weixin:
                isWXPay = true;
                setCheckBox(checkWeixin);
                break;
            case R.id.check_zfb:
                isWXPay = false;
                setCheckBox(checkZfb);
                break;
            case R.id.rela_weixin:
                isWXPay = true;
                setCheckBox(checkWeixin);
                break;
            case R.id.rela_zfb:
                isWXPay = false;
                setCheckBox(checkZfb);
                break;
            case R.id.btn_check:
                choicePay();
                break;
            case R.id.img_close:
                finish();
                break;
        }
    }

    private void setCheckBox(CheckBox checkBox) {
        checkWeixin.setChecked(false);
        checkZfb.setChecked(false);
        checkBox.setChecked(true);
    }

    private void choicePay() {
        if (isWXPay) {
            getWXPay(detail, body, totalFee);
        } else {
            getAlipay(detail, body, totalFee + "");
        }
    }

}
