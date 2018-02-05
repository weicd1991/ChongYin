package com.jsmy.chongyin.utils;

import com.jsmy.chongyin.contents.ServiceCode;

import java.io.File;

/**
 * Created by Administrator on 2017/5/27.
 */

public class CheckFiel {
    public static boolean checkTp(String name) {
        File file = new File(ServiceCode.BASE_PATH, name);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
