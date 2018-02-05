package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */

public class NewFriendBena {

    /**
     * check : 6.10
     * code : Y
     * data : {"list":[{"isgq":"Y","nc":"hbm","tx":"http://192.168.1.150:8080/chongyin/pictureUpload/2017/201705/20170515/cb9f5831451e4af9ad50f5020cc62eba.jpg ","vipdj":"0","yhids":10001,"zt":"1"},{"isgq":"Y","nc":"玮哥","tx":"http://192.168.1.150:8080/chongyin/pictureUpload/2017/201705/20170515/cb9f5831451e4af9ad50f5020cc62eba.jpg","vipdj":"0","yhids":10015,"zt":"0"},{"isgq":"Y","nc":"分动画部分的","tx":"http://192.168.1.150:8080/chongyin/pictureUpload/2017/201705/20170515/cb9f5831451e4af9ad50f5020cc62eba.jpg","vipdj":"0","yhids":10027,"zt":"2"},{"isgq":"Y","nc":"asdasd","tx":"http://192.168.1.150:8080/chongyin/pictureUpload/2017/201705/20170515/cb9f5831451e4af9ad50f5020cc62eba.jpg","vipdj":"0","yhids":10009,"zt":"3"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * isgq : Y
             * nc : hbm
             * tx : http://192.168.1.150:8080/chongyin/pictureUpload/2017/201705/20170515/cb9f5831451e4af9ad50f5020cc62eba.jpg
             * vipdj : 0
             * yhids : 10001
             * zt : 1
             */

            private String isgq;
            private String nc;
            private String tx;
            private String vipdj;
            private int yhids;
            private String zt;

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

            public String getVipdj() {
                return vipdj;
            }

            public void setVipdj(String vipdj) {
                this.vipdj = vipdj;
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
}
