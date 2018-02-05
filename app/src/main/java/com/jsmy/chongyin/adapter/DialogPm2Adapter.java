package com.jsmy.chongyin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsmy.chongyin.NetWork.NetWork;
import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.LoveRankActivity;
import com.jsmy.chongyin.bean.ZAXbean;
import com.jsmy.chongyin.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class DialogPm2Adapter extends RecyclerView.Adapter<DialogPm2Adapter.DialoghPm2Holder> {
    private LoveRankActivity context;
    private List<ZAXbean.DataBean> zaxList;

    public DialogPm2Adapter(LoveRankActivity context, List<ZAXbean.DataBean> zaxList) {
        this.context = context;
        this.zaxList = zaxList;
    }

    @Override
    public DialoghPm2Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_num_pm_2_item, null);
        DialoghPm2Holder holder = new DialoghPm2Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DialoghPm2Holder holder, int position) {
        switch (position) {
            case 0:
                holder.tvNum1.setImageResource(R.mipmap.no1);
                holder.tv200.setText("200");
                break;
            case 1:
                holder.tvNum1.setImageResource(R.mipmap.no2);
                holder.tv200.setText("150");
                break;
            case 2:
                holder.tvNum1.setImageResource(R.mipmap.no3);
                holder.tv200.setText("100");
                break;
            case 3:
                holder.tvNum1.setImageResource(R.mipmap.no4);
                holder.tv200.setText("50");
                break;
            case 4:
                holder.tvNum1.setImageResource(R.mipmap.no5);
                holder.tv200.setText("30");
                break;
        }
        NetWork.setImgGlide(context, zaxList.get(position).getTx(), holder.tvNum1Tx);
        holder.tvNum1Name.setText(zaxList.get(position).getNc());
    }

    @Override
    public int getItemCount() {
        return zaxList.size();
    }

    class DialoghPm2Holder extends RecyclerView.ViewHolder {
        private ImageView tvNum1;
        private CircleImageView tvNum1Tx;
        private TextView tvNum1Name;
        private TextView tv200;
        private ImageView img200;
        private TextView tv200Jl;

        public DialoghPm2Holder(View itemView) {
            super(itemView);
            tvNum1 = (ImageView) itemView.findViewById(R.id.tv_num1);
            tvNum1Tx = (CircleImageView) itemView.findViewById(R.id.tv_num1_tx);
            tvNum1Name = (TextView) itemView.findViewById(R.id.tv_num1_name);
            tv200 = (TextView) itemView.findViewById(R.id.tv_200);
            img200 = (ImageView) itemView.findViewById(R.id.img_200);
            tv200Jl = (TextView) itemView.findViewById(R.id.tv_200_jl);
        }
    }
}
