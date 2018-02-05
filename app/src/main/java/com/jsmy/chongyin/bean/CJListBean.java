package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class CJListBean {

    /**
     * check : 1.10
     * code : Y
     * data : {"list":[{"vcns3":0,"vcns4":18,"vdjs":"0","viskcj":"Y","visygg":"N","vtwolx":"免费抽奖","vxh":0,"vxh2":3}]}
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
             * vcns3 : 0
             * vcns4 : 18
             * vdjs : 0
             * viskcj : Y
             * visygg : N
             * vtwolx : 免费抽奖
             * vxh : 0
             * vxh2 : 3
             */

            private String vcns3;
            private String vcns4;
            private String vdjs;
            private String viskcj;
            private String visygg;
            private String vtwolx;
            private int vxh;
            private int vxh2;

            public String getVcns3() {
                return vcns3;
            }

            public void setVcns3(String vcns3) {
                this.vcns3 = vcns3;
            }

            public String getVcns4() {
                return vcns4;
            }

            public void setVcns4(String vcns4) {
                this.vcns4 = vcns4;
            }

            public String getVdjs() {
                return vdjs;
            }

            public void setVdjs(String vdjs) {
                this.vdjs = vdjs;
            }

            public String getViskcj() {
                return viskcj;
            }

            public void setViskcj(String viskcj) {
                this.viskcj = viskcj;
            }

            public String getVisygg() {
                return visygg;
            }

            public void setVisygg(String visygg) {
                this.visygg = visygg;
            }

            public String getVtwolx() {
                return vtwolx;
            }

            public void setVtwolx(String vtwolx) {
                this.vtwolx = vtwolx;
            }

            public int getVxh() {
                return vxh;
            }

            public void setVxh(int vxh) {
                this.vxh = vxh;
            }

            public int getVxh2() {
                return vxh2;
            }

            public void setVxh2(int vxh2) {
                this.vxh2 = vxh2;
            }
        }
    }
}
