package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/11.
 */

public class JCSJBean {


    /**
     * check : 1.03
     * code : Y
     * data : {"bscns":"","djcns":"","djcnsmax":"","petdj":"","petid":"","petnc":"","pettx":"","pm":1,"tb":"http://47.92.153.135/chongyin/headUpload/80684b63e2f142649eda97e0a421d0e6.jpg","tpurl":"","url":"https://www.baidu.com/","ybcns":30,"yhid":10050}
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
         * bscns :
         * djcns :
         * djcnsmax :
         * petdj :
         * petid :
         * petnc :
         * pettx :
         * pm : 1
         * tb : http://47.92.153.135/chongyin/headUpload/80684b63e2f142649eda97e0a421d0e6.jpg
         * tpurl :
         * url : https://www.baidu.com/
         * ybcns : 30
         * yhid : 10050
         */

        private String bscns;
        private String djcns;
        private String djcnsmax;
        private String petdj;
        private String petid;
        private String petnc;
        private String pettx;
        private int pm;
        private String tb;
        private String tpurl;
        private String url;
        private int ybcns;
        private int yhid;
        private String bt;

        public String getBt() {
            return bt;
        }

        public void setBt(String bt) {
            this.bt = bt;
        }

        public String getBscns() {
            return bscns;
        }

        public void setBscns(String bscns) {
            this.bscns = bscns;
        }

        public String getDjcns() {
            return djcns;
        }

        public void setDjcns(String djcns) {
            this.djcns = djcns;
        }

        public String getDjcnsmax() {
            return djcnsmax;
        }

        public void setDjcnsmax(String djcnsmax) {
            this.djcnsmax = djcnsmax;
        }

        public String getPetdj() {
            return petdj;
        }

        public void setPetdj(String petdj) {
            this.petdj = petdj;
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

        public int getPm() {
            return pm;
        }

        public void setPm(int pm) {
            this.pm = pm;
        }

        public String getTb() {
            return tb;
        }

        public void setTb(String tb) {
            this.tb = tb;
        }

        public String getTpurl() {
            return tpurl;
        }

        public void setTpurl(String tpurl) {
            this.tpurl = tpurl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getYbcns() {
            return ybcns;
        }

        public void setYbcns(int ybcns) {
            this.ybcns = ybcns;
        }

        public int getYhid() {
            return yhid;
        }

        public void setYhid(int yhid) {
            this.yhid = yhid;
        }
    }
}
