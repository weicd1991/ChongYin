package com.jsmy.chongyin.service;

import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.windowmanager.MyWindowManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FloatWindowService extends Service {

    /**
     * 用于在线程中创建或移除悬浮窗。
     */
    private Handler handler = new Handler();

    /**
     * 定时器，定时进行检测当前应该创建还是移除悬浮窗。
     */
    private Timer timer;

    private String msg = "";
    private String[] list = null;
    private int i = 0;
    private boolean isShowMsg = true;
    private boolean isFirst = true;
    private String isShow;
    private String name;
    private String change;
    private String chapet;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 开启定时器，每隔0.5秒刷新一次
        if (intent != null) {
            isShow = intent.getStringExtra("isShow");
            if (timer == null) {
                timer = new Timer();
                timer.scheduleAtFixedRate(new RefreshTask(), 0, 500);
            } else {

            }

            if (null != intent.getStringExtra("msg") && !"".equals(intent.getStringExtra("msg"))) {
                list = intent.getStringExtra("msg").split("&");
                isFirst = true;
            }

            if (isFirst) {
                mHandler.sendEmptyMessageDelayed(0, 100);
                isFirst = false;
            } else {

            }
            name = intent.getStringExtra("path");
            change = intent.getStringExtra("change");

            if ("Y".equals(change)) {
                if (null != MyWindowManager.mDesktopLayout)
                    MyWindowManager.mDesktopLayout.setImgLayoutParams();
            } else if ("o".equals(change)) {
                if (null != MyWindowManager.mDesktopLayout)
                    MyWindowManager.mDesktopLayout.showAnimation(1);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Service被终止的同时也停止定时器继续运行
        isShowMsg = false;
        MyWindowManager.removeMyWindow(getApplicationContext());
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    class RefreshTask extends TimerTask {

        @Override
        public void run() {
            // 当前界面是桌面，且没有悬浮窗显示，则创建悬浮窗。
            if (!MyWindowManager.isWindowShowing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if ("Y".equals(isShow)) {
                            isShowMsg = true;
                            MyWindowManager.createSmallWindow(getApplicationContext(), getStatusBarHeight(), name);
                        } else {

                        }
                    }
                });
            }
            // 当前界面不是桌面，且有悬浮窗显示，则移除悬浮窗。
            else if (MyWindowManager.isWindowShowing()) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if ("N".equals(isShow)) {
                            isShowMsg = false;
                            MyWindowManager.removeMyWindow(getApplicationContext());
                            timer.cancel();
                            timer = null;
                        }
                    }
                });

            }
            // 当前界面是桌面，且有悬浮窗显示，则更新内存数据。
            else if (MyWindowManager.isWindowShowing()) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        isShowMsg = true;
                    }
                });
            }
        }

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            if (!isShowMsg) {
                return;
            }
            if (null != list && list.length > 0 && i < list.length) {
                msg = list[i];
                i++;
                if (!"".equals(msg)) {
                    MyWindowManager.updateUsedPercent(msg);
                    mHandler.sendEmptyMessageDelayed(0, 7000);
                } else {

                }
            } else {
                i = 0;
            }
        }
    };

    /**
     * 判断当前界面是否是桌面
     */
    private boolean isHome() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        List<ActivityManager.RunningAppProcessInfo> list = mActivityManager.getRunningAppProcesses();
        return getHomes().contains(rti.get(0).topActivity.getPackageName());
    }

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private List<String> getHomes() {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = getApplicationContext().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }

    /**
     * 记录系统状态栏的高度
     */
    private static int statusBarHeight;

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

}
