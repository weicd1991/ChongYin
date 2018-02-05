package com.jsmy.chongyin.windowmanager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.jsmy.chongyin.activity.LoginActivity;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.activity.StartActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.view.DesktopLayout;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Administrator on 2017/4/5.
 */

public class MyWindowManager {

    /**
     * 用于控制在屏幕上添加或移除悬浮窗
     */
    private static WindowManager mWindowManager;

    /**
     * 用于获取手机可用内存
     */
    private static ActivityManager mActivityManager;

    /**
     * 如果WindowManager还未创建，则创建一个新的WindowManager返回。否则返回当前已创建的WindowManager。
     *
     * @param context 必须为应用程序的Context.
     * @return WindowManager的实例，用于控制在屏幕上添加或移除悬浮窗。
     */
    public static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    /**
     * 如果ActivityManager还未创建，则创建一个新的ActivityManager返回。否则返回当前已创建的ActivityManager。
     *
     * @param context 可传入应用程序上下文。
     * @return ActivityManager的实例，用于获取手机可用内存。
     */
    private static ActivityManager getActivityManager(Context context) {
        if (mActivityManager == null) {
            mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }
        return mActivityManager;
    }

    /**
     * 设置WindowManager
     */
    private static void createWindowManager(Context context) {
        // 取得系统窗体
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        // 窗体的布局样式
        mLayout = new WindowManager.LayoutParams();

        // 设置窗体显示类型——TYPE_SYSTEM_ALERT(系统提示)
        /**
         * 显示悬浮窗，需要有一个服务运行在后台, 通过getSystemService(Context.WINDOW_SERVICE)拿到WindowManager， 然后向其中addView,
         * addView第二个参数是一个WindowManager.LayoutParams，WindowManager.LayoutParams中有一个成员type，一般设置成TYPE_PHONE就可以悬浮在很多view的上方了,
         * 但是调用这个方法需要申请android.permission.SYSTEM_ALERT_WINDOW权限。
         * type 为 WindowManager.LayoutParams.TYPE_PHONE 和 WindowManager.LayoutParams.TYPE_SYSTEM_ALERT 需要申请android.permission.SYSTEM_ALERT_WINDOW 权限，
         * 否则无法显示，报错：
         * E/AndroidRuntime: android.view.WindowManager$BadTokenException: Unable to add window android.view.ViewRoot-- permission denied for this window type
         * 将type设置成TYPE_TOAST，不需要android.permission.SYSTEM_ALERT_WINDOW权限就能显示一个悬浮窗。
         * 但仅在 API level >= 19 时可以达到目的，API level 19 以下因无法接收无法接收触摸（点击)和按键事件。
         */

        String packname = context.getPackageName();
        PackageManager pm = context.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.SYSTEM_ALERT_WINDOW", packname));

//        if (permission) {
//            mLayout.type = WindowManager.LayoutParams.TYPE_PHONE;
//        } else {
//            mLayout.type = WindowManager.LayoutParams.TYPE_TOAST;
//        }

        mLayout.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;

//        mLayout.type = WindowManager.LayoutParams.TYPE_TOAST;
        // 设置窗体焦点及触摸：
        // FLAG_NOT_FOCUSABLE(不能获得按键输入焦点)

        mLayout.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        // 设置显示的模式
        mLayout.format = PixelFormat.RGBA_8888;

        // 设置对齐的方法
        mLayout.gravity = Gravity.TOP | Gravity.LEFT;

        // 设置窗体宽度和高度
        mLayout.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayout.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    private static WindowManager.LayoutParams mLayout;
    public static DesktopLayout mDesktopLayout;
    private static long startTime;
    private static long downTime;
    private static long upTime;
    // 声明屏幕的宽高
    private static float x, y;
    private static int top;

    /**
     * 创建一个小悬浮窗。初始位置为屏幕的右部中间位置。
     *
     * @param context 必须为应用程序的Context.
     */
    public static void createSmallWindow(final Context context, int higth, String name) {
        if (mDesktopLayout != null)
            return;
        createWindowManager(context);
        mDesktopLayout = new DesktopLayout(context, name);
        top = higth;
        mDesktopLayout.setOnTouchListener(new View.OnTouchListener() {
            float mTouchStartX;
            float mTouchStartY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 获取相对屏幕的坐标，即以屏幕左上角为原点
                x = event.getRawX();
                y = event.getRawY() - top; // 25是系统状态栏的高度
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 获取相对View的坐标，即以此View左上角为原点
                        mTouchStartX = event.getX();
                        mTouchStartY = event.getY();
                        downTime = System.currentTimeMillis();
//                        long end = System.currentTimeMillis() - startTime;
                        // 双击的间隔在 300ms以下
//                        if (end < 300) {
//                            if (isHome()) {
//                                Intent intent = new Intent(context, StartActivity.class);
//                                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                                context.startActivity(intent);
//                            }
//                        }else {
//
//                        }
//                        mDesktopLayout.showPanding();
//                        startTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 更新浮动窗口位置参数
                        mLayout.x = (int) (x - mTouchStartX);
                        mLayout.y = (int) (y - mTouchStartY);
                        mWindowManager.updateViewLayout(v, mLayout);
                        break;
                    case MotionEvent.ACTION_UP:

                        // 更新浮动窗口位置参数
                        mLayout.x = (int) (x - mTouchStartX);
                        mLayout.y = (int) (y - mTouchStartY);
                        mWindowManager.updateViewLayout(v, mLayout);

                        // 可以在此记录最后一次的位置
                        mTouchStartX = mTouchStartY = 0;
                        upTime = System.currentTimeMillis();
                        if (upTime - downTime < 500) {
                            mDesktopLayout.showPanding();
                        }
                        break;
                }
                return true;
            }
        });
        mWindowManager.addView(mDesktopLayout, mLayout);
    }

    /**
     * 将悬浮窗从屏幕上移除。
     *
     * @param context 必须为应用程序的Context.
     */
    public static void removeMyWindow(Context context) {
        if (mDesktopLayout != null) {
            WindowManager windowManager = getWindowManager(context);
            mDesktopLayout.closeTimer();
            mDesktopLayout.anima.stopAnima();
            windowManager.removeView(mDesktopLayout);
            mDesktopLayout = null;
        }
    }

    /**
     * 是否有悬浮窗显示在屏幕上。
     *
     * @return 有悬浮窗显示在桌面上返回true，没有的话返回false。
     */
    public static boolean isWindowShowing() {
        return mDesktopLayout != null;
    }

    public static void updateUsedPercent(String msg) {
        if (mDesktopLayout != null)
            mDesktopLayout.setTv(msg);
    }

    /**
     * 判断当前界面是否是桌面
     */
    private static boolean isHome() {
        ActivityManager mActivityManager = (ActivityManager) MyApplication.getMyApplication().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        List<ActivityManager.RunningAppProcessInfo> list = mActivityManager.getRunningAppProcesses();
        return getHomes().contains(rti.get(0).topActivity.getPackageName());
    }

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private static List<String> getHomes() {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = MyApplication.getMyApplication().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }
}
