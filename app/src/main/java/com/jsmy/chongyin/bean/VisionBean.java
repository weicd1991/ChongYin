package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/6/8.
 */

public class VisionBean {

    /**
     * check : 1.021
     * code : Y
     * data : {"app":"1.4","isqz":"N","ms":"http://47.92.153.135/chongyin/file/app/cyapp.apk"}
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
         * app : 1.4
         * isqz : N
         * ms : http://47.92.153.135/chongyin/file/app/cyapp.apk
         */

        private String app;
        private String isqz;
        private String ms;

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getIsqz() {
            return isqz;
        }

        public void setIsqz(String isqz) {
            this.isqz = isqz;
        }

        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }
    }
}
