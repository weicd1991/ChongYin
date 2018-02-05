package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/15.
 */

public class FriendBean {

    /**
     * check : 1.091
     * code : Y
     * data : {"bjimg":"http://47.92.153.135/chongyin/headUpload/00000000013f9f1a18df34df9810ffa3314659fe4.png","bjtpid":1,"isgq":"N","nc":"此用户不存在","tx":"http://q.qlogo.cn/qqapp/101398136/0B6E1264E8152B91B28DB8EEC65CA3D5/100","yhids":10042,"yhidsaxpm":2,"yhidspetid":11,"yhidspetnc":"还有人的","yhidspettp":"http://47.92.153.135/chongyin/headUpload/000000000d56e4d87e7224028a0012a8a2c962a32.jpg","yhidspettx":"http://47.92.153.135/chongyin/headUpload/00000000082cab702aa604ebda13f1e704b47b6f8.jpg","yhpetdj":2,"yhsvipdj":"0"}
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
         * bjimg : http://47.92.153.135/chongyin/headUpload/00000000013f9f1a18df34df9810ffa3314659fe4.png
         * bjtpid : 1
         * isgq : N
         * nc : 此用户不存在
         * tx : http://q.qlogo.cn/qqapp/101398136/0B6E1264E8152B91B28DB8EEC65CA3D5/100
         * yhids : 10042
         * yhidsaxpm : 2
         * yhidspetid : 11
         * yhidspetnc : 还有人的
         * yhidspettp : http://47.92.153.135/chongyin/headUpload/000000000d56e4d87e7224028a0012a8a2c962a32.jpg
         * yhidspettx : http://47.92.153.135/chongyin/headUpload/00000000082cab702aa604ebda13f1e704b47b6f8.jpg
         * yhpetdj : 2
         * yhsvipdj : 0
         */

        private String bjimg;
        private int bjtpid;
        private String isgq;
        private String nc;
        private String tx;
        private int yhids;
        private String yhidsaxpm;
        private String yhidspetid;
        private String yhidspetnc;
        private String yhidspettp;
        private String yhidspettx;
        private String yhpetdj;
        private String yhsvipdj;

        public String getBjimg() {
            return bjimg;
        }

        public void setBjimg(String bjimg) {
            this.bjimg = bjimg;
        }

        public int getBjtpid() {
            return bjtpid;
        }

        public void setBjtpid(int bjtpid) {
            this.bjtpid = bjtpid;
        }

        public String getIsgq() {
            return isgq;
        }

        public void setIsgq(String isgq) {
            this.isgq = isgq;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        public String getTx() {
            return tx;
        }

        public void setTx(String tx) {
            this.tx = tx;
        }

        public int getYhids() {
            return yhids;
        }

        public void setYhids(int yhids) {
            this.yhids = yhids;
        }

        public String getYhidsaxpm() {
            return yhidsaxpm;
        }

        public void setYhidsaxpm(String yhidsaxpm) {
            this.yhidsaxpm = yhidsaxpm;
        }

        public String getYhidspetid() {
            return yhidspetid;
        }

        public void setYhidspetid(String yhidspetid) {
            this.yhidspetid = yhidspetid;
        }

        public String getYhidspetnc() {
            return yhidspetnc;
        }

        public void setYhidspetnc(String yhidspetnc) {
            this.yhidspetnc = yhidspetnc;
        }

        public String getYhidspettp() {
            return yhidspettp;
        }

        public void setYhidspettp(String yhidspettp) {
            this.yhidspettp = yhidspettp;
        }

        public String getYhidspettx() {
            return yhidspettx;
        }

        public void setYhidspettx(String yhidspettx) {
            this.yhidspettx = yhidspettx;
        }

        public String getYhpetdj() {
            return yhpetdj;
        }

        public void setYhpetdj(String yhpetdj) {
            this.yhpetdj = yhpetdj;
        }

        public String getYhsvipdj() {
            return yhsvipdj;
        }

        public void setYhsvipdj(String yhsvipdj) {
            this.yhsvipdj = yhsvipdj;
        }
    }
}
