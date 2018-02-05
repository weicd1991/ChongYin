package com.jsmy.chongyin.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/29.
 */

public class SharePrefUtil {
    public final static String SP_NAME = "com.jsmy.chongyin";
    public final static String SP_PET = "com.jsmy.chongyin.pet";

    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getString(key, defValue);
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().clear().commit();
    }

    public static void saveString(Context context, String sp_name, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().putInt(key, value).commit();
    }

    public static int getString(Context context, String sp_name, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(sp_name, 0);
        return sp.getInt(key, defValue);
    }

    public static void saveStringPet(Context context,String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(SP_PET, 0);
        sp.edit().putString(key, value).commit();
    }

    public static String getStringPet(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_PET, 0);
        return sp.getString(key, defValue);
    }
}
