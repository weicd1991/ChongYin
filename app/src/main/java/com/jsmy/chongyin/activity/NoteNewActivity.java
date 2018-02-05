package com.jsmy.chongyin.activity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.NoteBean;
import com.jsmy.chongyin.bean.NoteNewBean;
import com.jsmy.chongyin.customclass.PickerScrollView;
import com.jsmy.chongyin.utils.DateUtil;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteNewActivity extends BaseActivity implements NetWork.CallListener {
    @BindView(R.id.edit_note)
    EditText editNote;
    @BindView(R.id.tv_titme)
    TextView tvTitme;
    @BindView(R.id.rela_clock)
    RelativeLayout relaClock;

    private String yuerizhou = "";
    private String shi = "";
    private String fen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_note_new;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void paresData(String result, String code) {

    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("Y".equals(code)) {
            if (type.equals(API.ADD_NOTE)) {
                sendClock(gson.fromJson(result, NoteNewBean.class).getData());
                ToastUtil.showShort(this, "新增记事成功");
                this.finish();
            }
        } else {
            ToastUtil.showShort(this, msg);
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @OnClick({R.id.img_back, R.id.img_clock,R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                backKey();
                break;
            case R.id.img_clock:
                showClockDialog();
                break;
            case R.id.tv_delete:
                tvTitme.setText("");
                relaClock.setVisibility(View.GONE);
                break;
        }
    }

    private String getChioceDate() {
        return yuerizhou + " " + shi + ":" + fen;
    }

    private void showClockDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_clock);
        dialog.setCancelable(false);
        Date now = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日E");
        yuerizhou = format2.format(now);
        SimpleDateFormat format3 = new SimpleDateFormat("HH");
        shi = format3.format(now);
        SimpleDateFormat format4 = new SimpleDateFormat("mm");
        fen = format4.format(now);
        List<String> date = new ArrayList<>();
        date.add(format2.format(now));
        for (int i = 0; i < 364; i++) {
            now = DateUtil.getNextDate(now);
            date.add(format2.format(now));
        }
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                hours.add("0" + i);
            } else {
                hours.add("" + i);
            }
        }
        List<String> minu = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                minu.add("0" + i);
            } else {
                minu.add("" + i);
            }
        }
        final TextView tvDay = (TextView) dialog.findViewById(R.id.tv_day);
        tvDay.setText(getChioceDate());
        PickerScrollView pickerYear = (PickerScrollView) dialog.findViewById(R.id.picker_year);
        pickerYear.setData(date);
        pickerYear.setSelected(yuerizhou);
        pickerYear.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(String str) {
                yuerizhou = str;
                tvDay.setText(getChioceDate());
            }
        });
        PickerScrollView pickerMoon = (PickerScrollView) dialog.findViewById(R.id.picker_moon);
        pickerMoon.setData(hours);
        pickerMoon.setSelected(shi);
        pickerMoon.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(String str) {
                shi = str;
                tvDay.setText(getChioceDate());
            }
        });
        PickerScrollView pickerDay = (PickerScrollView) dialog.findViewById(R.id.picker_day);
        pickerDay.setData(minu);
        pickerDay.setSelected(fen);
        pickerDay.setOnSelectListener(new PickerScrollView.onSelectListener() {
            @Override
            public void onSelect(String str) {
                fen = str;
                tvDay.setText(getChioceDate());
            }
        });
        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yuerizhou = "";
                shi = "";
                fen = "";
                dialog.dismiss();
            }
        });
        TextView tvCheck = (TextView) dialog.findViewById(R.id.tv_check);
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClock();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void sendClock(String noteId) {
        if ("".equals(yuerizhou)) {
            return;
        }
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(yuerizhou.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(yuerizhou.substring(5, 7)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(yuerizhou.substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(shi));
        calendar.set(Calendar.MINUTE, Integer.parseInt(fen));
        calendar.set(Calendar.SECOND, 0);
        Intent intent = new Intent(NoteEditActivity.INTENT_ALARM_LOG);
        intent.putExtra("noteId", noteId);
        intent.putExtra("nr", editNote.getText().toString().trim());
        PendingIntent pi = PendingIntent.getBroadcast(this, Integer.parseInt(noteId), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }
        MyLog.showLog("NNN", "闹钟提醒 - " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(calendar.getTimeInMillis()))));
    }


    private boolean isClock() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(yuerizhou.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(yuerizhou.substring(5, 7)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(yuerizhou.substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(shi));
        calendar.set(Calendar.MINUTE, Integer.parseInt(fen));
        calendar.set(Calendar.SECOND, 0);
        MyLog.showLog("NetWork", "-" + calendar.getTimeInMillis() + " ? " + "-" + Calendar.getInstance().getTimeInMillis());
        if (calendar.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
            return true;
        } else {
            ToastUtil.showShort(this, "提醒时间不能小于当前时间");
            return false;
        }
    }

    private void showClock() {
        if (isClock()) {
            tvTitme.setText(getChioceDate());
            relaClock.setVisibility(View.VISIBLE);
        } else {
            tvTitme.setText("");
            relaClock.setVisibility(View.GONE);
        }
    }

    private void backKey() {
        String nr = editNote.getText().toString().trim();
        String txsj = tvTitme.getText().toString().trim().replace("提醒", "");
        if (!"".equals(nr)) {
            MyLog.showLog("NOTE"," - " + nr.length());
            NetWork.addNote(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                    nr,
                    txsj,
                    this);
        } else {
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        backKey();
    }

}
