package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jsmy.chongyin.activity.PetShopActivity;
import com.jsmy.chongyin.bean.PetBean;
import com.jsmy.chongyin.viewholder.PetShopRecyclerViewHolder;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/5/4.
 */

public class PetShopRecyclerAdapter2 extends RecyclerAdapter<PetBean.DataBean.ListBean> {
    private PetShopActivity context;
    public PetShopRecyclerAdapter2(PetShopActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<PetBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new PetShopRecyclerViewHolder(parent,context);
    }
}
