package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/4/24.
 */

public class GoldBean {
    private int rosuceId;
    private String normalGold;
    private String vipGold;

    public GoldBean(int rosuceId, String normalGold, String vipGold) {
        this.rosuceId = rosuceId;
        this.normalGold = normalGold;
        this.vipGold = vipGold;
    }

    public int getRosuceId() {
        return rosuceId;
    }

    public void setRosuceId(int rosuceId) {
        this.rosuceId = rosuceId;
    }

    public String getNormalGold() {
        return normalGold;
    }

    public void setNormalGold(String normalGold) {
        this.normalGold = normalGold;
    }

    public String getVipGold() {
        return vipGold;
    }

    public void setVipGold(String vipGold) {
        this.vipGold = vipGold;
    }
}
