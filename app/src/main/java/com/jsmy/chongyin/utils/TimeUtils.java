package com.jsmy.chongyin.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/7.
 */

public class TimeUtils {

    public static String getCHTime(String time) {
        long old = Long.parseLong(time);
        long now = Calendar.getInstance().getTimeInMillis();
        long etc = now - old;
        if (etc <= 60 * 1000) {
            return "1分钟前";
        }
        if (etc < 60 * 60 * 1000) {
            String fen = etc / 1000 / 60 + "";
            return fen + "分钟前";
        }
        if (etc < 24 * 60 * 60 * 1000) {
            String shi = etc / 1000 / 60 / 60 + "";
            return shi + "小时前";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(old);
        return format.format(date);

    }
}
