package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/19.
 */

public class FriendPetThingBean {

    /**
     * check : 1.092
     * code : Y
     * data : {"lbaxcns":4,"pyhids":"10042","zbaxcns":5}
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
         * lbaxcns : 4
         * pyhids : 10042
         * zbaxcns : 5
         */

        private int lbaxcns;
        private String pyhids;
        private int zbaxcns;

        public int getLbaxcns() {
            return lbaxcns;
        }

        public void setLbaxcns(int lbaxcns) {
            this.lbaxcns = lbaxcns;
        }

        public String getPyhids() {
            return pyhids;
        }

        public void setPyhids(String pyhids) {
            this.pyhids = pyhids;
        }

        public int getZbaxcns() {
            return zbaxcns;
        }

        public void setZbaxcns(int zbaxcns) {
            this.zbaxcns = zbaxcns;
        }
    }
}
