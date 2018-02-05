package com.jsmy.chongyin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class PetTpListBean {

    /**
     * check :
     * code : Y
     * data : {"list":[{"curl1":"http://47.92.153.135/chongyin/headUpload/00000000018076c0b312340b883c1cd6a135f1c42.png","curl2":"http://47.92.153.135/chongyin/headUpload/0000000005204732950ad4e17a3790ae7909747be.png","curl3":"http://47.92.153.135/chongyin/headUpload/000000000ad845afa8ad042a3b7e273e6152801e1.png","curl4":"http://47.92.153.135/chongyin/headUpload/00000000000c21ba2f599483bbd4fcfb44051fc62.png","curl5":"http://47.92.153.135/chongyin/headUpload/000000000694e4cd7e5e144bd93c629d981e9a1fe.png","curl6":"http://47.92.153.135/chongyin/headUpload/000000000337bfd016ec248ed9913deb8e02081b8.png","curl7":"http://47.92.153.135/chongyin/headUpload/0000000008563991547ba41fe8a28445a18869d2e.png","dzhurl1":"http://47.92.153.135/chongyin/headUpload/00000000061ea0962e2054aba98659358697ef271.png","dzhurl2":"http://47.92.153.135/chongyin/headUpload/00000000059b81c7afe2b4b06b2d47051d4a81d3e.png","dzhurl3":"http://47.92.153.135/chongyin/headUpload/00000000046075ed3173144d4b7f804dca0c8cbf2.png","dzhurl4":"http://47.92.153.135/chongyin/headUpload/000000000a8a548cc3e304ee39e3ca78192ec1282.png","dzhurl5":"http://47.92.153.135/chongyin/headUpload/0000000007eeb92c385e4434cb45631ba12549ece.png","kurl1":"http://47.92.153.135/chongyin/headUpload/00000000010b553e2d9494f37b604eb3f80e10803.png","kurl2":"http://47.92.153.135/chongyin/headUpload/0000000008ff0ff14c2454991afba5af15cfec61c.png","kurl3":"http://47.92.153.135/chongyin/headUpload/0000000003320d489a7ec45658dcfef98fd9be3a7.png","kurl4":"http://47.92.153.135/chongyin/headUpload/0000000007bd673b6a97d4cc38532e8e5974c34c9.png","kurl5":"http://47.92.153.135/chongyin/headUpload/000000000058a9d8d56ff494fba6bfe72af22459a.png","petdj":"2","petid":11,"xurl1":"http://47.92.153.135/chongyin/headUpload/000000000c830d4a326544fa791eb9e4b32073ec4.png","xurl2":"http://47.92.153.135/chongyin/headUpload/0000000009b5eb0ebdb9345c984cc86bf18935772.png","xurl3":"http://47.92.153.135/chongyin/headUpload/000000000e0282f962e2f43ddb816a8536a023cb6.png","xurl4":"http://47.92.153.135/chongyin/headUpload/00000000050b18bd7ab604071af287b91ebd01b87.png","zyjurl1":"http://47.92.153.135/chongyin/headUpload/0000000003c0e7d41c7904dfe8bbef159a2b6c1c8.png","zyjurl2":"http://47.92.153.135/chongyin/headUpload/000000000b48a7476a75b4e408ef5ad02c19b891b.png","zyjurl3":"http://47.92.153.135/chongyin/headUpload/00000000060a567cc664d48dda8e1cad5ab2600ea.png","zyjurl4":"http://47.92.153.135/chongyin/headUpload/00000000023209bddca02477e89c1a9e4b14afa2b.png"},{"curl1":"http://47.92.153.135/chongyin/headUpload/0000000007b11e98fee4c429bb41ab6dd14bf24bc.png","curl2":"http://47.92.153.135/chongyin/headUpload/000000000581efe36066d4784a63369f9a8ede228.png","curl3":"http://47.92.153.135/chongyin/headUpload/0000000006bdbb1f7942642c4a7f4aa7cd9076dcf.png","curl4":"http://47.92.153.135/chongyin/headUpload/0000000007dcb770fa9cc40679bba050ecc78d0eb.png","curl5":"http://47.92.153.135/chongyin/headUpload/000000000d50005f16ea746a0a87b1145110d8f48.png","curl6":"http://47.92.153.135/chongyin/headUpload/0000000004857740f8dbc436e8e9c8669a8215f16.png","curl7":"http://47.92.153.135/chongyin/headUpload/000000000d1aa1872356740c28610dbc0e77456d9.png","dzhurl1":"http://47.92.153.135/chongyin/headUpload/00000000046a828eedd694041b0a3c83fc5091e93.png","dzhurl2":"http://47.92.153.135/chongyin/headUpload/000000000f4bf00e51cf44f6e95d552f53b2ed23d.png","dzhurl3":"http://47.92.153.135/chongyin/headUpload/000000000cec89bde09de4709a74a569a4f68048d.png","dzhurl4":"http://47.92.153.135/chongyin/headUpload/000000000964d5120e1614f009fade73d6c882278.png","dzhurl5":"http://47.92.153.135/chongyin/headUpload/000000000a0a8abb38e11474baaf96a29b24fd0ac.png","kurl1":"http://47.92.153.135/chongyin/headUpload/000000000b587e44eb9e1495ebdefb6a531effc2a.png","kurl2":"http://47.92.153.135/chongyin/headUpload/000000000b1eca1b976864ffab43793ce9b429f5e.png","kurl3":"http://47.92.153.135/chongyin/headUpload/000000000f41abe8a81db4b058f4aad6ef3f5f7aa.png","kurl4":"http://47.92.153.135/chongyin/headUpload/000000000219dc74f8717479a81934db486961468.png","kurl5":"http://47.92.153.135/chongyin/headUpload/000000000e184e29b12604c45933e103d259d3862.png","petdj":"1","petid":11,"xurl1":"http://47.92.153.135/chongyin/headUpload/000000000b514cb2085f14ec290baa383c5e497ed.png","xurl2":"http://47.92.153.135/chongyin/headUpload/00000000041cae62319324bf896c4644c0013e9cc.png","xurl3":"http://47.92.153.135/chongyin/headUpload/000000000267e1616f51b4b308d9c3ca9723cbd19.png","xurl4":"http://47.92.153.135/chongyin/headUpload/00000000050ca8abc40264b0995dad6416f1dd374.png","zyjurl1":"http://47.92.153.135/chongyin/headUpload/000000000b9fd36fa48f2444a8999d3b25f5b9598.png","zyjurl2":"http://47.92.153.135/chongyin/headUpload/0000000005391cd8947f94e208e2fe36f09b9d2a1.png","zyjurl3":"http://47.92.153.135/chongyin/headUpload/0000000008664e912e9b94a028cd8a88d5d7d1fe9.png","zyjurl4":"http://47.92.153.135/chongyin/headUpload/000000000ef89a673effd47beb686b335ed42d714.png"},{"curl1":"http://47.92.153.135/chongyin/headUpload/0000000000aea8d43d0d04d578b53a90e52fc03d9.png","curl2":"http://47.92.153.135/chongyin/headUpload/000000000f7b9495bbde849b8bb7d909cdbef9f78.png","curl3":"http://47.92.153.135/chongyin/headUpload/000000000adb1128823f344d88ff8de3223fc83ad.png","curl4":"http://47.92.153.135/chongyin/headUpload/0000000004c48d1119d5041309e143d3550ad026c.png","curl5":"http://47.92.153.135/chongyin/headUpload/000000000733ef74c498d479f8ea996ff3570edeb.png","curl6":"http://47.92.153.135/chongyin/headUpload/000000000bde9302f2fa84c0d93b84d344f31eaa6.png","curl7":"http://47.92.153.135/chongyin/headUpload/0000000008c462163915e4991b02f3ff552e44cee.png","dzhurl1":"http://47.92.153.135/chongyin/headUpload/0000000002c633d52537544f49227098337ae0cad.png","dzhurl2":"http://47.92.153.135/chongyin/headUpload/00000000066933c3a2a344a81b2c923eddc4f5b61.png","dzhurl3":"http://47.92.153.135/chongyin/headUpload/0000000004544e21be3d54a01a05afef0ee5c6a76.png","dzhurl4":"http://47.92.153.135/chongyin/headUpload/00000000048442cd07edc48d1baabc0cf748d9c32.png","dzhurl5":"http://47.92.153.135/chongyin/headUpload/0000000006c6c865ebf724a22a2961493b872c221.png","kurl1":"http://47.92.153.135/chongyin/headUpload/000000000a6f4f30b2f524bdd88683e6a0d92310a.png","kurl2":"http://47.92.153.135/chongyin/headUpload/000000000689ac91cb741445f86389680dd00ac81.png","kurl3":"http://47.92.153.135/chongyin/headUpload/000000000b61c3d00fd7c460fafd80ddb76b7ddef.png","kurl4":"http://47.92.153.135/chongyin/headUpload/0000000006b229868b73d424893baf95fc5ae020c.png","kurl5":"http://47.92.153.135/chongyin/headUpload/00000000013a96d67c60049bb910c3130f7192842.png","petdj":"3","petid":11,"xurl1":"http://47.92.153.135/chongyin/headUpload/0000000002427dedaaa734973b7c8afa459763833.png","xurl2":"http://47.92.153.135/chongyin/headUpload/0000000004fc5556782dc4ec784eed23a80605125.png","xurl3":"http://47.92.153.135/chongyin/headUpload/000000000d18bef75193e435b9962c53394598207.png","xurl4":"http://47.92.153.135/chongyin/headUpload/0000000009053c7558e3d451ba5103e3186f29aa8.png","zyjurl1":"http://47.92.153.135/chongyin/headUpload/000000000ac309da66d4142149d64d79ad0778dc3.png","zyjurl2":"http://47.92.153.135/chongyin/headUpload/000000000f1a7886e1283477c87821a6a675b715c.png","zyjurl3":"http://47.92.153.135/chongyin/headUpload/000000000fe685b2faa4d464e9e0bf559886cb66a.png","zyjurl4":"http://47.92.153.135/chongyin/headUpload/000000000841ffe1e10824cc8866852c0d68643b7.png"}]}
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
             * curl1 : http://47.92.153.135/chongyin/headUpload/00000000018076c0b312340b883c1cd6a135f1c42.png
             * curl2 : http://47.92.153.135/chongyin/headUpload/0000000005204732950ad4e17a3790ae7909747be.png
             * curl3 : http://47.92.153.135/chongyin/headUpload/000000000ad845afa8ad042a3b7e273e6152801e1.png
             * curl4 : http://47.92.153.135/chongyin/headUpload/00000000000c21ba2f599483bbd4fcfb44051fc62.png
             * curl5 : http://47.92.153.135/chongyin/headUpload/000000000694e4cd7e5e144bd93c629d981e9a1fe.png
             * curl6 : http://47.92.153.135/chongyin/headUpload/000000000337bfd016ec248ed9913deb8e02081b8.png
             * curl7 : http://47.92.153.135/chongyin/headUpload/0000000008563991547ba41fe8a28445a18869d2e.png
             * dzhurl1 : http://47.92.153.135/chongyin/headUpload/00000000061ea0962e2054aba98659358697ef271.png
             * dzhurl2 : http://47.92.153.135/chongyin/headUpload/00000000059b81c7afe2b4b06b2d47051d4a81d3e.png
             * dzhurl3 : http://47.92.153.135/chongyin/headUpload/00000000046075ed3173144d4b7f804dca0c8cbf2.png
             * dzhurl4 : http://47.92.153.135/chongyin/headUpload/000000000a8a548cc3e304ee39e3ca78192ec1282.png
             * dzhurl5 : http://47.92.153.135/chongyin/headUpload/0000000007eeb92c385e4434cb45631ba12549ece.png
             * kurl1 : http://47.92.153.135/chongyin/headUpload/00000000010b553e2d9494f37b604eb3f80e10803.png
             * kurl2 : http://47.92.153.135/chongyin/headUpload/0000000008ff0ff14c2454991afba5af15cfec61c.png
             * kurl3 : http://47.92.153.135/chongyin/headUpload/0000000003320d489a7ec45658dcfef98fd9be3a7.png
             * kurl4 : http://47.92.153.135/chongyin/headUpload/0000000007bd673b6a97d4cc38532e8e5974c34c9.png
             * kurl5 : http://47.92.153.135/chongyin/headUpload/000000000058a9d8d56ff494fba6bfe72af22459a.png
             * petdj : 2
             * petid : 11
             * xurl1 : http://47.92.153.135/chongyin/headUpload/000000000c830d4a326544fa791eb9e4b32073ec4.png
             * xurl2 : http://47.92.153.135/chongyin/headUpload/0000000009b5eb0ebdb9345c984cc86bf18935772.png
             * xurl3 : http://47.92.153.135/chongyin/headUpload/000000000e0282f962e2f43ddb816a8536a023cb6.png
             * xurl4 : http://47.92.153.135/chongyin/headUpload/00000000050b18bd7ab604071af287b91ebd01b87.png
             * zyjurl1 : http://47.92.153.135/chongyin/headUpload/0000000003c0e7d41c7904dfe8bbef159a2b6c1c8.png
             * zyjurl2 : http://47.92.153.135/chongyin/headUpload/000000000b48a7476a75b4e408ef5ad02c19b891b.png
             * zyjurl3 : http://47.92.153.135/chongyin/headUpload/00000000060a567cc664d48dda8e1cad5ab2600ea.png
             * zyjurl4 : http://47.92.153.135/chongyin/headUpload/00000000023209bddca02477e89c1a9e4b14afa2b.png
             */

            private String curl1;
            private String curl2;
            private String curl3;
            private String curl4;
            private String curl5;
            private String curl6;
            private String curl7;
            private String dzhurl1;
            private String dzhurl2;
            private String dzhurl3;
            private String dzhurl4;
            private String dzhurl5;
            private String kurl1;
            private String kurl2;
            private String kurl3;
            private String kurl4;
            private String kurl5;
            private String petdj;
            private int petid;
            private String xurl1;
            private String xurl2;
            private String xurl3;
            private String xurl4;
            private String zyjurl1;
            private String zyjurl2;
            private String zyjurl3;
            private String zyjurl4;

            public String getCurl1() {
                return curl1;
            }

            public void setCurl1(String curl1) {
                this.curl1 = curl1;
            }

            public String getCurl2() {
                return curl2;
            }

            public void setCurl2(String curl2) {
                this.curl2 = curl2;
            }

            public String getCurl3() {
                return curl3;
            }

            public void setCurl3(String curl3) {
                this.curl3 = curl3;
            }

            public String getCurl4() {
                return curl4;
            }

            public void setCurl4(String curl4) {
                this.curl4 = curl4;
            }

            public String getCurl5() {
                return curl5;
            }

            public void setCurl5(String curl5) {
                this.curl5 = curl5;
            }

            public String getCurl6() {
                return curl6;
            }

            public void setCurl6(String curl6) {
                this.curl6 = curl6;
            }

            public String getCurl7() {
                return curl7;
            }

            public void setCurl7(String curl7) {
                this.curl7 = curl7;
            }

            public String getDzhurl1() {
                return dzhurl1;
            }

            public void setDzhurl1(String dzhurl1) {
                this.dzhurl1 = dzhurl1;
            }

            public String getDzhurl2() {
                return dzhurl2;
            }

            public void setDzhurl2(String dzhurl2) {
                this.dzhurl2 = dzhurl2;
            }

            public String getDzhurl3() {
                return dzhurl3;
            }

            public void setDzhurl3(String dzhurl3) {
                this.dzhurl3 = dzhurl3;
            }

            public String getDzhurl4() {
                return dzhurl4;
            }

            public void setDzhurl4(String dzhurl4) {
                this.dzhurl4 = dzhurl4;
            }

            public String getDzhurl5() {
                return dzhurl5;
            }

            public void setDzhurl5(String dzhurl5) {
                this.dzhurl5 = dzhurl5;
            }

            public String getKurl1() {
                return kurl1;
            }

            public void setKurl1(String kurl1) {
                this.kurl1 = kurl1;
            }

            public String getKurl2() {
                return kurl2;
            }

            public void setKurl2(String kurl2) {
                this.kurl2 = kurl2;
            }

            public String getKurl3() {
                return kurl3;
            }

            public void setKurl3(String kurl3) {
                this.kurl3 = kurl3;
            }

            public String getKurl4() {
                return kurl4;
            }

            public void setKurl4(String kurl4) {
                this.kurl4 = kurl4;
            }

            public String getKurl5() {
                return kurl5;
            }

            public void setKurl5(String kurl5) {
                this.kurl5 = kurl5;
            }

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

            public String getXurl1() {
                return xurl1;
            }

            public void setXurl1(String xurl1) {
                this.xurl1 = xurl1;
            }

            public String getXurl2() {
                return xurl2;
            }

            public void setXurl2(String xurl2) {
                this.xurl2 = xurl2;
            }

            public String getXurl3() {
                return xurl3;
            }

            public void setXurl3(String xurl3) {
                this.xurl3 = xurl3;
            }

            public String getXurl4() {
                return xurl4;
            }

            public void setXurl4(String xurl4) {
                this.xurl4 = xurl4;
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
