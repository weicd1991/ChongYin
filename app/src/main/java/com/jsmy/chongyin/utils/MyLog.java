package com.jsmy.chongyin.utils;

import android.util.Log;

import com.jsmy.chongyin.application.MyApplication;

/**
 * Created by Administrator on 2017/3/29.
 */

public class MyLog {
    public static final boolean debug = false;

    public static void showLog(String TAG, String showContants) {
        if (debug) {
            Log.e(TAG, showContants);
        } else {

        }
    }
}
