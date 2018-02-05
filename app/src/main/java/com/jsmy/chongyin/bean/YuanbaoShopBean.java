package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/25.
 */

public class YuanbaoShopBean {

    /**
     * check : 2.04
     * code : Y
     * data : {"isgmxslb":"N"}
     * msg : 操作成功！
     */

    private String check;
    private String code;
    private DataBean data;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * isgmxslb : N
         */

        private String isgmxslb;

        public String getIsgmxslb() {
            return isgmxslb;
        }

        public void setIsgmxslb(String isgmxslb) {
            this.isgmxslb = isgmxslb;
        }
    }
}

