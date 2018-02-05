package com.jsmy.chongyin.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.app.ActionBar.LayoutParams;
import android.widget.RelativeLayout;

import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.NearbyAdapter;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.NearbyBean;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.jsmy.chongyin.utils.UtilsTools;
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

public class NearbyActivity extends BaseActivity implements NetWork.CallListener {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.my_recyclerview)
    RecyclerView myRecyclerview;
    @BindView(R.id.img_xiala)
    ImageView imgXiala;

    private NearbyAdapter adapter;
    private List<NearbyBean.DataBean> list;
    private int pageindex = 1;
    private String xb = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_nearby;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new NearbyAdapter(this, list);
        myRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerview.setAdapter(adapter);
        myRecyclerview.setItemAnimator(null);
    }

    @Override
    protected void initData() {
        getNearbyMan();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                pageindex = 1;
                getNearbyMan();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
                pageindex++;
                getNearbyMan();
            }
        });
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
                showPopupMenu();
                break;
        }
    }

    private void showPopupMenu() {
        final PopupWindow mPopWin = new PopupWindow(UtilsTools.dip2px(this, 100f), LayoutParams.WRAP_CONTENT);

        View view = LayoutInflater.from(this).inflate(R.layout.popupwind_menu, null);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        LinearLayout btnAll = (LinearLayout) view.findViewById(R.id.btn_all);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageindex = 1;
                xb = "";
                getNearbyMan();
                mPopWin.dismiss();
            }
        });
        LinearLayout btnGirl = (LinearLayout) view.findViewById(R.id.btn_girl);
        btnGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageindex = 1;
                xb = "女";
                getNearbyMan();
                mPopWin.dismiss();
            }
        });
        LinearLayout btnBoy = (LinearLayout) view.findViewById(R.id.btn_boy);
        btnBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageindex = 1;
                xb = "男";
                getNearbyMan();
                mPopWin.dismiss();
            }
        });
        mPopWin.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mPopWin.setContentView(view);

        mPopWin.setFocusable(true); //获取焦点
        mPopWin.setTouchable(true); //可以接收点击事件

        mPopWin.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //设置背景透明
        mPopWin.setOutsideTouchable(true);//允许外部点击隐藏popupwindow

        mPopWin.showAsDropDown(imgXiala);
    }

    public int getAge(String bdate) {
        if (!"".equals(bdate)) {
            int year = Integer.parseInt(bdate.substring(0, 4));
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            int now = Integer.parseInt(format.format(new Date()));
            return now - year;
        }
        return 0;
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        if ("Y".equals(code)) {
            if (type.equals(API.GET_FJPY_LIST)) {
                if (pageindex == 1) {
                    list.clear();
                    list.addAll(gson.fromJson(result, NearbyBean.class).getData());
                    adapter.notifyDataSetChanged();
                } else {
                    list.addAll(gson.fromJson(result, NearbyBean.class).getData());
                    adapter.notifyItemRangeChanged(list.size() - 1, gson.fromJson(result, NearbyBean.class).getData().size());
                }
            }
        } else {
            ToastUtil.showShort(this, "暂无数据,请打开定位权限在重试！");
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }

    public void getNearbyMan() {
        NetWork.getfjpyList(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""),
                SharePrefUtil.getString(this, "longitude", MyApplication.getMyApplication().longitude),
                SharePrefUtil.getString(this, "latitude", MyApplication.getMyApplication().latitude),
                xb,
                pageindex + "",
                this);
    }

}
