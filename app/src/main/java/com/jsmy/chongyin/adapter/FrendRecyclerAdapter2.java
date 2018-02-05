package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.bean.FriendListBean;
import com.jsmy.chongyin.viewholder.FrendRecyclerHolder;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/6/8.
 */

public class FrendRecyclerAdapter2 extends RecyclerAdapter<FriendListBean.DataBean.ListBean> {
    private MainActivity context;

    public FrendRecyclerAdapter2(MainActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<FriendListBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new FrendRecyclerHolder(parent, context);
    }
}
