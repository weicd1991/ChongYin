package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MyPetThingBean {

    /**
     * check : 1.04
     * code : Y
     * data : {"list":[{"axcns":4,"axtx":" http://47.92.153.135/chongyin/headUpload/0000000006c8ff1029cd240df8c76ce82ee70a88d.png ","cns":1,"lx":"2","spid":0,"tx":""},{"axcns":4,"axtx":" http://47.92.153.135/chongyin/headUpload/0000000006c8ff1029cd240df8c76ce82ee70a88d.png ","cns":0,"lx":"3","spid":20,"tx":""}]}
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
             * axcns : 4
             * axtx :  http://47.92.153.135/chongyin/headUpload/0000000006c8ff1029cd240df8c76ce82ee70a88d.png
             * cns : 1
             * lx : 2
             * spid : 0
             * tx :
             */

            private String axcns;
            private String axtx;
            private int cns;
            private String lx;
            private String spid;
            private String tx;

            public String getAxcns() {
                return axcns;
            }

            public void setAxcns(String axcns) {
                this.axcns = axcns;
            }

            public String getAxtx() {
                return axtx;
            }

            public void setAxtx(String axtx) {
                this.axtx = axtx;
            }

            public int getCns() {
                return cns;
            }

            public void setCns(int cns) {
                this.cns = cns;
            }

            public String getLx() {
                return lx;
            }

            public void setLx(String lx) {
                this.lx = lx;
            }

            public String getSpid() {
                return spid;
            }

            public void setSpid(String spid) {
                this.spid = spid;
            }

            public String getTx() {
                return tx;
            }

            public void setTx(String tx) {
                this.tx = tx;
            }
        }
    }
}
