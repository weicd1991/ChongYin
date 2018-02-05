package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/16.
 */

public class VIPInfobean {

    /**
     * check : 6.04
     * code : Y
     * data : {"maxjyz":600,"nc":"巧言令色","sj":"2017-08-31 15:17","tx":"http://192.168.1.150:8080/chongyin/pictureUpload/2017/201705/20170516/91729a6872c14c7fa56bf3faf0408987.jpg","vipdj":"1","vipjyz":0}
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
         * maxjyz : 600
         * nc : 巧言令色
         * sj : 2017-08-31 15:17
         * tx : http://192.168.1.150:8080/chongyin/pictureUpload/2017/201705/20170516/91729a6872c14c7fa56bf3faf0408987.jpg
         * vipdj : 1
         * vipjyz : 0
         */

        private int maxjyz;
        private String nc;
        private String sj;
        private String tx;
        private String vipdj;
        private int vipjyz;

        public int getMaxjyz() {
            return maxjyz;
        }

        public void setMaxjyz(int maxjyz) {
            this.maxjyz = maxjyz;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        public String getSj() {
            return sj;
        }

        public void setSj(String sj) {
            this.sj = sj;
        }

        public String getTx() {
            return tx;
        }

        public void setTx(String tx) {
            this.tx = tx;
        }

        public String getVipdj() {
            return vipdj;
        }

        public void setVipdj(String vipdj) {
            this.vipdj = vipdj;
        }

        public int getVipjyz() {
            return vipjyz;
        }

        public void setVipjyz(int vipjyz) {
            this.vipjyz = vipjyz;
        }
    }
}
