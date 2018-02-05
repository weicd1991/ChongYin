package com.jsmy.chongyin.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.NoteAdapter;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.NoteListBean;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteActivity extends BaseActivity implements NetWork.CallListener {

    @BindView(R.id.my_recyclerview)
    RecyclerView myRecyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private NoteAdapter adapter;
    private List<NoteListBean.DataBean> list;
    private int pageindex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_note;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new NoteAdapter(this, list);
        myRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerview.setItemAnimator(null);
        myRecyclerview.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                pageindex = 1;
                NetWork.getNoteList(SharePrefUtil.getString(NoteActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", NoteActivity.this);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
                pageindex++;
                NetWork.getNoteList(SharePrefUtil.getString(NoteActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", NoteActivity.this);
            }
        });
    }

    @Override
    protected void initData() {
        NetWork.getNoteList(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", this);
    }

    @Override
    protected void paresData(String result, String code) {

    }

    @OnClick({R.id.img_back, R.id.img_xiala})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_xiala:
                Intent intent = new Intent(this, NoteNewActivity.class);
                startActivityForResult(intent, 101);
                break;
        }
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("Y".equals(code)) {
            switch (type) {
                case API.GET_NOTE_LIST:
                    MyLog.showLog("NetWork", pageindex + " - " + result);
                    if (1 == pageindex) {
                        list.clear();
                        list.addAll(gson.fromJson(result, NoteListBean.class).getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        list.addAll(gson.fromJson(result, NoteListBean.class).getData());
                        adapter.notifyItemRangeChanged(list.size() - 1, gson.fromJson(result, NoteListBean.class).getData().size());
                    }

                    break;
            }
        } else {
            if (pageindex == 1) {
                list.clear();
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (101 == requestCode) {
            pageindex = 1;
            NetWork.getNoteList(SharePrefUtil.getString(NoteActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", NoteActivity.this);
        }
    }

    public boolean isClocked(String txsj) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(txsj.substring(0, 4)));
            calendar.set(Calendar.MONTH, Integer.parseInt(txsj.substring(5, 7)) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txsj.substring(8, 10)));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txsj.substring(14, 16)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(txsj.substring(17, 19)));
            calendar.set(Calendar.SECOND, 0);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MyLog.showLog("NetWork", "-" + format.format(new Date(calendar.getTimeInMillis())) + " ? " + "-" + format.format(new Date(Calendar.getInstance().getTimeInMillis())));
            if (calendar.getTimeInMillis() >= Calendar.getInstance().getTimeInMillis()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public void setClock(String txsj, String noteId, String nr) {
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(txsj.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(txsj.substring(5, 7)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txsj.substring(8, 10)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txsj.substring(14, 16)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(txsj.substring(17, 19)));
        calendar.set(Calendar.SECOND, 0);
        Intent intent = new Intent(NoteEditActivity.INTENT_ALARM_LOG);
        intent.putExtra("noteId", noteId);
        intent.putExtra("nr", nr);
        PendingIntent pi = PendingIntent.getBroadcast(this, Integer.parseInt(noteId), intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }
        MyLog.showLog("NNN", "闹钟提醒 - " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(calendar.getTimeInMillis()))));
    }

}
