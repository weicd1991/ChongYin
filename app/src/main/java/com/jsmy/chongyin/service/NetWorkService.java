package com.jsmy.chongyin.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.utils.CheckNetWork;
import com.jsmy.chongyin.utils.TrafficBean;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

public class NetWorkService extends Service {

//    /**
//     * 消息处理器
//     */
//    private Handler handler;
//    /**
//     * 流量信息对象
//     */
//    private TrafficBean trafficBean;

    private Timer timer;
    private ConnectivityManager manager;
    @Override
    public void onCreate() {
        super.onCreate();
//        try {
//            handler = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    if (msg.what == 1) {
//                        Double d = (Double) msg.obj;
//                        int i = d.compareTo(1d);
//                        if (i < 0) {
//                            MyApplication.getMyApplication().isShowNet = true;
//                        } else {
//                            MyApplication.getMyApplication().isShowNet = false;
//                        }
//                        EventBus.getDefault().post(new DowloadEvent("sss","network"));
//                    }
//                    super.handleMessage(msg);
//                }
//            };
//            trafficBean = new TrafficBean(this, handler, 12580);
//            trafficBean.startCalculateNetSpeed();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(), 0, 3000);
        }
    }

    /**
     * 检测网络是否连接
     */
    private boolean NetworkAvailable() {
        try {
            //得到网络连接信息
            if (manager == null){
                manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            }
            if (manager != null) {
                // 获取NetworkInfo对象
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();

                //去进行判断网络是否连接
                if (networkInfo != null || networkInfo.isConnected()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    class RefreshTask extends TimerTask {

        @Override
        public void run() {

//            if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication().getApplicationContext()) == CheckNetWork.NETWORKTYPE_INVALID) {
//                MyApplication.getMyApplication().isShowNet = true;
//                handler.sendEmptyMessage(2);
//            } else if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication().getApplicationContext()) == CheckNetWork.NETWORKTYPE_2G) {
//                MyApplication.getMyApplication().isShowNet = true;
//                handler.sendEmptyMessage(1);
//            } else {
//                MyApplication.getMyApplication().isShowNet = false;
//                handler.sendEmptyMessage(0);
//            }

            if (NetworkAvailable()){
                MyApplication.getMyApplication().isShowNet = false;
                handler.sendEmptyMessage(0);
//                EventBus.getDefault().post(new DowloadEvent("0", "network"));
            }else {
                MyApplication.getMyApplication().isShowNet = true;
                handler.sendEmptyMessage(2);
//                EventBus.getDefault().post(new DowloadEvent("2", "network"));
            }


        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    EventBus.getDefault().post(new DowloadEvent("2", "network"));
                    break;
                case 1:
                    EventBus.getDefault().post(new DowloadEvent("1", "network"));
                    break;
                case 0:
                    EventBus.getDefault().post(new DowloadEvent("0", "network"));
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(), 0, 3000);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        trafficBean.stopCalculateNetSpeed();
        timer.cancel();
        timer = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
