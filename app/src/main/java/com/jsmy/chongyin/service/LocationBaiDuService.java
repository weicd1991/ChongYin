package com.jsmy.chongyin.service;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LocationBaiDuService extends Service {

    public LocationClient mLocationClient = null;
    public BDAbstractLocationListener myListener = new MyLocationListener();

    public Timer timer;

    @Override
    public void onCreate() {
        MyLog.showLog("SSS", "onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initBaiduMap();
//        if (timer == null) {
//            timer = new Timer();
//            timer.schedule(new Work(), 0, 10 * 1000);
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    //百度地图
    private void initBaiduMap() {

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        initLocation();

        mLocationClient.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.setIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            MyLog.showLog("SSS", "onReceiveLocation()");
            MyLog.showLog("SSS", "latitude- " + location.getLatitude() + " - longitude- " + location.getLongitude());
            MyApplication.getMyApplication().latitude = location.getLatitude() + "";
            if (!"".equals(location.getLatitude()))
                SharePrefUtil.saveString(LocationBaiDuService.this, "latitude", location.getLatitude() + "");
            MyApplication.getMyApplication().longitude = location.getLongitude() + "";
            if (!"".equals(location.getLongitude()))
                SharePrefUtil.saveString(LocationBaiDuService.this, "longitude", location.getLongitude() + "");
            MyApplication.getMyApplication().addressLine = location.getAddress().address + location.getLocationDescribe();
            if (!"".equals(location.getAddress().address))
                SharePrefUtil.saveString(LocationBaiDuService.this, "addressLine", location.getAddress().address + location.getLocationDescribe());
            MyApplication.getMyApplication().countryName = location.getAddress().country;
            if (!"".equals(location.getAddress().country))
                SharePrefUtil.saveString(LocationBaiDuService.this, "countryName", location.getAddress().country + "");
            MyApplication.getMyApplication().locality = location.getAddress().city;
            if (!"".equals(location.getAddress().city))
                SharePrefUtil.saveString(LocationBaiDuService.this, "locality", location.getAddress().city + "");
            MyApplication.getMyApplication().admin = location.getAddress().province;
            if (!"".equals(location.getAddress().province))
                SharePrefUtil.saveString(LocationBaiDuService.this, "admin", location.getAddress().province + "");
            MyLog.showLog("SSS", "位置" + location.getAddress().address + location.getLocationDescribe());
            MyLog.showLog("SSS", "国家" + location.getAddress().country);
            MyLog.showLog("SSS", "城市" + location.getAddress().city);
            MyLog.showLog("SSS", "省份" + location.getAddress().province);
            MyLog.showLog("SSS", "当前位置描述信息" + location.getLocationDescribe());
            MyLog.showLog("SSS", "获取纬度信息" + location.getLatitude());
            MyLog.showLog("SSS", "获取经度信息" + location.getLongitude());
            setLocation(getApplicationContext());
            mLocationClient.stop();
        }

        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {
            MyLog.showLog("SSS", "onLocDiagnosticMessage()");
            Log.e("SSS", "onLocDiagnosticMessage()");

        }

    }

    class Work extends TimerTask {

        @Override
        public void run() {
            MyLog.showLog("SSS", "Work.run()");
//            if (mLocationClient != null) {
//                mLocationClient.start();
//            } else {
//                initBaiduMap();
//            }
            initBaiduMap();

        }
    }

    public void setLocation(Context context) {
        if (MyApplication.getMyApplication().userInfo != null && !"".equals(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""))) {
            //得到经度
            String jd = SharePrefUtil.getString(context, "longitude", MyApplication.getMyApplication().longitude);
            //得到纬度
            String wd = SharePrefUtil.getString(context, "latitude", MyApplication.getMyApplication().latitude);
            //得到位置
            String wz = SharePrefUtil.getString(context, "addressLine", MyApplication.getMyApplication().addressLine);
            String gj = SharePrefUtil.getString(context, "countryName", MyApplication.getMyApplication().countryName);
            String sf = SharePrefUtil.getString(context, "locality", MyApplication.getMyApplication().locality);
            String cs = SharePrefUtil.getString(context, "admin", MyApplication.getMyApplication().admin);
            String sip = SharePrefUtil.getString(context, "ip", MyApplication.getMyApplication().ip);
            Map<String, String> map = new HashMap<>();
            map.put("isAND", "Y");
            map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
            map.put("wz", wz);
            MyLog.showLog("WEI", "wz = " + wz);
            map.put("jd", jd);
            MyLog.showLog("WEI", "jd = " + jd);
            map.put("wd", wd);
            MyLog.showLog("WEI", "wd = " + wd);
            map.put("ip", sip);
            MyLog.showLog("WEI", "ip = " + sip);
            map.put("dl", SharePrefUtil.getString(context, "dl", "QQ"));
            MyLog.showLog("WEI", "dl = " + SharePrefUtil.getString(context, "dl", "QQ"));
            map.put("gj", gj);
            MyLog.showLog("WEI", "gj = " + gj);
            map.put("sf", sf);
            MyLog.showLog("WEI", "sf = " + cs);
            map.put("cs", cs);
            MyLog.showLog("WEI", "cs = " + sf);
            NetWork.getNetVolue(ServiceCode.UP_DATE_WZ, map, ServiceCode.NETWORK_GET, null);
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        } else {
            if (timer == null) {
                timer = new Timer();
                timer.schedule(new Work(), 0, 10 * 1000);
            }
        }
    }


}
