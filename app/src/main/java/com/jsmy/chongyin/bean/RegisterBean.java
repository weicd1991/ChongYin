package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/11.
 */

public class RegisterBean {

    /**
     * check : 1.01
     * code : Y
     * data : {"isQQ":"Y","isweibo":"N","isweiixn":"N","petid":"","petnc":"","pettx":"","qm":"","xb":"asdas","yhid":10009,"yhnc":"asdasd","yhtx":"asda"}
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
         * isQQ : Y
         * isweibo : N
         * isweiixn : N
         * petid :
         * petnc :
         * pettx :
         * qm :
         * xb : asdas
         * yhid : 10009
         * yhnc : asdasd
         * yhtx : asda
         */

        private String isQQ;
        private String isweibo;
        private String isweiixn;
        private String petid;
        private String petnc;
        private String pettx;
        private String qm;
        private String xb;
        private int yhid;
        private String yhnc;
        private String yhtx;

        public String getIsQQ() {
            return isQQ;
        }

        public void setIsQQ(String isQQ) {
            this.isQQ = isQQ;
        }

        public String getIsweibo() {
            return isweibo;
        }

        public void setIsweibo(String isweibo) {
            this.isweibo = isweibo;
        }

        public String getIsweiixn() {
            return isweiixn;
        }

        public void setIsweiixn(String isweiixn) {
            this.isweiixn = isweiixn;
        }

        public String getPetid() {
            return petid;
        }

        public void setPetid(String petid) {
            this.petid = petid;
        }

        public String getPetnc() {
            return petnc;
        }

        public void setPetnc(String petnc) {
            this.petnc = petnc;
        }

        public String getPettx() {
            return pettx;
        }

        public void setPettx(String pettx) {
            this.pettx = pettx;
        }

        public String getQm() {
            return qm;
        }

        public void setQm(String qm) {
            this.qm = qm;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public int getYhid() {
            return yhid;
        }

        public void setYhid(int yhid) {
            this.yhid = yhid;
        }

        public String getYhnc() {
            return yhnc;
        }

        public void setYhnc(String yhnc) {
            this.yhnc = yhnc;
        }

        public String getYhtx() {
            return yhtx;
        }

        public void setYhtx(String yhtx) {
            this.yhtx = yhtx;
        }
    }
}
