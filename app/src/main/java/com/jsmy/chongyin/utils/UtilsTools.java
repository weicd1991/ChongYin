package com.jsmy.chongyin.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/10.
 */

public class UtilsTools {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getPetDj(int dj) {
        if (0 <= dj && dj <= 15) {
            return 1;
        } else if (16 <= dj && dj <= 63) {
            return 2;
        } else if (dj >= 64) {
            return 3;
        } else {
            return 1;
        }
    }

    public static boolean isMobile(String number) {
        String num = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            return number.matches(num);
        }
    }

    public static boolean isPassword(String password) {
        String num = "[a-zA-Z_0-9]{5,18}";
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            return password.matches(num);
        }
    }

}
