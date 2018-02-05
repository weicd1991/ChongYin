package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class LoveRankBean {

    /**
     * check : 1.08
     * code : Y
     * data : {"jrksax":"5","list":[{"axcns":58,"isgq":"N","nc":"乌贼娘","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201706/20170612/0000000008327fbfa7cef41509058eb37c40712e9.png","vipdj":"1","xh":1,"yhid":10045,"yhsax":0},{"axcns":14,"isgq":"N","nc":"我喂自己袋盐","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201705/20170531/0000000000ecedbc259014d39a27e53be803005d7.jpg","vipdj":"1","xh":2,"yhid":10040,"yhsax":""},{"axcns":9,"isgq":"N","nc":"椎名の影","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201706/20170610/000000000e2baaa94d9e740c5890bac5ab2b577b7.jpg","vipdj":"0","xh":3,"yhid":10047,"yhsax":0},{"axcns":7,"isgq":"N","nc":"此用户不存在","tx":"http://q.qlogo.cn/qqapp/101398136/0B6E1264E8152B91B28DB8EEC65CA3D5/100","vipdj":"0","xh":4,"yhid":10042,"yhsax":0},{"axcns":2,"isgq":"N","nc":"Wintermelon","tx":"http://q.qlogo.cn/qqapp/101398136/6DF9072F74F272A4AF9635E006FDEEB8/100","vipdj":"0","xh":5,"yhid":10054,"yhsax":0},{"axcns":0,"isgq":"N","nc":"A   Quiet   Life","tx":"http://q.qlogo.cn/qqapp/101398136/3F56E90B5D9616F9B45CCF8B7149BACE/100","vipdj":"0","xh":6,"yhid":10049,"yhsax":0},{"axcns":0,"isgq":"N","nc":"此情休问天","tx":"http://q.qlogo.cn/qqapp/101398136/0CF508D4B7766D21C684CEE6AFA620C1/100","vipdj":"0","xh":7,"yhid":10051,"yhsax":0},{"axcns":0,"isgq":"N","nc":"寒风中的孤狼啸月","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201706/20170601/0000000004e03625b6b4a4524a82bc35e5d3d6f81.jpg","vipdj":"0","xh":8,"yhid":10055,"yhsax":0}]}
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
         * jrksax : 5
         * list : [{"axcns":58,"isgq":"N","nc":"乌贼娘","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201706/20170612/0000000008327fbfa7cef41509058eb37c40712e9.png","vipdj":"1","xh":1,"yhid":10045,"yhsax":0},{"axcns":14,"isgq":"N","nc":"我喂自己袋盐","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201705/20170531/0000000000ecedbc259014d39a27e53be803005d7.jpg","vipdj":"1","xh":2,"yhid":10040,"yhsax":""},{"axcns":9,"isgq":"N","nc":"椎名の影","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201706/20170610/000000000e2baaa94d9e740c5890bac5ab2b577b7.jpg","vipdj":"0","xh":3,"yhid":10047,"yhsax":0},{"axcns":7,"isgq":"N","nc":"此用户不存在","tx":"http://q.qlogo.cn/qqapp/101398136/0B6E1264E8152B91B28DB8EEC65CA3D5/100","vipdj":"0","xh":4,"yhid":10042,"yhsax":0},{"axcns":2,"isgq":"N","nc":"Wintermelon","tx":"http://q.qlogo.cn/qqapp/101398136/6DF9072F74F272A4AF9635E006FDEEB8/100","vipdj":"0","xh":5,"yhid":10054,"yhsax":0},{"axcns":0,"isgq":"N","nc":"A   Quiet   Life","tx":"http://q.qlogo.cn/qqapp/101398136/3F56E90B5D9616F9B45CCF8B7149BACE/100","vipdj":"0","xh":6,"yhid":10049,"yhsax":0},{"axcns":0,"isgq":"N","nc":"此情休问天","tx":"http://q.qlogo.cn/qqapp/101398136/0CF508D4B7766D21C684CEE6AFA620C1/100","vipdj":"0","xh":7,"yhid":10051,"yhsax":0},{"axcns":0,"isgq":"N","nc":"寒风中的孤狼啸月","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201706/20170601/0000000004e03625b6b4a4524a82bc35e5d3d6f81.jpg","vipdj":"0","xh":8,"yhid":10055,"yhsax":0}]
         */

        private String jrksax;
        private List<ListBean> list;

        public String getJrksax() {
            return jrksax;
        }

        public void setJrksax(String jrksax) {
            this.jrksax = jrksax;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * axcns : 58
             * isgq : N
             * nc : 乌贼娘
             * tx : http://47.92.153.135/chongyin/pictureUpload/2017/201706/20170612/0000000008327fbfa7cef41509058eb37c40712e9.png
             * vipdj : 1
             * xh : 1
             * yhid : 10045
             * yhsax : 0
             */

            private String axcns;
            private String isgq;
            private String nc;
            private String tx;
            private String vipdj;
            private String xh;
            private String yhid;
            private String yhsax;
            private String zaxcns;

            public String getZaxcns() {
                return zaxcns;
            }

            public void setZaxcns(String zaxcns) {
                this.zaxcns = zaxcns;
            }

            public String getAxcns() {
                return axcns;
            }

            public void setAxcns(String axcns) {
                this.axcns = axcns;
            }

            public String getIsgq() {
                return isgq;
            }

            public void setIsgq(String isgq) {
                this.isgq = isgq;
            }

            public String getNc() {
                return nc;
            }

            public void setNc(String nc) {
                this.nc = nc;
            }

            public String getTx() {
                return tx;
            }

            public void setTx(String tx) {
                this.tx = tx;
            }

            public String getVipdj() {
                return vipdj;
            }

            public void setVipdj(String vipdj) {
                this.vipdj = vipdj;
            }

            public String getXh() {
                return xh;
            }

            public void setXh(String xh) {
                this.xh = xh;
            }

            public String getYhid() {
                return yhid;
            }

            public void setYhid(String yhid) {
                this.yhid = yhid;
            }

            public String getYhsax() {
                return yhsax;
            }

            public void setYhsax(String yhsax) {
                this.yhsax = yhsax;
            }
        }
    }
}
