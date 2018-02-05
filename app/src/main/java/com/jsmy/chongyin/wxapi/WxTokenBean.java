package com.jsmy.chongyin.wxapi;

/**
 * Created by Administrator on 2017/5/18.
 */

public class WxTokenBean {

    /**
     * access_token : ibnk7w7GX5p-KS7_m5VDv_hb9eH2Xsq_2H_7ru7sJ77Ivi3QR-oYFE0bmP4V8eISdzp3N6sCi_i4dfPAUydYGYHpQASzDVNTRdm45zUwbnQ
     * expires_in : 7200
     * refresh_token : 9VcytYkuXnTgz2uHfiopBZ3z6Kdy1hW2VQ-HfeZ7VkQ9uQLx-7L-cEN_jDzP6v6T8IX9RlLgR3SgMPf55wGCUGygY033rOf_qXxwiUOZCzo
     * openid : oaWNI1r8cbkigSkgvluJ638S1KrQ
     * scope : snsapi_userinfo
     * unionid : oDwy40zzGPxx4yjLSOaqa6zClmb0
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
