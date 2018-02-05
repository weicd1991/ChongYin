package com.jsmy.chongyin.utils;


import android.content.Context;
import android.telephony.TelephonyManager;

import com.jsmy.chongyin.application.MyApplication;

/**
 * Created by Administrator on 2017/4/24.
 */

public class GetUUID {
    public static String getIdentity() {
        String identity = SharePrefUtil.getString(MyApplication.getMyApplication().getApplicationContext(), "identity", "identity");
        if (identity == null) {
            identity = java.util.UUID.randomUUID().toString();
            SharePrefUtil.saveString(MyApplication.getMyApplication().getApplicationContext(), "identity", identity);
        }
        return identity;
    }

    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        return DEVICE_ID;
    }
}
