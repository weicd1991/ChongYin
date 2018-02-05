package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class MyFoodBean {

    /**
     * check : 6.02
     * code : Y
     * data : {"list":[{"spcns":2,"spid":1,"sptx":""},{"spcns":1,"spid":2,"sptx":""}]}
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
             * spcns : 2
             * spid : 1
             * sptx :
             */

            private int spcns;
            private int spid;
            private String sptx;

            public int getSpcns() {
                return spcns;
            }

            public void setSpcns(int spcns) {
                this.spcns = spcns;
            }

            public int getSpid() {
                return spid;
            }

            public void setSpid(int spid) {
                this.spid = spid;
            }

            public String getSptx() {
                return sptx;
            }

            public void setSptx(String sptx) {
                this.sptx = sptx;
            }
        }
    }
}
