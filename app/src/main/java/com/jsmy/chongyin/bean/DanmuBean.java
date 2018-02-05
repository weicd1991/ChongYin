package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */

public class DanmuBean {

    /**
     * check :
     * code : Y
     * data : [{"is_Read":"N","lyrId":10081,"nc":"丽丽","nr":"?????f004f004f004","sj":"1512634310909","tx":"http://q.qlogo.cn/qqapp/101398136/D72FC9E52616A362ACD05551E0F9BFFB/100"},{"is_Read":"N","lyrId":10014,"nc":"宠小印","nr":"????","sj":"1512634139091","tx":"http://q.qlogo.cn/qqapp/101398136/C8843E945DBCECE294365E126AD71DB7/100"}]
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
         * is_Read : N
         * lyrId : 10081
         * nc : 丽丽
         * nr : ?????f004f004f004
         * sj : 1512634310909
         * tx : http://q.qlogo.cn/qqapp/101398136/D72FC9E52616A362ACD05551E0F9BFFB/100
         */

        private String is_Read;
        private String lyrId;
        private String nc;
        private String nr;
        private String sj;
        private String tx;

        public String getIs_Read() {
            return is_Read;
        }

        public void setIs_Read(String is_Read) {
            this.is_Read = is_Read;
        }

        public String getLyrId() {
            return lyrId;
        }

        public void setLyrId(String lyrId) {
            this.lyrId = lyrId;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        public String getNr() {
            return nr;
        }

        public void setNr(String nr) {
            this.nr = nr;
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
    }
}
