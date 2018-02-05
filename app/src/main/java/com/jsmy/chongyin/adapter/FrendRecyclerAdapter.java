package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.MainActivity;
import com.jsmy.chongyin.bean.FriendListBean;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.view.CircleImageView;

import java.util.List;


/**
 * Created by Administrator on 2017/4/11.
 */

public class FrendRecyclerAdapter extends RecyclerView.Adapter<FrendRecyclerAdapter.MyViewHolder> {
    private MainActivity context;
    private LayoutInflater inflater;
    private List<FriendListBean.DataBean.ListBean> list;
    private OnItemClickListener mOnItemClickListener;

    public FrendRecyclerAdapter(MainActivity context, List<FriendListBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pop_frend_recycler_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvNc.setText("" + list.get(position).getYhnc());
        NetWork.setImgGlide(context, list.get(position).getYhtx(), holder.imgTx);

        if ("Y".equals(list.get(position).getIsgq()) || "0".equals(list.get(position).getVipdj())) {
            holder.tvVip.setText("V" + list.get(position).getVipdj());
            holder.relaVip.setVisibility(View.GONE);
        } else {
            holder.relaVip.setVisibility(View.VISIBLE);
        }
        if ("Y".equals(list.get(position).getIsax())) {
            holder.imgLove.setVisibility(View.VISIBLE);
        } else {
            holder.imgLove.setVisibility(View.GONE);
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

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    class MyViewHolder extends ViewHolder {
        private CircleImageView imgTx;
        private ImageView imgLove;
        private TextView tvNc;
        private TextView tvVip;
        private RelativeLayout relaVip;


        public MyViewHolder(View itemView) {
            super(itemView);
            imgTx = (CircleImageView) itemView.findViewById(R.id.img_tx);
            imgLove = (ImageView) itemView.findViewById(R.id.img_love);
            tvNc = (TextView) itemView.findViewById(R.id.tv_nc);
            tvVip = (TextView) itemView.findViewById(R.id.tv_vip);
            relaVip = (RelativeLayout) itemView.findViewById(R.id.rela_vip);
        }
    }

}
