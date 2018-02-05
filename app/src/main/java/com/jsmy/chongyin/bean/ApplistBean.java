package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ApplistBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"nc":"枫落","tx":"http://q.qlogo.cn/qqapp/101398136/870155F37B9E3E9BE2DB39EAC21AEAD4/100","yhids":10006}]}
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
             * nc : 枫落
             * tx : http://q.qlogo.cn/qqapp/101398136/870155F37B9E3E9BE2DB39EAC21AEAD4/100
             * yhids : 10006
             */

            private String nc;
            private String tx;
            private int yhids;

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
        }
    }
}
