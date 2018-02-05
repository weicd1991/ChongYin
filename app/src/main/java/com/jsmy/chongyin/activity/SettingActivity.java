package com.jsmy.chongyin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.TencentQQ.TencentLogin;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.BangInfoBean;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.LoginBean;
import com.jsmy.chongyin.bean.PassWordTimeBean;
import com.jsmy.chongyin.contents.Constants;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.service.FloatWindowService;
import com.jsmy.chongyin.service.MusicService;
import com.jsmy.chongyin.utils.ActivityTack;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.wxapi.WXEntryActivity;
import com.jsmy.chongyin.wxapi.WeixinLogin;
import com.jsmy.chongyin.xinlangweibo.WeiboLogin;
import com.tencent.tauth.Tencent;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.switch_muisc)
    Switch switchMuisc;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;
    @BindView(R.id.rela_weixin_gold)
    RelativeLayout relaWeixinGold;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.rela_qq_gold)
    RelativeLayout relaQqGold;
    @BindView(R.id.tv_xinlang)
    TextView tvXinlang;
    @BindView(R.id.rela_xinlang_gold)
    RelativeLayout relaXinlangGold;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.rela_weixin)
    RelativeLayout relaWeixin;
    @BindView(R.id.rela_qq)
    RelativeLayout relaQq;
    @BindView(R.id.rela_xinlang)
    RelativeLayout relaXinlang;
    @BindView(R.id.tv_phone2)
    TextView tvPhone2;
    @BindView(R.id.img_phone2)
    ImageView imgPhone2;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_word2)
    TextView tvWord2;
    @BindView(R.id.img_word2)
    ImageView imgWord2;
    @BindView(R.id.tv_phone1)
    TextView tvPhone1;
    @BindView(R.id.tv_word1)
    TextView tvWord1;
    @BindView(R.id.tv_word)
    TextView tvWord;

    private BangInfoBean.DataBean info;
    private PassWordTimeBean.DataBean bean;

    //新浪微博
    private WeiboLogin weiboLogin;
    //QQ登录
    private TencentLogin tencentLogin;

    private String title = "绑定手机号";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        weiboLogin = WeiboLogin.getInstance();
        tencentLogin = TencentLogin.getInstance();
        if ("Y".equals(SharePrefUtil.getString(this, "play", "N"))) {
            switchMuisc.setChecked(true);
        } else {
            switchMuisc.setChecked(false);
        }

        switchMuisc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent intent = new Intent(SettingActivity.this, MusicService.class);
                if (b) {
                    SharePrefUtil.saveString(SettingActivity.this, "play", "Y");
                    intent.putExtra("play", "Y");
                    startService(intent);
                } else {
                    SharePrefUtil.saveString(SettingActivity.this, "play", "N");
                    intent.putExtra("play", "N");
                    stopService(intent);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getBangInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
        getsyts();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.GET_ZH_BD_NUM:
                    info = gson.fromJson(result, BangInfoBean.class).getData();
                    setDate();
                    break;
                case ServiceCode.UP_DATE_ZH_BD_NUM:
                    if ("".equals(MyApplication.getMyApplication().userInfo.getDh())) {
                        showDialogMissionComplePhone(title);
                    } else {
                        ToastUtil.showShort(getApplicationContext(), "手机号码更换成功");
                    }
                    getBangInfo();
                    getJCSJ();
                    getLoginDate();
                    break;
                case ServiceCode.GET_LOGIN_NUM:
                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
                    SharePrefUtil.saveString(this, "dh", MyApplication.getMyApplication().userInfo.getDh());
                    SharePrefUtil.saveString(this, "mm", MyApplication.getMyApplication().userInfo.getMm());
                    setDate();
                    break;
                case ServiceCode.GET_DH_LOGIN_NUM:
                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
                    SharePrefUtil.saveString(this, "dh", MyApplication.getMyApplication().userInfo.getDh());
                    SharePrefUtil.saveString(this, "mm", MyApplication.getMyApplication().userInfo.getMm());
                    setDate();
                    break;
                case ServiceCode.GET_SYTS_NUM:
                    bean = gson.fromJson(result, PassWordTimeBean.class).getData();
                    setPassWord();
                    break;
                case ServiceCode.UPDATE_MM_NUM:
                    if ("S".equals(code)) {
                        //修改密码获得元宝奖励
                        showDialogMissionComplePhone("设置登录密码");
                        getLoginDate();
                        getJCSJ();
                        getsyts();
                    } else if ("Y".equals(code)) {
                        ToastUtil.showShort(getApplicationContext(), "密码修改成功");
                        getLoginDate();
                        getJCSJ();
                        getsyts();
                    }
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else if ("updatephone".equals(code)) {
            getLoginDate();
            getJCSJ();
        } else if ("password1".equals(code)) {

        } else if ("password".equals(code)) {

        } else {
            if ("N".equals(code)) {
                if (ServiceCode.UP_DATE_ZH_BD_NUM.equals(DecodeData.getCodeRoMsg(result, DecodeData.CHECK))) {
                    ToastUtil.showShort(this, "账号已被绑定!");
                }
            } else if ("openid".equals(code)) {
                String bd = result.substring(0, result.indexOf("_"));
                String openid = result.substring(result.indexOf("_") + 1);
                if ("QQ".equals(bd)) {
                    title = "绑定腾讯QQ";
                } else if ("WX".equals(bd)) {
                    title = "绑定微信";
                } else {
                    title = "绑定新浪微博";
                }
                bangThreeWay(bd, openid);
            }
        }
    }

    //访问登录接口，更新数据
    private void getLoginDate() {
        String yhid = SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + "");
        String openid = SharePrefUtil.getString(this, "openid", "");
        String dh = SharePrefUtil.getString(this, "dh", MyApplication.getMyApplication().userInfo.getDh() + "");
        String mm = SharePrefUtil.getString(this, "mm", MyApplication.getMyApplication().userInfo.getMm() + "");
        map.clear();
        if (!"".equals(openid)) {
            map.put("yhid", yhid);
            map.put("openid", openid);
            NetWork.getNetVolue(ServiceCode.GET_LOGIN, map, ServiceCode.NETWORK_GET, null);
        } else {
            map.put("dh", dh);
            map.put("mm", mm);
            NetWork.getNetVolue(ServiceCode.GET_DH_LOGIN, map, ServiceCode.NETWORK_GET, null);
        }

    }

    //访问基础数据
    private void getJCSJ() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SY_JCSJ, map, ServiceCode.NETWORK_GET, null);
    }

    //查询绑定信息
    private void getBangInfo() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_ZH_BD, map, ServiceCode.NETWORK_GET, null);
    }

    private void getsyts() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SYTS, map, ServiceCode.NETWORK_GET, null);
    }

    //绑定
    private void bangThreeWay(String bd, String openid) {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("bd", bd);
        map.put("openid", openid);
        NetWork.getNetVolue(ServiceCode.UP_DATE_ZH_BD, map, ServiceCode.NETWORK_GET, null);
    }

    //设置绑定信息
    private void setDate() {

        if (!"".equals(MyApplication.getMyApplication().userInfo.getDh())) {
            String phone=MyApplication.getMyApplication().userInfo.getDh();
            if (!TextUtils.isEmpty(phone)){
                phone=phone.substring(0,3)+"****"+phone.substring(7);
            }
            tvPhone.setText(phone);
            tvPhone.setTextColor(Color.parseColor("#999999"));
            tvPhone1.setText("更换");
            tvPhone2.setVisibility(View.GONE);
            imgPhone2.setVisibility(View.GONE);
        } else {
            tvPhone.setText("绑定手机");
            tvPhone1.setText("绑定");
            tvPhone2.setVisibility(View.VISIBLE);
            imgPhone2.setVisibility(View.VISIBLE);
        }

        if ("Y".equals(info.getIsweinxin())) {
            tvWeixin.setText("已绑定");
            tvWeixin.setTextColor(Color.parseColor("#999999"));
            tvWeixin.setClickable(false);
            relaWeixinGold.setVisibility(View.GONE);
            relaWeixin.setClickable(false);
        } else {
            tvWeixin.setText("绑定微信");
            relaWeixinGold.setVisibility(View.VISIBLE);
        }

        if ("Y".equals(info.getIsqq())) {
            tvQq.setText("已绑定");
            tvQq.setTextColor(Color.parseColor("#999999"));
            tvQq.setClickable(false);
            relaQqGold.setVisibility(View.GONE);
            relaQq.setClickable(false);
        } else {
            tvQq.setText("绑定QQ");
            relaQqGold.setVisibility(View.VISIBLE);
        }

        if ("Y".equals(info.getIsweibo())) {
            tvXinlang.setText("已绑定");
            tvXinlang.setTextColor(Color.parseColor("#999999"));
            tvXinlang.setClickable(false);
            relaXinlangGold.setVisibility(View.GONE);
            relaXinlang.setClickable(false);
        } else {
            tvXinlang.setText("绑定微博");
            relaXinlangGold.setVisibility(View.VISIBLE);
        }

    }

    private void setPassWord() {
        if ("".equals(MyApplication.getMyApplication().userInfo.getMm())) {
            tvWord.setText("设置密码");
        } else {
            tvWord.setText("修改密码");
        }
        if ("0".equals(bean.getDay() + "")) {
            tvWord1.setText("去完成");
            tvWord2.setVisibility(View.VISIBLE);
            imgWord2.setVisibility(View.VISIBLE);
        } else {
            tvWord1.setText("修改");
            tvWord2.setVisibility(View.GONE);
            imgWord2.setVisibility(View.GONE);
        }
    }

    private void bangPhone() {
        Intent intent = new Intent(this, SetPhoneActivity.class);
        startActivity(intent);
    }

    private void bangWeiXin() {
        MyApplication.getMyApplication().isFirst = true;
        MyApplication.Tag_Now = Constants.TAG_WEIXIN;
        MyApplication.loginToWeiXin();
//        startActivity(new Intent(this, WXEntryActivity.class));
//        WeixinLogin.loginToWeiXin(this);
    }

    private void bangQQ() {
        tencentLogin.tencentLogout();
        MyApplication.Tag_Now = Constants.TAG_TENCENT;
        tencentLogin.Tencentlogin(this);
    }

    private void bangWeiBo() {
//        weiboLogin.logout(this);
        MyApplication.Tag_Now = Constants.TAG_WEIBO;
        weiboLogin.Weibologin(this);
    }

    private void bangWord() {
        if ("".equals(MyApplication.getMyApplication().userInfo.getDh())) {
            showDialogNoPhone();
        } else {
            Intent intent = new Intent(this, ForgetPasswordActivity.class);
            intent.putExtra("title", "修改密码");
            startActivity(intent);
        }
    }

    private void outLogin() {
        setLocationEsc(this);
        finish();
        ActivityTack.getInstanse().removeAllActivity();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        SharePrefUtil.clear(this);
        MyApplication.getMyApplication().isFirst = true;

        Intent intent1 = new Intent(this, FloatWindowService.class);
        intent1.putExtra("isShow", "N");
        intent1.putExtra("path", "");
        intent1.putExtra("change", "N");
        startService(intent1);

        TencentLogin.getInstance().tencentLogout();
        WeiboLogin.getInstance().logout(this);
    }

    @OnClick({R.id.tv_phone, R.id.rela_phone_gold, R.id.rela_phone,
            R.id.tv_weixin, R.id.rela_weixin_gold, R.id.rela_weixin,
            R.id.tv_qq, R.id.rela_qq_gold, R.id.rela_qq,
            R.id.tv_xinlang, R.id.rela_xinlang_gold, R.id.rela_xinlang,
            R.id.tv_word, R.id.rela_word_gold, R.id.rela_word,
            R.id.tv_login, R.id.rela_login_gold, R.id.rela_login,
            R.id.img_language, R.id.tv_language, R.id.rela_language,
            R.id.img_close, R.id.img_bangzhu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone:
                bangPhone();
                break;
            case R.id.rela_phone_gold:
                bangPhone();
                break;
            case R.id.rela_phone:
                bangPhone();
                break;
            case R.id.tv_weixin:
                bangWeiXin();
                break;
            case R.id.rela_weixin_gold:
                bangWeiXin();
                break;
            case R.id.rela_weixin:
                bangWeiXin();
                break;
            case R.id.tv_qq:
                bangQQ();
                break;
            case R.id.rela_qq_gold:
                bangQQ();
                break;
            case R.id.rela_qq:
                bangQQ();
                break;
            case R.id.tv_xinlang:
                bangWeiBo();
                break;
            case R.id.rela_xinlang_gold:
                bangWeiBo();
                break;
            case R.id.rela_xinlang:
                bangWeiBo();
                break;
            case R.id.tv_word:
                bangWord();
                break;
            case R.id.rela_word_gold:
                bangWord();
                break;
            case R.id.rela_word:
                bangWord();
                break;
            case R.id.img_bangzhu:
                showDialogMissionCompleTime();
                break;
            case R.id.tv_login:
                showDialogOut();
                break;
            case R.id.rela_login_gold:
                showDialogOut();
                break;
            case R.id.rela_login:
                showDialogOut();
                break;
            case R.id.img_language:
                gotoSomeActivity(this, AtyTag.ATY_AboutUs, false);
                break;
            case R.id.tv_language:
                gotoSomeActivity(this, AtyTag.ATY_AboutUs, false);
                break;
            case R.id.rela_language:
                gotoSomeActivity(this, AtyTag.ATY_AboutUs, false);
                break;
            case R.id.img_close:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    private void showDialogMissionCompleTime() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_password_mission);
        dialog.setCancelable(false);
        TextView time = (TextView) dialog.findViewById(R.id.tv_time);
        time.setText(bean.getDay() + "");
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

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

    private void showDialogNoPhone() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        ;
        tvTitle.setText("请先绑定手机号");
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        ;
        tvData.setVisibility(View.GONE);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        ;
        tvCancel.setVisibility(View.GONE);
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showDialogOut() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        ;
        tvTitle.setText("确定退出吗");
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
        ;
        tvData.setVisibility(View.GONE);
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                outLogin();
            }
        });
        dialog.show();
    }

}
