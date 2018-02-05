package com.jsmy.chongyin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jsmy.chongyin.NetWork.API;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.DanMuAdapter;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DanmuBean;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.SharePrefUtil;
import com.jsmy.chongyin.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DanMuLiuYanActivity extends BaseActivity implements NetWork.CallListener {

    @BindView(R.id.my_recyclerview)
    RecyclerView myRecyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private DanMuAdapter adapter;
    private List<DanmuBean.DataBean> list;
    private int pageindex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_dan_mu_liu_yan;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new DanMuAdapter(this, list);
        myRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerview.setAdapter(adapter);
        myRecyclerview.setItemAnimator(null);

    }

    @Override
    protected void initData() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageindex = 1;
                NetWork.getYhlylList(SharePrefUtil.getString(DanMuLiuYanActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", DanMuLiuYanActivity.this);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageindex++;
                NetWork.getYhlylList(SharePrefUtil.getString(DanMuLiuYanActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", DanMuLiuYanActivity.this);
            }
        });
        NetWork.getYhlylList(SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", this);
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)){
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check){
                case ServiceCode.UP_DATE_FRIEND_DEL_NUM:
                    pageindex = 1;
                    NetWork.getYhlylList(SharePrefUtil.getString(DanMuLiuYanActivity.this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""), pageindex + "", DanMuLiuYanActivity.this);
                    break;
            }
        }
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(String type, String check, String code, String result, String msg) throws JSONException {
        switch (type) {
            case API.GET_YHLI_LIST:
                if ("Y".equals(code)) {
                    if (pageindex == 1) {
                        list.clear();
                        list.addAll(gson.fromJson(result, DanmuBean.class).getData());
                        adapter.notifyDataSetChanged();
                        refreshLayout.finishRefresh();
                    } else {
                        list.addAll(gson.fromJson(result, DanmuBean.class).getData());
                        adapter.notifyItemRangeChanged(list.size() - 1, gson.fromJson(result, DanmuBean.class).getData().size());
                        refreshLayout.finishLoadmore();
                    }
                } else {
                    if (pageindex == 1) {
                        refreshLayout.finishRefresh();
                    } else {
                        refreshLayout.finishLoadmore();
                    }
                    ToastUtil.showShort(this, msg);
                }
                break;
        }
    }

    @Override
    public void onFailure(String type, String arg1) {

    }
}
