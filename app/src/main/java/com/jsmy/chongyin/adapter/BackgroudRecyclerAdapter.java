package com.jsmy.chongyin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.application.MyApplication;
import com.jsmy.chongyin.bean.BJTPBean;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.utils.MyLog;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class BackgroudRecyclerAdapter extends RecyclerView.Adapter<BackgroudRecyclerAdapter.MyViewHolder> {
    private MainActivity context;
    private LayoutInflater inflater;
    private List<BJTPBean.DataBean.ListBean> list;
    private OnItemClickListener mOnItemClickListener;

    public BackgroudRecyclerAdapter(MainActivity context, List<BJTPBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pop_back_recycler_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void setDate(List<BJTPBean.DataBean.ListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (position == list.size()) {
            holder.imgBack.setImageResource(R.mipmap.beijingtu_tianjiabeijing_icon);
            holder.imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.isBackGroud = "Y";
                    context.gotoSomeActivity(context, AtyTag.ATY_ChoiceImage, true);
                }
            });
        } else {
            NetWork.setImgGlide(context, list.get(position).getTp(), holder.imgBack);
            holder.imgBack.setScaleType(ImageView.ScaleType.FIT_XY);
            holder.imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.setBJTP(list.get(position).getId() + "", position);
                }
            });

            if (MyApplication.getMyApplication().userInfo.getBjtpid().equals(list.get(position).getId() + "")) {
                holder.imgBack.setBackgroundResource(R.drawable.bjline);
            } else {
                holder.imgBack.setBackgroundResource(R.drawable.bjline2);
            }
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBack;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgBack = (ImageView) itemView.findViewById(R.id.img_back);
        }
    }
}
