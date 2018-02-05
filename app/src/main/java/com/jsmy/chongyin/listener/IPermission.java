package com.jsmy.chongyin.listener;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public interface IPermission {
    //权限被允许时的回调
    void onGranted();
    //权限被拒绝时的回调， 参数：被拒绝权限的集合
    void onDenied(List<String> deniedPermissions);
}
