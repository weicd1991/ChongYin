package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.bean.BJTPBean;
import com.jsmy.chongyin.viewholder.BackgroudRecyclerHolder;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/6/7.
 */

public class BackgroudRecyclerAdapter2 extends RecyclerAdapter<BJTPBean.DataBean.ListBean> {
    private MainActivity context;
    public BackgroudRecyclerAdapter2(MainActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<BJTPBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new BackgroudRecyclerHolder(parent,context);
    }
}
