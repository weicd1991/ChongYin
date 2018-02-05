package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.bean.MyFoodBean;
import com.jsmy.chongyin.viewholder.FoodRecyclerHolder;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/6/13.
 */

public class FoodRecyclerAdapter2 extends RecyclerAdapter<MyFoodBean.DataBean.ListBean> {
    private MainActivity context;

    public FoodRecyclerAdapter2(MainActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<MyFoodBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new FoodRecyclerHolder(parent, context);
    }
}
