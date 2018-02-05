package com.jsmy.chongyin.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.alibaba.PayResult;
import com.jsmy.chongyin.alibaba.SignUtils;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AlipayActivity extends Activity {

    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_alipay);
        info = getIntent().getStringExtra("info");
        testAlipay(info);
    }

    // 商户PID
    public static final String PARTNER = "2088621969664511";
    // 商户收款账号
    public static final String SELLER = "Develop@urmer.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnbGd0UKZpw1NNieR14sgu1DobTA1d3xhjyWgCozZUkEa1KJSpwPJRv2k/26Dtjc1x8OT1qFkBOEhB9lJWOpmtmjpUnkSofuk39yVXSJn7UGX1Rk+wQO82w5GOrOI543tqShBVQoZ6uUZva8DsJjCGH0DY0iHEbbTIYId5yOG/KszYd5P+D3kM3vZo9fkjSCbqCD8zDoOtOfPlWs6tsHOwcDs0j5FuyBgwmlNRoyJ5rxwmDfCoq6jNaJNlVBk0UCAuqd9rJOlQ+cMObQtl2ZAwo6tG9hz9N6kwqKaTMnxUpWlNZ/tGhitNz7mAsahL59q5LRPqJhJSNUuXOVb8nA1PAgMBAAECggEARmKsm5nMXGphexedlHHi9s0CILe4Zt6Dwm/J6lHzZVnCYCbPWQ5k0TlHZjFPVo9m75/2odBEmt6uggysUNQSB4nvO+PNuYihQ4YDjM5Y7e6+7n7AwbiK2NsfNnw4H14jZGv+sdll7/I0SmNil+T2QWyiNoUg7ROyDQuH6Qsq3NLTB7D1rnc7bDLZcQlHe39gy8o8sSqG67/VLjvJa+4o+VvWJjm0bOts43peXS/tsi+UpQrIdYlLkx8Mg9L13hIeB42uiJFdR0nNra4BEF0eVYiRRuPvXDuRPE90SFIO6b4fGaRSDNcChOlQ9d9Du9ealcuvySZRb40MdLiTL0HgAQKBgQDeU7LNNSAkpIA4pPUDdiRSPWpb5MTS8VZMdM0ktrEilWLTGQpDaBsAbgih3915/OdpVo+D5XtA1QaTD7GewvumV1PVG9TPjVSgc3KnBSQPK5HjH9ObQcQ5UxcP0K9w1FswUBSOF899oJBCTtNVsxp/6kXpm29d/Kx2J340igfCgQKBgQDAx+2I8iTByvNaa/gb/MKjguNiTtDASQ7CEYxBO8Irkoina1rWJBzyKHxEs1lUUuHMyKkdX2tEUvgn2j+GxbJ30ZP6DB4YWIp6ZX/BdPG5ouMJuAi60VoJ9Q0fm4C/bIuNgecXGFRq0NFAMk/2LFINYNVGEBG8wOJd5NTtv2RHzwKBgFU6GqnluquxT+HnAnHQQT4xgus1OGpwz5dBkz6GtkK+p4Kq769FYkx44eBD9CAGXiOYZi22s9f3kaygcIOe1V8/zMg+AgeKcy2K9CGoq9kQ57z8f17eCD29Z3LNmeOT1F0h5a9O22CaQ5LXqtYIcZklkqVpy3hpGye0HgJAA5eBAoGBAJDBz3iksrseJSvBpTWGz7SuJQX9vryUX1OUj6ewMrY7OwfUEgp4ZY/knKONtMcf0VPPXUWkD8znLFXoiQkvTDqykZYSIUsQuTL9IjhnEtfdGvSCT3PQnfaaHrecMcoLvKKOZ7Ms3r6yfF3jKa3lS9ul2Z/ZkygUAiaVHNpFfAx7AoGABkF78OhPf//pIdHF7EX70IdgzfX5f2/P2MfZgoycVKHNIaRkaShqptpv/u27ibBC1yIRs7Bvui/4uBicefw8JE8jaOwOVel6SiQ7u0bF0l1Y79cLMTWnBveH+J8tJVHuCsdGoX/oAXGEJJpdaBvRWkuQ2X/BgL1qinmH9IkV0Zg=";


    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    MyLog.showLog("VVV", payResult.toString());
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        Toast.makeText(AlipayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        ToastUtil.showShort(AlipayActivity.this,"支付成功!");
                        EventBus.getDefault().post(new DowloadEvent("支付成功","zfb"));
                    } else {
                        EventBus.getDefault().post(new DowloadEvent("支付失败","zfbfail"));
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
//                            Toast.makeText(AlipayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                            ToastUtil.showShort(AlipayActivity.this,"支付结果确认中!");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            Toast.makeText(AlipayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            ToastUtil.showShort(AlipayActivity.this,"支付失败!");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
            finish();
        }

    };


    public void testAlipay(final String info) {
        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(AlipayActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(info, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }


}
