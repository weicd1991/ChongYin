package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/6/16.
 */

public class FJDRBean {

    /**
     * check : 1.023
     * code : Y
     * data : {"vtx":"0","vyhids":0}
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
         * vtx : 0
         * vyhids : 0
         */

        private String vtx;
        private int vyhids;

        public String getVtx() {
            return vtx;
        }

        public void setVtx(String vtx) {
            this.vtx = vtx;
        }

        public int getVyhids() {
            return vyhids;
        }

        public void setVyhids(int vyhids) {
            this.vyhids = vyhids;
        }
    }
}
