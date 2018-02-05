package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public class TJYHBean {

    /**
     * check :
     * code : Y
     * data : [{"bdate":"","dj":1,"nc":"枫翎","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/B524E4C717BC1ADDBB1E5F651110BDD4/100","xb":"男","yhid":17242},{"bdate":"1999年08月21日","dj":3,"nc":"太陽ぎぅぁいて","qm":"乖，饿了给你吃鸡腿儿～","tx":"http://q.qlogo.cn/qqapp/101398136/3636ED4950B34E6A5829BCCE47FBAD0E/100","xb":"男","yhid":15520},{"bdate":"","dj":17,"nc":"林","qm":"","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201711/20171120/00000000040e8e2068134491f9fe40d9069c1a742.png","xb":"男","yhid":16104},{"bdate":"1999年11月02日","dj":3,"nc":"触手TV50576442","qm":"","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201711/20171120/0000000004e9d84fc4d634dedb8911aa4d172083a.png","xb":"男","yhid":14725},{"bdate":"1998年04月01日","dj":3,"nc":"彼岸","qm":"","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201711/20171120/0000000008ea00597d0b144a185c8cc4d4e16d3f9.png","xb":"女","yhid":17014},{"bdate":"","dj":3,"nc":"柒月","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/C23347D466D87434125CE0E4364601CF/100","xb":"女","yhid":16852},{"bdate":"","dj":"","nc":"拾柒","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/9FDF7FABBA8A72626687F61C9E668ED7/100","xb":"女","yhid":17232},{"bdate":"","dj":"","nc":"✎","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/34D980FF6D3662527A3E55BA61F42FEC/100","xb":"男","yhid":17221},{"bdate":"","dj":1,"nc":"偌曦铭","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/533D4F9541FCC22EF78B28E78CED2508/100","xb":"女","yhid":17218},{"bdate":"","dj":3,"nc":"情绪","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/9D846BE0232A3D40596CF277B70E8D20/100","xb":"女","yhid":17132}]
     * msg : 操作成功！
     */

    private String check;
    private String code;
    private String msg;
    private List<DataBean> data;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bdate :
         * dj : 1
         * nc : 枫翎
         * qm :
         * tx : http://q.qlogo.cn/qqapp/101398136/B524E4C717BC1ADDBB1E5F651110BDD4/100
         * xb : 男
         * yhid : 17242
         */

        private String bdate;
        private String dj;
        private String nc;
        private String qm;
        private String tx;
        private String xb;
        private String yhid;

        public String getBdate() {
            return bdate;
        }

        public void setBdate(String bdate) {
            this.bdate = bdate;
        }

        public String getDj() {
            return dj;
        }

        public void setDj(String dj) {
            this.dj = dj;
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

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getYhid() {
            return yhid;
        }

        public void setYhid(String yhid) {
            this.yhid = yhid;
        }
    }
}
