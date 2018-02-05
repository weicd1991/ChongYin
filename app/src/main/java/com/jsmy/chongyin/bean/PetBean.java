package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class PetBean {

    /**
     * check : 2.02
     * code : Y
     * data : {"list":[{"bq":"1","csize1":0.20000000298023224,"csize2":0.20000000298023224,"csize3":0.20000000298023224,"csize4":0.20000000298023224,"csize5":0.20000000298023224,"csize6":0.20000000298023224,"csize7":0.20000000298023224,"dzhsize1":0.30000001192092896,"dzhsize2":0.20000000298023224,"dzhsize3":0.20000000298023224,"dzhsize4":0.20000000298023224,"dzhsize5":0.20000000298023224,"hqlx":"3","id":4,"isxz":"","isyy":"N","jb":200,"kusize1":0.20000000298023224,"kusize2":0.20000000298023224,"kusize3":0.20000000298023224,"kusize4":0.20000000298023224,"kusize5":0.20000000298023224,"mc":"齐二梅","ms":"祁小美的邻居，可是，可是，为什么和祁小美长得一模一样？","tpurl":"http://47.92.153.135/chongyin/headUpload/0000000003427b58eb15a4300b4ab742c9e117f1f.png","xsize1":0.20000000298023224,"xsize2":0.20000000298023224,"xsize3":0.30000001192092896,"xsize4":0.20000000298023224,"zt":"","zyjsize1":0.30000001192092896,"zyjsize2":0.20000000298023224,"zyjsize3":0.20000000298023224,"zyjsize4":0.20000000298023224},{"bq":"1","csize1":0.4000000059604645,"csize2":0.6000000238418579,"csize3":0.699999988079071,"csize4":0.6000000238418579,"csize5":0.8999999761581421,"csize6":0.30000001192092896,"csize7":0.5,"dzhsize1":0.5,"dzhsize2":0.30000001192092896,"dzhsize3":0.699999988079071,"dzhsize4":0.699999988079071,"dzhsize5":0.5,"hqlx":"3","id":3,"isxz":"Y","isyy":"Y","jb":30,"kusize1":1,"kusize2":1,"kusize3":1,"kusize4":1,"kusize5":0,"mc":"齐美晓","ms":"祁小美的妹妹！","tpurl":"http://47.92.153.135/chongyin/headUpload/00000000042b25fa03f464afc944c1c647edc94ea.png","xsize1":0.5,"xsize2":0.699999988079071,"xsize3":0.8999999761581421,"xsize4":0.4000000059604645,"zt":"N","zyjsize1":0.5,"zyjsize2":0.30000001192092896,"zyjsize3":0.800000011920929,"zyjsize4":0.4000000059604645},{"bq":"3","csize1":0.4000000059604645,"csize2":0.6000000238418579,"csize3":0.699999988079071,"csize4":0.6000000238418579,"csize5":0.8999999761581421,"csize6":0.30000001192092896,"csize7":0.5,"dzhsize1":0.5,"dzhsize2":0.30000001192092896,"dzhsize3":0.699999988079071,"dzhsize4":0.699999988079071,"dzhsize5":0.5,"hqlx":"2","id":2,"isxz":"Y","isyy":"Y","jb":1000,"kusize1":1,"kusize2":1,"kusize3":1,"kusize4":1,"kusize5":0,"mc":"梅晓琪","ms":"梅晓琪是祁小美的妹妹？","tpurl":"http://47.92.153.135/chongyin/headUpload/000000000eceb44b6bdc1413fa1d7742c65c4a6f6.png","xsize1":0.5,"xsize2":0.699999988079071,"xsize3":0.8999999761581421,"xsize4":0.4000000059604645,"zt":"Y","zyjsize1":0.5,"zyjsize2":0.30000001192092896,"zyjsize3":0.800000011920929,"zyjsize4":0.4000000059604645}]}
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
             * bq : 1
             * csize1 : 0.20000000298023224
             * csize2 : 0.20000000298023224
             * csize3 : 0.20000000298023224
             * csize4 : 0.20000000298023224
             * csize5 : 0.20000000298023224
             * csize6 : 0.20000000298023224
             * csize7 : 0.20000000298023224
             * dzhsize1 : 0.30000001192092896
             * dzhsize2 : 0.20000000298023224
             * dzhsize3 : 0.20000000298023224
             * dzhsize4 : 0.20000000298023224
             * dzhsize5 : 0.20000000298023224
             * hqlx : 3
             * id : 4
             * isxz :
             * isyy : N
             * jb : 200
             * kusize1 : 0.20000000298023224
             * kusize2 : 0.20000000298023224
             * kusize3 : 0.20000000298023224
             * kusize4 : 0.20000000298023224
             * kusize5 : 0.20000000298023224
             * mc : 齐二梅
             * ms : 祁小美的邻居，可是，可是，为什么和祁小美长得一模一样？
             * tpurl : http://47.92.153.135/chongyin/headUpload/0000000003427b58eb15a4300b4ab742c9e117f1f.png
             * xsize1 : 0.20000000298023224
             * xsize2 : 0.20000000298023224
             * xsize3 : 0.30000001192092896
             * xsize4 : 0.20000000298023224
             * zt :
             * zyjsize1 : 0.30000001192092896
             * zyjsize2 : 0.20000000298023224
             * zyjsize3 : 0.20000000298023224
             * zyjsize4 : 0.20000000298023224
             */

            private String bq;
            private double csize1;
            private double csize2;
            private double csize3;
            private double csize4;
            private double csize5;
            private double csize6;
            private double csize7;
            private double dzhsize1;
            private double dzhsize2;
            private double dzhsize3;
            private double dzhsize4;
            private double dzhsize5;
            private String hqlx;
            private int id;
            private String isxz;
            private String isyy;
            private int jb;
            private double kusize1;
            private double kusize2;
            private double kusize3;
            private double kusize4;
            private double kusize5;
            private String mc;
            private String ms;
            private String tpurl;
            private double xsize1;
            private double xsize2;
            private double xsize3;
            private double xsize4;
            private String zt;
            private double zyjsize1;
            private double zyjsize2;
            private double zyjsize3;
            private double zyjsize4;

            private int maxPress;
            private int press;

            public int getMaxPress() {
                return maxPress;
            }

            public void setMaxPress(int maxPress) {
                this.maxPress = maxPress;
            }

            public int getPress() {
                return press;
            }

            public void setPress(int press) {
                this.press = press;
            }

            public String getBq() {
                return bq;
            }

            public void setBq(String bq) {
                this.bq = bq;
            }

            public double getCsize1() {
                return csize1;
            }

            public void setCsize1(double csize1) {
                this.csize1 = csize1;
            }

            public double getCsize2() {
                return csize2;
            }

            public void setCsize2(double csize2) {
                this.csize2 = csize2;
            }

            public double getCsize3() {
                return csize3;
            }

            public void setCsize3(double csize3) {
                this.csize3 = csize3;
            }

            public double getCsize4() {
                return csize4;
            }

            public void setCsize4(double csize4) {
                this.csize4 = csize4;
            }

            public double getCsize5() {
                return csize5;
            }

            public void setCsize5(double csize5) {
                this.csize5 = csize5;
            }

            public double getCsize6() {
                return csize6;
            }

            public void setCsize6(double csize6) {
                this.csize6 = csize6;
            }

            public double getCsize7() {
                return csize7;
            }

            public void setCsize7(double csize7) {
                this.csize7 = csize7;
            }

            public double getDzhsize1() {
                return dzhsize1;
            }

            public void setDzhsize1(double dzhsize1) {
                this.dzhsize1 = dzhsize1;
            }

            public double getDzhsize2() {
                return dzhsize2;
            }

            public void setDzhsize2(double dzhsize2) {
                this.dzhsize2 = dzhsize2;
            }

            public double getDzhsize3() {
                return dzhsize3;
            }

            public void setDzhsize3(double dzhsize3) {
                this.dzhsize3 = dzhsize3;
            }

            public double getDzhsize4() {
                return dzhsize4;
            }

            public void setDzhsize4(double dzhsize4) {
                this.dzhsize4 = dzhsize4;
            }

            public double getDzhsize5() {
                return dzhsize5;
            }

            public void setDzhsize5(double dzhsize5) {
                this.dzhsize5 = dzhsize5;
            }

            public String getHqlx() {
                return hqlx;
            }

            public void setHqlx(String hqlx) {
                this.hqlx = hqlx;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsxz() {
                return isxz;
            }

            public void setIsxz(String isxz) {
                this.isxz = isxz;
            }

            public String getIsyy() {
                return isyy;
            }

            public void setIsyy(String isyy) {
                this.isyy = isyy;
            }

            public int getJb() {
                return jb;
            }

            public void setJb(int jb) {
                this.jb = jb;
            }

            public double getKusize1() {
                return kusize1;
            }

            public void setKusize1(double kusize1) {
                this.kusize1 = kusize1;
            }

            public double getKusize2() {
                return kusize2;
            }

            public void setKusize2(double kusize2) {
                this.kusize2 = kusize2;
            }

            public double getKusize3() {
                return kusize3;
            }

            public void setKusize3(double kusize3) {
                this.kusize3 = kusize3;
            }

            public double getKusize4() {
                return kusize4;
            }

            public void setKusize4(double kusize4) {
                this.kusize4 = kusize4;
            }

            public double getKusize5() {
                return kusize5;
            }

            public void setKusize5(double kusize5) {
                this.kusize5 = kusize5;
            }

            public String getMc() {
                return mc;
            }

            public void setMc(String mc) {
                this.mc = mc;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getTpurl() {
                return tpurl;
            }

            public void setTpurl(String tpurl) {
                this.tpurl = tpurl;
            }

            public double getXsize1() {
                return xsize1;
            }

            public void setXsize1(double xsize1) {
                this.xsize1 = xsize1;
            }

            public double getXsize2() {
                return xsize2;
            }

            public void setXsize2(double xsize2) {
                this.xsize2 = xsize2;
            }

            public double getXsize3() {
                return xsize3;
            }

            public void setXsize3(double xsize3) {
                this.xsize3 = xsize3;
            }

            public double getXsize4() {
                return xsize4;
            }

            public void setXsize4(double xsize4) {
                this.xsize4 = xsize4;
            }

            public String getZt() {
                return zt;
            }

            public void setZt(String zt) {
                this.zt = zt;
            }

            public double getZyjsize1() {
                return zyjsize1;
            }

            public void setZyjsize1(double zyjsize1) {
                this.zyjsize1 = zyjsize1;
            }

            public double getZyjsize2() {
                return zyjsize2;
            }

            public void setZyjsize2(double zyjsize2) {
                this.zyjsize2 = zyjsize2;
            }

            public double getZyjsize3() {
                return zyjsize3;
            }

            public void setZyjsize3(double zyjsize3) {
                this.zyjsize3 = zyjsize3;
            }

            public double getZyjsize4() {
                return zyjsize4;
            }

            public void setZyjsize4(double zyjsize4) {
                this.zyjsize4 = zyjsize4;
            }
        }
    }
}
