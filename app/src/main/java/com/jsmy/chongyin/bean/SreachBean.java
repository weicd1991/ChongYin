package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/18.
 */

public class SreachBean {

    /**
     * check : 6.12
     * code : Y
     * data : {"bdate":"","isgq":"Y","ishy":"N","nc":"公子小白","qm":"","tx":"http://wx.qlogo.cn/mmopen/4QtDCkraacNqh9EDaMYCtCpWCdFAgZ7cwg3EpR5YhImnwZqZUqTZeI4So8giawZscFv0ON4nKZkEEib5icrPQAqdw/0","vipdj":"0","xb":"男","yhids":10053,"zt":"3"}
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
         * bdate :
         * isgq : Y
         * ishy : N
         * nc : 公子小白
         * qm :
         * tx : http://wx.qlogo.cn/mmopen/4QtDCkraacNqh9EDaMYCtCpWCdFAgZ7cwg3EpR5YhImnwZqZUqTZeI4So8giawZscFv0ON4nKZkEEib5icrPQAqdw/0
         * vipdj : 0
         * xb : 男
         * yhids : 10053
         * zt : 3
         */

        private String bdate;
        private String isgq;
        private String ishy;
        private String nc;
        private String qm;
        private String tx;
        private String vipdj;
        private String xb;
        private int yhids;
        private String zt;

        public String getBdate() {
            return bdate;
        }

        public void setBdate(String bdate) {
            this.bdate = bdate;
        }

        public String getIsgq() {
            return isgq;
        }

        public void setIsgq(String isgq) {
            this.isgq = isgq;
        }

        public String getIshy() {
            return ishy;
        }

        public void setIshy(String ishy) {
            this.ishy = ishy;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        public String getQm() {
            return qm;
        }

        public void setQm(String qm) {
            this.qm = qm;
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

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public int getYhids() {
            return yhids;
        }

        public void setYhids(int yhids) {
            this.yhids = yhids;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }
    }
}
