package com.jsmy.chongyin.contents;

/**
 * EventBus传递信息类
 * Created by Administrator on 2017/3/29.
 */

public class DowloadEvent {
    private String msg;
    private String code;
    public DowloadEvent(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
