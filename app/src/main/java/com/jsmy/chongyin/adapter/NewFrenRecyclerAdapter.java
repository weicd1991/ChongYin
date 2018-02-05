package com.jsmy.chongyin.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.AddNewFrendActivity;
import com.jsmy.chongyin.activity.BaseActivity;
import com.jsmy.chongyin.activity.NewFrendActivity;
import com.jsmy.chongyin.bean.NewFriendBena;
import com.jsmy.chongyin.utils.AtyTag;
import com.jsmy.chongyin.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class NewFrenRecyclerAdapter extends RecyclerView.Adapter<NewFrenRecyclerAdapter.MyViewHolder> {
    private NewFrendActivity context;
    private LayoutInflater inflater;
    private List<NewFriendBena.DataBean.ListBean> list;
    //两个final int类型表示ViewType的两种类型
    private final int NORMAL_TYPE = 0;
    private final int FOOT_TYPE = 1;
    //最大item数加1，最后一个为foot
    private int max_count;

    public NewFrenRecyclerAdapter(NewFrendActivity context, List<NewFriendBena.DataBean.ListBean> list) {
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
        View view = inflater.inflate(R.layout.activity_new_frend_item, null);
        View foot = inflater.inflate(R.layout.recycler_footview1, null);
        if (viewType == FOOT_TYPE) {
            return new MyViewHolder(foot, FOOT_TYPE);
        } else {
            return new MyViewHolder(view, NORMAL_TYPE);
        }
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder.relaBtn != null)
            holder.relaBtn.setVisibility(View.GONE);
        if (holder.tvIsAgree != null)
            holder.tvIsAgree.setVisibility(View.GONE);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (getItemViewType(position) == FOOT_TYPE) {
            return;
        }

        holder.tvVip.setText("V" + list.get(position).getVipdj());
        if ("".equals(list.get(position).getVipdj()) || 0 == Integer.parseInt(list.get(position).getVipdj())) {
            holder.relaVip.setVisibility(View.GONE);
        } else {
            holder.relaVip.setVisibility(View.VISIBLE);
            if ("Y".equals(list.get(position).getIsgq())) {
                holder.imgVip.setImageResource(R.mipmap.haoyouliebiao_guojidengji_xiao);
            } else {
                holder.imgVip.setImageResource(R.mipmap.haoyouliebiao_touxianghuangguan_icon);
            }
        }
        NetWork.setImgGlide(context, list.get(position).getTx(), holder.imgTx);
        holder.tvName.setText(list.get(position).getNc());
        holder.tvId.setText("ID:" + list.get(position).getYhids());
        switch (list.get(position).getZt()) {
            case BaseActivity.zero:
                holder.relaBtn.setVisibility(View.VISIBLE);
                holder.tvIsAgree.setVisibility(View.GONE);
                break;
            case BaseActivity.one:
                holder.relaBtn.setVisibility(View.GONE);
                holder.tvIsAgree.setVisibility(View.VISIBLE);
                holder.tvIsAgree.setText("已同意");
                break;
            case BaseActivity.two:
                holder.relaBtn.setVisibility(View.GONE);
                holder.tvIsAgree.setVisibility(View.VISIBLE);
                holder.tvIsAgree.setText("已拒绝");
                break;
            case BaseActivity.three:
                holder.relaBtn.setVisibility(View.GONE);
                holder.tvIsAgree.setVisibility(View.VISIBLE);
                holder.tvIsAgree.setText("等待验证");
                break;
        }
        holder.imgTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.type = "sreach";
                context.zt = list.get(position).getZt();
                context.friendID = list.get(position).getYhids() + "";
                context.gotoSomeActivity(context, AtyTag.ATY_AddNewFrend, true);
            }
        });

        holder.btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.agreeFrend(list.get(position).getYhids() + "");
            }
        });

        holder.btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.refuseFrend(list.get(position).getYhids() + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return max_count;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgTx;
        private RelativeLayout relaVip;
        private TextView tvVip;
        private TextView tvName;
        private TextView tvId;
        private RelativeLayout relaBtn;
        private TextView btnAgree;
        private TextView btnRefuse;
        private TextView tvIsAgree;
        private ImageView imgVip;


        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == NORMAL_TYPE) {
                imgTx = (CircleImageView) itemView.findViewById(R.id.img_tx);
                relaVip = (RelativeLayout) itemView.findViewById(R.id.rela_vip);
                tvVip = (TextView) itemView.findViewById(R.id.tv_vip);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
                tvId = (TextView) itemView.findViewById(R.id.tv_id);
                relaBtn = (RelativeLayout) itemView.findViewById(R.id.rela_btn);
                btnAgree = (TextView) itemView.findViewById(R.id.btn_agree);
                btnRefuse = (TextView) itemView.findViewById(R.id.btn_refuse);
                tvIsAgree = (TextView) itemView.findViewById(R.id.tv_isAgree);
                imgVip = (ImageView) itemView.findViewById(R.id.img_vip);
            } else {

            }
        }
    }

}
