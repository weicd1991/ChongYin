package com.jsmy.chongyin.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.TencentQQ.TencentLogin;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.LoginBean;
import com.jsmy.chongyin.bean.RegisterBean;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.listener.IPermission;
import com.jsmy.chongyin.service.LocationBaiDuService;
import com.jsmy.chongyin.service.LocationService;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.CheckNetWork;
import com.jsmy.chongyin.utils.MD5;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UtilsTools;
import com.jsmy.chongyin.view.CircleImageView;
import com.jsmy.chongyin.wxapi.WXEntryActivity;
import com.jsmy.chongyin.wxapi.WeixinLogin;
import com.jsmy.chongyin.xinlangweibo.WeiboLogin;
import com.tencent.tauth.Tencent;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.img_weiixn)
    CircleImageView imgWeiixn;
    @BindView(R.id.img_qq)
    CircleImageView imgQq;
    @BindView(R.id.img_xinlang)
    CircleImageView imgXinlang;
    @BindView(R.id.tv_contanst)
    TextView tvContanst;
    @BindView(R.id.edit_mm)
    EditText editMm;
    @BindView(R.id.edit_dh)
    EditText editDh;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_icon)
    TextView tvIcon;
    @BindView(R.id.linear_bottom)
    LinearLayout linearBottom;
    @BindView(R.id.tv_contanst2)
    TextView tvContanst2;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    //新浪微博
    private WeiboLogin weiboLogin;
    //QQ登录
    private TencentLogin tencentLogin;

    private String yhid;
    private String openid;
    private String dh;
    private String mm;
    private String isWX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        MyApplication.getMyApplication().userInfo = null;
        weiboLogin = WeiboLogin.getInstance();
        tencentLogin = TencentLogin.getInstance();
        getBuildVERSION();
        editMm.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        isWX = getIntent().getStringExtra("iswx");
