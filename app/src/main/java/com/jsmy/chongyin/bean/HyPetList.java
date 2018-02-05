package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/10.
 */

public class HyPetList {

    /**
     * check : 2.061
     * code : Y
     * data : {"list":[{"petdj":"1","petid":11,"zyjurl1":"http://47.92.153.135/chongyin/headUpload/000000000b9fd36fa48f2444a8999d3b25f5b9598.png","zyjurl2":"http://47.92.153.135/chongyin/headUpload/0000000005391cd8947f94e208e2fe36f09b9d2a1.png","zyjurl3":"http://47.92.153.135/chongyin/headUpload/0000000008664e912e9b94a028cd8a88d5d7d1fe9.png","zyjurl4":"http://47.92.153.135/chongyin/headUpload/000000000ef89a673effd47beb686b335ed42d714.png"}]}
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
             * petdj : 1
             * petid : 11
             * zyjurl1 : http://47.92.153.135/chongyin/headUpload/000000000b9fd36fa48f2444a8999d3b25f5b9598.png
             * zyjurl2 : http://47.92.153.135/chongyin/headUpload/0000000005391cd8947f94e208e2fe36f09b9d2a1.png
             * zyjurl3 : http://47.92.153.135/chongyin/headUpload/0000000008664e912e9b94a028cd8a88d5d7d1fe9.png
             * zyjurl4 : http://47.92.153.135/chongyin/headUpload/000000000ef89a673effd47beb686b335ed42d714.png
             */

            private String petdj;
            private int petid;
            private String zyjurl1;
            private String zyjurl2;
            private String zyjurl3;
            private String zyjurl4;

            public String getPetdj() {
                return petdj;
            }

            public void setPetdj(String petdj) {
                this.petdj = petdj;
            }

            public int getPetid() {
                return petid;
            }

            public void setPetid(int petid) {
                this.petid = petid;
            }

            public String getZyjurl1() {
                return zyjurl1;
            }

            public void setZyjurl1(String zyjurl1) {
                this.zyjurl1 = zyjurl1;
            }

            public String getZyjurl2() {
                return zyjurl2;
            }

            public void setZyjurl2(String zyjurl2) {
                this.zyjurl2 = zyjurl2;
            }

            public String getZyjurl3() {
                return zyjurl3;
            }

            public void setZyjurl3(String zyjurl3) {
                this.zyjurl3 = zyjurl3;
            }

            public String getZyjurl4() {
                return zyjurl4;
            }

            public void setZyjurl4(String zyjurl4) {
                this.zyjurl4 = zyjurl4;
            }
        }
    }
}
