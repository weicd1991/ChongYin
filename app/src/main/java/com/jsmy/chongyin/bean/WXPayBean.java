package com.jsmy.chongyin.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/23.
 */

public class WXPayBean {

    /**
     * check : 1.13
     * code : Y
     * data : {"mch_id":"1480112312","nonceStr":"000000005cbe9292015cbe9bbc5a0009","package":"Sign=WXPay","paySign":"31CC8DD49C2CCADF32F207D345DC7A15","prepay_id":"wx201706191228165f65687a4a0095558866","timeStamp":"1497846496","total_fee":"10"}
     * msg : 操作成功！
     * noncestr : 000000005cbe9292015cbe9bbc5a0009
     * packageValue : Sign=WXPay
     * partnerid : 1480112312
     * prepay_id : wx201706191228165f65687a4a0095558866
     * timeStamp : 1497846496
     */

    private String check;
    private String code;
    private DataBean data;
    private String msg;
    private String noncestr;
    private String packageValue;
    private String partnerid;
    private String prepay_id;
    private int timeStamp;

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

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public static class DataBean {
        /**
         * mch_id : 1480112312
         * nonceStr : 000000005cbe9292015cbe9bbc5a0009
         * package : Sign=WXPay
         * paySign : 31CC8DD49C2CCADF32F207D345DC7A15
         * prepay_id : wx201706191228165f65687a4a0095558866
         * timeStamp : 1497846496
         * total_fee : 10
         */

        private String mch_id;
        private String nonceStr;
        @SerializedName("package")
        private String packageX;
        private String paySign;
        private String prepay_id;
        private String timeStamp;
        private String total_fee;

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }
    }
}
