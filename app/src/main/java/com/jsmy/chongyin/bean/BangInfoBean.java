package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BangInfoBean {

    /**
     * check : 6.16
     * code : Y
     * data : {"isqq":"Y","isweibo":"N","isweinxin":"N"}
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
         * isqq : Y
         * isweibo : N
         * isweinxin : N
         */

        private String isqq;
        private String isweibo;
        private String isweinxin;

        public String getIsqq() {
            return isqq;
        }

        public void setIsqq(String isqq) {
            this.isqq = isqq;
        }

        public String getIsweibo() {
            return isweibo;
        }

        public void setIsweibo(String isweibo) {
            this.isweibo = isweibo;
        }

        public String getIsweinxin() {
            return isweinxin;
        }

        public void setIsweinxin(String isweinxin) {
            this.isweinxin = isweinxin;
        }
    }
}
