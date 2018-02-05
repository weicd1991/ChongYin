package com.jsmy.chongyin.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

import com.jsmy.chongyin.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/12/12.
 */

public class TextEnjomUtil {
    public static String getLYtext(String ly) {
        String replace = ly;
        try {
            for (int i = 0; i < 300; i++) {
                if (i < 10) {
                    replace.replaceAll("f00" + i, "<img src='" + getResId("f00" + i) + "'/>");
                } else if (i < 100) {
                    replace.replaceAll("f0" + i, "<img src='" + getResId("f0" + i) + "'/>");
                } else {
                    replace.replaceAll("f" + i, "<img src='" + getResId("f" + i) + "'/>");
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return replace;
    }

    public static int getResId(String key) throws NoSuchFieldException, IllegalAccessException {
        Field field = R.drawable.class.getDeclaredField(key);
        int resId = Integer.parseInt(field.get(null).toString());
        return resId;
    }

    public static CharSequence getEmjonText(final Context context, String ly){
        CharSequence charSequence = Html.fromHtml(ly,
                new Html.ImageGetter() {

                    @Override
                    public Drawable getDrawable(String source) {
                        // TODO Auto-generated method stub
                        Drawable drawable = context.getResources().getDrawable(
                                Integer.parseInt(source));
                        // 设置drawable的大小。设置为实际大小
                        drawable.setBounds(0, 0, UtilsTools.dip2px(context, 23), UtilsTools.dip2px(context, 23));
                        return drawable;
                    }
                }, null);
        return charSequence;
    }
}
