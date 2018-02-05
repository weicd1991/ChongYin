package com.jsmy.chongyin.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.PetShopActivity;
import com.jsmy.chongyin.bean.PetBean;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.service.DownLoadService;
import com.jsmy.chongyin.utils.MyLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class PetShopRecyclerAdapter extends RecyclerView.Adapter<PetShopRecyclerAdapter.MyViewHolder> {
    private PetShopActivity context;
    private LayoutInflater inflater;
    private List<PetBean> list;
    private OnItemClickListener mOnItemClickListener;

    //两个final int类型表示ViewType的两种类型
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 1;
    //最大item数加1，最后一个为foot
    private int max_count;

    public PetShopRecyclerAdapter(PetShopActivity context, List<PetBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.max_count = list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOT_TYPE;
        }
        return NORMAL_TYPE;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_pet_shop_item, null);
        View foot = inflater.inflate(R.layout.recycler_footview, null);
        if (viewType == FOOT_TYPE) {
            return new MyViewHolder(foot, FOOT_TYPE);
        } else {
            return new MyViewHolder(view, NORMAL_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (getItemViewType(position) == FOOT_TYPE) {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return max_count;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == NORMAL_TYPE) {

            } else {

            }
        }

    }


}
