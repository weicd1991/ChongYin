package com.jsmy.chongyin.service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * @author xiong_it
 * @description
 * @charset UTF-8
 * @date 2015-7-20上午10:31:39
 */
public class LocationService extends IntentService implements LocationListener {
    private static final String TAG = "LocationService";
    private static final String TAG1 = "LocationService1";
    private static final String TAG2 = "LocationService2";
    private static final String SERVICE_NAME = "LocationService";

    private static final long MIN_TIME = 1000l;
    private static final float MIN_DISTANCE = 10f;

    private LocationManager locationManager;
    private Location location;

    public LocationService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MyLog.showLog(TAG, "onHandleIntent");
        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

            /*
             * 进行定位
			 * provider:用于定位的locationProvider字符串
			 * minTime:时间更新间隔，单位：ms
			 * minDistance:位置刷新距离，单位：m
			 * listener:用于定位更新的监听者locationListener
			 */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null || locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
                MyLog.showLog(TAG, "正在定位");
                try {
                    List<String> providerList = locationManager.getProviders(true);
                    String addressLine;
                    if (providerList.contains(LocationManager.GPS_PROVIDER)) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        addressLine = showLocation(location.getLatitude(), location.getLongitude());
                        EventBus.getDefault().post(new DowloadEvent(location.getLongitude() + "_" + location.getLatitude() + "-" + addressLine, Constants.LOCATION));
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                        MyApplication.getMyApplication().latitude = location.getLatitude() + "";
                        if (!"".equals(location.getLatitude()))
                            SharePrefUtil.saveString(this, "latitude", location.getLatitude() + "");
                        MyApplication.getMyApplication().longitude = location.getLongitude() + "";
                        if (!"".equals(location.getLongitude()))
                            SharePrefUtil.saveString(this, "longitude", location.getLongitude() + "");
                        List<Address> locationList = getLocationList(location.getLatitude(), location.getLongitude());
                        Address address = locationList.get(0);
                        MyApplication.getMyApplication().addressLine = addressLine + "";
                        if (!"".equals(addressLine))
                            SharePrefUtil.saveString(this, "addressLine", addressLine + "");
                        MyApplication.getMyApplication().countryName = address.getCountryName();
                        if (!"".equals(address.getCountryName()))
                            SharePrefUtil.saveString(this, "countryName", address.getCountryName() + "");
                        MyApplication.getMyApplication().locality = address.getLocality();
                        if (!"".equals(address.getLocality()))
                            SharePrefUtil.saveString(this, "locality", address.getLocality() + "");
                        MyApplication.getMyApplication().admin = address.getAdminArea();
                        if (!"".equals(address.getAdminArea()))
                            SharePrefUtil.saveString(this, "admin", address.getAdminArea() + "");
                    } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        addressLine = showLocation(location.getLatitude(), location.getLongitude());
                        EventBus.getDefault().post(new DowloadEvent(location.getLongitude() + "_" + location.getLatitude() + "-" + addressLine, Constants.LOCATION));
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                        MyApplication.getMyApplication().latitude = location.getLatitude() + "";
                        if (!"".equals(location.getLatitude() + ""))
                            SharePrefUtil.saveString(this, "latitude", location.getLatitude() + "");
                        MyApplication.getMyApplication().longitude = location.getLongitude() + "";
                        if (!"".equals(location.getLongitude() + ""))
                            SharePrefUtil.saveString(this, "longitude", location.getLongitude() + "");
                        List<Address> locationList = getLocationList(location.getLatitude(), location.getLongitude());
                        Address address = locationList.get(0);
                        MyApplication.getMyApplication().addressLine = addressLine + "";
                        if (!"".equals(addressLine + ""))
                            SharePrefUtil.saveString(this, "addressLine", addressLine + "");
                        MyApplication.getMyApplication().countryName = address.getCountryName();
                        if (!"".equals(address.getCountryName() + ""))
                            SharePrefUtil.saveString(this, "countryName", address.getCountryName() + "");
                        MyApplication.getMyApplication().locality = address.getLocality();
                        if (!"".equals(address.getLocality() + ""))
                            SharePrefUtil.saveString(this, "locality", address.getLocality() + "");
                        MyApplication.getMyApplication().admin = address.getAdminArea();
                        if (!"".equals(address.getAdminArea() + ""))
                            SharePrefUtil.saveString(this, "admin", address.getAdminArea() + "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(getApplicationContext(), "无法定位，请打开定位服务!");
                    }
                });
            }
        }


    }

    @Override
    public void onLocationChanged(Location location) {
        //得到纬度
        double latitude = location.getLatitude();
        MyLog.showLog(TAG, "C 纬度 =" + latitude);
        //得到经度
        double longitude = location.getLongitude();
        MyLog.showLog(TAG, "C 经度 =" + longitude);
        //得到位置
        String addressLine = showLocation(latitude, longitude);
        MyLog.showLog(TAG, "C 位置 =" + addressLine);
        // 通知Activity
        EventBus.getDefault().post(new DowloadEvent(longitude + "_" + latitude + "-" + addressLine, Constants.LOCATION));
        MyLog.showLog(TAG, "sendBroadcast");
        List<Address> locationList = getLocationList(latitude, longitude);
        if (locationList != null && locationList.size() > 0) {
            Address address = locationList.get(0);
        }
//        MyApplication.getMyApplication().latitude = location.getLatitude() + "";
//        MyApplication.getMyApplication().longitude = location.getLongitude() + "";
//        MyApplication.getMyApplication().addressLine = addressLine + "";
//        MyApplication.getMyApplication().countryName = address.getCountryName();
//        MyApplication.getMyApplication().locality = address.getLocality();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    private List<Address> getLocationList(double latitude, double longitude) {
        MyLog.showLog(TAG, "G getLocationList");
        Geocoder gc = new Geocoder(this, Locale.getDefault());
        List<Address> locationList = null;
        try {
            locationList = gc.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationList;
    }

    private String showLocation(double latitude, double longitude) {
        List<Address> locationList = getLocationList(latitude, longitude);
        String addressLine = "";
        MyLog.showLog(TAG, "S 经度 =" + longitude);
        MyLog.showLog(TAG, "S 纬度 =" + latitude);
        if (!locationList.isEmpty()) {
            Address address = locationList.get(0);
            MyLog.showLog(TAG, "S address =" + address);
            String countryName = address.getCountryName();
            MyLog.showLog(TAG, "S countryName = " + countryName);
            String countryCode = address.getCountryCode();
            MyLog.showLog(TAG, "S countryCode = " + countryCode);
            String locality = address.getLocality();
            MyLog.showLog(TAG, "S locality = " + locality);
            for (int i = 0; address.getAddressLine(i) != null; i++) {
                addressLine = address.getAddressLine(i);
                MyLog.showLog(TAG, "S addressLine = " + addressLine);
            }
            MyApplication.getMyApplication().latitude = latitude + "";
            if (!"".equals(latitude + ""))
                SharePrefUtil.saveString(this, "latitude", latitude + "");
            MyApplication.getMyApplication().longitude = longitude + "";
            if (!"".equals(longitude + ""))
                SharePrefUtil.saveString(this, "longitude", longitude + "");
            MyApplication.getMyApplication().addressLine = addressLine + "";
            if (!"".equals(addressLine + ""))
                SharePrefUtil.saveString(this, "addressLine", addressLine + "");
            MyApplication.getMyApplication().countryName = address.getCountryName();
            if (!"".equals(address.getCountryName() + ""))
                SharePrefUtil.saveString(this, "countryName", address.getCountryName() + "");
            MyApplication.getMyApplication().locality = address.getLocality();
            if (!"".equals(address.getLocality() + ""))
                SharePrefUtil.saveString(this, "locality", address.getLocality() + "");
            MyApplication.getMyApplication().admin = address.getAdminArea();
            if (!"".equals(address.getAdminArea() + ""))
                SharePrefUtil.saveString(this, "admin", address.getAdminArea() + "");

        }

        return addressLine;
    }

}
