package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/12/13.
 */

public class ZSYXXBean {

    /**
     * check :
     * code : Y
     * data : {"cns":"1","id":1,"nr":"白日依山尽，黄河入海流。","scsj":"2017-12-12 11:04:17"}
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
         * cns : 1
         * id : 1
         * nr : 白日依山尽，黄河入海流。
         * scsj : 2017-12-12 11:04:17
         */

        private String cns;
        private String id;
        private String nr;
        private String scsj;

        public String getCns() {
            return cns;
        }

        public void setCns(String cns) {
            this.cns = cns;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNr() {
            return nr;
        }

        public void setNr(String nr) {
            this.nr = nr;
        }

        public String getScsj() {
            return scsj;
        }

        public void setScsj(String scsj) {
            this.scsj = scsj;
        }
    }
}
