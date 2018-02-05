package com.jsmy.chongyin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonChangeGenderActivity extends BaseActivity {

    @BindView(R.id.check_man)
    CheckBox checkMan;
    @BindView(R.id.check_woman)
    CheckBox checkWoman;
    private String xb = "男";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_person_change_gender;
    }

    @Override
    protected void initView() {
        xb = getIntent().getStringExtra("personDate");
        if ("男".equals(xb)){
            setCheckBox(checkMan);
        }else {
            setCheckBox(checkWoman);
        }
        isFrendData = "N";
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }
    @Override
    protected void paresData(String result, String code) {
        String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
        if ("Y".equals(code)) {
            switch (check) {
                case ServiceCode.UP_DATE_XB_NUM:
//                    Toast.makeText(getApplicationContext(), "性别修改成功！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(getApplicationContext(),"性别修改成功！");
                    EventBus.getDefault().post(new DowloadEvent("change", "change"));
                    finish();
//                    gotoSomeActivity(this, AtyTag.ATY_PersonData, true);
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
            switch (check) {
                case ServiceCode.UP_DATE_XB_NUM:
//                    Toast.makeText(getApplicationContext(), "性别修改失败！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(getApplicationContext(),"性别修改失败！");
                    break;
            }

        }

    }

    //修改性别
    private void setXB() {
        map.clear();
        map.put("isAND", "Y");
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("xb", xb);
        NetWork.getNetVolue(ServiceCode.UP_DATE_XB, map, ServiceCode.NETWORK_GET, null);
    }

    @OnClick({R.id.check_man, R.id.check_woman, R.id.img_close, R.id.rela_man, R.id.rela_woman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_man:
                setCheckBox(checkMan);
                xb = "男";
                break;
            case R.id.check_woman:
                setCheckBox(checkWoman);
                xb = "女";
                break;
            case R.id.img_close:
                setXB();
                break;
            case R.id.rela_man:
                setCheckBox(checkMan);
                xb = "男";
                break;
            case R.id.rela_woman:
                setCheckBox(checkWoman);
                xb = "女";
                break;
        }
    }

    private void setCheckBox(CheckBox checkBox) {
        checkMan.setChecked(false);
        checkWoman.setChecked(false);
        checkBox.setChecked(true);
    }
}
