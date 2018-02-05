package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jsmy.chongyin.activity.AddFoodPayActivity;
import com.jsmy.chongyin.bean.ShiWuBean;
import com.jsmy.chongyin.viewholder.AddFoodPayHolder;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/6/12.
 */

public class AddFoodPayRecyclerAdapter2 extends RecyclerAdapter<ShiWuBean.DataBean.ListBean> {
    private AddFoodPayActivity context;

    public AddFoodPayRecyclerAdapter2(AddFoodPayActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<ShiWuBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new AddFoodPayHolder(parent, context);
    }
}
