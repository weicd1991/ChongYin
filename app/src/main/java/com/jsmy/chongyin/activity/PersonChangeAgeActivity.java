package com.jsmy.chongyin.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.customclass.PickerScrollView;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonChangeAgeActivity extends BaseActivity {

    @BindView(R.id.picker_year)
    PickerScrollView pickerYear;
    @BindView(R.id.picker_moon)
    PickerScrollView pickerMoon;
    @BindView(R.id.picker_day)
    PickerScrollView pickerDay;
    private List<String> years;
    private List<String> moons;
    private List<String> days;
    private boolean isLeapyear;
    private String seletYear = "2010年";
    private String seletMoon = "06月";
    private String seletDay = "15日";
    private String age = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_person_change_age;
    }

    @Override
    protected void initView() {
        age = getIntent().getStringExtra("personDate");

        if (!"".equals(age)) {
            seletYear = age.substring(0, 5);
            seletMoon = age.substring(5, 8);
            seletDay = age.substring(8);
            MyLog.showLog("aaa", " - " + seletYear + " - " + seletMoon + " - " + seletDay);
        }
        lisenpickerYear();
        lisenpickerMoon();
        lisenpickerDay(31);
    }

    @Override
    protected void initData() {
        isFrendData = "N";
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
                case ServiceCode.UP_DATE_AGE_NUM:
//                    Toast.makeText(getApplicationContext(), "生日修改成功！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(getApplicationContext(),"生日修改成功!");
                    EventBus.getDefault().post(new DowloadEvent("change", "change"));
                    finish();
//                    gotoSomeActivity(this, AtyTag.ATY_PersonData, true);
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        } else {
            switch (check) {
                case ServiceCode.UP_DATE_AGE_NUM:
//                    Toast.makeText(getApplicationContext(), "生日修改失败！", Toast.LENGTH_SHORT).show();
                    ToastUtil.showShort(getApplicationContext(),"生日修改失败!");
                    break;
            }

        }

    }

    //设置生日
    private void setAge() {
        age = seletYear + seletMoon + seletDay;
        map.clear();
        map.put("isAND", "Y");
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        map.put("sj", age);
        NetWork.getNetVolue(ServiceCode.UP_DATE_AGE, map, ServiceCode.NETWORK_GET, null);
    }

    private void initYear() {
        years = new ArrayList<>();
        for (int i = 1960; i <= 2017; i++) {
            years.add(i + "年");
        }
    }

    private void lisenpickerYear() {
        initYear();
        pickerYear.setData(years);
        pickerYear.setSelected(seletYear);
        pickerYear.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(String str) {
                int year = Integer.parseInt(str.substring(0, str.length() - 1));
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    //是闰年
                    isLeapyear = true;
                } else {
                    //不是闰年
                    isLeapyear = false;
                }
                seletYear = str;
            }
        });
    }

    private void initMoon() {
        moons = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                moons.add("0" + i + "月");
            } else {
                moons.add(i + "月");
            }

        }
    }

    private void lisenpickerMoon() {
        initMoon();
        pickerMoon.setData(moons);
        pickerMoon.setSelected(seletMoon);
        pickerMoon.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(String str) {
                switch (str) {
                    case "01月":
                        lisenpickerDay(31);
                        break;
                    case "02月":
                        if (isLeapyear) {
                            lisenpickerDay(29);
                        } else {
                            lisenpickerDay(28);
                        }
                        break;
                    case "03月":
                        lisenpickerDay(31);
                        break;
                    case "04月":
                        lisenpickerDay(30);
                        break;
                    case "05月":
                        lisenpickerDay(31);
                        break;
                    case "06月":
                        lisenpickerDay(30);
                        break;
                    case "07月":
                        lisenpickerDay(31);
                        break;
                    case "08月":
                        lisenpickerDay(31);
                        break;
                    case "09月":
                        lisenpickerDay(30);
                        break;
                    case "10月":
                        lisenpickerDay(31);
                        break;
                    case "11月":
                        lisenpickerDay(30);
                        break;
                    case "12月":
                        lisenpickerDay(31);
                        break;
                }
                seletMoon = str;
            }
        });
    }

    private void initDay(int day) {
        days = new ArrayList<>();
        for (int i = 1; i <= day; i++) {
            if (i < 10) {
                days.add("0" + i + "日");
            } else {
                days.add(i + "日");
            }

        }
    }

    private void lisenpickerDay(int day) {
        initDay(day);
        pickerDay.setData(days);
        pickerDay.setSelected(seletDay);
        pickerDay.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(String str) {
                seletDay = str;
            }
        });
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        setAge();
    }

}
