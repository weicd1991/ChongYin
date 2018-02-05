package com.jsmy.chongyin.activity;

import android.app.Dialog;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.LoginBean;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MD5;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UtilsTools;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class SetPasswordActivity extends BaseActivity {

    @BindView(R.id.edit_mm)
    EditText editMm;
    @BindView(R.id.edit_mme)
    EditText editMme;
    @BindView(R.id.tv_check)
    TextView tvCheck;
    private String mm = "";

    private boolean isdh;
    private boolean isyzm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void initView() {
        setPassword = getIntent().getStringExtra("setpassword");
        phone = getIntent().getStringExtra("phone");
        editMm.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editMm.addTextChangedListener(watcherDH);
        editMme.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editMme.addTextChangedListener(watcherYZM);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void paresData(String result, String code) {

        switch (DecodeData.getCodeRoMsg(result, DecodeData.CHECK)) {
            case ServiceCode.UPDATE_DHZC_NUM:
                if ("Y".equals(code)) {
                    ToastUtil.showShort(getApplicationContext(), "注册成功！");
                    getdhlogin();
                } else {
                    ToastUtil.showLong(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
                    tvCheck.setBackgroundResource(R.drawable.button_bg);
                    tvCheck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkPassword();
                        }
                    });
                }
                break;
            case ServiceCode.UPDATE_MM_NUM:
                if ("S".equals(code)) {
                    //修改密码获得元宝奖励
                    EventBus.getDefault().post(new DowloadEvent("password1", "password1"));
                    finish();
                } else if ("Y".equals(code)) {
                    //修改密码无奖励
                    ToastUtil.showShort(this, "密码修改成功");
                    EventBus.getDefault().post(new DowloadEvent("password", "password"));
                    finish();
                } else {
                    ToastUtil.showLong(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
                }
                break;
            case ServiceCode.UPDATE_DHMM_NUM:
                if ("S".equals(code)) {
                    //修改密码获得元宝奖励
                    EventBus.getDefault().post(new DowloadEvent("password1", "password1"));
                    finish();
                } else if ("Y".equals(code)) {
                    //修改密码无奖励
                    ToastUtil.showShort(this, "密码修改成功");
                    EventBus.getDefault().post(new DowloadEvent("password", "password"));
                    finish();
                } else {
                    ToastUtil.showLong(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
                }
                break;
            case ServiceCode.GET_DH_LOGIN_NUM:
                MyLog.showLog("FFF", result);
                if ("Y".equals(code)) {
                    MyApplication.getMyApplication().userInfo = gson.fromJson(result, LoginBean.class).getData();
                    setAliasByYhid(MyApplication.getMyApplication().userInfo.getYhid() + "");
                    SharePrefUtil.saveString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + "");
                    SharePrefUtil.saveString(this, "dh", MyApplication.getMyApplication().userInfo.getDh());
                    SharePrefUtil.saveString(this, "mm", MyApplication.getMyApplication().userInfo.getMm());
                    if ("N".equals(MyApplication.getMyApplication().userInfo.getIszt())) {
                        showCloseWindow();
                    } else {
                        gotoSomeActivity(this, AtyTag.ATY_Main, false);
                        EventBus.getDefault().post(new DowloadEvent("closelogin", "closelogin"));
                        finish();
                    }
                } else {
                    ToastUtil.showLong(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
                }
                break;
            default:
                break;
        }

    }

    private void checkPassword() {
        mm = editMm.getText().toString().trim();
        if (mm == null || "".equals(mm)) {
            ToastUtil.showShort(this, "请输入密码！");
            return;
        }
        if (!UtilsTools.isPassword(mm)) {
            ToastUtil.showShort(this, "密码由5-18位字母、数字或下划线组成");
            editMm.setHint("输入新密码");
            return;
        }
        String mme = editMme.getText().toString().trim();
        if (mme == null || "".equals(mme)) {
            ToastUtil.showShort(this, "请再次输入密码！");
            return;
        }
        if (!UtilsTools.isPassword(mme)) {
            ToastUtil.showShort(this, "密码由5-18位字母、数字或下划线组成");
            editMme.setHint("再次输入新密码");
            return;
        }
        if (!mm.equals(mme)) {
            ToastUtil.showShort(this, "密码不一致，请重新输入");
            editMm.setHint("输入新密码");
            editMme.setHint("再次输入新密码");
            return;
        }
        switch (setPassword) {
            case "register":
                updatedhzc();
                tvCheck.setBackgroundResource(R.drawable.button_bg2);
                tvCheck.setOnClickListener(null);
                break;
            case "update":
                updatemm();
                break;
            default:

                break;
        }
    }

    @OnClick({R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void updatedhzc() {
        map.clear();
        map.put("dh", phone);
        map.put("mm", MD5.md5(mm));
        NetWork.getNetVolue(ServiceCode.UPDATE_DHZC, map, ServiceCode.NETWORK_GET, null);
    }

    private void updatemm() {
        map.clear();
        if (MyApplication.getMyApplication().userInfo != null) {
            map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
            map.put("mm", MD5.md5(mm));
            NetWork.getNetVolue(ServiceCode.UPDATE_MM, map, ServiceCode.NETWORK_GET, null);
        }else {
            map.put("dh", phone);
            map.put("mm", MD5.md5(mm));
            NetWork.getNetVolue(ServiceCode.UPDATE_DHMM, map, ServiceCode.NETWORK_GET, null);
        }

    }

    private void getdhlogin() {
        map.clear();
        map.put("dh", phone);
        map.put("mm", MD5.md5(mm));
        NetWork.getNetVolue(ServiceCode.GET_DH_LOGIN, map, ServiceCode.NETWORK_GET, null);
    }

    private void setNextBtn() {
        if (isdh && isyzm) {
            tvCheck.setBackgroundResource(R.drawable.button_bg);
            tvCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkPassword();
                }
            });
        } else {
            tvCheck.setBackgroundResource(R.drawable.button_bg2);
            tvCheck.setOnClickListener(null);
        }
    }

    TextWatcher watcherDH = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = editMm.getSelectionStart();
            editEnd = editMm.getSelectionEnd();
            if (temp.length() >= 5 && temp.length() <= 18) {
                isdh = true;
            } else if (temp.length() > 18) {
                isdh = false;
            } else {
                isdh = false;
            }
            setNextBtn();
        }
    };

    TextWatcher watcherYZM = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = editMme.getSelectionStart();
            editEnd = editMme.getSelectionEnd();
            if (temp.length() >= 5 && temp.length() <= 18) {
                isyzm = true;
            } else if (temp.length() > 18) {
                isyzm = false;
            } else {
                isyzm = false;
            }
            setNextBtn();
        }
    };

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

}
