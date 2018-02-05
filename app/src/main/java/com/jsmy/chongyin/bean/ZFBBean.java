package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ZFBBean {

    /**
     * check : 1.14
     * code : Y
     * data : {}
     * date :
     * alipay_sdk=alipay-sdk-java-dynamicVersionNo&
     * app_id=2017051607251464&
     * biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%222017053117032225417114%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95Java%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221%22%7D&
     * charset=utf-8&
     * format=json&
     * method=alipay.trade.app.pay¬ify_url=https%3A%2F%2Fopenapi.alipay.com%2Fgateway.do&
     * sign=gRkGEytW8Up5jM7JxXvdqzmDpxPS1L6ZdJM4L54YTQtb4GhKRTmc%2BXFUkbU1J8xP3qY9efyIaQcIppZ8T0rf7yovxX%2FvfXyHyvv2MzVILTmcmJZwqvykVAdmLYmLwfrNK%2F9W%2BfkOTJGS7VNFJK28gN9HMpwsZq03ZvisZINMnFxoTBKgWXTxU7X%2F%2F8cW24%2F6aLbwBQ5xIuS1gICWiaWsCorn8DxIFNUt5j6NbebE3JK49ndjz2QHnPZDoaT2UYHhZP9Z2iVA%2BNMgNQYHfXTGHC5mVL%2FYG9DFaTXaemT2frObvJkJP1ilr1j%2FiPBGylT8XJn6wdn%2FdozVaBNzRirr3w%3D%3D&
     * sign_type=RSA2×tamp=2017-05-31+17%3A03%3A22&
     * version=1.0
     * msg : 操作成功！
     */

    private String check;
    private String code;
    private DataBean data;
    private String date;
    private String msg;

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
    }
}
