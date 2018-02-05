package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/6/13.
 */

public class LogSxxBean {

    /**
     * check :
     * code : Y
     * data : {"vlx1":"今天是节日双抽，每10分钟可以抽俩次奖","vmc":""}
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
         * vlx1 : 今天是节日双抽，每10分钟可以抽俩次奖
         * vmc :
         */

        private String vlx1;
        private String vmc;

        public String getVlx1() {
            return vlx1;
        }

        public void setVlx1(String vlx1) {
            this.vlx1 = vlx1;
        }

        public String getVmc() {
            return vmc;
        }

        public void setVmc(String vmc) {
            this.vmc = vmc;
        }
    }
}
