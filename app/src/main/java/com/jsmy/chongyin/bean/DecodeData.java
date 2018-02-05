package com.jsmy.chongyin.bean;

import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/12.
 */

public class DecodeData {

    public static final String MSG = "msg";
    public static final String CHECK = "check";
    public static final String CODE = "code";

    public static String getCodeRoMsg(String result, String need) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String msg = jsonObject.getString(MSG);
            String code = jsonObject.getString(CODE);
            String check = jsonObject.getString(CHECK);
            if (MSG.equals(need)) {
                return msg;
            } else if (CODE.equals(need)) {
                return code;
            } else if (CHECK.equals(need)) {
                return check;
            } else {
                return "";
            }
        } catch (JSONException e) {
            return "";
        }
    }

}
