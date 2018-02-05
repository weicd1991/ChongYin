package com.jsmy.chongyin.bean;

/**
 * Created by Administrator on 2017/9/22.
 */

public class RegisteryzmBean {

    /**
     * check : 1.011
     * code : Y
     * data : {}
     * msg : 验证码发送成功
     * telcode : 98794
     */

    private String check;
    private String code;
    private DataBean data;
    private String msg;
    private String telcode;

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

    public String getTelcode() {
        return telcode;
    }

    public void setTelcode(String telcode) {
        this.telcode = telcode;
    }

    public static class DataBean {
    }
}
