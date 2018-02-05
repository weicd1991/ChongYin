package com.jsmy.chongyin.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 检查网络状态
 * Created by Administrator on 2017/3/29.
 */

public class CheckNetWork {
    /**
     * 没有网络
     */
    public static final int NETWORKTYPE_INVALID = 0;
    /**
     * wap网络
     */
    public static final int NETWORKTYPE_WAP = 1;
    /**
     * 2G网络
     */
    public static final int NETWORKTYPE_2G = 2;
    /**
     * 3G和3G以上网络，或统称为快速网络
     */
    public static final int NETWORKTYPE_3G = 3;
    /**
     * wifi网络
     */
    public static final int NETWORKTYPE_WIFI = 4;
    //当前网络状态
    private static int mNetWorkType;


    /**
     * 获取网络状态，wifi,wap,2g,3g.
     *
     * @param context 上下文
     * @return int 网络状态 {@link #NETWORKTYPE_2G},{@link #NETWORKTYPE_3G},          *{@link #NETWORKTYPE_INVALID},{@link #NETWORKTYPE_WAP}* <p>{@link #NETWORKTYPE_WIFI}
     */
    public static int getNetWorkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                mNetWorkType = NETWORKTYPE_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                mNetWorkType = TextUtils.isEmpty(proxyHost)
                        ? (isFastMobileNetwork(context) ? NETWORKTYPE_3G : NETWORKTYPE_2G)
                        : NETWORKTYPE_WAP;
            }
        } else {
            mNetWorkType = NETWORKTYPE_INVALID;
        }
        return mNetWorkType;
    }


    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps ,2G CDMA2000 1xRTT (RTT - 无线电传输技术) 144kbps 2G的过渡
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps ,2G 电信 Code Division Multiple Access 码分多址
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps ,2G(2.75G) Enhanced Data Rate for GSM Evolution 384kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps ,3G (EVDO 全程 CDMA2000 1xEV-DO) Evolution - Data Only (Data Optimized) 153.6kps - 2.4mbps 属于3G
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps, 3G 1.8mbps - 3.1mbps 属于3G过渡，3.5G
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps ,2G(2.5) General Packet Radia Service 114kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps ,3.5G 高速下行分组接入 3.5G WCDMA High Speed Downlink Packet Access 14.4mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps ,3G (分HSDPA,HSUPA) High Speed Packet Access
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps ,3.5G High Speed Uplink Packet Access 高速上行链路分组接入 1.4 - 5.8 mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps ,3G WCDMA 联通3G Universal MOBILE Telecommunication System 完整的3G移动通信技术标准
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps ,3G CDMA2000向LTE 4G的中间产物 Evolved High Rate Packet Data HRPD的升级
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps ,3G EV-DO Rev.B 14.7Mbps 下行 3.5G
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps ,3G HSPAP 比 HSDPA 快些
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps,2G Integrated Dispatch Enhanced Networks 集成数字增强型网络 （属于2G，来自维基百科）
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps ,4G Long Term Evolution FDD-LTE 和 TDD-LTE , 3G过渡，升级版 LTE Advanced 才是4G
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }
}
