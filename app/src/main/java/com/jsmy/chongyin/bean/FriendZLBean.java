package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/19.
 */

public class FriendZLBean {

    /**
     * check : 1.093
     * code : Y
     * data : {"bdate":"2010年06月15日","isgq":"N","qm":"","vipdj":"0","xb":"男","yhids":10042,"yhnc":"此用户不存在","yhtx":"http://q.qlogo.cn/qqapp/101398136/0B6E1264E8152B91B28DB8EEC65CA3D5/100"}
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
         * bdate : 2010年06月15日
         * isgq : N
         * qm :
         * vipdj : 0
         * xb : 男
         * yhids : 10042
         * yhnc : 此用户不存在
         * yhtx : http://q.qlogo.cn/qqapp/101398136/0B6E1264E8152B91B28DB8EEC65CA3D5/100
         */

        private String bdate;
        private String isgq;
        private String qm;
        private String vipdj;
        private String xb;
        private int yhids;
        private String yhnc;
        private String yhtx;

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

        public String getQm() {
            return qm;
        }

        public void setQm(String qm) {
            this.qm = qm;
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
