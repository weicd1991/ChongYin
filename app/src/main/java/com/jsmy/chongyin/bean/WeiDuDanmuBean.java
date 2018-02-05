package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */

public class WeiDuDanmuBean {

    /**
     * check :
     * code : Y
     * data : [{"is_Read":"N","lyid":41,"lyrId":12109,"nc":"上流小瘪三","nr":"f018f018f018???f002f002f002???f003f003f003???","sj":"1512638726867","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201710/20171010/0000000005c785bb2142b4e32ab1f712f2fa4a14d.png"},{"is_Read":"N","lyid":40,"lyrId":12109,"nc":"上流小瘪三","nr":"???????????????????????????","sj":"1512638550198","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201710/20171010/0000000005c785bb2142b4e32ab1f712f2fa4a14d.png"},{"is_Read":"N","lyid":39,"lyrId":12109,"nc":"上流小瘪三","nr":"??????f002f002f002f002f002f002f002f002f002f002f002f002f002f002","sj":"1512638330654","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201710/20171010/0000000005c785bb2142b4e32ab1f712f2fa4a14d.png"},{"is_Read":"N","lyid":38,"lyrId":12109,"nc":"上流小瘪三","nr":"?????f023f015f022f014f021f004f019f018","sj":"1512638206042","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201710/20171010/0000000005c785bb2142b4e32ab1f712f2fa4a14d.png"},{"is_Read":"N","lyid":37,"lyrId":12109,"nc":"上流小瘪三","nr":"f002f003f020f020f022","sj":"1512638197629","tx":"http://47.92.153.135/chongyin/pictureUpload/2017/201710/20171010/0000000005c785bb2142b4e32ab1f712f2fa4a14d.png"}]
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
         * is_Read : N
         * lyid : 41
         * lyrId : 12109
         * nc : 上流小瘪三
         * nr : f018f018f018???f002f002f002???f003f003f003???
         * sj : 1512638726867
         * tx : http://47.92.153.135/chongyin/pictureUpload/2017/201710/20171010/0000000005c785bb2142b4e32ab1f712f2fa4a14d.png
         */

        private String is_Read;
        private String lyid;
        private String lyrId;
        private String nc;
        private String nr;
        private String sj;
        private String tx;

        public String getIs_Read() {
            return is_Read;
        }

        public void setIs_Read(String is_Read) {
            this.is_Read = is_Read;
        }

        public String getLyid() {
            return lyid;
        }

        public void setLyid(String lyid) {
            this.lyid = lyid;
        }

        public String getLyrId() {
            return lyrId;
        }

        public void setLyrId(String lyrId) {
            this.lyrId = lyrId;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        public String getNr() {
            return nr;
        }

        public void setNr(String nr) {
            this.nr = nr;
        }

        public String getSj() {
            return sj;
        }

        public void setSj(String sj) {
            this.sj = sj;
        }

        public String getTx() {
            return tx;
        }

        public void setTx(String tx) {
            this.tx = tx;
        }
    }
}
