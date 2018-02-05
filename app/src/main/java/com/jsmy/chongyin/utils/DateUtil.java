package com.jsmy.chongyin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/30.
 */

public class DateUtil {
    public static String formatDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String parse(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
        return sdf.parse(strDate).toString();
    }

    public static String formatDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1); //今天的时间加一天
        date = calendar.getTime();
        return date;
    }

    public static Date getFrontDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1); //今天的时间减一天
        date = calendar.getTime();
        return date;
    }

    public static Date getNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1); //今天的时间加一月
        date = calendar.getTime();
        return date;
    }

    public static Date getFrontMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1); //今天的时间减一月
        date = calendar.getTime();
        return date;
    }
}
