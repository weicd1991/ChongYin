package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/12/13.
 */

public class NoteBean {

    /**
     * check :
     * code : Y
     * data : {"id":11,"nr":"askdjklasjdlk","sjsj":"2017-12-13 11:45:24","txsj":"2017年12月13日周三 18:50:00","yhid":12109}
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
         * id : 11
         * nr : askdjklasjdlk
         * sjsj : 2017-12-13 11:45:24
         * txsj : 2017年12月13日周三 18:50:00
         * yhid : 12109
         */

        private String id;
        private String nr;
        private String sjsj;
        private String txsj;
        private String yhid;

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

        public String getSjsj() {
            return sjsj;
        }

        public void setSjsj(String sjsj) {
            this.sjsj = sjsj;
        }

        public String getTxsj() {
            return txsj;
        }

        public void setTxsj(String txsj) {
            this.txsj = txsj;
        }

        public String getYhid() {
            return yhid;
        }

        public void setYhid(String yhid) {
            this.yhid = yhid;
        }
    }
}
