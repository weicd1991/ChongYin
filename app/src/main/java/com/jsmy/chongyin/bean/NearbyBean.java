package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/13.
 */

public class NearbyBean {

    /**
     * check :
     * code : Y
     * data : [{"bdate":"","dj":3,"jl":23,"nc":"一枪穿云","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/27FCA853068EB2D5A29C8F307273C972/100","xb":"男","yhid":16550},{"bdate":"","dj":5,"jl":49,"nc":"时٩๛ 恋","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/765C0C3FFEEB2364DB1E0601EBBEDC59/100","xb":"女","yhid":16041},{"bdate":"","dj":1,"jl":64,"nc":"汐梓","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/947C5AFE2E694083AEF7110764DDD90E/100","xb":"女","yhid":17112},{"bdate":"2001年10月21日","dj":1,"jl":114,"nc":"阿夏","qm":"死者会有签名吗","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201711/20171120/000000000c0236a41852c482f8c75739608009032.png","xb":"男","yhid":15839},{"bdate":"","dj":1,"jl":308,"nc":"情绪","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/9D846BE0232A3D40596CF277B70E8D20/100","xb":"女","yhid":17132},{"bdate":"","dj":1,"jl":345,"nc":"零绪","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/6F36EAC1C325B682DDF8694717E0AFD0/100","xb":"男","yhid":16886},{"bdate":"","dj":10,"jl":374,"nc":"纯真年代","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/318BF6DF04D40B6EB0441ADE54056192/100","xb":"女","yhid":14242},{"bdate":"","dj":5,"jl":463,"nc":"顾悠璃","qm":"","tx":"http://tvax1.sinaimg.cn/crop.0.0.664.664.1024/006ere1Nly8fltenfkam5j30ig0igta6.jpg","xb":"女","yhid":15273},{"bdate":"","dj":8,"jl":492,"nc":"会吐槽的外星人","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/B17EC603281C400DDC9A9D6823763223/100","xb":"男","yhid":10896},{"bdate":"","dj":"","jl":506,"nc":"夏目家的大魔王","qm":"","tx":"http://q.qlogo.cn/qqapp/101398136/56C082C6984DF6999D34E24DE9BEEC78/100","xb":"男","yhid":17131}]
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
         * bdate :
         * dj : 3
         * jl : 23
         * nc : 一枪穿云
         * qm :
         * tx : http://q.qlogo.cn/qqapp/101398136/27FCA853068EB2D5A29C8F307273C972/100
         * xb : 男
         * yhid : 16550
         */

        private String bdate;
        private String dj;
        private String jl;
        private String nc;
        private String qm;
        private String tx;
        private String xb;
        private String yhid;

        public String getBdate() {
            return bdate;
        }

        public void setBdate(String bdate) {
            this.bdate = bdate;
        }

        public String getDj() {
            return dj;
        }

        public void setDj(String dj) {
            this.dj = dj;
        }

        public String getJl() {
            return jl;
        }

        public void setJl(String jl) {
            this.jl = jl;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        public String getQm() {
            return qm;
        }

        public void setQm(String qm) {
            this.qm = qm;
        }

        public String getTx() {
            return tx;
        }

        public void setTx(String tx) {
            this.tx = tx;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getYhid() {
            return yhid;
        }

        public void setYhid(String yhid) {
            this.yhid = yhid;
        }
    }
}
