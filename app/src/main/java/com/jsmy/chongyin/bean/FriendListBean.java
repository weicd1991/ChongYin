package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class FriendListBean {

    /**
     * check : 1.09
     * code : Y
     * data : {"list":[{"isax":"Y","isgq":"Y","qm":"","vipdj":"0","yhid":10042,"yhids":10042,"yhnc":"此用户不存在","yhtx":"http://q.qlogo.cn/qqapp/101398136/0B6E1264E8152B91B28DB8EEC65CA3D5/100"}]}
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
             * isax : Y
             * isgq : Y
             * qm :
             * vipdj : 0
             * yhid : 10042
             * yhids : 10042
             * yhnc : 此用户不存在
             * yhtx : http://q.qlogo.cn/qqapp/101398136/0B6E1264E8152B91B28DB8EEC65CA3D5/100
             */

            private String isax;
            private String isgq;
            private String qm;
            private String vipdj;
            private int yhid;
            private int yhids;
            private String yhnc;
            private String yhtx;
            private boolean isChosice;

            public boolean isChosice() {
                return isChosice;
            }

            public void setChosice(boolean chosice) {
                isChosice = chosice;
            }

            public String getIsax() {
                return isax;
            }

            public void setIsax(String isax) {
                this.isax = isax;
            }

            public String getIsgq() {
                return isgq;
            }

            public void setIsgq(String isgq) {
                this.isgq = isgq;
            }

            public String getQm() {
                return qm;
            }

            public void setQm(String qm) {
                this.qm = qm;
            }

            public String getVipdj() {
                return vipdj;
            }

            public void setVipdj(String vipdj) {
                this.vipdj = vipdj;
            }

            public int getYhid() {
                return yhid;
            }

            public void setYhid(int yhid) {
                this.yhid = yhid;
            }

            public int getYhids() {
                return yhids;
            }

            public void setYhids(int yhids) {
                this.yhids = yhids;
            }

            public String getYhnc() {
                return yhnc;
            }

            public void setYhnc(String yhnc) {
                this.yhnc = yhnc;
            }

            public String getYhtx() {
                return yhtx;
            }

            public void setYhtx(String yhtx) {
                this.yhtx = yhtx;
            }
        }
    }
}
