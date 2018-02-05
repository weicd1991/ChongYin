package com.jsmy.chongyin.wxapi;

/**
 * Created by Administrator on 2017/5/25.
 */

public class WXRequset {
    private String key;
    private String value;

    public WXRequset(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
