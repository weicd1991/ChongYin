package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */

public class ShiWuFeiLeiBean {

    /**
     * check : 2.05
     * code : Y
     * data : {"list":[{"id":1,"mc":"蔬菜","tx":"http://192.168.1.150:8080/chongyin/headUpload/shiwufenlei_sucai.png"},{"id":2,"mc":"水果","tx":"http://192.168.1.150:8080/chongyin/headUpload/shiwufenlei_shuiguo.png"},{"id":3,"mc":"肉食","tx":"http://192.168.1.150:8080/chongyin/headUpload/shiwufenlei_roushi.png"},{"id":4,"mc":"主食","tx":"http://192.168.1.150:8080/chongyin/headUpload/shiwufenlei_zhushi.png"},{"id":5,"mc":"汤","tx":"http://192.168.1.150:8080/chongyin/headUpload/shiwufenlei_tang.png"},{"id":6,"mc":"饮料","tx":"http://192.168.1.150:8080/chongyin/headUpload/shiwufenlei_yinliao.png"}]}
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
             * mc : 蔬菜
             * tx : http://192.168.1.150:8080/chongyin/headUpload/shiwufenlei_sucai.png
             */

            private int id;
            private String mc;
            private String tx;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMc() {
                return mc;
            }

            public void setMc(String mc) {
                this.mc = mc;
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
