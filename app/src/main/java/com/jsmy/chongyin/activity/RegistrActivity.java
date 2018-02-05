package com.jsmy.chongyin.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.RegisteryzmBean;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UtilsTools;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistrActivity extends BaseActivity {

    @BindView(R.id.edit_dh)
    EditText editDh;
    @BindView(R.id.edit_msg)
    EditText editMsg;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_next)
    TextView tvNext;

    private String dh = "";
    private String msg = "";
    private String telcode = "123456";
    private boolean isdh;
    private boolean isyzm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_registr;
    }

    @Override
    protected void initView() {
        if (MyApplication.msgTime1 > 0) {
            getMsg(false);
            tvMsg.setText(MyApplication.msgTime1 + "秒后再次获取");
            handler.sendEmptyMessageDelayed(100, 1000);
        } else {
            getMsg(false);
        }
        editDh.addTextChangedListener(watcherDH);
        editMsg.addTextChangedListener(watcherYZM);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        handler.removeMessages(100);
        super.onDestroy();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            switch (DecodeData.getCodeRoMsg(result, DecodeData.CHECK)) {
                case ServiceCode.REGISTER_YZM_NUM:
                    telcode = gson.fromJson(result, RegisteryzmBean.class).getTelcode();
                    break;
                default:
                    break;
            }
        } else {
            switch (DecodeData.getCodeRoMsg(result, DecodeData.CHECK)) {
                case ServiceCode.REGISTER_YZM_NUM:
                    ToastUtil.showLong(this, DecodeData.getCodeRoMsg(result, DecodeData.MSG));
                    break;
                default:

                    break;
            }
        }
    }

    private void getMsg(boolean can) {
        if (can) {
            tvMsg.setBackgroundResource(R.drawable.button_bg);
            tvMsg.setText("获取短信验证码");
            tvMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkDh()) {
                        MyApplication.msgTime1 = 120;
                        getMsg(false);
                        handler.sendEmptyMessageDelayed(100, 0);
                        registeryzm();
                    } else {
                        ToastUtil.showShort(RegistrActivity.this, "请输入11位手机号！");
                        editDh.setHint("输入11位手机号");
                    }
                }
            });
        } else {
            tvMsg.setBackgroundResource(R.drawable.button_bg2);
            tvMsg.setOnClickListener(null);
        }
    }

    private boolean checkDh() {
        dh = editDh.getText().toString().trim();
        return UtilsTools.isMobile(dh);
    }

    private void gotoSetPassword() {
        if (!checkDh()) {
            ToastUtil.showShort(this, "请输入11位手机号");
            editDh.setHint("输入11位手机号");
            return;
        }
        if (telcode.equals(editMsg.getText().toString().trim())) {
            setPassword = "register";
            phone = dh;
            gotoSomeActivity(RegistrActivity.this, AtyTag.ATY_SetPassword, false);
            finish();
        } else {
            ToastUtil.showShort(this, "验证码错误，请重新输入！");
            editMsg.setHint("输入短信验证码");
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (100 == msg.what) {
                if (MyApplication.msgTime1 > 0) {
                    tvMsg.setText(MyApplication.msgTime1 + "秒后再次获取");
                    MyApplication.msgTime1--;
                    handler.sendEmptyMessageDelayed(100, 1000);
                } else {
                    getMsg(true);
                    handler.removeMessages(100);
                }
            }
        }
    };

    private void registeryzm() {
        map.clear();
        map.put("dh", dh);
        NetWork.getNetVolue(ServiceCode.REGISTER_YZM, map, ServiceCode.NETWORK_GET, null);
    }

    private void setNextBtn() {
        if (isdh && isyzm) {
            tvNext.setBackgroundResource(R.drawable.button_bg);
            tvNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoSetPassword();
                }
            });
        } else {
            tvNext.setBackgroundResource(R.drawable.button_bg2);
            tvNext.setOnClickListener(null);
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
            editStart = editDh.getSelectionStart();
            editEnd = editDh.getSelectionEnd();
            if (temp.length() >= 11) {
                if (MyApplication.msgTime1 <= 0) {
                    getMsg(true);
                }
                isdh = true;
            } else {
                isdh = false;
                getMsg(false);
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
            editStart = editMsg.getSelectionStart();
            editEnd = editMsg.getSelectionEnd();
            if (temp.length() >= 6) {
                isyzm = true;
            } else {
                isyzm = false;
            }
            setNextBtn();
        }
    };

}
