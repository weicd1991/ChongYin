package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/5/31.
 */

public class MyPetThing2Bean {
    private String lx;
    private int spid;
    private String tx;

    public MyPetThing2Bean(String lx, int spid, String tx) {
        this.lx = lx;
        this.spid = spid;
        this.tx = tx;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }
}
