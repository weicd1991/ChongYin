package com.jsmy.chongyin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.NearbyActivity;
import com.jsmy.chongyin.bean.NearbyBean;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.view.CircleImageView;

import java.util.List;

import cn.lemon.view.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/11/22.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.NearbyHolder> {
    private NearbyActivity context;
    private List<NearbyBean.DataBean> list;
    private LayoutInflater inflater;

    public NearbyAdapter(NearbyActivity context, List<NearbyBean.DataBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NearbyHolder holder = new NearbyHolder(inflater.inflate(R.layout.activity_nearby_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(NearbyHolder holder, final int position) {
//        Glide.with(context).load(list.get(position).getTx()).into(holder.imgTx);
        NetWork.setImgGlide(context,list.get(position).getTx(),holder.imgTx);
        holder.tvName.setText(list.get(position).getNc());
        holder.tvOli.setText(list.get(position).getJl() + "km");
        if ("女".equals(list.get(position).getXb())) {
            holder.linearSex.setBackgroundResource(R.drawable.base_rectangle_girl);
            holder.imgSex.setImageResource(R.mipmap.women21);
            holder.tvSex.setText(context.getAge(list.get(position).getBdate()) + "");
        } else {
            holder.linearSex.setBackgroundResource(R.drawable.base_rectangle_boy);
            holder.imgSex.setImageResource(R.mipmap.man21);
            holder.tvSex.setText(context.getAge(list.get(position).getBdate()) + "");
        }
        holder.tvLevel.setText("LV" + list.get(position).getDj());
        holder.tvSign.setText(list.get(position).getQm());
        holder.relaBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.zt = context.zero;
                context.type = "sreach";
                context.friendID = list.get(position).getYhid();
                context.gotoSomeActivity(context, AtyTag.ATY_AddNewFrend, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NearbyHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgTx;
        private TextView tvName;
        private TextView tvOli;
        private LinearLayout linearSex;
        private ImageView imgSex;
        private TextView tvSex;
        private TextView tvLevel;
        private TextView tvSign;
        private RelativeLayout relaBg;

        public NearbyHolder(View itemView) {
            super(itemView);
            imgTx = (CircleImageView) itemView.findViewById(R.id.img_tx);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvOli = (TextView) itemView.findViewById(R.id.tv_oli);
            linearSex = (LinearLayout) itemView.findViewById(R.id.linear_sex);
            imgSex = (ImageView) itemView.findViewById(R.id.img_sex);
            tvSex = (TextView) itemView.findViewById(R.id.tv_sex);
            tvLevel = (TextView) itemView.findViewById(R.id.tv_level);
            tvSign = (TextView) itemView.findViewById(R.id.tv_sign);
            relaBg = (RelativeLayout) itemView.findViewById(R.id.rela_bg);
        }
    }

}
