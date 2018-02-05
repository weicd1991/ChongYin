package com.jsmy.chongyin.wxapi;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class WxUserBean {

    /**
     * openid : oaWNI1r8cbkigSkgvluJ638S1KrQ
     * nickname : 上流小瘪三
     * sex : 1
     * language : zh_CN
     * city :
     * province :
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/PndsXplJdPMAdP6663joKha8ibyoJLnjLfEjy7GHUKLOWGZ4gvlgEPGa7ShfZo80MZKEL6c1AwQu9I9MQ7RMlLF4ian2PUsAK6/0
     * privilege : []
     * unionid : oDwy40zzGPxx4yjLSOaqa6zClmb0
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
