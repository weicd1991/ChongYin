package com.jsmy.chongyin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.adapter.AddFoodRecyclerAdapter;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.DecodeData;
import com.jsmy.chongyin.bean.ShiWuFeiLeiBean;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.SharePrefUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddFoodActivity extends BaseActivity {

    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.recycler_food)
    RecyclerView recyclerFood;
    private AddFoodRecyclerAdapter adapter;
    private List<ShiWuFeiLeiBean.DataBean.ListBean> list;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContenView() {
        return R.layout.activity_add_food;
    }

    @Override
    protected void initView() {
        context = this;
        list = new ArrayList<>();
    }

    @Override
    protected void initData() {
        getSWFLlist();
    }

    @Override
    protected void paresData(String result, String code) {
        if ("Y".equals(code)) {
            String check = DecodeData.getCodeRoMsg(result, DecodeData.CHECK);
            switch (check) {
                case ServiceCode.GET_SPFL_LIST_NUM:
                    list.addAll(gson.fromJson(result, ShiWuFeiLeiBean.class).getData().getList());
                    initRecycler();
                    break;
                default:
                    break;
            }
        } else if ("network".equals(code)) {
            choseDialog(Integer.parseInt(result));
        }
    }

    private void initRecycler(){
        adapter = new AddFoodRecyclerAdapter(this, list);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerFood.setLayoutManager(layoutManager);
        recyclerFood.setItemAnimator(null);
        recyclerFood.setAdapter(adapter);
        adapter.setOnItemClickListener(new AddFoodRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                addType = list.get(position).getId() + "";
                gotoSomeActivity(context, AtyTag.ATY_AddFoodPay, true);
            }

        });
    }

    //访问食物分类列表
    private void getSWFLlist() {
        map.clear();
        map.put("yhid", SharePrefUtil.getString(this, "yhid", MyApplication.getMyApplication().userInfo.getYhid() + ""));
        NetWork.getNetVolue(ServiceCode.GET_SPFL_LIST, map, ServiceCode.NETWORK_GET, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        finish();
    }

}
