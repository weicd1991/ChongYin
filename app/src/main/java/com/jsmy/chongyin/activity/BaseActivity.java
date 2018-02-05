package com.jsmy.chongyin.activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.listener.IPermission;
import com.jsmy.chongyin.service.FloatWindowService;
import com.jsmy.chongyin.service.MusicService;
import com.jsmy.chongyin.utils.ActivityTack;
import com.jsmy.chongyin.utils.AnimaUtils;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.UtilsTools;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public abstract class BaseActivity extends FragmentActivity {
    public Gson gson;
    public Map<String, String> map;
    public String type = "";
    public static final String ADDFREND = "add";
    public static final String DELETFREND = "delet";
    public String zt = "";
    public static final String zero = "0";
    public static final String one = "1";
    public static final String two = "2";
    public static final String three = "3";

    public String friendID = "";
    public String payType = "";
    public String moon = "1";
    public String price = "20";
    public String isBackGroud = "N";
    public String isFrendData = "N";
    public String addType = "";
    public String petnc = "";
    public String yhids = "";
    public String hdNet = "";
    public String hdBt = "";

    public String personDate = "";
    public AnimaUtils anima;

    public String shaungchou = "";
    public String djs = "";

    public String drawable = "";
    public int cropWidth;
    public int cropHeight;

    public String setPassword = "";
    public String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        if (getContenView() != 0) {
            setContentView(getContenView());
        }
        //注册广播
        registerReceiver(mHomeKeyEventReceiver, new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        ButterKnife.bind(this);
        ActivityTack.getInstanse().addActivity(this);
        gson = new Gson();
        map = new HashMap<>();
        initView();
        initData();
        EventBus.getDefault().register(this);
        anima = new AnimaUtils();
        creatFolder();
    }

    //创建文件夹
    public void creatFolder() {
        File file = new File(ServiceCode.BASE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    //显示桌面宠物
    public void openDeskPet(String change) {
        String tag = SharePrefUtil.getStringPet(this, AtyTag.DeskPet, "0");
        Intent intent = new Intent(this, FloatWindowService.class);
        if (!"".equals(MyApplication.getMyApplication().userInfo.getPetid()) && !"0".equals(tag)) {
            intent.putExtra("isShow", "Y");
        } else {
            intent.putExtra("isShow", "N");
        }
        if (!"".equals(MyApplication.getMyApplication().userInfo.getPetid())) {
            String name = MyApplication.getMyApplication().userInfo.getPetid() + "_" +
                    UtilsTools.getPetDj(Integer.parseInt(MyApplication.getMyApplication().userInfo.getPetdj())) + "_" +
                    "zyjurl" + "_1.png";
            intent.putExtra("path", name);
            intent.putExtra("change", change);
            startService(intent);
        }
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 删除集合存储记录
        ActivityTack.getInstanse().removeActivity(this);
        EventBus.getDefault().unregister(this);
        unregisterReceiver(mHomeKeyEventReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Subscribe
    public void onEventMainThread(DowloadEvent event) {
        MyLog.showLog("EEE", " - " + event.getCode() + " - " + event.getMsg());
        if (null != event.getMsg() && !"".equals(event.getMsg())) {
            paresData(event.getMsg(), event.getCode());
        } else {

        }
    }

    //播放音乐
    public void playMusic() {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
    }

    //停止音乐
    public void stopMusic() {
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }

    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";
        String SYSTEM_HOME_KEY_LONG = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    //表示按了home键,程序到了后台
                    stopMusic();
                } else if (TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)) {
                    //表示长按home键,显示最近使用的程序列表
                    stopMusic();
                }
            }
        }
    };

    protected abstract int getContenView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void paresData(String result, String code);

    public void gotoSomeActivity(Context context, String ATY_TAG, boolean isAnim) {
        Intent intent = null;
        switch (ATY_TAG) {
            case AtyTag.ATY_Login:
                intent = new Intent(context, LoginActivity.class);
                break;
            case AtyTag.ATY_Setting:
                intent = new Intent(context, SettingActivity.class);
                break;
            case AtyTag.ATY_LuckPan:
                intent = new Intent(context, LuckPan2Activity.class);
                break;
            case AtyTag.ATY_DesktopPets:
                intent = new Intent(context, DesktopPetsActivity.class);
                break;
            case AtyTag.ATY_PersonCenter:
                intent = new Intent(context, PersonCenterActivity.class);
                break;
            case AtyTag.ATY_Main:
                intent = new Intent(context, MainActivity.class);
                intent.putExtra("petnc", petnc);
                intent.putExtra("yhids", yhids);
                intent.putExtra("type", type);
                break;
            case AtyTag.ATY_UseTerms:
                intent = new Intent(context, UseTermsActivity.class);
                break;
            case AtyTag.ATY_AddFood:
                intent = new Intent(context, AddFoodActivity.class);
                break;
            case AtyTag.ATY_AddFoodPay:
                intent = new Intent(context, AddFoodPayActivity.class);
                intent.putExtra("addType", addType);
                break;
            case AtyTag.ATY_Advertisement:
                intent = new Intent(context, AdvertisementActivity.class);
                break;
            case AtyTag.ATY_Advertisement2:
                intent = new Intent(context, Advertisement2Activity.class);
                break;
            case AtyTag.ATY_BeingVIP:
                intent = new Intent(context, BeingVIPActivity.class);
                break;
            case AtyTag.ATY_PetShop:
                intent = new Intent(context, PetShopActivity.class);
                break;
            case AtyTag.ATY_NewFrend:
                intent = new Intent(context, NewFrendActivity.class);
                break;
            case AtyTag.ATY_PersonData:
                intent = new Intent(context, PersonDataActivity.class);
                intent.putExtra("isFrendData", isFrendData);
                break;
            case AtyTag.ATY_AddGold:
                intent = new Intent(context, AddGoldActivity.class);
                break;
            case AtyTag.ATY_LoveRank:
                intent = new Intent(context, LoveRankActivity.class);
                break;
            case AtyTag.ATY_PetChangeName:
                intent = new Intent(context, PetChangeNameActivity.class);
                intent.putExtra("personDate", personDate);
                break;
            case AtyTag.ATY_WebView:
                intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url", hdNet);
                intent.putExtra("bt", hdBt);
                break;
            case AtyTag.ATY_PersonChangeName:
                intent = new Intent(context, PersonChangeNameActivity.class);
                intent.putExtra("personDate", personDate);
                break;
            case AtyTag.ATY_PersonChangeGender:
                intent = new Intent(context, PersonChangeGenderActivity.class);
                intent.putExtra("personDate", personDate);
                break;
            case AtyTag.ATY_PersonChangeAge:
                intent = new Intent(context, PersonChangeAgeActivity.class);
                intent.putExtra("personDate", personDate);
                break;
            case AtyTag.ATY_PersonSignature:
                intent = new Intent(context, PersonSignatureActivity.class);
                intent.putExtra("personDate", personDate);
                break;
            case AtyTag.ATY_AddNewFrend:
                intent = new Intent(context, AddNewFrendActivity.class);
                intent.putExtra("type", type);
                intent.putExtra("friendID", friendID);
                intent.putExtra("zt", zt);
                break;
            case AtyTag.ATY_OpenVIP:
                intent = new Intent(context, OpenVIPActivity.class);
                break;
            case AtyTag.ATY_PayVIP:
                intent = new Intent(context, PayVIPActivity.class);
                intent.putExtra("payType", payType);
                intent.putExtra("moon", moon);
                intent.putExtra("price", price);
                break;
            case AtyTag.ATY_ChoiceImage:
                intent = new Intent(context, ChoiceImageActivity.class);
                intent.putExtra("isBackGroud", isBackGroud);
                break;
            case AtyTag.ATY_LuckPan2:
                intent = new Intent(context, LuckPan2Activity.class);
                intent.putExtra("shuangchou", shaungchou);
                intent.putExtra("djs", djs);
                break;
            case AtyTag.ATY_AboutUs:
                intent = new Intent(context, AboutUsActivity.class);
                break;
            case AtyTag.ATY_Crop:
                intent = new Intent(context, CropActivity.class);
                intent.putExtra("drawable", drawable);
                intent.putExtra("cropWidth", cropWidth);
                intent.putExtra("cropHeight", cropHeight);
                break;
            case AtyTag.ATY_ForgetPassword:
                intent = new Intent(context, ForgetPasswordActivity.class);
                break;
            case AtyTag.ATY_SetPassword:
                intent = new Intent(context, SetPasswordActivity.class);
                intent.putExtra("setpassword", setPassword);
                intent.putExtra("phone", phone);
                break;
            case AtyTag.ATY_Registr:
                intent = new Intent(context, RegistrActivity.class);
                break;
            case AtyTag.ATY_Nearby:
                intent = new Intent(context, NearbyActivity.class);
                break;
            case AtyTag.ATY_Note:
                intent = new Intent(context, NoteActivity.class);
                break;
            case AtyTag.ATY_DanMuLiuYan:
                intent = new Intent(context, DanMuLiuYanActivity.class);
                break;
            case AtyTag.ATY_WeekRank:
                intent = new Intent(context,WeekRankActivity.class);
                break;
            default:
                break;
        }
        if (null != intent) {
            if (isAnim) {
                intent.putExtra(AtyTag.isAnim, isAnim);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_open, 0);
            } else {
                if (ATY_TAG.equals(AtyTag.ATY_Login) || ATY_TAG.equals(AtyTag.ATY_Main)) {
                    MyLog.showLog("TTT", ATY_TAG);
                    overridePendingTransition(R.anim.activity_aplha_in, R.anim.activity_aplha_out);
                }
                startActivity(intent);
            }

        }

    }

    private long lastClickTime;// 上次点击时间点

    /**
     * 判断事件出发时间间隔是否超过预定值
     *
     * @return
     * @Description
     */
    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 2000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 防止连续点击
     *
     * @Description
     */
    public void startActivity(Intent intent) {
        if (isFastDoubleClick()) {
            return;
        }
        super.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        if (getIntent().getBooleanExtra(AtyTag.isAnim, false)) {
            this.overridePendingTransition(0, R.anim.activity_close);
        }
    }

    //设置当前位置
    public void setLocation(Context context) {
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
        map.clear();
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
    }

    public void setLocationEsc(Context context) {
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
        map.clear();
        map.put("isAND", "Y");
        map.put("yhid", SharePrefUtil.getString(context, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("jd", jd);
        map.put("wd", wd);
        map.put("ip", sip);
        map.put("czwz", wz);
        map.put("gj", gj);
        map.put("sf", cs);
        map.put("cs", sf);
        NetWork.getNetVolue(ServiceCode.UP_DATE_ESC, map, ServiceCode.NETWORK_GET, null);
    }

    private static final int REQUEST_CODE = 1;
    private static IPermission mListener;

    //申请权限的方法
    public static void requestRunTimePermission(Activity activity, String[] permissions, IPermission listener) {
        //权限申请必须要用Activity对象
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), REQUEST_CODE);
        } else {
            //doSomething
            mListener.onGranted();
        }
    }

    //当用户拒绝或者同意权限时的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    //新朋友列表
    public void getApplyList() {
        map.clear();
        map.put("yhid", MyApplication.getMyApplication().userInfo.getYhid() + "");
        NetWork.getNetVolue(ServiceCode.APPLY_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    //设置当前喂养宠物
    private void setCurentPet(String petid) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("petid", petid);
        NetWork.getNetVolue(ServiceCode.UP_DATE_PET, map, ServiceCode.NETWORK_GET, null);

    }

    //购买食物
    public void buyFood(String spid, String cns, String ybcns) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("spid", spid);
        map.put("cns", "1");
        map.put("ybcns", ybcns);
        NetWork.getNetVolue(ServiceCode.UP_DATE_SP_SHOP, map, ServiceCode.NETWORK_GET, null);
    }

    //修改宠物名称
    public void changePetNc(String nc) {
        map.clear();
        map.put("isAND", "Y");
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("ybcns", "100");
        map.put("petnc", nc);
        map.put("ybpetid", MyApplication.getMyApplication().userInfo.getPetid() + "");
        NetWork.getNetVolue(ServiceCode.UP_DATE_PET_NC, map, ServiceCode.NETWORK_GET, null);
    }

    public void setAliasByYhid(final String yhid) {

        MyLog.showLog("JJJ", " --- " + yhid + " ---");
        //这是别名
        JPushInterface.setAlias(this, yhid, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                MyLog.showLog("JJJ", " --- " + yhid + " ---" + i);
                switch (i) {
                    case 0:
                        break;
                    case 6002:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //删除好友
    public void deletFriend(String yhids) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhids);
        NetWork.getNetVolue(ServiceCode.UP_DATE_FRIEND_DEL, map, ServiceCode.NETWORK_GET, null);
    }

    //添加好友
    public void addFriend(String yhids) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("yhids", yhids);
        NetWork.getNetVolue(ServiceCode.UP_DATE_FRIEND_SQ, map, ServiceCode.NETWORK_GET, null);
    }



    //设置宠物
    public void showSetPet(final String petid) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("确定更换宠物吗？");
        tvData.setVisibility(View.GONE);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurentPet(petid);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //删除好友
    public void deletFrriendDialog(final String yhids) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("确定删除好友吗？");
        tvData.setVisibility(View.GONE);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletFriend(yhids);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //添加好友
    public void addFriendDialog(final String yhids) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("添加该用户为好友？");
        tvData.setVisibility(View.GONE);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFriend(yhids);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //失败
    public void showDialogFail() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_two);
        dialog.setCancelable(true);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    //成功
    public void showDialogSucess(String title) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        tvData.setVisibility(View.GONE);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tvCancel.setVisibility(View.GONE);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText(title);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //禁用提示框
    public void showCloseWindow() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        tvData.setVisibility(View.GONE);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tvCancel.setVisibility(View.GONE);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvTitle.setText("您的账号已被禁用，解封入口www.urmer.com");
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ActivityTack.getInstanse().exit();
            }
        });
        dialog.show();
    }




    /**
     * 获取ip地址
     *
     * @return
     */
    public String getHostIP() {
        String hostIp = null;

        //获取wifi服务
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            hostIp = intToIp(ipAddress);
        } else {
            try {
                Enumeration nis = NetworkInterface.getNetworkInterfaces();
                InetAddress ia = null;
                while (nis.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) nis.nextElement();
                    Enumeration<InetAddress> ias = ni.getInetAddresses();
                    while (ias.hasMoreElements()) {
                        ia = ias.nextElement();
                        if (ia instanceof Inet6Address) {
                            continue;// skip ipv6
                        }
                        String ip = ia.getHostAddress();
                        if (!"127.0.0.1".equals(ip)) {
                            hostIp = ia.getHostAddress();
                            break;
                        }
                    }
                }
            } catch (SocketException e) {
                MyLog.showLog("IP", "SocketException");
                e.printStackTrace();
            }
        }
        return hostIp;

    }

    private String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }


    public void getNetIp() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL infoUrl = null;
                InputStream inStream = null;
                String line = "";
                try {
                    infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
                    URLConnection connection = infoUrl.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        inStream = httpConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                        StringBuilder strber = new StringBuilder();
                        while ((line = reader.readLine()) != null)
                            strber.append(line + "\n");
                        inStream.close();
                        // 从反馈的结果中提取出IP地址
                        int start = strber.indexOf("{");
                        int end = strber.indexOf("}");
                        String json = strber.substring(start, end + 1);
                        if (json != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                line = jsonObject.optString("cip");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        MyApplication.getMyApplication().ip = line;
                        SharePrefUtil.saveString(getApplicationContext(), "ip", line);
                        MyLog.showLog("WEI", "ip = " + line);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public Dialog netDialog;

    public void showNetWorkState(int c) {
        MyLog.showLog("EEE", " - " + c);
        netDialog = new Dialog(this, R.style.MyDialog2);
        netDialog.setContentView(R.layout.dialog_network);
        netDialog.setCancelable(false);
        ImageView img = (ImageView) netDialog.findViewById(R.id.img_xh);
        ImageView img2 = (ImageView) netDialog.findViewById(R.id.img_xh2);
        if (c == 2) {
            img.setImageResource(R.mipmap.duanwang1);
            img2.setImageResource(R.mipmap.duanwang2);
        } else {
            img.setImageResource(R.mipmap.xinhaocha1);
            img2.setImageResource(R.mipmap.xinhaocha2);
        }

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f, 1f);
        animator1.setDuration(2000);
        animator1.setRepeatCount(50000);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(img2, "alpha", 0f, 1f, 0f);
        animator2.setDuration(2000);
        animator2.setRepeatCount(50000);

        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2);
        netDialog.show();
        set.start();

    }

    public void closeNetWorkState() {
        MyLog.showLog("EEE", " - close");
        if (netDialog != null)
            netDialog.dismiss();
    }

    public void choseDialog(int c) {
        MyLog.showLog("EEE", " - " + c);
        if (MyApplication.getMyApplication().isShowNet) {
            if (netDialog != null && netDialog.isShowing()) {

            } else {
                showNetWorkState(c);
            }
        } else {
            closeNetWorkState();
        }
    }


}
