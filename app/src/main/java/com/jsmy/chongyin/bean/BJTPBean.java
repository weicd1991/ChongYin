package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class BJTPBean {

    /**
     * check : 1.07
     * code : Y
     * data : {"list":[{"id":1,"tp":"http://192.168.1.150:8080/chongyin/headUpload/1.png"}]}
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
             * id : 1
             * tp : http://192.168.1.150:8080/chongyin/headUpload/1.png
             */

            private int id;
            private String tp;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTp() {
                return tp;
            }

            public void setTp(String tp) {
                this.tp = tp;
            }
        }
    }
}
