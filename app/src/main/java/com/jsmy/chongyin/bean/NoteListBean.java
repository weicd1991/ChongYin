package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13.
 */

public class NoteListBean {

    /**
     * check :
     * code : Y
     * data : [{"noteId":11,"nr":"askdjklasjdlk","sj":"2017-12-13 11:45:24:000000","txsj":"2017年12月13日周三 18:50:00"}]
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
         * noteId : 11
         * nr : askdjklasjdlk
         * sj : 2017-12-13 11:45:24:000000
         * txsj : 2017年12月13日周三 18:50:00
         */

        private String noteId;
        private String nr;
        private String sj;
        private String txsj;

        public String getNoteId() {
            return noteId;
        }

        public void setNoteId(String noteId) {
            this.noteId = noteId;
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

        public String getTxsj() {
            return txsj;
        }

        public void setTxsj(String txsj) {
            this.txsj = txsj;
        }
    }
}
