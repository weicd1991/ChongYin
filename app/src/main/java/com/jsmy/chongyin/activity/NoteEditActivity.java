package com.jsmy.chongyin.activity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.bean.NoteBean;
import com.jsmy.chongyin.customclass.PickerScrollView;
import com.jsmy.chongyin.utils.DateUtil;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.ToastUtil;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteEditActivity extends BaseActivity implements NetWork.CallListener {
    public static final String INTENT_ALARM_LOG = "intent_alarm_log";

    @BindView(R.id.edit_note)
    EditText editNote;
    @BindView(R.id.img_clock_b)
    ImageView imgClockB;
    @BindView(R.id.tv_titme)
    TextView tvTitme;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.rela_clock)
    RelativeLayout relaClock;

    private String noteId = "";
    private String yuerizhou = "";
    private String shi = "";
    private String fen = "";
    private NoteBean.DataBean bean;
    private boolean isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_note_edit;
    }

    @Override
    protected void initView() {
        noteId = getIntent().getStringExtra("noteId");
    }

    @Override
    protected void initData() {
        NetWork.getnoteInfo(noteId, this);
    }

    @Override
    protected void paresData(String result, String code) {

    }

    @OnClick({R.id.img_back, R.id.img_delete, R.id.img_clock, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                isBack = true;
                saveNoteInfo();
                break;
            case R.id.img_delete:
                showDeletWindow();
                break;
            case R.id.img_clock:
                showClockDialog();
                break;
            case R.id.tv_delete:
                if (bean != null)
                    cancelClock(bean.getTxsj());
                NetWork.updateClockDel(noteId, this);
                break;
        }
    }

    public void showDeletWindow() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_num_one);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        tvTitle.setText("确定删除吗？");
        TextView tvData = (TextView) dialog.findViewById(R.id.tv_data);
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
                NetWork.updateNoteDel(noteId, NoteEditActivity.this);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String getChioceDate() {
        return yuerizhou + " " + shi + ":" + fen;
    }

    private void showClockDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_clock);
        dialog.setCancelable(false);
        Date now;
        if (null != bean.getTxsj() && !"".equals(bean.getTxsj())) {
            now = new Date(getTxsjMillion(bean.getTxsj()));
        } else {
            now = new Date();
        }

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

    private void setClock(String txsj) {
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(txsj.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(txsj.substring(5, 7)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txsj.substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txsj.substring(14, 16)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(txsj.substring(17, 19)));
        calendar.set(Calendar.SECOND, 0);
        Intent intent = new Intent(INTENT_ALARM_LOG);
        intent.putExtra("noteId", noteId);
        intent.putExtra("nr", editNote.getText().toString().trim());
        PendingIntent pi = PendingIntent.getBroadcast(this, Integer.parseInt(noteId), intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }
        MyLog.showLog("NNN", "闹钟提醒 - " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(calendar.getTimeInMillis()))));
    }

    private void cancelClock(String txsj) {
        if (null == txsj || "".equals(txsj))
            return;
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(txsj.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(txsj.substring(5, 7)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txsj.substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txsj.substring(14, 16)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(txsj.substring(17, 19)));
        calendar.set(Calendar.SECOND, 0);
        Intent intent = new Intent(INTENT_ALARM_LOG);
        intent.putExtra("noteId", noteId);
        intent.putExtra("nr", editNote.getText().toString().trim());
        PendingIntent pi = PendingIntent.getBroadcast(this, Integer.parseInt(noteId), intent, 0);
        am.cancel(pi);
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
        if (calendar.getTimeInMillis() >= Calendar.getInstance().getTimeInMillis()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isClocked(String txsj) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(txsj.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(txsj.substring(5, 7)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txsj.substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txsj.substring(14, 16)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(txsj.substring(17, 19)));
        calendar.set(Calendar.SECOND, 0);
        MyLog.showLog("NetWork", "-" + calendar.getTimeInMillis() + " ? " + "-" + Calendar.getInstance().getTimeInMillis());
        if (calendar.getTimeInMillis() >= Calendar.getInstance().getTimeInMillis()) {
            return true;
        } else {
            return false;
        }
    }

    private long getTxsjMillion(String txsj) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(txsj.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(txsj.substring(5, 7)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txsj.substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txsj.substring(14, 16)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(txsj.substring(17, 19)));
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    private void showClock() {
        if (isClock()) {
            tvTitme.setText(getChioceDate());
            saveNoteInfo();
        } else {
            ToastUtil.showShort(this, "提醒时间不能小于当前时间");
        }
    }

    private void setNoteInfo() {
        editNote.setText(bean.getNr());
        if (null != bean.getTxsj() && !"".equals(bean.getTxsj())) {
            tvTitme.setText(bean.getTxsj() + "提醒");
            relaClock.setVisibility(View.VISIBLE);
            if (isClocked(bean.getTxsj())) {
                relaClock.setBackgroundColor(Color.parseColor("#FEF5BC"));
                imgClockB.setImageResource(R.mipmap.clock_y);
                tvTitme.setTextColor(Color.parseColor("#DCC22D"));
                tvDelete.setTextColor(Color.parseColor("#DCC22D"));
                setClock(bean.getTxsj());
            } else {
                relaClock.setBackgroundColor(Color.parseColor("#D9D9D9"));
                imgClockB.setImageResource(R.mipmap.clock_b);
                tvTitme.setTextColor(Color.parseColor("#FFFFFF"));
                tvDelete.setTextColor(Color.parseColor("#FFFFFF"));
            }
        } else {
            tvTitme.setText("");
            relaClock.setVisibility(View.GONE);
        }
    }

    private void saveNoteInfo() {
        String txsj = tvTitme.getText().toString().trim().replace("提醒", "");
        String nr = editNote.getText().toString().trim();
        MyLog.showLog("NOTE", " - " + nr.length());
        NetWork.updateNoteClock(noteId, txsj, nr, this);
    }

    @Override
    public void onBackPressed() {
        isBack = true;
        saveNoteInfo();
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("Y".equals(code)) {
            switch (type) {
                case API.UPDATE_NOTE_CLOCK:
                    NetWork.getnoteInfo(noteId, this);
                    break;
                case API.GET_NOTE_INFO:
                    bean = gson.fromJson(result, NoteBean.class).getData();
                    setNoteInfo();
                    if (isBack) {
                        isBack = false;
                        this.finish();
                    }
                    break;
                case API.UPDATE_CLOCK_DEL:
                    NetWork.getnoteInfo(noteId, this);
                    break;
                case API.UPDATE_NOTE_DEL:
                    if (bean != null)
                        cancelClock(bean.getTxsj());
                    this.finish();
                    break;
            }
        } else {
            if (isBack) {
                this.finish();
            } else {
//                ToastUtil.showShort(this, msg);
            }
        }
    }

    @Override
    public void onFailure(String type, String arg1) {
        if (isBack) {
            this.finish();
        }
    }
}
