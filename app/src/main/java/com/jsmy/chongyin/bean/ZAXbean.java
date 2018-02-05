package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public class ZAXbean {

    /**
     * check :
     * code : Y
     * data : [{"id":16104,"nc":"林","pm":256,"pmjl":"N","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201711/20171120/00000000040e8e2068134491f9fe40d9069c1a742.png","zaxcns":639},{"id":14512,"nc":"欺骗自、己","pm":197,"pmjl":"N","tx":"http://q.qlogo.cn/qqapp/101398136/00B6E4CCC8F1CD7A4CF1EC658C0B1C61/100","zaxcns":720},{"id":16884,"nc":"逆伤","pm":196,"pmjl":"N","tx":"http://q.qlogo.cn/qqapp/101398136/4F7156103B813AA5B987F19B90DD4C7F/100","zaxcns":199},{"id":14593,"nc":"[ ]","pm":182,"pmjl":"N","tx":"http://q.qlogo.cn/qqapp/101398136/41DAA7DD1F0FBDC1DA4F999C9896BA9A/100","zaxcns":591},{"id":13545,"nc":"没吃药萌萌哒","pm":178,"pmjl":"N","tx":"http://q.qlogo.cn/qqapp/101398136/B2DF0B5213FCB67B4E7A9EA5D2D5B061/100","zaxcns":1258}]
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
         * id : 16104
         * nc : 林
         * pm : 256
         * pmjl : N
         * tx : http://47.92.153.135/chongyin/pictureUpload/2017/201711/20171120/00000000040e8e2068134491f9fe40d9069c1a742.png
         * zaxcns : 639
         */

        private String id;
        private String nc;
        private String pm;
        private String pmjl;
        private String tx;
        private String zaxcns;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        public String getPm() {
            return pm;
        }

        public void setPm(String pm) {
            this.pm = pm;
        }

        public String getPmjl() {
            return pmjl;
        }

        public void setPmjl(String pmjl) {
            this.pmjl = pmjl;
        }

        public String getTx() {
            return tx;
        }

        public void setTx(String tx) {
            this.tx = tx;
        }

        public String getZaxcns() {
            return zaxcns;
        }

        public void setZaxcns(String zaxcns) {
            this.zaxcns = zaxcns;
        }
    }
}
