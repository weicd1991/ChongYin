package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/6/22.
 */

public class PetTPSiseBean {

    /**
     * check : 2.063
     * code : Y
     * data : {"vzsize":"15.2","vzyjsize":"2"}
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
         * vzsize : 15.2
         * vzyjsize : 2
         */

        private String vzsize;
        private String vzyjsize;

        public String getVzsize() {
            return vzsize;
        }

        public void setVzsize(String vzsize) {
            this.vzsize = vzsize;
        }

        public String getVzyjsize() {
            return vzyjsize;
        }

        public void setVzyjsize(String vzyjsize) {
            this.vzyjsize = vzyjsize;
        }
    }
}
