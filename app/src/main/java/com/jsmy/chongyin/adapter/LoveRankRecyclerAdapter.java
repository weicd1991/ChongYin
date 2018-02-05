package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.LoveRankActivity;
import com.jsmy.chongyin.bean.LoveRankBean;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.view.CircleImageView;
import com.jsmy.chongyin.viewholder.LoveRankRecyclerHolder;

import java.util.List;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/4/25.
 */

public class LoveRankRecyclerAdapter extends RecyclerAdapter<LoveRankBean.DataBean.ListBean> {
    private LoveRankActivity context;
    public LoveRankRecyclerAdapter(LoveRankActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder<LoveRankBean.DataBean.ListBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new LoveRankRecyclerHolder(parent,context);
    }
}