//        if (!"".equals(isWX)){
//            yhid = getIntent().getStringExtra("yhid");
//            openid = getIntent().getStringExtra("openid");
//            loginData();
//        }
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            switch (DecodeData.getCodeRoMsg(result, DecodeData.CHECK)) {
                case ServiceCode.GET_REGISTER_NUM:
                    MyLog.showLog("FFF", result);
                    RegisterBean.DataBean bean = gson.fromJson(result, RegisterBean.class).getData();
                    yhid = "" + bean.getYhid();
                    SharePrefUtil.saveString(this, "yhid", yhid);
                    openid = SharePrefUtil.getString(this, "openid", "");
                    loginData();
                    break;
                case ServiceCode.GET_LOGIN_NUM:
                    MyLog.showLog("FFF", result);
                    SharePrefUtil.saveString(this, "yhid", yhid);
                    SharePrefUtil.saveString(this, "openid", openid);
                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
                        showCloseWindow();
                    } else {
                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
                        finish();
                    }
                    break;
                case ServiceCode.UP_DATE_WZ_NUM:

                    break;
                case ServiceCode.GET_DH_LOGIN_NUM:
                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
                    SharePrefUtil.saveString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + "");
                    SharePrefUtil.saveString(this, "dh", MyApplication.getMyApplication().userInfo.getDh());
                    SharePrefUtil.saveString(this, "mm", MyApplication.getMyApplication().userInfo.getMm());
                    SharePrefUtil.saveString(LoginActivity.this, "dl", "DH");
                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
                        showCloseWindow();
                    } else {
                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
                        finish();
                    }
                    break;
                case ServiceCode.UPDATE_MM_NUM:
                    if ("S".equals(code)) {
                        //修改密码获得元宝奖励
                        showDialogMissionComplePhone("设置登录密码");
                    } else if ("Y".equals(code)) {
                        //修改密码无奖励
                        ToastUtil.showShort(this, "密码修改成功");
                    } else {
                    }
                    break;
                case ServiceCode.UPDATE_DHMM_NUM:

                    if ("S".equals(code)) {
                        //修改密码获得元宝奖励
                        showDialogMissionComplePhone("设置登录密码");
                    } else if ("Y".equals(code)) {
                        //修改密码无奖励
                        MyLog.showLog("WWW", "密码修改成功");
                        ToastUtil.showShort(this, "密码修改成功");
                    } else {
                    }
                    break;
                default:

                    break;
            }
        } else if (Constants.LOCATION.equals(code)) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else if ("password1".equals(code)) {
            showDialogMissionComplePhone("设置登录密码");
        } else if ("closelogin".equals(code)) {
            finish();
        } else {
            switch (DecodeData.getCodeRoMsg(result, DecodeData.CHECK)) {
                case ServiceCode.GET_LOGIN_NUM:
                    ToastUtil.showShort(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
//                    MyLog.showLog("FFF", result);
//                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
//                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
//                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
//                        showCloseWindow();
//                    } else {
//                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
//                        finish();
//                    }
                    break;
                case ServiceCode.GET_DH_LOGIN_NUM:
                    ToastUtil.showShort(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
//                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
//                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
//                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
//                        showCloseWindow();
//                    } else {
//                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
//                        finish();
//                    }
                    break;
            }
        }
    }

    //获取本地地数据自动登录
    private void getLocationDate() {
        yhid = SharePrefUtil.getString(this, "yhid", "");
        openid = SharePrefUtil.getString(this, "openid", "");
        loginData();
    }

    //访问登录接口
    private void loginData() {
        if (!"".equals(yhid) && !"".equals(openid)) {
            map.put("yhid", yhid);
            map.put("openid", openid);
            NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
        }
    }

    @OnClick({R.id.img_weiixn, R.id.img_qq, R.id.img_xinlang, R.id.tv_contanst, R.id.tv_forga, R.id.tv_contanst2,
            R.id.edit_dh, R.id.edit_mm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_weiixn:
                if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
                    ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
                    return;
                }
                if (!canClick){
                    MyLog.showLog("MMM","img_weiixn" + "false");
                    return;
                }
                MyApplication.Tag_Now = Constants.TAG_WEIXIN;
                MyApplication.loginToWeiXin();
                canClick = false;
                handler.sendEmptyMessageDelayed(0,1000);
                MyLog.showLog("MMM","img_weiixn" + "true");
                break;
            case R.id.img_qq:
                if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
                    ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
                    return;
                }
                if (!canClick){
                    MyLog.showLog("MMM","img_qq" + "false");
                    return;
                }
                tencentLogin.tencentLogout();
                MyApplication.Tag_Now = Constants.TAG_TENCENT;
                tencentLogin.Tencentlogin(this);
                canClick = false;
                handler.sendEmptyMessageDelayed(0,1000);
                MyLog.showLog("MMM","img_qq" + "true");
                break;
            case R.id.img_xinlang:
                if (CheckNetWork.getNetWorkType(MyApplication.getMyApplication()) == CheckNetWork.NETWORKTYPE_INVALID) {
                    ToastUtil.showShort(MyApplication.getMyApplication(), "网络链接异常，请检查网络状态!");
                    return;
                }
                if (!canClick){
                    MyLog.showLog("MMM","img_xinlang" + "false");
                    return;
                }
                weiboLogin.logout(this);
                MyApplication.Tag_Now = Constants.TAG_WEIBO;
                weiboLogin.Weibologin(this);
                canClick = false;
                handler.sendEmptyMessageDelayed(0,1000);
                MyLog.showLog("MMM","img_xinlang" + "true");
                break;
            case R.id.tv_contanst:
                gotoSomeActivity(this, AtyTag.ATY_Registr, false);
                break;
            case R.id.tv_forga:
                Intent intent = new Intent(this, ForgetPasswordActivity.class);
                intent.putExtra("title", "找回密码");
                startActivity(intent);
                break;
            case R.id.tv_contanst2:
                gotoSomeActivity(this, AtyTag.ATY_Registr, false);
                break;
            case R.id.edit_mm:
                if (linearBottom.getVisibility() == View.VISIBLE) {
                    changeLayout(true);
                }
                break;
            case R.id.edit_dh:
                if (linearBottom.getVisibility() == View.VISIBLE) {
                    changeLayout(true);
                }
                break;
        }
    }

    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     *
     * @see {@link Activity#onActivityResult}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 新浪 SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults

        switch (MyApplication.Tag_Now) {
            case Constants.TAG_WEIBO:
                if (weiboLogin.mSsoHandler != null) {
                    weiboLogin.mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
                }
                break;
            case Constants.TAG_TENCENT:
                Tencent.onActivityResultData(requestCode, resultCode, data, tencentLogin.mListener);
                break;
            case Constants.TAG_WEIXIN:

                break;
            default:
                break;
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_aplha_in, R.anim.activity_aplha_out);
    }

    //检查权限
    public void getBuildVERSION() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                showPermissionDialog();
            } else {
                getLocation();
            }
        } else {
            getLocation();
        }
    }

    //申请权限
    private void getPermission(final Activity activity) {
        requestRunTimePermission(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA}, new IPermission() {

            //用户同意时的回调
            @Override
            public void onGranted() {
                getLocation();
            }

            //用户拒绝时的回调，并打印出具体拒绝了什么权限
            @Override
            public void onDenied(List<String> deniedPermissions) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    for (String deniedPermission : deniedPermissions) {
                        boolean b = shouldShowRequestPermissionRationale(deniedPermission);
                        if (!b) {
                            showPermissionDialog();
                        }
                    }
                }
            }
        });
    }

    //提示用户开启权限
    private void showPermissionDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setText("开启");
        tvTitle.setText("宠物需要访问手机状态，地理位置，相机和读写SD卡的权限！");
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
                getPermission(LoginActivity.this);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //开启service获取位置
    private void getLocation() {
//        Intent intent = new Intent(this, LocationService.class);
//        intent.setClass(this, LocationService.class);
//        startService(intent);
        Intent intent = new Intent(this, LocationBaiDuService.class);
        intent.setClass(this, LocationBaiDuService.class);
        startService(intent);
    }

    private void login() {
        dh = editDh.getText().toString().trim();
        if (!UtilsTools.isMobile(dh)) {
            ToastUtil.showShort(this, "请输入11位手机号！");
            return;
        }
        mm = editMm.getText().toString().trim();
        if (!UtilsTools.isPassword(mm)) {
            ToastUtil.showShort(this, "请输入密码！");
            return;
        }
        map.clear();
        map.put("dh", dh);
        map.put("mm", MD5.md5(mm));
        NetWork.getNetVolue(ServiceCode.GET_DH_LOGIN, map, ServiceCode.NETWORK_GET, null);
    }

    private void changeLayout(boolean isChange) {
        if (isChange) {
            imgIcon.setVisibility(View.VISIBLE);
            tvIcon.setVisibility(View.GONE);
            linearBottom.setVisibility(View.GONE);
            tvContanst2.setVisibility(View.VISIBLE);
            activityLogin.setBackgroundColor(Color.parseColor("#F6F6F6"));
            tvLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        } else {
            imgIcon.setVisibility(View.GONE);
            tvIcon.setVisibility(View.VISIBLE);
            linearBottom.setVisibility(View.VISIBLE);
            tvContanst2.setVisibility(View.GONE);
            activityLogin.setBackgroundResource(R.mipmap.denglu);
            tvLogin.setOnClickListener(null);
        }

    }

    @Override
    public void onBackPressed() {
        if (linearBottom.getVisibility() == View.GONE) {
            changeLayout(false);
            return;
        }
        super.onBackPressed();
    }

    private String msg = "";

    private void showDialogMissionComplePhone(String msg) {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_missioncomple);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        tvTitle.setText(msg + "任务已完成");
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private boolean canClick = true;
    private int time = 5;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                if (time > 0){
                    time--;
                    handler.sendEmptyMessageDelayed(0,1000);
                }else {
                    canClick = true;
                    time = 5;
                    handler.removeMessages(0);
                }
            }
        }
    };

}
