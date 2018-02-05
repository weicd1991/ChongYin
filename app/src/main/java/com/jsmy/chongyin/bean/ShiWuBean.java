package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ShiWuBean {

    /**
     * check : 2.01
     * code : Y
     * data : {"list":[{"cns":1,"jbsd":25,"spid":25,"sptx":"http://47.92.153.135/chongyin/headUpload/00000000090895e9d561b4989abfa1769f9acba94.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":26,"sptx":"http://47.92.153.135/chongyin/headUpload/000000000ccace0e830ef41d7996da804e0b76e00.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":27,"sptx":"http://47.92.153.135/chongyin/headUpload/000000000637cfb5392a141ffa7fe8a689482b75c.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":28,"sptx":"http://47.92.153.135/chongyin/headUpload/0000000002a9f6dff9be247e4b4ddd88520ea7b61.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":29,"sptx":"http://47.92.153.135/chongyin/headUpload/0000000000b2ba6b8b7b74226833c70c8999c86eb.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":30,"sptx":"http://47.92.153.135/chongyin/headUpload/000000000b9348d6b32e54676b9576419deaf3c05.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":31,"sptx":"http://47.92.153.135/chongyin/headUpload/000000000e3cd71354e474fad9beb5637e0b38db5.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":32,"sptx":"http://47.92.153.135/chongyin/headUpload/0000000001c5608487ce944e99277772300a65f21.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":33,"sptx":"http://47.92.153.135/chongyin/headUpload/0000000000c9b810df9f04fc9a4ea52ea18241eb2.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":34,"sptx":"http://47.92.153.135/chongyin/headUpload/0000000001047673c421a427b98bf01b65d5ea6b0.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":35,"sptx":"http://47.92.153.135/chongyin/headUpload/00000000054cc441db0044296a76c48d6c4b2dd11.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":36,"sptx":"http://47.92.153.135/chongyin/headUpload/00000000052c46705b58e465fa51f9f49ecdec62e.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":37,"sptx":"http://47.92.153.135/chongyin/headUpload/0000000008169bfd6c70f479eaa986f1494697fa0.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":38,"sptx":"http://47.92.153.135/chongyin/headUpload/000000000449ddf52359d4d4da69cca0a4991f874.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":39,"sptx":"http://47.92.153.135/chongyin/headUpload/000000000f045777fb7db4e60849155eeeef433bd.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":40,"sptx":"http://47.92.153.135/chongyin/headUpload/0000000006cbd6cd6f31f464e805307d25f7e9bb6.png","vipybcns":8,"ybcns":10},{"cns":1,"jbsd":25,"spid":41,"sptx":"http://47.92.153.135/chongyin/headUpload/000000000fe60c34a3f21427b92749a1004e90e11.png","vipybcns":8,"ybcns":10}]}
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
             * cns : 1 数量
             * jbsd : 25 加饱食度
             * spid : 25 食品ID
             * sptx : http://47.92.153.135/chongyin/headUpload/00000000090895e9d561b4989abfa1769f9acba94.png 图片
             * vipybcns : 8 VIP元宝
             * ybcns : 10 非VIP元宝
             */

            private int cns;
            private int jbsd;
            private int spid;
            private String sptx;
            private int vipybcns;
            private int ybcns;

            public int getCns() {
                return cns;
            }

            public void setCns(int cns) {
                this.cns = cns;
            }

            public int getJbsd() {
                return jbsd;
            }

            public void setJbsd(int jbsd) {
                this.jbsd = jbsd;
            }

            public int getSpid() {
                return spid;
            }

            public void setSpid(int spid) {
                this.spid = spid;
            }

            public String getSptx() {
                return sptx;
            }

            public void setSptx(String sptx) {
                this.sptx = sptx;
            }

            public int getVipybcns() {
                return vipybcns;
            }

            public void setVipybcns(int vipybcns) {
                this.vipybcns = vipybcns;
            }

            public int getYbcns() {
                return ybcns;
            }

            public void setYbcns(int ybcns) {
                this.ybcns = ybcns;
            }
        }
    }
}
